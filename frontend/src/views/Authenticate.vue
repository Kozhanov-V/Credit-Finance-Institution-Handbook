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
