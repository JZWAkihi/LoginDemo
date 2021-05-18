<template>
  <div>
    <el-form ref="loginForm" :rules="rules" :model="loginForm" class="loginContainer">
      <h3 class="loginTitle">系统登录</h3>
      <el-form-item prop="username">
        <!-- auto-complete="off"  禁止浏览器表单自动填充 -->
        <!-- placeholder  输入框占位文本 -->
        <el-input type="text" auto-complete="off" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" ></el-input>
      </el-form-item>

      <el-form-item prop="code">
        <el-input type="text" v-model="loginForm.code" size="normal" placeholder="点击图片更换验证码" auto-complete="off" style="width:250px;margin-right:5px"></el-input>
        <img :src="captchaUrl" @click="updataCaptcha" class="el-form-item__content">
      </el-form-item>

      <el-button type="primary" style="width:100%" @click="submitLogin">登录</el-button>

    </el-form>
  </div>
</template>

<script>
import {postRequest} from "../utils/api";
export default {
  name:"Login",
  data(){
    return{
      captchaUrl:'captcha?time' + new Date(),
      loginForm:{
          username:'',
          password:'',
          code:''
      },
      // rules  表单的验证  required   message：提示信息
      rules:{
        username:[{required:true,message:'请输入用户名',trigger:'blur'}],
        password:[{required:true,message:'请输入密码',trigger:'blur'}],
        code:[{required:true,message:'请输入验证码',trigger:'blur'}]
      }
    }
  },
  methods:{
    //点击更新图片
    updataCaptcha(){
      this.captchaUrl = 'captcha?time=' + new Date();
      },
    
    //登录
    submitLogin(){
      this.$refs.loginForm.validate((valid) => {
        if(valid){
          postRequest('/login',this.loginForm).then(resp => {
            console.log(resp);
            if(resp){
              const token = resp.object.tokenHead + resp.object.token;
              window.sessionStorage.setItem('tokenStr',token)
              this.$router.replace('/Home')
            }
          })
        }else{
          console.log('error submit');
          return false;
        }
      })
    }
  }
}
</script>

<style>
  .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background:#fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
  }

  .loginTitle{
        margin: 0px auto 40px auto;
        text-align: center;
  }

    .el-form-item__content{
        display: flex;
        align-items: center;
    }

</style>