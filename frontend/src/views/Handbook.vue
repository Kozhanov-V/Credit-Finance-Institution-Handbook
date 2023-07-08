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
			<button @click="resetAllFilter" class="filter-element" id="clear-filter"><img src="img/clear.svg" alt="c">
				Сбросить</button>

			<button @click="openFilterMenu" class="filter-element" id="filterBtn"><img src="img/filter.png" alt="">
				Фильтр</button>

			<Filter :visible="isFilterVisible" :participantTypes="participantTypes" @apply-filter="applyFilter"
				@reset-filter="resetFilter" />

			<button @click="openSaveModal" v-show="isAdmin" class="filter-element" id="addBtn"><img src="img/plus.png" alt=""> Добавить
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
									<button v-if="!item.editMode" @click="this.isAccountsFormVisible = true"><img src="img/accounts.svg"
											alt="a"></button>
											<button v-if="!item.editMode && isUser"><img src="img/favorites.svg" alt="f"></button>
									<button v-if="!item.editMode && isAdmin" @click="deleteItem(item)"><img src="img/delete.svg" alt="d"></button>
									<button v-if="!item.editMode && isAdmin" @click="item.editMode = true"><img src="img/settings.svg"
											alt="u"></button>
								</div>
								<div class="edit_mode_action">
									<button v-if="item.editMode" @click="item.editMode = false"><img src="img/cancel.svg" alt="c"></button>
									<button v-if="item.editMode && isAdmin" @click="saveItem(item)"><img src="img/save.svg" alt="s"></button>
								</div>

							</td>
							<td>{{ item.bic }}</td>
							<td v-if="!item.editMode">{{ item.nameParticipant }}</td>
							<td v-else><input v-model="item.nameParticipant" type="text"></td>
							<td v-if="!item.editMode">{{ item.registrationNumber }}</td>
							<td v-else><input v-model="item.registrationNumber" type="text"></td>
							<td v-if="!item.editMode">{{ item.countryCode }}</td>
							<td v-else><input v-model="item.countryCode" type="text"></td>
							<td v-if="!item.editMode">{{ item.regionCode }}</td>
							<td v-else><input v-model="item.regionCode" type="text"></td>
							<td v-if="!item.editMode">{{ item.index }}</td>
							<td v-else><input v-model="item.index" type="text"></td>
							<td v-if="!item.editMode">{{ item.typeLocation }}</td>
							<td v-else><input v-model="item.typeLocation" type="text"></td>
							<td v-if="!item.editMode">{{ item.nameLocation }}</td>
							<td v-else><input v-model="item.nameLocation" type="text"></td>
							<td v-if="!item.editMode">{{ item.address }}</td>
							<td v-else><input v-model="item.address" type="text"></td>
							<td v-if="!item.editMode">{{ item.parentBIC }}</td>
							<td v-else><input v-model="item.parentBIC" type="number"></td>
							<td v-if="!item.editMode">{{ item.dateIn }}</td>
							<td v-else><input v-model="item.dateIn" type="date"></td>
							<td v-if="!item.editMode">{{ item.dateOut }}</td>
							<td v-else><input v-model="item.dateOut" type="date"></td>
							<td v-if="!item.editMode">{{ item.participantType }}</td>
							<td v-else>
								<select v-model="item.participantType" name="participantType">
									<option v-for="(type, index) in participantTypes" :value="type.code" :key="index">
										{{ type.code }}
									</option>
								</select>
							</td>
							<td v-if="!item.editMode">{{ item.availableTransferService }}</td>
							<td v-else>
								<select v-model="item.availableTransferService" name="availableTransferService">
									<option v-for="(type, index) in availableTransferServices" :value="type.code" :key="index">
										{{ type.code }}
									</option>
								</select>
							</td>
							<td v-if="!item.editMode">{{ item.exchangeParticipant }}</td>
							<td v-else>
								<input type="checkbox" :checked="item.exchangeParticipant == 1"
									@change="item.exchangeParticipant = $event.target.checked ? 1 : 0" name="exchangeParticipant">
							</td>
							<td v-if="!item.editMode">{{ item.uid }}</td>
							<td v-else><input v-model="item.uid" type="number"></td>
							<td>{{ item.participantStatus }}</td>


						</tr>

					</template>
				</tbody>

			</table>

		</div>
		<div class="control-pages-block">
			<button @click="previousPage" :disabled="currentPage == 1">
				<!-- <img src="img/previous_page.png" alt=""> -->
				&#60;
			</button>
			<button @click="firstPage" :disabled="currentPage < 3">1</button>
			<button @click="previousPage" :disabled="currentPage < 2">{{ currentPage - 1 }}</button>
			<p class="currentPage">{{ currentPage }}</p>
			<button @click="nextPage" :disabled="currentPage > totalPage - 1">{{ currentPage + 1 }}</button>
			<button @click="lastPage" :disabled="currentPage > totalPage - 2">{{ totalPage }}</button>
			<button @click="nextPage" :disabled="currentPage == totalPage">
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
import { required, between } from '@vuelidate/validators'
import Filter from '@/components/Filter.vue';

