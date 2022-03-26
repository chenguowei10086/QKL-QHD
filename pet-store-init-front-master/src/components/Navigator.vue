<template>
  <el-row class="tac" style="height: 100%;">
    <el-menu
        class="el-menu-vertical-demo"
        style="height: 100%;" @select="handleSelect">
      <el-menu-item index="1" style="font-size: 20px">主页</el-menu-item>
      <el-menu-item index="2" style="font-size: 20px">已领养宠物页</el-menu-item>
    </el-menu>

  </el-row>

</template>

<script>
export default {
  name: "Navigator",
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
      if (key == '1') {
        this.$router.push('/home')
      }
      if (key == '2') {
        this.$router.push('/adoption')
      }
    },

    logout() {
      this.$cookies.remove('token')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>