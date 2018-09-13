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
    accessToken: '',
    isLoggedIn: false
  },
  mutations: {
    LOGIN (state, accessToken) {
      localStorage.setItem('accessToken', accessToken)
      state.accessToken = accessToken
      state.isLoggedIn = true
    },
    LOGOUT (state) {
      localStorage.removeItem('accessToken')
      state.accessToken = null
      state.isLoggedIn = false
    }
  }
})

export default store
