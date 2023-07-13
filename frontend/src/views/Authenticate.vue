<template>
    <div class="authenticate">
				<div class="logo" style="background-image: url(img/logoAuth.svg);"></div>
			<h1>АВТОРИЗАЦИЯ</h1>

        <form @submit.prevent="submitForm">
					<div class="input-group">
					<label for="login"><h2>ЛОГИН</h2></label>
            <input type="text" placeholder="login" name="login" id="login" v-model="username">
					</div>
					<div class="input-group">
						<label for="password"><h2>ПАРОЛЬ</h2></label>
            <input type="password" placeholder="password" name="password" id="password" v-model="password">
					</div>
            <input type="submit" value="ВОЙТИ" />
						<p>Нет аккаунта? <a href="/signup">Зарегистрироваться</a></p>
        </form>
			
    </div>
</template>
<style scoped>
@import '@/assets/css/authenticate.css';
</style>

<script>
export default {
    data() {
        return {
            username: '',
            password: '',
        }
    },
    methods: {
        async submitForm() {
            try {
                await this.$store.dispatch('login', {
                    username: this.username,
                    password: this.password
                });
                this.$router.push("/")
            } catch (error) {
                alert("Неверные учетные данные");
                console.error(error);
            }

        },
    }
}
</script>
