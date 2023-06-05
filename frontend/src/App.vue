
<template>
    <Header/>

     <br>
      <RouterLink to="/">handbook</RouterLink><br>
      <RouterLink to="/import">import</RouterLink>
      <RouterView></RouterView>
</template>

<script>

import Header from './components/Header.vue';
export default{
  components:{Header}
}

</script>
<style>

  #app{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: #333333;
    background: #EDEDEF;
  }

</style>