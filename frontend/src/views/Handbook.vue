<template>
  <div class="container">
    <div class="handbook">
      <h1>СПРАВОЧНИК</h1>
    </div>

    <div class="filter">
      <div id="filter-form">

        <div class="block">
          <label for="bic-input">БИК</label>
          <input id="bic-input" type="number" class="long-button" v-model="formFilter.bic">

          <label for="name-record-input">Наименование</label>
          <input id="name-record-input" type="text" class="long-button" v-model="formFilter.nameRecord">
        </div>


        <div class="block">
          <label for="valid-from-datepicker">Действует на дату с:</label>
          <input id="valid-from-datepicker" type="date" class="long-button" v-model="formFilter.validFrom">

          <label for="valid-until-datepicker">по:</label>
          <input id="valid-until-datepicker" type="date" class="long-button" v-model="formFilter.validUntil">
        </div>

        <div class="block">
          <label for="type-organization-select">Тип участника</label>
          <select id="type-organization-select" class="long-button" v-model="formFilter.typeTransfer">
            <option v-for="(type, index) in participantTypes" :value="type.code" :key="index">
              {{ type.code }}
            </option>

          </select>
        </div>

        <div class="block" style="width: 98px; margin-top: 4px; margin-right: 16px;">
          <button id="clear-form" class="short-button" @click="resetForm">Сбросить</button>
          <button type="button" class="short-button" @click="submitForm">Найти</button>
        </div>




      </div>

      <button id="update-table" class="short-button" @click="reload">Обновить</button>
      <button id="add-item" class="short-button" @click="openSaveModal">Добавить</button>
    </div>
    <SaveModalForm :visible="isSaveModalVisible" @close="isSaveModalVisible = false"
      :available-transfer-services="availableTransferServices" :participant-types="participantTypes"
      :participant-statuses="participantStatuses" :editingItem="selectedItem" />


    <div class="output-info">
      <table>
        <tr>
          <th>ID ЭС</th>
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
          <th>Actions</th>
        </tr>
        <tr v v-for="item in tableData" :key="item.bic">
          <td>{{ item.idES }}</td>
          <td>{{ item.bic }}</td>
          <td>{{ item.nameParticipant }}</td>
          <td>{{ item.registrationNumber }}</td>
          <td>{{ item.countryCode }}</td>
          <td>{{ item.regionCode }}</td>
          <td>{{ item.index }}</td>
          <td>{{ item.typeLocation }}</td>
          <td>{{ item.nameLocation }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.parentBIC }}</td>
          <td>{{ item.dateIn }}</td>
          <td>{{ item.dateOut }}</td>
          <td>{{ item.participantType }}</td>
          <td>{{ item.availableTransferService }}</td>
          <td>{{ item.exchangeParticipant }}</td>
          <td>{{ item.UID }}</td>
          <td>{{ item.participantStatus }}</td>
          <td>
            <button @click="openPopover(item)">
              <img src="/more_horiz.png" />
            </button>

            <popover v-if="selectedItem && selectedItem.bic === item.bic" @close="closePopover">
              <div class="popover">
                <button @click="openSaveModal()">Обновить</button>
                <button @click="deleteItem()">Удалить</button>
                <button @click="openAccounts(selectedItem)">Открыть счета</button>
                <button @click="closePopover()">Отменить</button>
              </div>

            </popover>
          </td>
        </tr>

      </table>
    </div>
  </div>
</template>

<script>

import { useVuelidate } from '@vuelidate/core'
import { required, between } from '@vuelidate/validators'

import SaveModalForm from '@/components/SaveForm.vue';

import axios from 'axios';

export default {
  components: {
    SaveModalForm
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
        bic: '',
        nameRecord: '',
        typeTransfer: '',
        validFrom: '',
        validUntil: '',

      },
      tableData: [],
      selectedItem: null,
      isSaveModalVisible: false
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
    reload() {
      location.reload();
    },
    fillTable(bicDirectoryEntries) {
      this.tableData = bicDirectoryEntries.map(item => ({
        idES: item.electronicDocuments.number,
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
        UID: item.participantInfo.UID,
        participantStatus: item.participantInfo.participantStatus?.code,
      }))
    },

    async deleteItem() {
      console.log(this.selectedItem.bic)
      const response = await axios.delete(`http://localhost:8080/api/delete/${this.selectedItem.bic}`, {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
      })
      location.reload();
    },
    openPopover(item) {
      this.selectedItem = item;
    },
    closePopover() {
      this.selectedItem = null;
    },

    openSaveModal() {
      this.isSaveModalVisible = true;
      // this.closePopover();
    },

    resetForm() {
      this.formFilter.bic = '';
      this.formFilter.nameRecord = '';
      this.formFilter.typeTransfer = '';
      this.formFilter.validFrom = '';
      this.formFilter.validUntil = '';
    },
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/filter', this.formFilter, {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        });
        this.bicDirectoryEntries = response.data;
        console.log(this.bicDirectoryEntries);
        this.fillTable(this.bicDirectoryEntries);

      } catch (error) {
        console.error(error);
      }
    },

    async fetchData() {

      try {
        const response = await axios.get('http://localhost:8080/api/data', {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        });

        this.bicDirectoryEntries = response.data.bicDirectoryEntries;
        this.participantTypes = response.data.participantTypes;
        this.availableTransferServices = response.data.availableTransferServices;
        this.participantStatuses = response.data.participantStatuses;


        this.typeTransfers = this.participantTypes;
        this.fillTable(this.bicDirectoryEntries);

      } catch (error) {
        console.error(error);
      }
    }
  },
  created() {
    this.fetchData();
  }
};
</script>
<style>
.popover {
  position: absolute;
  width: 96px;
  height: 116px;
  padding: 0px 0px;
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
  background-color: #E6E6E9;
  z-index: 1;
  display: grid;
  grid-template-rows: repeat(4, 1fr);
  gap: 4px;
  top: 0px;
}

.popover button {
  /* Здесь вы можете применить стили для ваших кнопок */
  width: 100%;
  height: 100%;
  border: none;
  cursor: pointer;
  background: #E6E6E9;
  text-align: center;
  transition: 0.2s;
}

.popover button:hover {
  background: #D4D4D8;
}
</style>