<template>
  <el-row style="height: 100%;">
    <el-col :span="8" :offset="8" style="border-style: solid;border-color: #e6e6e6;margin-top: 5%">

      <el-row>
        <el-col :span="16" :offset="4">
          <el-form>
            <h3>注册界面</h3>
            <el-form-item label="账户地址">
              <el-input v-model="address" type="primary"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-button type="primary" @click="register">注册</el-button>
        <el-button type="primary" @click="goback">返回</el-button>
      </el-row>
    </el-col>

  </el-row>

</template>

<script>
export default {
  data(){
    return {address:''}
  },

  name: "Register",
  methods: {
    register: function () {
      this.axios.get(`user/register?address=${this.address}`).then((response) => {
        if (response.data.result) {
          alert("注册成功！")
          this.$router.push('/login')
        }else {
          this.address = ''
          alert("注册失败!请重新输入")
        }
      })
    },
    goback: function () {
      this.address = ''
      this.$router.push('/login')
    }

  }
}
</script>

<style scoped>

</style>