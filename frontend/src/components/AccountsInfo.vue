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

						<td v-if="editItem && editItem.account === item.account">
							<select v-model="editItem.regulationAccountType" name="regulationAccountType">
								<option v-for="(type, index) in regulationAccountTypes" :value="type" :key="index">
									{{ type }}
								</option>
							</select>
						</td>
						<td v-else>{{ item.regulationAccountType }}</td>


						<td v-if="editItem && editItem.account === item.account"><input v-model="editItem.controlKey" type="number">
						</td>
						<td v-else>{{ item.controlKey }}</td>

						<td v-if="editItem && editItem.account === item.account"><input v-model="editItem.dateIn" type="date"></td>
						<td v-else>{{ item.dateIn }}</td>

						<td v-if="editItem && editItem.account === item.account"><input v-model="editItem.dateOut" type="date"></td>
						<td v-else>{{ item.dateOut }}</td>

						<td v-if="editItem && editItem.account === item.account"><input v-model="editItem.accountCBRBIC"
								type="number">
						</td>
						<td v-else>{{ item.accountCBRBIC }}</td>

						<td>

							{{ item.accountStatus }}

						</td>




						<td>


							<div class="buttons" v-if="isAdmin">
								<div v-if="editItem && editItem.account === item.account">
									<button @click="cancelEdit()"><img src="img/cancel.svg" alt="O"></button>
									<button @click="saveEdit()"><img src="img/save.svg" alt="S"></button>
								</div>

								<div v-else>
									<button @click="editAccount()"><img src="img/settings.svg" alt="E"></button>
									<button @click="deleteCurrentItem()"><img src="img/delete.svg" alt="D"></button>
								</div>
							</div>



						</td>
					</tr>
					<tr v-if="addMode">
						<td> <input type="text" v-model="addItem.account"></td>
						<td> <select v-model="addItem.regulationAccountType" name="regulationAccountType">
								<option v-for="(type, index) in regulationAccountTypes" :value="type" :key="index">
									{{ type}}
								</option>
							</select></td>
						<td> <input type="text" v-model="addItem.controlKey"></td>
						<td> <input type="date" v-model="addItem.dateIn"></td>
						<td> <input type="date" v-model="addItem.dateOut"></td>
						<td> <input type="number" v-model="addItem.accountCBRBIC"></td>
						<td>
						</td>
						<td>
							<div class="buttons">

								<button @click="closeAddMode"><img src="img/cancel.svg" alt="О"></button>
								<button @click="saveAccount"><img src="img/save.svg" alt="S"></button>


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
export default {
	props: ['visible', 'entryAccounts'],
	data() {
		return {
			activeItem: null,
			editItem: null,
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
				accountRestrictions: item.accountRestrictions,
			}))
		},

		saveAccount() {

			axios
				.post(`http://localhost:8080/api/account/save?bic=${this.entryAccounts.bic}`, this.addItem, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					this.tableData.push({
						account: this.addItem.account,
						regulationAccountType: this.addItem?.regulationAccountType,
						controlKey: this.addItem.controlKey,
						dateIn: this.addItem.dateIn,
						dateOut: this.addItem.dateOut,
						accountStatus: this.addItem?.accountStatus,
						accountCBRBIC: this.addItem.accountCBRBIC,
						accountRestrictions: this.addItem.accountRestrictions,
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
		editAccount() {
			this.editItem = this.activeItem;

		},
		cancelEdit() {
			this.editItem = null;
		},
		saveEdit() {
			axios
				.put(`http://localhost:8080/api/account/update/${this.editItem.account}`, this.editItem, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					// обновить запись в локальном состоянии
					let item = this.tableData.find(item => item.account === this.editItem.account);
					Object.assign(item, this.editItem);
				})
				.catch(error => {
					console.error(error);
				})
				.finally(() => {
					this.editItem = null;
				});

		},
		deleteCurrentItem() {
			axios
				.delete(`http://localhost:8080/api/account/delete/${this.activeItem.account}`, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				})
				.then(() => {
					this.entryAccounts.accounts = this.entryAccounts.accounts.filter(item => item.account !== this.activeItem.account);
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