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
    accessToken: null,
    isLoggedIn: false,
    site: null,
    bankCode: null,
    cardCode: null
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
      localStorage.removeItem('site')
      state.accessToken = null
      state.isLoggedIn = false
      state.site = null
    }
  }
})

export default store
