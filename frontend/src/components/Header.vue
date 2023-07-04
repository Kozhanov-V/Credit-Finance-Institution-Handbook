<template>
    <header>
        <div class="logo" style="background-image: url(img/headerLogo.svg);">
            
        </div>
        <h2 v-if="windowWidth > 600">USERNAME</h2>
    </header>
</template>
<script>
export default {
  data() {
    return {
      windowWidth: 0,
    };
  },
  mounted() {
    this.getWindowWidth();
    window.addEventListener('resize', this.getWindowWidth);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.getWindowWidth);
  },
  methods: {
    getWindowWidth(event) {
      this.windowWidth = document.documentElement.clientWidth;
    },
  },
};
</script>
<style scoped>@import '@/assets/css/header.css';</style>