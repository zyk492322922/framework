import axios from "axios"
import Router from "koa-router";
const router = new Router();
import errorMsg from "../utils/error-msg"
import { BASE_URL } from '../config'
// 创建axios实例
const customAxios = axios.create({
    baseURL: BASE_URL,
    timeout: 10000
});
// axios实例默认配置
// customAxios.defaults.headers = {
//     "Content-Type": "application/json"
// }
customAxios.defaults.headers.post['Content-Type'] = 'application/json';
customAxios.defaults.transformRequest = data => {
    return JSON.stringify(data)
}
customAxios.interceptors.request.use(function (config) {
    // Do something before request is sent
    // console.log(config)
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });
// 返回状态拦截，进行状态的集中判断
axios.interceptors.response.use(
    response => {
        const res = response.data;
        console.log(response.headers)
        if (res.status == 1) {
            return Promise.resolve(res)
        } else {
            // 内部错误码处理
            if (res.status == 99) {
                errorMsg(res.statusDesc || '未登录，请重新登录！')
                router.redirect('/login',"/")
            } if (res.status == 999) {
                errorMsg(res.statusDesc || '登录已过期，请重新登录！')
                router.redirect('/login',"/")
            } else {
                // 默认的错误提示
                errorMsg(res.statusDesc || '网络异常，请稍后重试！')
            }
            return Promise.reject(res);
        }
    },
    error => {
        if (/timeout\sof\s\d+ms\sexceeded/.test(error.message)) {
            // 超时
            errorMsg('网络出了点问题，请稍后重试！')
        }
        if (error.response) {
            // http状态码判断
            switch (error.response.status) {
                // http status handler
                case 404:
                    errorMsg('请求的资源不存在！')
                    break
                case 500:
                    errorMsg('内部错误，请稍后重试！')
                    break
                case 503:
                    errorMsg('服务器正在维护，请稍等！')
                    break
            }
        }
        return Promise.reject(error.response)
    }
)

// 处理get请求
export const get = (url, params, config = {}) => customAxios.get(url, { ...config, params })
// 处理delete请求，为了防止和关键词delete冲突，方法名定义为deletes
// const deletes = (url, params, config = {}) => customAxios.delete(url, { ...config, params })
// 处理post请求
export const post = (url, params, config = {}) => customAxios.post(url, params, config)
// 处理put请求
// const put = (url, params, config = {}) => customAxios.put(url, params, config)