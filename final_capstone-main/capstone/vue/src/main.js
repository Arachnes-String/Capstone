import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import '@/assets/main.css';


Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

const googleMapsApiKey = process.env.VUE_APP_GOOGLE_MAPS_API_KEY; 
console.log(googleMapsApiKey);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

