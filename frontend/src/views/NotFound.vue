<template>
    <div class="not-found">
      <button @click="goHome">
        Back
      </button>
      <h1>Not Found</h1>
    </div>
  </template>
  <script>
  export default {
    methods: {
      goHome() {
        this.$router.push("/");
      }
    }
  }
  </script>
  