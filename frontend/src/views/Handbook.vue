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
								<input v-model.trim="editedItem.nameParticipant" type="text">
								<span class="error" v-if="v$.editedItem.nameParticipant.$dirty
						&& v$.editedItem.nameParticipant.$error">Поле обязательно</span>
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.registrationNumber }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.registrationNumber" type="text">
								<span class="error" v-if="v$.editedItem.registrationNumber.$dirty
						&& v$.editedItem.registrationNumber.$error">Не более 9 символов</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.countryCode }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.countryCode" type="text">
								<span class="error" v-if="v$.editedItem.countryCode.$dirty
						&& v$.editedItem.countryCode.$error">Не более 2 символов</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.regionCode }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.regionCode" type="text">
								<span class="error" v-if="v$.editedItem.regionCode.$dirty
						&& v$.editedItem.regionCode.$error">Не более 2 символова</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.index }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.index" type="text">
								<span class="error" v-if="v$.editedItem.index.$dirty
						&& v$.editedItem.index.$error">Не более 6 символов</span>
				
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.typeLocation }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.typeLocation" type="text">
								<span class="error" v-if="v$.editedItem.typeLocation.$dirty
						&& v$.editedItem.typeLocation.$error">Не более 6 символов</span>
				
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.nameLocation }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.nameLocation" type="text">
								<span class="error" v-if="v$.editedItem.nameLocation.$dirty
						&& v$.editedItem.nameLocation.$error">Не более 25 символов</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.address }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.address" type="text">
								<span class="error" v-if="v$.editedItem.address.$dirty
						&& v$.editedItem.address.$error">Не более 160 символов</span>
				
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.parentBIC }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.parentBIC" type="number">
								<span class="error" v-if="v$.editedItem.parentBIC.$dirty
						&& v$.editedItem.parentBIC.$error">Требуется 9-значный БИК</span>
				
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.dateIn }}
							</td>
							<td v-else>
								<input v-model="editedItem.dateIn" type="date">
								<span class="error" v-if="v$.editedItem.dateIn.$dirty
						&& v$.editedItem.dateIn.$error">Требуется дата</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.dateOut }}
							
							</td>
							<td v-else>
								<input v-model="editedItem.dateOut" type="date">
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.participantType }}
							</td>
							<td v-else>
								<select v-model="editedItem.participantType" name="participantType">
									<option v-for="(type, index) in participantTypes" :value="type" :key="index">
										{{ type }}
									</option>
								</select>
								<span class="error" v-if="v$.editedItem.participantType.$dirty
						&& v$.editedItem.participantType.$error">Выберите тип</span>
				
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.availableTransferService }}
							</td>
							<td v-else>
								<select v-model="editedItem.availableTransferService" name="availableTransferService">
									<option v-for="(type, index) in availableTransferServices" :value="type" :key="index">
										{{ type }}
									</option>
								</select>
								<span class="error" v-if="v$.editedItem.availableTransferService.$dirty
						&& v$.editedItem.availableTransferService.$error">Выберите сервис</span>
			
							</td>

							<td v-if="item !== currentlyEditing">
								{{ item.exchangeParticipant }}
							</td>
							<td v-else>
								<input type="checkbox" :checked="editedItem.exchangeParticipant == 1"
									@change="editedItem.exchangeParticipant = $event.target.checked ? 1 : 0" name="exchangeParticipant">
									<span class="error" v-if="v$.editedItem.exchangeParticipant.$dirty
						&& v$.editedItem.exchangeParticipant.$error">Выберите статус</span>
			
								</td>

							<td v-if="item !== currentlyEditing">
									{{ item.uid }}
							</td>
							<td v-else>
								<input v-model.trim="editedItem.uid" type="number">
								<span class="error" v-if="v$.editedItem.uid.$dirty
						&& v$.editedItem.uid.$error">Цифровой, 10 знаков</span>
		
							</td>

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
import { required, minLength, maxLength, sameAs, between, and } from '@vuelidate/validators';
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
				bic: { required, between: between(0, 999999999) }, 
				nameParticipant: { required, maxLength: maxLength(140) },
					registrationNumber: {maxLength: maxLength(9)},
					countryCode: {maxLength: maxLength(2)},
					regionCode:{maxLength: maxLength(2)},
					index:{maxLength: maxLength(6)},
					typeLocation:{maxLength: maxLength(6)},
					nameLocation:{maxLength: maxLength(25)},
					address:{maxLength: maxLength(160)},
					parentBIC: { between: between(0, 999999999) },
					dateIn: { required },
					participantType: { required },
					availableTransferService: { required },
					uid: {  between: between(0, 999999999) },
					exchangeParticipant: { required }
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
			
			this.v$.editedItem.$touch()
      if (this.v$.editedItem.$invalid) {
				alert('ошибка')
        return
      }
			try {
				const response = await axios.put(`http://localhost:8080/api/update/${this.editedItem.bic}`, {
					bic: this.editedItem.bic,
					participantInfo: {
						nameParticipant: this.editedItem.nameParticipant,
						registrationNumber: this.editedItem.registrationNumber,
						countryCode: this.editedItem.countryCode,
						regionCode: this.editedItem.regionCode,
						index: this.editedItem.index,
						typeLocation: this.editedItem.typeLocation,
						nameLocation: this.editedItem.nameLocation,
						address: this.editedItem.address,
						parentBIC: this.editedItem.parentBIC,
						dateIn: this.editedItem.dateIn,
						dateOut: this.editedItem.dateOut,
						participantType: this.editedItem.participantType,
						availableTransferService: this.editedItem.availableTransferService,
						uid: this.editedItem.uid,
						exchangeParticipant: this.editedItem.exchangeParticipant,
					}
				}
					, {
						headers: {
							'Authorization': 'Bearer ' + localStorage.getItem('token')
						}
					});

			
				if (response.status === 200) {
					console.log(this.editedItem)
					console.log(item)
					Object.assign(this.currentlyEditing, this.editedItem); 
  			  this.currentlyEditing = null;
  			  this.editedItem = null;
					
				} else {
					console.error('Error updating item:', response);
				}
			} catch (error) {
				console.error('Error updating item:', error);
			}

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
	
		this.startupDataLoader();
	},
	mounted() {
		this.$nextTick(function () {
			document.getElementById('handbookBtn').click();
		})
		this.currentQuery = this.fetchData;
		this.performQuery(this.currentQueryParams);
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
