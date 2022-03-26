import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router'
import routes from './router'
import axios from "axios";
import VueAxios from 'vue-axios'
import App from './App.vue'
import VueCookies from 'vue-cookies'
axios.defaults.baseURL='/api'
// axios.defaults.baseURL='http://127.0.0.1:5003'
Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(VueAxios, axios);
Vue.use(VueCookies)
Vue.config.productionTip = false

const router = new VueRouter({
  routes
})

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')
