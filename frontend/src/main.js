import { createApp } from 'vue'
import { createStore } from 'vuex'
import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios';
import jwtDecode from "jwt-decode";
import App from './App.vue'
import Handbook from '@/views/Handbook.vue'
import Import from '@/views/Import.vue'
import Authenticate from '@/views/Authenticate.vue'
import NotFound from '@/views/NotFound.vue'
import Logout from '@/views/Logout.vue'
import Home from '@/views/Home.vue'



import createPersistedState from 'vuex-persistedstate'
const store = createStore({
    state: {
        token: null, // Переменная для хранения токена пользователя
				username: null,
				roles:[],
    },
    getters: {
			isLoggedIn: (state) => !!state.token,
			getUsername: (state) => state.username,
			getRoles: (state) => state.roles,
    },
    
    mutations: {
        setToken: (state, token) => {
					state.token = token;

					const decodedToken = jwtDecode(token);
					console.log(decodedToken);
					state.username = decodedToken.username;
					state.roles = decodedToken.roles;
        },
        clearToken: (state) => {
            state.token = null;
						state.username=null;
						state.roles=[];
        },
    },
    
    actions: {
        login: async ({ commit }, { username, password }) => {
            // отправляет запрос на сервер, получает токен и сохраняет его в состоянии
             await axios.post('http://localhost:8080/authenticate', {
                username,
                password,
    
            }).then(response => {
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
                resolve();
                router.push("/")
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
