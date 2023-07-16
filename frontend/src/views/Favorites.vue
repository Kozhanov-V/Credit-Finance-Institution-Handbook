<template>
	<div class="container">

		<AccountsInfo :visible="isAccountsFormVisible" :entryAccounts="selectedItem" @close="isAccountsFormVisible = false" />

		<h1>Избранные записи</h1>
		<div v-if="favoritesIsEmpty" class="favorites_empty">
			<p>
				У вас нет избранных записей. Вы можете добавить избранные записи в справочнике.
			</p>

			<button @click="goToHandbook">
				Перейти в справочник
			</button>
		</div>

		<div v-else class="table-container">
			<table>
				<thead>
					<tr class="sticky-header">
						<th style="background-color: white; border: none;"></th>
						<th>БИК</th>
						<th>Наименование</th>
						<th>Рег. поряд. номер</th>
						<th>Код страны</th>
						<th>Код региона</th>
						<th>Индекс</th>
						<th>Тип нас. пункта</th>
						<th>Населенный пункт</th>
						<th>Адрес</th>
						<th>БИК голов. организации</th>
						<th>Дата включения</th>
						<th>Дата исключения</th>
						<th>Тип участника</th>
						<th>Доступные серв. перевода</th>
						<th>Участник обмена</th>
						<th>УИС</th>
						<th>Статус участника</th>
					</tr>
				</thead>
				<tbody>
					<template :key="item.bic" v-for="item in tableData">
						<tr @mouseover="selectedItem = item">
							<td class="actions_container">
								<div class="actions">
									<button v-if="item !== currentlyEditing" @click="this.isAccountsFormVisible = true"><img src="/img/accounts.svg"
											alt="a"></button>
									<button v-if="item !== currentlyEditing && isUser" @click="deleteFavorite(item)">
										<img src="/img/favorites_added.svg" alt="f">
									</button>

									<button v-if="item !== currentlyEditing && isAdmin" @click="startEditing(item)"><img src="/img/settings.svg"
											alt="u"></button>

									<button v-if="item !== currentlyEditing && isAdmin" @click="deleteItem(item)"><img src="/img/delete.svg"
											alt="d"></button>

								</div>
								<div class="edit_mode_action">
									<button v-if="item === currentlyEditing" @click="cancelEditing"><img src="/img/cancel.svg" alt="c"></button>
									<button v-if="item === currentlyEditing && isAdmin" @click="saveItem(item)"><img src="/img/save.svg"
											alt="s"></button>
								</div>

							</td>
							<td>{{ item.bic }}</td>
							<td v-if="item !== currentlyEditing">
								{{ item.nameParticipant }}
							</td>
							<td v-else>
								<input v-model.trim="item.nameParticipant" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.registrationNumber }}
							</td>
							<td v-else>
								<input v-model.trim="item.registrationNumber" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.countryCode }}
							</td>
							<td v-else>
								<input v-model.trim="item.countryCode" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.regionCode }}
							</td>
							<td v-else>
								<input v-model.trim="item.regionCode" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.index }}
							</td>
							<td v-else>
								<input v-model.trim="item.index" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.typeLocation }}
							</td>
							<td v-else>
								<input v-model.trim="item.typeLocation" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.nameLocation }}
							</td>
							<td v-else>
								<input v-model.trim="item.nameLocation" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.address }}
							</td>
							<td v-else>
								<input v-model.trim="item.address" type="text">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.parentBIC }}
							</td>
							<td v-else>
								<input v-model.trim="item.parentBIC" type="number">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.dateIn }}
							</td>
							<td v-else>
								<input v-model="item.dateIn" type="date">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.dateOut }}
							</td>
							<td v-else>
								<input v-model="item.dateOut" type="date">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.participantType }}
							</td>
							<td v-else>
								<select v-model="item.participantType" name="participantType">
									<option v-for="(type, index) in participantTypes" :value="type" :key="index">
										{{ type }}
									</option>
								</select>
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.availableTransferService }}
							</td>
							<td v-else>
								<select v-model="item.availableTransferService" name="availableTransferService">
									<option v-for="(type, index) in availableTransferServices" :value="type" :key="index">
										{{ type }}
									</option>
								</select>
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.exchangeParticipant }}
							</td>
							<td v-else>
								<input type="checkbox" :checked="item.exchangeParticipant == 1"
									@change="item.exchangeParticipant = $event.target.checked ? 1 : 0" name="exchangeParticipant">
							</td>

							<td v-if="item !== currentlyEditing">
									{{ item.uid }}
							</td>
							<td v-else><input v-model.trim="item.uid" type="number"></td>

							<td>{{ item.participantStatus }}</td>


						</tr>

					</template>
				</tbody>

			</table>

		</div>

	</div>
