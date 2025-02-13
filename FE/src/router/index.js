import { createRouter, createWebHistory } from 'vue-router'
import UserProfile from "../components/user/UserProfile.vue";
import MainPage from "../components/main/MainPage.vue";
import UserSignin from "../components/user/UserSignin.vue";
import UserSignup from "../components/user/UserSignup.vue";

const routes = [
    { path: '/', component: MainPage },
    { path: '/signin', component: UserSignin },
    { path: '/signup', component: UserSignup },
    { path: '/profile', component: UserProfile }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
})

export default router
