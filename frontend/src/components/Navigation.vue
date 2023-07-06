<template>
  <nav>
    <div class="logo" style="background-image: url(img/logo.svg);"></div>
    <div class="navigation-elements" :style="{'--top': `${(hoverItem !== null ? hoverItem : activeItem)* 2.9 + 0.85}`}">
      <!-- We added 19 to account for initial top offset of .navigation-elements -->
      <div class="active-indicator"></div>
			<div  v-for="(item, index) in items" >
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
			
    </div>
  </nav>
</template>


<script>
export default {
  data() {
    return {
      activeItem: null,
      hoverItem: null, // Add this new data property
      items: [
        {id: 'handbookBtn', img: 'img/handbook.png', url: "/handbook" },
        {id: 'importBtn', img: 'img/import.png', url: "/import"}, 
        {id: 'favoritesBtn', img: 'img/favorites.png', url: "/" },
        // Add more items as required
      ]
    }
  }
}
</script>
<style scoped>@import '@/assets/css/navigation.css';</style>