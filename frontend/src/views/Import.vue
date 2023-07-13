<template>
  <div class="import">
    <h1>ПАКЕТНЫЙ ИМПОРТ ДАННЫХ</h1>
		<div class="import_from_cb">
			<h2>ПРЯМОЙ ИМПОРТ С ЦБ РФ</h2>
		<p>Эта функция позволяет вам напрямую импортировать данные с официального сайта Центрального банка Российской Федерации всего в один клик. Используйте эту возможность для обновления базы данных нашего ресурса и поддержания актуальности представляемой информации. Помните, что точность и своевременность данных - это основа доверия и удовлетворенности наших пользователей. С помощью этой функции вы значительно ускорите процесс обновления информации, что повысит эффективность работы всего сайта.</p>
		<button @click="updateDataCB">
			Импортировать
		</button>
		</div>
	
  </div>
</template>


<style scoped>
@import '@/assets/css/import.css';
</style>

<script>
import axios from 'axios';
export default {
  methods: {
		async updateDataCB(){
			axios.post(`http://localhost:8080/api/import/update`,null, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				}).then((response) => {
					alert('Данные успешно импортировались')
				})
				.catch((error) =>{
					alert('Произошла ошибка при импортировании данных')
				}) 
		},
  },
	mounted() {
    this.$nextTick(function() {
      document.getElementById('importBtn').click();
    })
  },
}
</script>