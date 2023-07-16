<template>
	<div class="container">
		<SaveModalForm :visible="isSaveModalVisible" :participantTypes="participantTypes"
			:availableTransferServices="availableTransferServices" :participantStatuses="participantStatuses"
			@close="isSaveModalVisible = false" @entryCreated="addEntry" />
		<AccountsInfo :visible="isAccountsFormVisible" :entryAccounts="selectedItem" @close="isAccountsFormVisible = false" />

		<div class="functional-panel">
			<div class="filter-element" id="bic">
				<input v-model="formFilter.bicInput" type="number" name="bic" placeholder="Поиск по BIC"
					@keyup.enter="filterByBic">
				<input @click="filterByBic" type="submit" id="submitBic" />
			</div>
			<div class="filter-element" id="name">
				<input v-model="formFilter.nameInput" type="text" name="name" placeholder="Поиск по наименованию"
					@keyup.enter="filterByName">
				<input @click="filterByName" type="submit" id="submitName" />
			</div>
			<button @click="resetAllFilter" class="filter-element" id="clear-filter"><img src="/img/clear.svg" alt="c">
				Сбросить</button>

			<button @click="openFilterMenu" class="filter-element" id="filterBtn"><img src="/img/filter.png" alt="">
				Фильтр</button>

			<Filter :visible="isFilterVisible" :participantTypes="participantTypes" :shouldResetFilter="shouldResetFilter"
				@apply-filter="applyFilter" @reset-filter="resetFilter" />

			<button @click="openSaveModal" v-show="isAdmin" class="filter-element" id="addBtn"><img src="/img/plus.png" alt="">
				Добавить
				запись</button>
		</div>
		<div class="table-container">
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
									<button v-if="item !== currentlyEditing && isUser" @click="toggleFavorite(item)">
										<img :src="isFavorite(item) ? 'img/favorites_added.svg' : 'img/favorites.svg'" alt="f">
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
		<div class="control-pages-block">
			<button @click="previousPage" :disabled="currentQueryParams.page == 1">
				<!-- <img src="img/previous_page.png" alt=""> -->
				&#60;
			</button>
			<button @click="firstPage" :disabled="currentQueryParams.page < 3">1</button>
			<button @click="previousPage" :disabled="currentQueryParams.page < 2">{{ currentQueryParams.page - 1 }}</button>
			<p class="currentPage">{{ currentQueryParams.page }}</p>
			<button @click="nextPage" :disabled="currentQueryParams.page > currentQueryParams.totalPage - 1">{{ currentQueryParams.page+ 1 }}</button>
			<button @click="lastPage" :disabled="currentQueryParams.page > currentQueryParams.totalPage - 2">{{ currentQueryParams.totalPage }}</button>
			<button @click="nextPage" :disabled="currentQueryParams.page == currentQueryParams.totalPage">
				<!-- <img src="img/next_page.png" alt=""> -->
				&#62;
			</button>

		</div>

	</div>
</template>



<style scoped>
@import '@/assets/css/handbook.css';
</style>



<script>
import { useVuelidate } from '@vuelidate/core'
import Filter from '@/components/Filter.vue';

import SaveModalForm from '@/components/SaveForm.vue';
import AccountsInfo from '@/components/AccountsInfo.vue';

