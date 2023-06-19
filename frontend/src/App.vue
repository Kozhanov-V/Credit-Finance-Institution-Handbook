
<template>
  <Header v-if="route.path !== '/login'" />
  <RouterView></RouterView>
</template>

<script>

import Header from './components/Header.vue';
import { useRoute } from 'vue-router'

export default {
  setup() {
    const route = useRoute();
    return { route }
  },
  components: { Header }
}

</script>
<style>
#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333333;
}
</style>