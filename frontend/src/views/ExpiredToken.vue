<template>
	<div class="expired_token">
		<div class="center-elements">
		<h1>Срок действия вашего кода истек</h1>
		<router-link to="/login">
		<button>Войти</button>
	</router-link>
	<router-link to="/signup">
		<button>Зарегистрироваться</button>
	</router-link>
	<router-link to="/">
		<p>Перейти на основную страницу</p>
	</router-link>
	</div>
	
	</div>
	
	
</template>

<style scoped>
@import '@/assets/css/expiredToken.css';
</style>
<script>
export default {
    created() {
        this.$store.dispatch('logout');
    }
}
</script>
