import { createApp } from 'vue'
import App from './App'
import { createRouter, createWebHistory } from 'vue-router'
import queryStudents from './components/showAll'
import addStudent from './components/addStudent'
import modifyStudent from './components/modifyStudent'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/showAll',
            name: 'showAll',
            component: queryStudents
        },
        {
            path: '/addStudent',
            name: 'addStudent',
            component: addStudent
        },
        {
            path: '/modifyStudent',
            name: 'modifyStudent',
            component: modifyStudent
        }
    ]
})

const app = createApp(App)
app.use(router)
app.mount('#app')
