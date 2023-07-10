<template>
	<div class="modal-container" v-if="visible">

		<div class="modal">
			<form @submit.prevent="submitForm">
				<h2>Добавление записи</h2>

				<div class="input-group">
					<label class="required-field" for="BIC">БИК *</label>
					<input v-model.trim="bicDirectoryEntry.bic" type="number" placeholder="BIC" name="BIC">
					<span class="error" v-if="v$.bicDirectoryEntry.bic.$dirty && v$.bicDirectoryEntry.bic.$error">Требуется
						9-значный БИК</span>

				</div>

				<div class="input-group">
					<LAbel class="required-field" for="nameParticipant">Наименование *</LAbel>
					<input v-model.trim="bicDirectoryEntry.participantInfo.nameParticipant" type="text" placeholder="Наименование"
						name="nameParticipant">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.nameParticipant.$dirty
						&& v$.bicDirectoryEntry.participantInfo.nameParticipant.$error">Поле обязательно</span>
				</div>

				<div class="input-group">
					<label for="registrationNumber">Регистрационный номер</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.registrationNumber" type="text" placeholder="Рег.порядок"
						name="registrationNumber">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.registrationNumber.$dirty
						&& v$.bicDirectoryEntry.participantInfo.registrationNumber.$error">Не более 9 символов</span>
				</div>

				<div class="input-group">
					<label for="countryCode">Код страны</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.countryCode" type="text" placeholder="Код страны"
						name="countryCode">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.countryCode.$dirty
						&& v$.bicDirectoryEntry.participantInfo.countryCode.$error">Не более 2 символов</span>
				</div>

				<div class="input-group">
					<label for="regionCode">Код региона</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.regionCode" type="text" placeholder="Код региона"
						name="regionCode">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.regionCode.$dirty
						&& v$.bicDirectoryEntry.participantInfo.regionCode.$error">Не более 2 символова</span>
				</div>

				<div class="input-group">
					<label for="index">Индекс</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.index" type="text" placeholder="Индекс" name="index">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.index.$dirty
						&& v$.bicDirectoryEntry.participantInfo.index.$error">Не более 6 символов</span>
				</div>

				<div class="input-group">
					<label for="typeLocation">Тип населенного пункта</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.typeLocation" type="text"
						placeholder="тип населенного пункта" name="typeLocation">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.typeLocation.$dirty
						&& v$.bicDirectoryEntry.participantInfo.typeLocation.$error">Не более 6 символов</span>
				</div>

				<div class="input-group">
					<label for="nameLocation">Населенный пункт</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.nameLocation" type="text"
						placeholder="Название населенного пункта" name="nameLocation">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.nameLocation.$dirty
						&& v$.bicDirectoryEntry.participantInfo.nameLocation.$error">Не более 25 символов</span>
				</div>

				<div class="input-group">
					<label for="address">Адрес</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.address" type="text" placeholder="Адрес" name="address">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.address.$dirty
						&& v$.bicDirectoryEntry.participantInfo.address.$error">Не более 160 символов</span>
				</div>

				<div class="input-group">
					<label for="parentBIC">БИК головной организации</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.parentBIC" type="number"
						placeholder="БИК головной организации" name="parentBIC">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.parentBIC.$dirty
						&& v$.bicDirectoryEntry.participantInfo.parentBIC.$error">Требуется 9-значный БИК</span>
				</div>

				<div class="input-group">
					<label class="required-field" for="dateIn">Дата включения *</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.dateIn" type="date" name="dateIn">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.dateIn.$dirty
						&& v$.bicDirectoryEntry.participantInfo.dateIn.$error">Требуется дата</span>
				</div>

				<div class="input-group">
					<label for="dateOut">Дата исключения</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.dateOut" type="date" name="dateOut">
				</div>

				<div class="input-group">
					<label class="required-field" for="participantType">Тип перевода участника *</label>
					<select v-model="bicDirectoryEntry.participantInfo.participantType" name="participantType">
						<option v-for="(type, index) in participantTypes" :value="type" :key="index">
							{{ type}}
						</option>
					</select>
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.participantType.$dirty
						&& v$.bicDirectoryEntry.participantInfo.participantType.$error">Выберите тип</span>
				</div>

				<div class="input-group">
					<label class="required-field" for="availableTransferService">Доступные серв. перевода *</label>
					<select v-model="bicDirectoryEntry.participantInfo.availableTransferService" name="availableTransferService">
						<option v-for="(type, index) in availableTransferServices" :value="type" :key="index">
							{{ type}}
						</option>
					</select>
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.availableTransferService.$dirty
						&& v$.bicDirectoryEntry.participantInfo.availableTransferService.$error">Выберите сервис</span>
				</div>



				<div class="input-group">
					<label class="required-field" for="exchangeParticipant">Участник обмена *</label>
					<input type="checkbox" :checked="bicDirectoryEntry.participantInfo.exchangeParticipant == 1"
						@change="bicDirectoryEntry.participantInfo.exchangeParticipant = $event.target.checked ? 1 : 0"
						name="exchangeParticipant">
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.exchangeParticipant.$dirty
						&& v$.bicDirectoryEntry.participantInfo.exchangeParticipant.$error">Выберите статус</span>
				</div>

				<div class="input-group">
					<label for="uid">УИС</label>
					<input v-model.trim="bicDirectoryEntry.participantInfo.uid" type="number" placeholder="УИС" name="uid">
					
					<span class="error" v-if="v$.bicDirectoryEntry.participantInfo.uid.$dirty
						&& v$.bicDirectoryEntry.participantInfo.uid.$error">Цифровой, 10 знаков</span>
				</div>

				<div class="button-row">
					<button style="background-color: #5A77DF; color: white;" type="submit"> Submit </button>
					<button @click="closeModal">Close</button>
				</div>

			</form>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { required, maxLength, between } from '@vuelidate/validators';
