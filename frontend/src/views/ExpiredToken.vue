<template>
	<div class="center-elements">
		<p>Срок действия вашего кода истек</p>
	</div>
	<router-link to="/login">
		<button>Войти</button>
	</router-link>
	<router-link to="/login">
		<button>Зарегистрироваться</button>
	</router-link>
	<router-link to="/">
		<p>Перейти на основную страницу</p>
	</router-link>
	
</template>

<script>
export default {
    created() {
        this.$store.dispatch('logout');
    }
}
</script>
