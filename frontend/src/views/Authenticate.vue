<template>
    <div class="authenticate">
        <h1>Auth</h1>
        <form @submit.prevent="submitForm">
            <input type="text" placeholder="login" v-model="username">
            <input type="password" placeholder="password" v-model="password">
            <input type="submit" value="Войти" />
        </form>
    </div>
</template>
<script>
import axios from 'axios';
export default {

    data() {
        return {
            username: '',
            password: '',
        }
    },
    methods: {
        async submitForm() {
            // отправить запрос на аутентификацию с помощью axios и сохранить полученный токен JWT.
            await axios.post('http://localhost:8080/authenticate', {
                username: this.username,
                password: this.password,

            })
                .then(response => {
                    // Сохраняем токен в localStorage
                    console.log(response.data.token)
                    localStorage.setItem('token', response.data.token);
                    this.$store.commit('setToken', response.data.token);
                    this.$router.push('/');
                })
                .catch(error => {
                    console.log(error);
                });

        },
    }
}
</script>