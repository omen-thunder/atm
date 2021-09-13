import axios from 'axios';

const AXIOS = axios.create()

AXIOS.interceptors.request.use(function (config) {
    const token = localStorage.getItem("authtoken");

    console.log("Current token before request ", token);

    if (token) {
        config.headers.Authorization = token;
    }
    return config;
});

export default AXIOS;