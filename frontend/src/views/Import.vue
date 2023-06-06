<template>
    <div class="import">
        <button @click="goBack">
      Back
    </button>
        <h1>Import</h1>
    </div>
</template>
<script>
  export default {
    methods: {
      goBack() {
        this.$router.go(-1)
      }
    }
  }
</script>