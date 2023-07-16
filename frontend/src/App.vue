
<template>
	  <Navigation v-if="route.path !== '/login' && route.path!=='/' && route.path!=='/expired' && route.path!=='/signup'"/>
  <Header v-if="route.path !== '/login' && route.path!=='/' && route.path!=='/signup'"  />


  <RouterView></RouterView>
	<Footer v-if="route.path !== '/login' && route.path!=='/signup'"  />
</template>

<script>
import Navigation from './components/Navigation.vue';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import { useRoute } from 'vue-router'

export default {
  setup() {
    const route = useRoute();
    return { route }
  },
  components: { Header, Navigation,Footer }
}

</script>
<style>
#app {

}
</style>