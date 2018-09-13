import Vue from 'vue'
import Vuex from 'vuex'
// import Constant from './../assets/js/constant'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    title: 'FINSET',
    user: {
      noPerson: '',
      nmPerson: '',
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
    LOGIN (state, data) {
      localStorage.setItem('accessToken', data.accessToken)
      state.accessToken = data.accessToken
      state.nmPerson = data.nmPerson
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
