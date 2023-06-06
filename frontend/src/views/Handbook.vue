<template>
    
    <div class="container">
        <div class="handbook">
            <h1>СПРАВОЧНИК</h1>
        </div>
    <div class="filter">
            <div id="filter-form">

        <div class="block">
        <label for="deleted-record-input">Удаленные</label>
        <input id="deleted-record-input" type="checkbox" v-model="deletedRecord">

        <label for="order-by">Сортировка</label>
        <select id="order-by" class="short-button" v-model="orderBy">
            <option>Код по убыванию </option>
            <option>Код по возрастанию </option>
            <option>Наименование возрастанию</option>
            <option>Наименование убывание</option>
            <option>БИК по убыванию </option>
            <option>БИК по возрастанию </option>
        </select>
        </div>

        <div class="block">
        <label for="cod-input">Код</label>
        <input id="cod-input" type="text" class="long-button" v-model="cod">

        <label for="name-record-input">Наименование</label>
        <input id="name-record-input" type="text" class="long-button" v-model="nameRecord">
        </div>

        <div class="block">
        <label for="type-organization-select">Вид организации</label>
        <select id="type-organization-select" class="long-button" v-model="typeOrganization">
            <option></option>
            <option>Банк</option>
            <option>Фонд</option>
            <option>Компания</option>
        </select>
        <label for="type-payer-select">Вид плательщика</label>  
        <select id="type-payer-select" class="long-button" v-model="typePayer">
            <option></option>
            <option>Физ. лицо</option>
            <option>Юр лицо</option>
        </select>
        </div>

        <div class="block">
        <label for="valid-from-datepicker">Действует с</label>
        <input id="valid-from-datepicker" type="date" class="long-button" v-model="validFrom">
        
        <label for="valid-until-datepicker">Действует по</label>
        <input id="valid-until-datepicker" type="date" class="long-button" v-model="validUntil">
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
                    <th>Код</th>
                    <th>Наименование</th>
                    <th>Сокращение</th>
                    <th>Вид</th>
                    <th>Наименование ЦБ РФ</th>
                    <th>БИК</th>
                    <th>Период действия с</th>
                    <th>Период действия по</th>
                    <th>Actions</th>
                </tr>
                <tr>
                    <td>B016</td>
                    <td>БУРЯТСКОЕ ОТДЕЛЕНИЕ N8601 ПАО СБЕРБАНК г...</td>
                    <td>БУРЯТСКОЕ ОТДЕЛЕНИЕ N8601 ПАО СБЕРБАНК г....</td>
                    <td>банк</td>
                    <td>ВЛАДИМИРСКОЕ ОТДЕЛЕНИЕ №8611 ПАО...</td>
                    <td>042520607</td>
                    <td>01.01.2000</td>
                    <td>01.01.2000</td>
                    <td><button><img src="/more_horiz.png"/></button></td>
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
      deletedRecord: false,
      orderBy: '',
      cod: '',
      nameRecord: '',
      typeOrganization: '',
      typePayer: '',
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
      this.deletedRecord = false;
      this.orderBy = '';
      this.cod = '';
      this.nameRecord = '';
      this.typeOrganization = '';
      this.typePayer = '';
      this.validFrom = '';
      this.validUntil = '';
    },
    async submitForm() {
  try {
    const response = await axios.post('http://localhost:8080/api/filter', {
      deletedRecord: this.deletedRecord,
      orderBy: this.orderBy,
      cod: this.cod,
      nameRecord: this.nameRecord,
      typeOrganization: this.typeOrganization,
      typePayer: this.typePayer,
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
        console.log(response.data.item.cod)
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