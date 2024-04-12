import axios from "axios";
import { getToken, removeToken } from "@/composables/cookie"
import {showMessage } from "@/composables/utils"

//创建axios实例
const instance = axios.create({
    // baseURL: "http://localhost:8080/",  //基础URL
    baseURL: "/api",  //基础URL
    timeout: 10000, //请求超时时间
})
//添加请求拦截器
instance.interceptors.request.use(function(config){
    //请求发送前添加逻辑
    const token = getToken()
    console.log("统一添加请求头中的token： " + token);
    if(token){
        config.headers['Authorization'] = "Bearer " + token
    }
    return config;
},function(error){
    //请求错误后添加逻辑
    console.log("请求失败添加逻辑");
    return Promise.reject(error);
})
//添加响应拦截器
instance.interceptors.response.use(function(response){
    //对2xx范围内的状态码都会触发该逻辑
    return response.data;

}, function(error){
    //超出2xx范围内的状态码都会触发该函数
    console.log("响应异常拦截");
    let status = error.response.status;
    console.log("响应码"+status);
     // 状态码 401
    if(status == 401 ){
        //删除 cookie中的令牌
        removeToken()
        //刷新页面
        location.reload()
    }

    let errorMsg = error.response.data.message || "请求失败";
    showMessage(errorMsg, "error")
    return Promise.reject(error);


})

//暴露出去
export default instance;