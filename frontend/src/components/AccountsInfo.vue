<template>
	<div class="accounts-container" v-if="visible">
		<div class="main-info--accounts">

			<h1>Информация о счетах: БИК-{{ entryAccounts.bic }} </h1>

			<div class="table-accounts">


				<table>
					<tr class="sticky-header">
						<th>Номер счета</th>
						<th>Тип счета</th>
						<th>Контрольный ключ</th>
						<th>Дата открытия счета</th>
						<th>Дата исключения информации о счете</th>
						<th>БИК ПБР</th>
						<th>Статус счета</th>
						<th v-if="isAdmin">Actions</th>
					</tr>
					<tr v-for="item in tableData" :key="item.account" @mouseover="activeItem = item">

						<td>{{ item.account }}</td>

						<td v-if="item === currentlyEditing">
							<select v-model="editedItem.regulationAccountType" name="regulationAccountType">
								<option v-for="(type, index) in regulationAccountTypes" :value="type" :key="index">
									{{ type }}
								</option>
							</select>
							<span class="error" v-if="v$.editedItem.regulationAccountType.$dirty
						&& v$.editedItem.regulationAccountType.$error">выберите тип счета</span>
						</td>
						<td v-else>{{ item.regulationAccountType }}</td>


						<td v-if="item === currentlyEditing">
							<input v-model="editedItem.controlKey" type="number">
							<span class="error" v-if="v$.editedItem.controlKey.$dirty
						&& v$.editedItem.controlKey.$error">не больше 2 символов</span>
						</td>
						<td v-else>{{ item.controlKey }}</td>

						<td v-if="item === currentlyEditing">
							<input v-model="editedItem.dateIn" type="date">
							<span class="error" v-if="v$.editedItem.dateIn.$dirty
						&& v$.editedItem.dateIn.$error">обязательное поле</span>
						</td>
						<td v-else>{{ item.dateIn }}</td>

						<td v-if="item === currentlyEditing">
							<input v-model="editedItem.dateOut" type="date"></td>
							
						<td v-else>{{ item.dateOut }}</td>

						<td v-if="item === currentlyEditing">
							<input v-model="editedItem.accountCBRBIC"
								type="number">
								<span class="error" v-if="v$.editedItem.accountCBRBIC.$dirty
						&& v$.editedItem.accountCBRBIC.$error">Цифровой, 9 знаков</span>
						</td>
						<td v-else>{{ item.accountCBRBIC }}</td>

						<td>

							{{ item.accountStatus }}

						</td>




						<td>


							<div class="buttons" v-if="isAdmin">
								<div v-if="item === currentlyEditing">
									<button @click="cancelEdit()"><img src="/img/cancel.svg" alt="O"></button>
									<button @click="saveEdit(item)"><img src="/img/save.svg" alt="S"></button>
								</div>

								<div v-else>
									<button @click="editAccount(item)"><img src="/img/settings.svg" alt="E"></button>
									<button @click="deleteCurrentItem(item)"><img src="/img/delete.svg" alt="D"></button>
								</div>
							</div>



						</td>
					</tr>
					<tr v-if="addMode">
						<td> <input type="text" v-model="addItem.account">
							<span class="error" v-if="v$.addItem.account.$dirty
						&& v$.addItem.account.$error">текстовой, 20 знаков</span>
						</td>
						<td> <select v-model="addItem.regulationAccountType" name="regulationAccountType">
						
								<option v-for="(type, index) in regulationAccountTypes" :value="type" :key="index">
									{{ type }}
								</option>
							</select>
							<span class="error" v-if="v$.addItem.regulationAccountType.$dirty
						&& v$.addItem.regulationAccountType.$error">выберите тип счета</span>
						</td>
						<td> 
							<input type="text" v-model="addItem.controlKey">
							<span class="error" v-if="v$.addItem.controlKey.$dirty
						&& v$.addItem.controlKey.$error">не больше 2 символов</span>
						</td>
						<td>
							 <input type="date" v-model="addItem.dateIn">
							 <span class="error" v-if="v$.addItem.dateIn.$dirty
						&& v$.addItem.dateIn.$error">обязательное поле</span>
							</td>
						<td> <input type="date" v-model="addItem.dateOut"></td>
						<td> 
							<input type="number" v-model="addItem.accountCBRBIC">
							<span class="error" v-if="v$.addItem.accountCBRBIC.$dirty
						&& v$.addItem.accountCBRBIC.$error">Цифровой, 9 знаков</span>
						</td>
						<td>
						</td>
						<td>
							<div class="buttons">

								<button @click="closeAddMode"><img src="/img/cancel.svg" alt="О"></button>
								<button @click="saveAccount"><img src="/img/save.svg" alt="S"></button>


							</div>
						</td>
					</tr>
				</table>

			</div>
			<div class="button-container">
				<button id="back-btn" @click="closeModal">Назад</button>
				<button @click="addMode = true" id="add-account" v-if="isAdmin">Добавить счет</button>
			</div>

		</div>
	</div>
