<template>
    <div class="not-found">
      <h1>Ой!</h1>
			<h2>Похоже, мы не можем найти<span>нужную вам страницу.</span> </h2>
			<p>Код ошибки: 404</p>
			<div class="not_found_ilustr" style="background-image: url(/img/notFoundIlustr.png);"></div>
			<router-link to="/handbook">
				<p style=" text-decoration: underline; margin-top: 2vh;">Перейти в справочник</p>
			</router-link>
	
    </div>
  </template>

<style scoped>
@import '@/assets/css/notFound.css';
</style>

  <script>
  export default {
    methods: {
      goHome() {
        this.$router.push("/handbook");
      }
    }
  }
  </script>
