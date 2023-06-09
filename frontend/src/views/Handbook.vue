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
        <tr>
          <td>1</td>
          <td>123456789</td>
          <td>УФК по Астраханской области</td>
          <td>3292/2</td>
          <td>RU</td>
          <td>12</td>
          <td>414056</td>
          <td>г.</td>
          <td>Астрахань</td>
          <td>ул. Владимира Ленина, 127</td>
          <td>123456789</td>
          <td>18.05.2023</td>
          <td>18.05.2023</td>
          <td>52</td>
          <td></td>
          <td></td>
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
    };
  },
  methods: {

    data() {
      return {
        // остальные данные
        tableData: [],
      };
    },

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
        // убедитесь, что в ответе есть нужные данные и добавьте их в data
        this.tableData = response.data;
        console.log(response.data)
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