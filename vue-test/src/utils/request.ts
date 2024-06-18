
import axios from "axios";
import { requestCommon } from "./common";
import { useUserStore } from "@/store/userStore";
import { pinia } from "@/plugins/pinia";

const request = axios.create({
    // baseURL: '/api',
    baseURL: requestCommon.baseURL,
    timeout: requestCommon.timeout,
});


request.defaults.withCredentials = true

// 添加请求拦截器
request.interceptors.request.use(function (config) {

    // 把token 注入进去
    const userStore = useUserStore(pinia);
    if (userStore.token) {
        config.headers.token = userStore.token
    }

    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
request.interceptors.response.use(function (response) {


    // 直接返回response.data

    return response.data;

}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});



export default request 