<template>
  <el-row style="height: 100%;">
    <el-col :span="8" :offset="8" style="border-style: solid;border-color: #e6e6e6;margin-top: 5%">

      <el-row >
        <el-col :span="16" :offset="4">
          <el-form label-width="80px">
            <h1>Pet Store 应用</h1>
            <h3>登录界面</h3>
            <el-form-item label="账户地址">
              <el-input type="primary" v-model="address"></el-input>
            </el-form-item>
          </el-form>
        </el-col>

      </el-row>
      <el-row style="margin-bottom: 20px">
        <el-button type="primary" @click="login">登录</el-button>
        <el-button type="primary" @click="register">注册</el-button>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Login",
  data(){
    return {address:''}
  },
  methods: {
    login: function () {
      this.axios.get(`user/login?address=${this.address}`).then((response) => {
        if (response.data.result && response.data.result >0)
        {
          if (response.data.result > 0) {
            this.$cookies.set('address', this.address)
            this.$router.push('/home')
          }
        }else {
          alert("登录失败！请重新输入地址")
        }
      })
    },
    register: function () {
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>

</style>