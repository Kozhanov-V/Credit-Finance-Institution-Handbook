<template>
  <div class="modal-container" v-if="visible">

    <div class="modal">
      <form @submit.prevent="submitForm">
        <h2>Добавление записи</h2>

        <label class="required-field" for="BIC">БИК *</label>
        <input v-model.trim="bicDirectoryEntry.bic" type="number" placeholder="BIC" name="BIC">
        <span class="error" v-if="v$.bicDirectoryEntry.bic.$dirty && v$.bicDirectoryEntry.bic.$error">Поле "BIC"
          обязательно и должно быть числом от 0 до 999999999</span>

        <LAbel class="required-field" for="nameParticipant">Наименование *</LAbel>
        <input v-model.trim="bicDirectoryEntry.participantInfo.nameParticipant" type="text" placeholder="Наименование"
          name="nameParticipant">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.nameParticipant.$dirty
          && v$.bicDirectoryEntry.participantInfo.nameParticipant.$error">Поле "Наименование" обязательно и не должно
          превышать 140 символов</span>


        <label for="registrationNumber">Регистрационный порядковый номер</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.registrationNumber" type="text" placeholder="Рег.порядок"
          name="registrationNumber">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.registrationNumber.$dirty
          && v$.bicDirectoryEntry.participantInfo.registrationNumber.$error">Поле "Регистрационный порядковый номер" не
          должно превышать 9 символов</span>


        <label for="countryCode">Код страны</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.countryCode" type="text" placeholder="Код страны"
          name="countryCode">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.countryCode.$dirty
          && v$.bicDirectoryEntry.participantInfo.countryCode.$error">Поле "Код страны" не должно превышать 2
          символа</span>


        <label for="regionCode">Код региона</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.regionCode" type="text" placeholder="Код региона"
          name="regionCode">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.regionCode.$dirty
          && v$.bicDirectoryEntry.participantInfo.regionCode.$error">Поле "Код региона" не должно превышать 2
          символа</span>


        <label for="index">Индекс</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.index" type="text" placeholder="Индекс" name="index">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.index.$dirty
          && v$.bicDirectoryEntry.participantInfo.index.$error">Поле "Индекс" не должно превышать 6 символов</span>


        <label for="typeLocation">Тип населенного пункта</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.typeLocation" type="text"
          placeholder="тип населенного пункта" name="typeLocation">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.typeLocation.$dirty
          && v$.bicDirectoryEntry.participantInfo.typeLocation.$error">Поле "Тип населенного пункта"не должно превышать 6
          символов</span>


        <label for="nameLocation">Населенный пункт</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.nameLocation" type="text"
          placeholder="Название населенного пункта" name="nameLocation">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.nameLocation.$dirty
          && v$.bicDirectoryEntry.participantInfo.nameLocation.$error">Поле "Населенный пункт" не должно превышать 25
          символов</span>


        <label for="address">Адрес</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.address" type="text" placeholder="Адрес" name="address">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.address.$dirty
          && v$.bicDirectoryEntry.participantInfo.address.$error">Поле "Адрес" не должно превышать 160 символов</span>


        <label for="parentBIC">БИК головной организации</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.parentBIC" type="number"
          placeholder="БИК головной организации" name="parentBIC">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.parentBIC.$dirty
          && v$.bicDirectoryEntry.participantInfo.parentBIC.$error">Поле "БИК головной организации" должно быть числом от
          0 до 999999999</span>


        <label class="required-field" for="dateIn">Дата включения *</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.dateIn" type="date" name="dateIn">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.dateIn.$dirty
          && v$.bicDirectoryEntry.participantInfo.dateIn.$error">Поле "Дата включения" обязательно</span>


        <label for="dateOut">Дата исключения</label>
        <input v-model.trim="bicDirectoryEntry.participantInfo.dateOut" type="date" name="dateOut">

        <label class="required-field" for="participantType">Тип перевода участника *</label>
        <select v-model="bicDirectoryEntry.participantInfo.participantType" name="participantType">
          <option v-for="(type, index) in participantTypes" :value="type.code" :key="index">
            {{ type.code }}
          </option>
        </select>
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.participantType.$dirty
          && v$.bicDirectoryEntry.participantInfo.participantType.$error">Поле "Тип перевода
          участника" обязательно</span>

        <label class="required-field" for="availableTransferService">Доступные серв. перевода *</label>
        <select v-model="bicDirectoryEntry.participantInfo.availableTransferService" name="availableTransferService">
          <option v-for="(type, index) in availableTransferServices" :value="type.code" :key="index">
            {{ type.code }}
          </option>
        </select>
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.availableTransferService.$dirty
          && v$.bicDirectoryEntry.participantInfo.availableTransferService.$error">Поле "Доступные серв. перевода"
          обязательно</span>

        <label class="required-field" for="exchangeParticipant">Участник обмена *</label>
        <input type="checkbox" :checked="bicDirectoryEntry.participantInfo.exchangeParticipant == 1"
          @change="bicDirectoryEntry.participantInfo.exchangeParticipant = $event.target.checked ? 1 : 0"
          name="exchangeParticipant">
        <span class="error" v-if="v$.bicDirectoryEntry.participantInfo.exchangeParticipant.$dirty
          && v$.bicDirectoryEntry.participantInfo.exchangeParticipant.$error">Поле "Участник обмена"
          обязательно</span>

        <div class="button-row">
          <input type="submit" value="Submit" />
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
export default {

  props: ['visible', 'participantTypes', 'availableTransferServices', 'participantStatuses'],
  setup() {
    return { v$: useVuelidate() }
  },
  watch: {
    visible(val) {
      if (val) {
        document.body.style.overflow = 'hidden';
      } else {
        document.body.style.overflow = 'auto';
      }
    }
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
          exchangeParticipant: { required }
        }
      }
    }
  },

  methods: {
    closeModal() {
      this.$emit('close');
    },
    async submitForm() {
      this.v$.bicDirectoryEntry.$touch()
      if (!this.v$.bicDirectoryEntry.$error) {
        console.log("Ошибка")
        return;
      }
      try {
        const response = await axios.post('http://localhost:8080/api/save', this.bicDirectoryEntry);
        if (response.status === 200) {
          // Если запрос успешный, закройте модальное окно и очистите форму
          this.inputData = { name: '', age: '' };

        } else {
          console.error('Error:', response);
        }
      }
      catch (error) {
        console.log(error)
        return;
      }
      this.closeModal();
    },

  },


}

</script>
<style>
.modal-container {
  position: fixed;
  z-index: 100;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
}

.modal {
  box-sizing: border-box;
  padding: 48px;
  width: 561px;
  height: 599px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #EDEDEF;
}

.modal form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 8px;
  row-gap: 4px;
  width: 100%;
  margin-top: -32px;
}

.modal form h2 {
  grid-column: 1 / -1;
  margin-bottom: 24px;
  text-align: center;
}

.modal form label {
  justify-self: left;
  width: 248px;
}

.modal form input,
.modal form select {
  width: 100%;
  max-width: 239px;
  height: 19px;
}

.modal form .button-row {
  grid-column: 1 / -1;
  display: flex;
  justify-content: left;
  margin-top: 20px;

}

.modal form button {
  width: 100px;
  height: 30px;
  margin-right: 16px;
}

.error {
  color: red;
  font-size: 0.8rem;
}</style>