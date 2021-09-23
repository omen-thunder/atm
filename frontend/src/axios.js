import axios from 'axios';

const AXIOS = axios.create()

AXIOS.interceptors.request.use( config => {
    const token = localStorage.getItem("authtoken");

    if (token) {
        config.headers.Authorization = token;
    }

    config.url

    return config;
});

export default AXIOS;