<template>
  <nav>
    <div class="logo" style="background-image: url(img/logo.svg);"></div>
    <div class="navigation-elements" :style="{'--top': `${(hoverItem !== null ? hoverItem : activeItem)* 2.9 + 0.85}`}">
      <div class="active-indicator"></div>
			<div  v-for="(item, index) in items" v-show="item.access">
				<router-link :to= item.url>
					<button 
        :key="index"
        class="element-item" 
        :id="item.id"
        :style="{backgroundImage: `url(${item.img})`}"
        @click="activeItem = index"
        @mouseover="hoverItem = index"
        @mouseout="hoverItem = null">
      </button>
				</router-link>
				
			</div>
			<div id="logoutBtn" v-if="isLoggedIn">
      <router-link to="/logout">
        <button 
          
          class="element-item" 
          style="background-image: url('img/logout.png')"
        ></button>
      </router-link>
    </div>
    </div>
		
  </nav>
</template>


<script>
export default {
  data() {
    return {
      activeItem: null,
      hoverItem: null, 
    }
  },
	computed: {
    items() {
      return [
        {id: 'handbookBtn', img: 'img/handbook.png', url: "/handbook", access: true},
        
        {id: 'favoritesBtn', img: 'img/favorites.png', url: "/favorites", access: this.isUser},
				{id: 'importBtn', img: 'img/import.png', url: "/import", access: this.isAdmin}, 
      ];
    },
    isAdmin() {
		
      const roles = this.$store.getters.getRoles;
      return roles ? roles.map(e => e.name).includes('ROLE_ADMIN') : false;
    },
    isUser() {
      const roles = this.$store.getters.getRoles;
      return roles ? roles.map(e => e.name).includes('ROLE_USER') : false;
    },
		isLoggedIn() {
		return this.$store.getters.isLoggedIn;
	},
}
}
</script>
<style scoped>@import '@/assets/css/navigation.css';</style>