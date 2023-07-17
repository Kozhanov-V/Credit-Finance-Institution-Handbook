
<template>
	  <Navigation v-if="shouldShowNavigation"/>
  <Header v-if="route.path !== '/login' && route.path!=='/' && route.path!=='/signup' && route.path!=='/expired'"  />


  <RouterView></RouterView>
	<Footer v-if="route.path !== '/login' && route.path!=='/signup'&& route.path!=='/expired'"  />
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
	computed: {
    shouldShowNavigation() {
      const excludedRoutes = ['Home', 'Authenticate', 'Signup', 'ExpiredToken', 'NotFound'];
      return !excludedRoutes.includes(this.$route.name);
    }
  },
  components: { Header, Navigation,Footer }
}

</script>
<style>
#app {

}
</style>