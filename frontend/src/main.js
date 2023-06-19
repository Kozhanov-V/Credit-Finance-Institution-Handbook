import { createApp } from 'vue'
import { createStore } from 'vuex'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import Handbook from '@/views/Handbook.vue'
import Import from '@/views/Import.vue'
import Authenticate from '@/views/Authenticate.vue'
import NotFound from '@/views/NotFound.vue'
import createPersistedState from 'vuex-persistedstate'
const store = createStore({
    state: {
        token: null, // Переменная для хранения токена пользователя
    },
    getters: {
        isLoggedIn: (state) => !!state.token,
    },
    
    mutations: {
        setToken: (state, token) => {
            state.token = token; // Устанавливает токен пользователя
        },
    },
    
    actions: {
        login: async ({ commit }, { username, password }) => {
            // отправляет запрос на сервер, получает токен и сохраняет его в состоянии
            const token = await api.authenticate(username, password);
            commit('setToken', token);
        },
    },
    plugins: [createPersistedState()],
    
})

const router = createRouter({
    history: createWebHistory(),
    routes: [
      
        {path: '/', name: 'Handbook', component: Handbook},
        {path: '/import', name: 'Import', component: Import},
        {path: '/login', name: 'Authenticate', component: Authenticate},
        {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
     
    ]
})

router.beforeEach((to, from, next) => {
    const isAuthenticated = store.getters.isLoggedIn;  // Используем геттер из хранилища Vuex
    console.log(isAuthenticated); // ДАННУЮ СТРОЧКУ ДОБАВИЛ
    if (to.path === '/login' && isAuthenticated) {
        // Если пользователь уже аутентифицирован, то не пускаем его на страницу входа
        next('/');
    } else if (to.path !== '/login' && to.name !== 'NotFound' && !isAuthenticated) {
        // Если пользователь не аутентифицирован и пытается перейти на любую другую страницу кроме '/login' и 'NotFound', 
        // то перенаправляем его на страницу входа
        next('/login');
    } else {
        // Во всех остальных случаях разрешаем переход
        next();
    }
});


  


createApp(App)
    .use(router)
    .use(store)
    .mount('#app')
