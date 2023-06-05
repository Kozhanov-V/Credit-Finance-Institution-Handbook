import { createApp } from 'vue'

import App from './App.vue'

import { createRouter, createWebHistory } from 'vue-router'

import Handbook from '@/views/Handbook.vue'

import Import from '@/views/Import.vue'

import path from 'path';


const router = createRouter({
    history: createWebHistory(),
    routes:[
        {path:'/', name: 'Handbook', component: Handbook},
        {path:'/import', name: 'Import', component: Import}
    ]
})


createApp(App)
.use(router)
.mount('#app')
