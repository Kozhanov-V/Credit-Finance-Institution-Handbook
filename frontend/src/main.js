import { createApp } from 'vue'
import { createStore } from 'vuex'
import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios';
import jwtDecode from "jwt-decode";
import App from './App.vue'
import Handbook from '@/views/Handbook.vue'
import Import from '@/views/Import.vue'
import Authenticate from '@/views/Authenticate.vue'
import Favorites from '@/views/Favorites.vue'
import NotFound from '@/views/NotFound.vue'
import Logout from '@/views/Logout.vue'
import Home from '@/views/Home.vue'



import createPersistedState from 'vuex-persistedstate'
const store = createStore({
    state: {
        token: null, 
				username: null,
				roles:[],
				favoritesEntry:[],
    },
    getters: {
			isLoggedIn: (state) => !!state.token,
			getUsername: (state) => state.username,
			getRoles: (state) => state.roles,
			getFavorites: (state) => state.favoritesEntry,
    },
    
    mutations: {
        setToken: (state, token) => {
					state.token = token;

					const decodedToken = jwtDecode(token);
					// console.log(decodedToken.username);
					// console.log(decodedToken.roles);
					 console.log(decodedToken.favorites);
					 state.username = decodedToken.username;
					 state.roles = decodedToken.roles;
					 state.favoritesEntry = decodedToken.favorites.map(item => ({
						bic: item.bic,
						nameParticipant: item.participantInfo.nameParticipant,
						registrationNumber: item.participantInfo.registrationNumber,
						countryCode: item.participantInfo.countryCode,
						regionCode: item.participantInfo.regionCode,
						index: item.participantInfo.index,
						typeLocation: item.participantInfo.typeLocation,
						nameLocation: item.participantInfo.nameLocation,
						address: item.participantInfo.address,
						parentBIC: item.participantInfo.parentBIC,
						dateIn: item.participantInfo.dateIn,
						dateOut: item.participantInfo.dateOut,
						participantType: item.participantInfo?.participantType?.code,
						availableTransferService: item.participantInfo?.availableTransferService?.code,
						exchangeParticipant: item.participantInfo?.exchangeParticipant?.code,
						uid: item.participantInfo.uid,
						participantStatus: item.participantInfo?.participantStatus?.code,
						accounts: item.accounts,
						editMode: false,
					 }));
			
        },
        clearToken: (state) => {
            state.token = null;
						state.username=null;
						state.roles=[];
						state.favoritesEntry=[];
        },
				 addFavoritesEntry: async (state, bicDirectoryEntry) => {
           state.favoritesEntry.push(bicDirectoryEntry);
        },
				
				deleteFavoritesEntry: async (state, bicDirectoryEntry) => {
				  state.favoritesEntry = state.favoritesEntry.filter(entry => entry.bic !== bicDirectoryEntry.bic);
					console.log(state.favoritesEntry)
        },
    },
    
    actions: {
        login: async ({ commit }, { username, password }) => {
            // отправляет запрос на сервер, получает токен и сохраняет его в состоянии
             await axios.post('http://localhost:8080/authenticate', {
                username,
                password,
    
            }).then(response => {
							console.log(response.data);
                localStorage.setItem('token', response.data.token); 
                commit('setToken', response.data.token);
                
            });
        },
        logout: ({ commit }) => {

            return new Promise((resolve) => {
                commit('clearToken');
                localStorage.removeItem('token');
                localStorage.removeItem('username');
                localStorage.removeItem('roles');
                localStorage.removeItem('favoritesEntry');
                resolve();
                router.push("/")
            });
        },
				addFavoritesEntry: async({state,commit}, bicDirectoryEntry)=>{ 
					await axios.post(`http://localhost:8080/api/favorites/add/${bicDirectoryEntry.bic}`, state.username, {
							headers: {
									'Authorization': 'Bearer ' + state.token
							}
					}).then(response => {
							commit('addFavoritesEntry',bicDirectoryEntry)
					});
				},

				deleteFavoritesEntry: async({state,commit}, bicDirectoryEntry)=>{ 
					await axios.post(`http://localhost:8080/api/favorites/delete/${bicDirectoryEntry.bic}`, state.username, {
							headers: {
									'Authorization': 'Bearer ' + state.token
							}
					}).then(response => {
							commit('deleteFavoritesEntry',bicDirectoryEntry)
					});
				},
				
    },

    plugins: [createPersistedState()],
    
})

const router = createRouter({
    history: createWebHistory(),
    routes: [
      
        {path: '/', name: 'Home', component: Home},
        {path: '/import', name: 'Import', component: Import},
        {path: '/favorites', name: 'Favorites', component: Favorites},
        {path: '/login', name: 'Authenticate', component: Authenticate},
        {path: '/logout', name: 'Logout', component: Logout},
        {path: '/handbook', name: 'Handbook', component: Handbook},
        {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
     
     
    ]
})

router.beforeEach((to, from, next) => {
    const isAuthenticated = store.getters.isLoggedIn;  // Используем геттер из хранилища Vuex
    if (to.path === '/login' && isAuthenticated) {
        // Если пользователь уже аутентифицирован, то не пускаем его на страницу входа
        next('/');
    } else {
        // Во всех остальных случаях разрешаем переход
        next();
    }
});


  


createApp(App)
    .use(router)
    .use(store)
    .mount('#app')
