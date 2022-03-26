<template>
  <el-row class="adoption" style="height: 100%">
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
        <h1>已领养宠物页</h1>
        <el-row>
          <el-col :span="20" :offset="2">
            <el-table :data="adoptionList" style="font-size: 20px">
              <el-table-column prop="id" label="宠物编号" ></el-table-column>
              <el-table-column label="宠物图片" >
                <template slot-scope="scope">
                  <el-image style="width: 100px; height: 100px"  :src="scope.row.img" :preview-src-list="scope.row.picList"></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="name" label="宠物名称"></el-table-column>
              <el-table-column prop="type" label="宠物种类"></el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-row>
</template>

<script>
import Navigator from "@/components/Navigator";
import pic0 from '../assets/哈士奇.png';
import pic1 from '../assets/博美.png';
import pic2 from '../assets/拉布拉多.png';
import pic3 from '../assets/柴犬.png';
import pic4 from '../assets/法斗.png';
import pic5 from '../assets/萨摩.png';
import pic6 from '../assets/边牧.png';
import pic7 from '../assets/金毛.png';
export default {
  name: "Adoption",
  components: {
    Navigator
  },
  data() {
    return {

      defaultList:[
        {
          id:0,
          img: pic0,
          name: "小哈",
          type: "哈士奇",
          picList: [
            pic0
          ],
        },{
          id:1,
          img: pic1,
          name: "小美",
          type: "博美",
          picList: [
            pic1
          ],
        },
        {
          id:2,
          img: pic2,
          name: "小多",
          type: "拉布拉多",
          picList: [
            pic2
          ],
        },
        {
          id:3,
          img: pic3,
          name: "小柴",
          type: "柴犬",
          picList: [
            pic3
          ],
        },
        {
          id:4,
          img: pic4,
          name: "小法",
          type: "法斗",
          picList: [
            pic4
          ],
        },
        {
          id:5,
          img: pic5,
          name: "小白",
          type: "萨摩",
          picList: [
            pic5
          ],
        },
        {
          id:6,
          img: pic6,
          name: "小牧",
          type: "边牧",
          picList: [
            pic6
          ],
        },
        {
          id:7,
          img: pic7,
          name: "小黄",
          type: "金毛",
          picList: [
            pic7
          ],
        },
      ],
      adoptionList: []
    }
  },
  methods: {
    logout: function () {
      this.$cookies.remove("address")
      this.$router.push('/login')
    },
    addressExist: function (){
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