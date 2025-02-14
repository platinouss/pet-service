import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        isAuthenticated: false,
        email: null,
    }),
    actions: {
        login(email) {
            this.isAuthenticated = true;
            this.email = email;
        },
        logout() {
            this.isAuthenticated = false;
            this.email = null;
        }
    },
    persist: {
        key: 'auth',
        storage: localStorage,
        paths: ['email', 'isAuthenticated']
    }
});
