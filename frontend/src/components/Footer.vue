<template>
	<footer>
		<div class="footer-block">
			<h2>КОНТАКТЫ</h2>
			<p>+7 (999) 999-99-99</p>
			<p>email@email.com</p>
		</div>
		<div class="footer-block">
			<h2>СТРАНИЦЫ</h2>
			
			<router-link to="/"><p>Главная</p></router-link>
			<router-link to="/handbook"><p>Справочник</p></router-link>
			<router-link to="/favorite"><p>Избранное</p></router-link>
		</div>
		<div class="footer-block">
			<h2>API СПРАВОЧНИКА</h2>
			<button>Подробнее</button>
		</div>
	</footer>
</template>
<style scoped>@import '@/assets/css/footer.css';</style>