import { useVuelidate } from '@vuelidate/core';
import { UNREF } from '@vue/compiler-core';
export default {

	props: ['visible', 'participantTypes', 'availableTransferServices', 'participantStatuses'],
	setup() {
		return { v$: useVuelidate() }
	},
	data() {
		return {
			bicDirectoryEntry: {
				bic: '',
				participantInfo: {
					nameParticipant: '',
					registrationNumber: '',
					countryCode: '',
					regionCode: '',
					index: '',
					typeLocation: '',
					nameLocation: '',
					address: '',
					parentBIC: '',
					dateIn: '',
					dateOut: '',
					participantType: '',
					availableTransferService: '',
					exchangeParticipant: 0,
					uid: 0,
				}

			}
		}
	},

	validations() {
		return {
			bicDirectoryEntry: {
				bic: { required, between: between(0, 999999999) },
				participantInfo: {
					nameParticipant: { required, maxLength: maxLength(140) },
					registrationNumber: maxLength(9),
					countryCode: maxLength(2),
					regionCode: maxLength(2),
					index: maxLength(6),
					typeLocation: maxLength(6),
					nameLocation: maxLength(25),
					address: maxLength(160),
					parentBIC: { between: between(0, 999999999) },
					dateIn: { required },
					participantType: { required },
					availableTransferService: { required },
					uid: {  between: between(0, 999999999) },
					exchangeParticipant: { required }
				}
			}
		}
	},

	methods: {
		closeModal() {
			// Сброс полей формы
			this.bicDirectoryEntry = {
				bic: '',
				participantInfo: {
					nameParticipant: '',
					registrationNumber: '',
					countryCode: '',
					regionCode: '',
					index: '',
					typeLocation: '',
					nameLocation: '',
					address: '',
					parentBIC: '',
					dateIn: '',
					dateOut: '',
					participantType: '',
					availableTransferService: '',
					uid: '',
					exchangeParticipant: 0,
				}
			}
			this.v$.bicDirectoryEntry.$reset();

			this.$emit('close');
		},
		async submitForm() {
			this.v$.bicDirectoryEntry.$touch()
			if (!this.v$.bicDirectoryEntry.$error) {
				console.log("Ошибка")
				return;
			}
			try {
				let response = await axios.post('http://localhost:8080/api/save', this.bicDirectoryEntry, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				});
				if (response.status === 200) {
					// После успешного создания записи отправьте событие в родительский компонент с новой записью.
					this.$emit('entryCreated', this.bicDirectoryEntry);
				} else {
					console.error('Error:', response);
				}
				this.closeModal();
			}
			catch (error) {
				console.log(error)
			}
			
		},


	},


}

</script>
<style scoped>@import '@/assets/css/saveForm.css';</style>