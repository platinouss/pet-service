import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./router";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const options = {
    position: "top-right",
    timeout: 3000,
    closeOnClick: true,
    pauseOnFocusLoss: true,
    pauseOnHover: true,
    draggable: true,
    draggablePercent: 0.6,
    showCloseButtonOnHover: false,
    hideProgressBar: false,
    closeButton: "button",
    icon: true,
    rtl: false
};

const pinia = createPinia();
createApp(App).use(router).use(Toast, options).use(pinia)
    .mount('#app');

pinia.use(piniaPluginPersistedstate)
