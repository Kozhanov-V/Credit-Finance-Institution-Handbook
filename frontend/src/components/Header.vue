<template>
	<header>
			<div class="logo" style="background-image: url(img/headerLogo.svg);"></div>
			<div v-if="isLoggedIn">
					<h2>{{ username }}</h2>
			</div>
			<div v-else>
					<router-link to="/login">Login</router-link>
			</div>
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
	}
},
computed: {
	isLoggedIn() {
		return this.$store.getters.isLoggedIn;
	},
	username() {
		return this.$store.getters.getUsername;
	}
}
};
</script>

<style scoped>@import '@/assets/css/header.css';</style>
