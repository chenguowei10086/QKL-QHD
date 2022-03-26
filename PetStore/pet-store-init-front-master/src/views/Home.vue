<template>
  <el-row style="height: 100%;">
    <el-row style="height: 10%;border-bottom-style: solid;border-bottom-color: #e6e6e6">
      <el-col :span="4" style="height: 100%">
        <h1>PetStore 应用</h1>
      </el-col>
      <el-button type="text" size="medium" style="position:absolute;right:10%;bottom:0px;font-size: 20px" @click="logout">登出</el-button>
    </el-row>
    <el-row style="height: 90%">
      <el-col :span="4" style="height: 100%">
        <navigator></navigator>
      </el-col>
      <el-col :span="20" style="height: 100%">
        <h1>宠物领养列表</h1>
        <el-row :gutter="20">
          <el-col :span="5" :offset="1">
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/哈士奇.png">
              <el-row>
                <span>宠物名称：小哈</span>
              </el-row>
              <el-row>
                <span>宠物种类：哈士奇</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[0]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5" >
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/博美.png">
              <el-row>
                <span>宠物名称：小美</span>
              </el-row>
              <el-row>
                <span>宠物种类：博美</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[1]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5" >
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/拉布拉多.png">
              <el-row>
                <span>宠物名称：小多</span>
              </el-row>
              <el-row>
                <span>宠物种类：拉布拉多</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[2]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5" >
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/柴犬.png">
              <el-row>
                <span>宠物名称：小柴</span>
              </el-row>
              <el-row>
                <span>宠物种类：柴犬</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[3]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 2%">
          <el-col :span="5" :offset="1">
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/法斗.png">
              <el-row>
                <span>宠物名称：小法</span>
              </el-row>
              <el-row>
                <span>宠物种类：法斗</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[4]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/萨摩.png">
              <el-row>
                <span>宠物名称：小白</span>
              </el-row>
              <el-row>
                <span>宠物种类：萨摩</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[5]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5" >
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/边牧.png">
              <el-row>
                <span>宠物名称：小牧</span>
              </el-row>
              <el-row>
                <span>宠物种类：边牧</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[6]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="5" >
            <el-card :body-style="{ padding: '0px' }">
              <img height="200" width="250" src="../assets/金毛.png">
              <el-row>
                <span>宠物名称：小黄</span>
              </el-row>
              <el-row>
                <span>宠物种类：金毛</span>
              </el-row>
              <el-row>
                <el-button type="primary" :disabled="adoptList[7]" @click="adopt(0)">领养</el-button>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </el-row>

</template>

<script>
import Navigator from "@/components/Navigator";
// import config from '@/config'
export default {
  name: "Home",
  data() {
    return {
      adoptList: [0,0,0,0,0,0,0,0,0]
    }
  },
  components: {
    Navigator
  },
  methods: {
    logout: function () {
      this.$cookies.remove("address")
      this.$router.push('/login')
    },

    addressExist: function() {
      if (!this.$cookies.get("address")) {
        this.$router.push('/login')
      }
    },
    listPetAdoption: function () {
      let address = this.$cookies.get("address");
      this.axios.get(`myAdoption/listPetAdoption?address=${address}`).then((response) => {
        if (response.data.code == 200) {
          this.adoptList = response.data.result;
          console.log(this.adoptList);
        }
      })
    },
    adopt: function (petId) {
      let address = this.$cookies.get("address");
      this.axios.get(`myAdoption/listPetAdoption?address=${address}&petId=${petId}`).then((response) => {
        if (response.data.code == 200) {
          alert("领养成功！")
          this.listPetAdoption();
        }
      })
    }
  },
  created() {
    this.addressExist()
    this.listPetAdoption()

  }
}
</script>

<style scoped>

</style>