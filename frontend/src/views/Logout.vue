<template>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
    async created() {
        const router = useRouter()
        await this.$store.dispatch('logout');
        router.push("/")
    }
}
</script>