</template>



<style scoped>
@import '@/assets/css/favorites.css';
</style>



<script>
import { useVuelidate } from '@vuelidate/core'
import { numeric, required, between, maxLength } from '@vuelidate/validators'
import Filter from '@/components/Filter.vue';

import SaveModalForm from '@/components/SaveForm.vue';
import AccountsInfo from '@/components/AccountsInfo.vue';

import { reactive } from 'vue';
import axios from 'axios';

export default {
	components: {
		SaveModalForm,
		AccountsInfo,
		Filter,
	},

	data() {
		return {
			bicDirectoryEntries: [],
			participantTypes: [],
			availableTransferServices: [],
			participantStatuses: [],
			idES: '',
			tableData: [],
			selectedItem: null,
			isAccountsFormVisible: false,

			editedItem: null,
        currentlyEditing: null,
			data: [],
			favoritesIsEmpty:true,
		};
	},
	methods: {
		startEditing(item) {
    this.editedItem = Object.assign({}, item); 
    this.currentlyEditing = item;
},
cancelEditing() {
	Object.assign(this.currentlyEditing, this.editedItem); 
    this.currentlyEditing = null;
    this.editedItem = null;
},
		deleteFavorite(bicDirectoryEntry) {
			try {
				this.$store.dispatch('deleteFavoritesEntry', bicDirectoryEntry);
				const index = this.tableData.findIndex(item => item.bic === bicDirectoryEntry.bic);
				if (index !== -1) {
					this.tableData.splice(index, 1);
				}

				if(this.tableData.length <1){
					this.favoritesIsEmpty=true;
				}
				else{
					this.favoritesIsEmpty=false;
				}
				console.log(this.tableData.length)

			} catch (error) {
				alert("Ошибка");
				console.error(error);
			}
		},
		async saveItem(item) {
			try {
				const response = await axios.put(`http://localhost:8080/api/update/${item.bic}`, {
					bic: item.bic,
					participantInfo: {
						nameParticipant: item.nameParticipant,
						registrationNumber: item.registrationNumber,
						countryCode: item.countryCode,
						regionCode: item.regionCode,
						index: item.index,
						typeLocation: item.typeLocation,
						nameLocation: item.nameLocation,
						address: item.address,
						parentBIC: item.parentBIC,
						dateIn: item.dateIn,
						dateOut: item.dateOut,
						participantType: item.participantType,
						availableTransferService: item.availableTransferService,
						uid: item.uid,
						exchangeParticipant: item.exchangeParticipant,
					}
				}
					, {
						headers: {
							'Authorization': 'Bearer ' + localStorage.getItem('token')
						}
					});

			
				if (response.status === 200) {
					item=this.editedItem;
    this.currentlyEditing = null;
    this.editedItem = null;
				} else {
					console.error('Error updating item:', response);
				}
			} catch (error) {
				console.error('Error updating item:', error);
			}

			// В любом случае выходим из режима редактирования
			item.editMode = false;
		},
		fillTable(bicDirectoryEntries) {

			this.tableData = bicDirectoryEntries.map(item => ({
				bic: item.bic,
				nameParticipant: item.nameParticipant,
				registrationNumber: item.registrationNumber,
				countryCode: item.countryCode,
				regionCode: item.regionCode,
				index: item.index,
				typeLocation: item.typeLocation,
				nameLocation: item.nameLocation,
				address: item.address,
				parentBIC: item.parentBIC,
				dateIn: item.dateIn,
				dateOut: item.dateOut,
				participantType: item.participantType,
				availableTransferService: item.availableTransferService,
				exchangeParticipant: item.exchangeParticipant,
				uid: item.uid,
				participantStatus: item.participantStatus,
				accounts: item.accounts,
				editMode: false,
			}))

		},

		goToHandbook() {
			this.$router.push('/handbook');
		},

		async deleteItem() {
			if (!this.selectedItem) {
				console.error("No item selected");
				return;
			}
			try {
				const response = await axios.delete(`http://localhost:8080/api/delete/${this.selectedItem.bic}`, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				if (response.status === 200) {
					// Удаление элемента из списка
					const index = this.tableData.findIndex(item => item.bic === this.selectedItem.bic);
					if (index !== -1) {
						this.tableData.splice(index, 1);
					}
					console.log(this.tableData.length)
					if(this.tableData.length <1){
					this.favoritesIsEmpty=true;
				}
				else{
					this.favoritesIsEmpty=false;
				}

					// Сброс выбранного элемента
					this.selectedItem = null;
				} else {
					console.error("Error deleting item:", response);
				}
			} catch (error) {
				console.error("Error deleting item:", error);
			}
		},
		async startupDataLoader() {
			await axios.get('http://localhost:8080/api/participantTypes', {
			}).then(response => {
				this.participantTypes = response.data.map(e => e.code);
			})

			await axios.get('http://localhost:8080/api/availableTransferServices').then(response => {
				this.availableTransferServices = response.data.map(e => e.code);
			})

			await axios.get('http://localhost:8080/api/participantStatuses').then(response => {
				this.participantStatuses = response.data.map(e => e.code);
			})
			
			
			this.typeTransfer = this.participantTypes.map(e => e.code);
		}
	},
	created() {
		this.startupDataLoader();
	},
	async mounted() {
	  this.$nextTick(function() {
      document.getElementById('favoritesBtn').click();
    })
		await axios.get(`http://localhost:8080/api/favorites/get?token=${this.$store.getters.getToken}`).then(response => {
				console.log(response.data)
				this.$store.state.favoritesEntry = response.data.map(item => ({
						bic: item.bic,
						nameParticipant: item.participantInfo.nameParticipant,
						registrationNumber: item.participantInfo.registrationNumber,
						countryCode: item.participantInfo.countryCode,
						regionCode: item.participantInfo.regionCode,
						index: item.participantInfo.index,
						typeLocation: item.participantInfo.typeLocation,
						nameLocation: item.participantInfo.nameLocation,
						address: item.participantInfo.address,
						parentBIC: item.participantInfo.parentBIC,
						dateIn: item.participantInfo.dateIn,
						dateOut: item.participantInfo.dateOut,
						participantType: item.participantInfo?.participantType?.code,
						availableTransferService: item.participantInfo?.availableTransferService?.code,
						exchangeParticipant: item.participantInfo?.exchangeParticipant?.code,
						uid: item.participantInfo.uid,
						participantStatus: item.participantInfo?.participantStatus?.code,
						accounts: item.accounts,
						editMode: false,
					 }));
			})
		this.bicDirectoryEntries = this.$store.getters.getFavorites;
		if (this.bicDirectoryEntries && this.bicDirectoryEntries.length > 0) {
			this.fillTable(this.bicDirectoryEntries);
		}
		if(this.tableData.length <1){
					this.favoritesIsEmpty=true;
				}
				else{
					this.favoritesIsEmpty=false;
				}
	},
	computed: {
		isAdmin() {

			const roles = this.$store.getters.getRoles;
			return roles ? roles.map(e => e.name).includes('ROLE_ADMIN') : false;
		},
		isUser() {
			const roles = this.$store.getters.getRoles;
			return roles ? roles.map(e => e.name).includes('ROLE_USER') : false;
		},
		isLoggedIn() {
			return this.$store.getters.isLoggedIn;
		},
	},

};
</script>
