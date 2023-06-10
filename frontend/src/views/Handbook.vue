<template>
  <div class="container">
    <div class="handbook">
      <h1>СПРАВОЧНИК</h1>
    </div>
    <div class="filter">
      <div id="filter-form">

        <div class="block">

          <label for="order-by">Сортировка</label>
          <select id="order-by" class="short-button" v-model="orderBy">
            <option>Наименование возрастанию</option>
            <option>Наименование убывание</option>
            <option>БИК по убыванию </option>
            <option>БИК по возрастанию </option>
          </select>
        </div>

        <div class="block">
          <label for="bic-input">БИК</label>
          <input id="bic-input" type="text" class="long-button" v-model="bic">

          <label for="name-record-input">Наименование</label>
          <input id="name-record-input" type="text" class="long-button" v-model="nameRecord">
        </div>

      
        <div class="block">
          <label for="valid-from-datepicker">Действует на дату с:</label>
          <input id="valid-from-datepicker" type="date" class="long-button" v-model="validFrom">

          <label for="valid-until-datepicker">по:</label>
          <input id="valid-until-datepicker" type="date" class="long-button" v-model="validUntil">
        </div>

        <div class="block">
          <label for="type-organization-select">Тип участника</label>
          <select id="type-organization-select" class="long-button" v-model="typeOrganization">
            <option></option>
            <option>00</option>
            <option>10</option>
            <option>12</option>
            <option>15</option>
          </select>
        </div>

        <div class="block">
          <button id="clear-form" class="short-button" @click="resetForm">Сбросить</button>
          <button type="button" class="short-button" @click="submitForm">Найти</button>
        </div>

       


      </div>
      <button id="update-table" class="short-button">Обновить</button>
      <button id="add-item" class="short-button">Добавить</button>
    </div>
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
          <td>{{ item.dateIn}}</td>
          <td>{{ item.dateOut}}</td>
          <td>{{ item.participantType }}</td>
          <td>{{ item.availableTransferService}}</td>
          <td>{{ item.exchangeParticipant }}</td>
          <td><button><img src="/more_horiz.png" /></button></td>
        </tr>      
         
      </table>
    </div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  data() {
    return {
      orderBy: '',
      bic: '',
      nameRecord: '',
      typeTransfer: '',
      validFrom: '',
      validUntil: '',
      tableData:[],
    };
  },
  methods: {

    

    resetForm() {
      this.orderBy = 'БИК по убыванию';
      this.bic = '';
      this.nameRecord = '';
      this.typeTransfer = '';
      this.validFrom = '';
      this.validUntil = '';
    },
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/filter', {
          orderBy: this.orderBy,
          bic: this.bic,
          nameRecord: this.nameRecord,
          typeTransfer: this.typeTransfer,
          validFrom: this.validFrom,
          validUntil: this.validUntil,
        });
        // обрабатывайте ответ сервера
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    },

    async fetchData() {
      try {
      const response = await axios.get('http://localhost:8080/api/data');

      // Обработайте данные здесь, преобразовав их в формат, который вы ожидаете в tableData

      this.tableData = response.data.map(item => ({

        // Убедитесь, что поля здесь соответствуют полям, которые вы хотите отобразить в таблице

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
        participantType: item.participantInfo.participantType.code,
        availableTransferService: item.participantInfo.availableTransferService.code,
        exchangeParticipant: item.participantInfo.exchangeParticipant.code,

        // и так далее для каждого поля, которое вам нужно
      }));
    } catch (error) {
      console.error(error);
    }
    }
  },
  created() {
    // Получаем данные при загрузке страницы
    this.fetchData();
  }
};
</script>