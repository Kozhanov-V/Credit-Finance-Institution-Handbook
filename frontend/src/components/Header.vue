<template>
    <header>
        <RouterLink class="import" to="/import"><span>ИМПОРТИРОВАТЬ ЭС</span></RouterLink>
        <div class="logo">
            <img src="/headerLogo.svg" width="680" height="90" />
        </div>


        <h2>USERNAME</h2>
        <button>LOG OUT</button>
    </header>
</template>
<script>
export default {
    methods: {
        // Когда пользователь выходит из системы, вы можете удалить токен из localStorage.
        logout() {
            localStorage.removeItem('token');
        }

    }
}
</script>