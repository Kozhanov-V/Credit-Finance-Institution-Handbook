
<template>
    <Header/>
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
  }

</style>