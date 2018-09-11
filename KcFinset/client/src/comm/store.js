import Vue from 'vue'
import Vuex from 'vuex'
// import Constant from './../assets/js/constant'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    title: 'FINSET',
    user: {
      noPerson: '',
      hp: '',
      cntFailPwd: 0,
      cntFailFinger: 0,
      ynFingerprint: '',
      isEventPush: false
    },
    returnUrl: '',
    accesToken: '',
    isLoggedIn: !!localStorage.getItem('lbUser')
  },
  mutations: {
  }
})

export default store
