import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'


//请求拦截器
axios.interceptors.request.use(config => {
    if(window.sessionStorage.getItem('tokenStr')){
        //请求携带token
        config.headers['Authorization'] = window.sessionStorage.getItem('tokenStr');
    }
    return config
},error => {
    console.log(error);
})



//响应拦截器
// success 成功调到后端接口之后，但是接口不允许进行该操作
axios.interceptors.response.use(success =>{
    if(success.status && success.status == 200){
        if(success.data.code == 500 || success.data.code == 401 || success.data.code == 403){
            Message.error({message:success.data.message});
            return;
        }

        if(success.data.message){
            Message.success({message:success.data.message})
        }
    }
    return success.data;
},error=>{
    //没有访问到接口
    if(error.response.code == 504 || error.response.code == 404){
        Message.error({message:'服务器崩了'});
    }else if(error.response.code == 403){
        Message.error({message:'权限不足'})
    }else if(error.response.code == 401){
        Message.error({message:'未登录'})
        router.replace('/')
    }else{
        if(error.response.data.message){
            Message.error({message:error.response.data.message})
        }else{
            Message.error({message:'未知错误'})
        }
    }
})




let base = '';

export const postRequest = (url,params) => {
    return axios({
        method:'post',
        url:`${base}${url}`,
        data:params
    })
}

export const getRequest = (url,params) => {
    return axios({
        method:'get',
        url:`${base}${url}`,
        data:params
    })
}