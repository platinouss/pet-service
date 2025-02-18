import { createRouter, createWebHistory } from 'vue-router'
import UserProfile from "../components/user/UserProfile.vue";
import MainPage from "../components/main/MainPage.vue";
import UserSignin from "../components/user/UserSignin.vue";
import UserSignup from "../components/user/UserSignup.vue";
import Petsitter from "../components/pet-sitter/Petsitter.vue";
import PetsitterRegister from "../components/pet-sitter/PetsitterRegister.vue";
import PetSitterProfile from "../components/pet-sitter/PetsitterProfile.vue";
import CodeManagement from '../components/admin/CodeManagement.vue';
import Forbidden from '../components/common/Forbidden.vue'

const routes = [
    { path: '/', component: MainPage },
    { path: '/signin', component: UserSignin },
    { path: '/signup', component: UserSignup },
    { path: '/profile', component: UserProfile },
    { path: '/pet-sitter', component: Petsitter },
    { path: '/pet-sitter/register', component: PetsitterRegister },
    { path: '/pet-sitter/me', component: PetSitterProfile },
    { path: '/admin/code-management', component: CodeManagement },
    {
        path: '/forbidden',
        name: 'Forbidden',
        component: Forbidden
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
})

export default router
