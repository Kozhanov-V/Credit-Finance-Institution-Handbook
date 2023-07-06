<template>
  <div class="import">
    <button @click="goBack">
      Back
    </button>
    <h1>Import</h1>
		<button @click="updateData">ИМПОРТИРОВАТЬ НАПРЯМУЮ ИЗ ЦЕНТРОБАНКА</button>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  methods: {
    goBack() {
      this.$router.go(-1)
    },
		async updateData(){
			axios.post(`http://localhost:8080/api/import/update`,null, {
					headers: {
						'Authorization': 'Bearer ' + localStorage.getItem('token')
					}
				}).then((response) => {
					this.regulationAccountTypes = response.data;
				})
		}
  },
	mounted() {
    this.$nextTick(function() {
      document.getElementById('importBtn').click();
    })
  },
}
</script>