import SaveModalForm from '@/components/SaveForm.vue';
import AccountsInfo from '@/components/AccountsInfo.vue';

import axios from 'axios';

export default {
	components: {
		SaveModalForm,
		AccountsInfo,
		Filter,
	},
	setup() {
		return { v$: useVuelidate() }
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

			itemsPerPage: 32,
			currentPage: 1,
			totalPage: 0,
			data: [],
		};
	},
	validations() {
		return {
			formFilter: {
				bic: { between: between(0, 999999999) }
			}
		}
	},
	methods: {
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
				participantType: item.participantInfo.participantType?.code,
				availableTransferService: item.participantInfo.availableTransferService?.code,
				exchangeParticipant: item.participantInfo.exchangeParticipant?.code,
				uid: item.participantInfo.uid,
				participantStatus: item.participantInfo.participantStatus?.code,
				accounts: item.accounts,
				editMode: false,
			}))

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

				// Проверяем, успешно ли выполнился запрос
				if (response.status === 200) {
					console.log('Item successfully updated');
				} else {
					console.error('Error updating item:', response);
				}
			} catch (error) {
				console.error('Error updating item:', error);
			}

			// В любом случае выходим из режима редактирования
			item.editMode = false;
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
			this.currentPage = 1;
			this.fetchData(1);
		},
		lastPage() {
			this.currentPage = this.totalPage;
			this.fetchData(this.totalPage);
		},
		previousPage() {
			if (this.currentPage > 1) {
				this.currentPage--;
				this.fetchData(this.currentPage);
			}
		},
		nextPage() {
			if (this.currentPage < this.totalPage) {
				this.currentPage++;
				this.fetchData(this.currentPage);
			}
		},
		async fetchData(currentPage) {

			axios.get(`http://localhost:8080/api/data?page=${currentPage - 1}&size=${this.itemsPerPage}`)
				.then(response => {
					this.fillTable(response.data.bicDirectoryEntries);
					this.bicDirectoryEntries = response.data.bicDirectoryEntries;
					this.totalPage = response.data.totalPage;
				}).catch(error => {
					console.log(error)
				});
		},

		openFilterMenu() {
			this.isFilterVisible = true;
		},
		resetAllFilter() {
			this.formFilter.bicInput = '';
			this.formFilter.nameInput = '';
			this.formFilter.typeTransfer = '';
			this.formFilter.validFrom = '';
			this.formFilter.validUntil = '';
			this.fetchData();
		},
		async filterByBic() {
			let bic = this.formFilter.bicInput;

			try {
				let response;
				if (bic !== "") {
					response = await axios.get(`http://localhost:8080/api/findBy/bic/${bic}`);

				} else {

					await this.fetchData();
				}
				this.nameInput = "";
				this.fillTable(response.data)
			} catch (error) {
				console.error(error);
			}
		},

		async filterByName() {
			let name = this.formFilter.nameInput;

			try {
				let response;
				if (name !== "") {
					response = await axios.get(`http://localhost:8080/api/findBy/name/${name}`);

				} else {

					await this.fetchData();
				}
				this.bicInput = "";
				this.fillTable(response.data)
			} catch (error) {
				console.error(error);
			}
		},


		async applyFilter(filter) {
			this.formFilter.typeTransfer = filter.participantType;
			this.formFilter.validFrom = filter.validFrom;
			this.formFilter.validUntil = filter.validUntil;
			try {
				const response = await axios.post('http://localhost:8080/api/filter', this.formFilter);
				this.fillTable(response.data);

			} catch (error) {
				console.error(error);
			}
			this.isFilterVisible = false;
		},
		async resetFilter() {
			await this.fetchData();
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
				participantType: newEntry.participantInfo.participantType?.code,
				availableTransferService: newEntry.participantInfo.availableTransferService?.code,
				exchangeParticipant: newEntry.participantInfo.exchangeParticipant?.code,
				uid: newEntry.participantInfo.uid,
				participantStatus: newEntry.participantInfo.participantStatus?.code,
				editMode: false,
			})
		},
		async startupDataLoader() {
			await axios.get('http://localhost:8080/api/participantTypes', {
			}).then(response => {
				this.participantTypes = response.data;
			})

			await axios.get('http://localhost:8080/api/availableTransferServices').then(response => {
				this.availableTransferServices = response.data;
			})

			await axios.get('http://localhost:8080/api/participantStatuses').then(response => {
				this.participantStatuses = response.data;
			})
			this.typeTransfer = this.participantTypes;
		}
	},
	created() {
		this.fetchData();
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
	},

};
</script>
