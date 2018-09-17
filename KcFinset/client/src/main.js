// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App'
import VeeValidate from 'vee-validate'
import ko from 'vee-validate/dist/locale/ko.js'
import axios from 'axios'
import router from './comm/router'
import store from './comm/store'
import 'bootstrap'
import 'jquery'

import toast from 'vue2-toast'
import swiper from 'vue2-swiper'

import 'vue2-toast/lib/toast.css'

import './assets/css/bootstrap-datepicker-customize.css'
import './assets/css/bootstrap.customize.css'
import './assets/css/bootstrap-select.css'
import './assets/css/mobile.css'

import './comm/message.js'

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

Vue.use(swiper)

Vue.config.productionTip = false
Vue.prototype.$http = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
