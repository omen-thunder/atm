import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import './css/index.css';
import axios from "axios";

axios.interceptors.request.use(function (config) {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = token;
    }
    return config;
});

createApp(App).use(store).use(router).mount("#app");
