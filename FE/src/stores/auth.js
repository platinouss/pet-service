import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
    const email = ref(localStorage.getItem('userEmail') || '');
    const isAuthenticated = ref(!!localStorage.getItem('userEmail'));

    const login = (userEmail) => {
        email.value = userEmail;
        isAuthenticated.value = true;
        localStorage.setItem('userEmail', userEmail);
    };

    const logout = () => {
        email.value = '';
        isAuthenticated.value = false;
        localStorage.removeItem('userEmail');
    };

    return {
        email,
        isAuthenticated,
        login,
        logout
    };
});
