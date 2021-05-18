<template>
  <div>
      <el-row>
        <el-button type="info" @click="getInfo">点击获取当前登录信息</el-button>
      </el-row>


      当前登录用户:  
      <el-form ref="loginForm">

        <el-form-item>
          <el-input type="text" v-model="loginForm.name" ></el-input>
        </el-form-item>
      </el-form>
  </div>
</template>

<script>
import {getRequest} from "../utils/api";
export default {
  data(){
    return{
      loginForm:{
        name:''
      }
    }
  },
  methods:{
    //点击得到信息
      getInfo(){
        getRequest('/info').then(resp => {
          console.log(resp.name);
          this.loginForm.name = resp.name;
          console.log(this.loginForm.name);
        })
      }
  }
}
</script>

<style>

</style>