</template>
<script>
import axios from 'axios';
import { parseISO, format } from 'date-fns';
import { required, maxLength, between, and } from '@vuelidate/validators';
import { useVuelidate } from '@vuelidate/core';
export default {
	props: ['visible', 'entryAccounts'],
	setup() {
		return { v$: useVuelidate() }
	},
	validations() {
		return {
			editedItem:{
				regulationAccountType:{required},
				controlKey:{maxLength: maxLength(2)},
				dateIn:{required},
				accountCBRBIC:{ between: between(0, 999999999)},
			},		
			addItem:{
				account:{required,maxLength: maxLength(20)},
				regulationAccountType:{required},
				controlKey:{maxLength: maxLength(2)},
				dateIn:{required},
				accountCBRBIC:{ between: between(0, 999999999)},
			},
		}
	},
	data() {
		return {
			activeItem: null,
			editedItem: null,
			currentlyEditing: null,
			addMode: false,
			accountRestrictions: [],
			accountStatuses: [],
			regulationAccountTypes: [],
			addItem: {
				regulationAccountType: '',
				controlKey: '',
				dateIn: '',
				dateOut: '',
				accountStatus: '',
				accountCBRBIC: '',
				accountRestrictions: [],
			},
			tableData: []
		}
	},
	watch: {
		entryAccounts: {
			handler(newValue) {
				if (newValue && newValue.accounts) {
					this.fillAccountTable(newValue.accounts);
				}
			},
			deep: true,
		},
	},
	methods: {
		fillAccountTable(accounts) {
			this.tableData = accounts.map(item => ({
				account: item.account,
				regulationAccountType: item?.regulationAccountType?.code,
				controlKey: item.controlKey,
				dateIn: item.dateIn,
				dateOut: item.dateOut,
				accountStatus: item?.accountStatus?.code,
				accountCBRBIC: item.accountCBRBIC,
			}))
		},
		getAccountStatusByDates(dateIn, dateOut) {

			let today = format(new Date(), "yyyy-MM-dd")

			if (dateOut === '') {
				dateOut = today;
			}

			if (dateOut < today || dateIn > today) {
				console.log("ACDL")
				return "ACDL"
			} else {
				console.log("ACAC")
				return "ACAC"
			}
		},

		saveAccount() {
			this.v$.addItem.$touch()
			if (this.v$.addItem.$error) {
				console.log("Ошибка")
				alert('Ошибка')
				return;
			}
			axios
				.post(`http://localhost:8080/api/account/save?bic=${this.entryAccounts.bic}`, this.addItem, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					let accountStatusForAddItem = this.getAccountStatusByDates(this.addItem.dateIn, this.addItem.dateOut);
					console.log(accountStatusForAddItem)
					this.tableData.push({
						account: this.addItem.account,
						regulationAccountType: this.addItem?.regulationAccountType,
						controlKey: this.addItem.controlKey,
						dateIn: this.addItem.dateIn,
						dateOut: this.addItem.dateOut,
						accountStatus: accountStatusForAddItem,
						accountCBRBIC: this.addItem.accountCBRBIC,
					});
					this.closeAddMode();
				})
				.catch(error => {
					console.error(error);
				});




		},
		closeAddMode() {
			this.addMode = false;
			this.addItem.account = '';
			this.addItem.regulationAccountType = '';
			this.addItem.controlKey = '';
			this.addItem.dateIn = '';
			this.addItem.dateOut = '';
			this.addItem.accountStatus = '';
			this.addItem.accountCBRBIC = '';
			accountRestrictions = [];
		},
		editAccount(item) {
		this.editedItem = item; 
    this.currentlyEditing = item;

		},
		cancelEdit() {
			Object.assign(this.editedItem,this.currentlyEditing); 
    this.currentlyEditing = null;
    this.editedItem = null;
		},
		saveEdit(item) {
			this.v$.editedItem.$touch()
			if (this.v$.editedItem.$error) {
				console.log("Ошибка")
				alert('Ошибка')
				return;
			}
			axios
				.put(`http://localhost:8080/api/account/update/${this.editedItem.account}`, this.editedItem, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					// обновить запись в локальном состоянии
					let accountStatusForeditedItem = this.getAccountStatusByDates(this.editedItem.dateIn, this.editedItem.dateOut);
					console.log(accountStatusForeditedItem)
					item.accountStatus = accountStatusForeditedItem;
					Object.assign(item, this.editedItem);
					this.currentlyEditing = null;
   			 this.editedItem = null;
				})
				.catch(error => {
					console.error(error);
				});

		},
		deleteCurrentItem(deletedItem) {
			console.log(deletedItem)
			axios
				.delete(`http://localhost:8080/api/account/delete/${deletedItem.account}`, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					this.entryAccounts.accounts = this.entryAccounts.accounts.filter(item => item.account !== deletedItem.account);
				})
				.catch(error => {
					console.error(error);
				});
		},
		closeModal() {
			this.$emit('close');
		}
	},
	computed: {
		isAdmin() {

			const roles = this.$store.getters.getRoles;
			return roles ? roles.map(e => e.name).includes('ROLE_ADMIN') : false;
		},


	},
	async created() {
		try {
			let response = await axios.get(`http://localhost:8080/api/account/regulationAccountTypes`);
			this.regulationAccountTypes = response.data.map(e => e.code);

			response = await axios.get(`http://localhost:8080/api/account/accountStatuses`);
			this.accountStatuses = response.data.map(e => e.code);

			response = await axios.get(`http://localhost:8080/api/account/accountRestrictions`);
			this.accountRestrictions = response.data.map(e => e.code);
		} catch (error) {
			console.log(error);
		}
	},


}
</script>



<style scoped>
@import '@/assets/css/accountsInfo.css';
</style>