// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import axios from 'axios'
import router from './comm/router'
import store from './comm/store'
import 'bootstrap'

import toast from 'vue2-toast'
import swiper from 'vue2-swiper'

import 'vue2-toast/lib/toast.css'

import './assets/css/bootstrap-datepicker-customize.css'
import './assets/css/bootstrap.customize.css'
import './assets/css/bootstrap-select.css'
import './assets/css/mobile.css'

Vue.use(toast, {
  type: 'center',
  duration: 3000,
  wordWrap: true,
  width: '150px'
})

Vue.use(swiper)

Vue.config.productionTip = false
Vue.prototype.$http = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store
})