import { reactive } from 'vue';
import axios from 'axios';
import { required, minLength, maxLength, sameAs } from '@vuelidate/validators';
export default {
	components: {
		SaveModalForm,
		AccountsInfo,
		Filter,
	},

	setup() {
		return {v$: useVuelidate() }
	},

	data() {
		return {

			bicDirectoryEntries: [],
			participantTypes: [],
			availableTransferServices: [],
			participantStatuses: [],

			formFilter: {
				bicInput: '',
				nameInput: '',
				typeTransfer: '',
				validFrom: '',
				validUntil: '',

			},
			idES: '',
			tableData: [],
			selectedItem: null,
			isSaveModalVisible: false,
			isFilterVisible: false,
			isAccountsFormVisible: false,
			currentQuery: null,
			currentQueryParams: {
				page: 1,
				size: 32,
				totalPage: 0,
			},
			editedItem: null,
        currentlyEditing: null,
			data: [],
			shouldResetFilter: false
		};
	},
	validations() {
		return{
			editedItem: {
				nameParticipant: { required, minLength: minLength(20) },
    },
	}
}
  ,
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
		async performQuery() {
			if (this.currentQuery) {
				const response = await this.currentQuery(this.currentQueryParams);
				this.currentQueryParams.totalPage = response.data.totalPage;
				this.fillTable(response.data.bicDirectoryEntries);
			}
		},
		toggleFavorite(item) {
			if (this.isFavorite(item)) {
				this.deleteFavorite(item)
			} else {
				this.addFavorite(item);
			}
		},
		isFavorite(item) {
			return this.favoritesEntry.some(favoriteItem => favoriteItem.bic === item.bic);
		},
		deleteFavorite(bicDirectoryEntry) {
			try {
				this.$store.dispatch('deleteFavoritesEntry', bicDirectoryEntry);
			} catch (error) {
				alert("Ошибка");
				console.error(error);
			}
		},
		addFavorite(bicDirectoryEntry) {
			try {
				this.$store.dispatch('addFavoritesEntry', bicDirectoryEntry);
			} catch (error) {
				alert("Ошибка");
				console.error(error);
			}
		},
		async saveItem(item) {
			this.v$.$touch()
      if (this.v$.$invalid) {
				alert('ошибка')
        return
      }
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
			}))

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
					// Сброс выбранного элемента
					this.selectedItem = null;
				} else {
					console.error("Error deleting item:", response);
				}
			} catch (error) {
				console.error("Error deleting item:", error);
			}
		},


		openSaveModal() {
			this.isSaveModalVisible = true;
		},
		firstPage() {
			this.currentQueryParams.page = 1;
			this.performQuery(this.currentQueryParams);
		},
		lastPage() {
			this.currentQueryParams.page = this.currentQueryParams.totalPage;
			this.performQuery(this.currentQueryParams);
		},
		previousPage() {
			if (this.currentQueryParams.page > 1) {
				this.currentQueryParams.page--;
			}
			this.performQuery(this.currentQueryParams);
		},
		nextPage() {
			if (this.currentQueryParams.page < this.currentQueryParams.totalPage) {
				this.currentQueryParams.page++;
				this.performQuery(this.currentQueryParams);
			}
		},
		async fetchData(queryParams) {

			this.currentQuery = this.fetchData; 
			this.currentQueryParams = queryParams; // Сохраните текущие параметры запроса

			const { page, size } = queryParams;
			const response = await axios.get(`http://localhost:8080/api/data?page=${page - 1}&size=${size}`);
			return response;
		},

		openFilterMenu() {
			this.isFilterVisible = true;
		},
		resetAllFilter() {
			this.shouldResetFilter = !this.shouldResetFilter;
			this.formFilter.bicInput = '';
			this.formFilter.nameInput = '';
			this.formFilter.typeTransfer = '';
			this.formFilter.validFrom = '';
			this.formFilter.validUntil = '';
			this.fetchData(this.currentQueryParams);
			this.performQuery(this.currentQueryParams);
		},
		async filterByBicQuery(queryParams){
			this.currentQuery = this.filterByBicQuery; 
			this.currentQueryParams = queryParams; 

			const { page, size } = queryParams;

			let bic = this.formFilter.bicInput;

			const response = await axios.get(`http://localhost:8080/api/findBy/bic/${bic}?page=${page - 1}&size=${size}`);
			console.log(response.data.bicDirectoryEntries)
			return response;
		},

		async filterByBic() {
			let bic = this.formFilter.bicInput;
			
			try {
				if (bic !== "") {
					
				this.nameInput = "";
				this.filterByBicQuery({
					page: 1,
				size: 32,
				totalPage: 1,
				});
				this.performQuery(this.currentQuery);
				} else {

					this.currentQuery = this.fetchData;
					this.performQuery(this.currentQueryParams);
				}
				
			} catch (error) {
				console.error(error);
			}
		},
		async filterByNameQuery(queryParams) {
			this.currentQuery = this.filterByNameQuery; 
			this.currentQueryParams = queryParams; 

			const { page, size } = queryParams;

			let name = this.formFilter.nameInput;

			const response = await axios.get(`http://localhost:8080/api/findBy/name/${name}?page=${page - 1}&size=${size}`);
			console.log(response.data.bicDirectoryEntries)
			return response;
		},
		async filterByName() {
			let name = this.formFilter.nameInput;

			try {
				if (name !== "") {
					this.bicInput = "";

					this.filterByNameQuery({
					page: 1,
					size: 32,
					totalPage: 1,
					});
				this.performQuery(this.currentQuery);
				} else {

					this.currentQuery = this.fetchData;
					this.performQuery();
				}
				
			} catch (error) {
				console.error(error);
			}
		},

		async fetchDataFilter(queryParams){

			this.currentQuery = this.fetchDataFilter; 
			this.currentQueryParams = queryParams;
			const { page, size } = queryParams;
			try {
				const response = await axios.post(`http://localhost:8080/api/filter?page=${page - 1}&size=${size}`, this.formFilter);
				this.currentQueryParams.totalPage = response.data.totalPage;
				this.isFilterVisible = false;
				return response;
			} catch (error) {
				console.error(error);
			}

		},

		 applyFilter(filter) {
			this.formFilter.typeTransfer = filter.participantType;
			this.formFilter.validFrom = filter.validFrom;
			this.formFilter.validUntil = filter.validUntil;
			this.fetchDataFilter({
				page: 1,
				size: 32,
				totalPage: 1,
			});
			this.performQuery(this.currentQueryParams);
			
		},
		async resetFilter() {
			this.isFilterVisible = false;
		},
		addEntry(newEntry) {
			this.tableData.push({
				bic: newEntry.bic,
				idES: this.idES,
				nameParticipant: newEntry.participantInfo.nameParticipant,
				registrationNumber: newEntry.participantInfo.registrationNumber,
				countryCode: newEntry.participantInfo.countryCode,
				regionCode: newEntry.participantInfo.regionCode,
				index: newEntry.participantInfo.index,
				typeLocation: newEntry.participantInfo.typeLocation,
				nameLocation: newEntry.participantInfo.nameLocation,
				address: newEntry.participantInfo.address,
				parentBIC: newEntry.participantInfo.parentBIC,
				dateIn: newEntry.participantInfo.dateIn,
				dateOut: newEntry.participantInfo.dateOut,
				participantType: newEntry.participantInfo?.participantType,
				availableTransferService: newEntry.participantInfo?.availableTransferService,
				exchangeParticipant: newEntry.participantInfo?.exchangeParticipant,
				uid: newEntry.participantInfo.uid,
				participantStatus: newEntry.participantInfo?.participantStatus,
				editMode: false,
			})
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
		this.currentQuery = this.fetchData;
		this.performQuery(this.currentQueryParams);
		this.startupDataLoader();
	},
	mounted() {
		this.$nextTick(function () {
			document.getElementById('handbookBtn').click();
		})
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
		favoritesEntry() {
			return this.$store.getters.getFavorites;
		},
	},

};
</script>
