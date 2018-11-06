// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import VeeValidate from 'vee-validate'
import ko from 'vee-validate/dist/locale/ko.js'
import axios from 'axios'
import router from './comm/router'
import store from './comm/store'

import toast from 'vue2-toast'
import VueCarousel from 'vue-carousel'
import VueModal from 'vue2-modal'

import 'jquery'
import 'vue2-toast/lib/toast.css'

import './assets/css/reset.css'
import './assets/css/style.css'

import './assets/js/jquery.js'
import './assets/js/jockey.js'
import './comm/message.js'

import 'chart.js'
import 'hchs-vue-charts'

Vue.use(VeeValidate, {
  locale: 'ko',
  dictionary: {
    ko
  }
})

Vue.use(toast, {
  type: 'center',
  duration: 2000,
  wordWrap: true,
  width: '250px'
})

Vue.use(VueCarousel)

Vue.use(window.VueCharts)

Vue.use(VueModal)

Vue.config.debug = true
Vue.config.devtools = true
Vue.config.productionTip = false

axios.interceptors.request.use(function (config) {
  config.headers.AJAX = true
  return config
}, function (err) {
  return Promise.reject(err)
})
Vue.prototype.$http = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
