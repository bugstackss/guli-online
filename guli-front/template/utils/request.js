import axios from "axios";
import { Message, MessageBox } from "element-ui";
import cookie from "js-cookie";

// 创建axios实例
const service = axios.create({
  baseURL: "http://172.16.102.135:9001",
  timeout: 20000,
});

// http request 拦截器
service.interceptors.request.use(
  (config) => {
    //debugger
    //判断cookie中是否有名称叫 guli_token的数据
    if (cookie.get("guli_token")) {
      //把获取到的cookie值放到header中
      config.headers["token"] = cookie.get("guli_token");
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

// http response 拦截器
service.interceptors.response.use(
  (response) => {
    //debugger
    if (response.data.code == 28004) {
      console.log("response.data.resultCode是28004");
      // 返回 错误代码-1 清除ticket信息并跳转到登录页面
      //debugger
      window.location.href = "/login";
      return;
    } else {
      if (response.data.code !== 20000) {
        //25000：订单支付中，不做任何提示
        if (response.data.code != 25000) {
          Message({
            message: response.data.message || "error",
            type: "error",
            duration: 5 * 1000,
          });
        }
      } else {
        return response;
      }
    }
  },
  (error) => {
    return Promise.reject(error.response); // 返回接口返回的错误信息
  }
);

export default service;
