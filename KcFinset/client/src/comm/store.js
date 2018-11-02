import Vue from 'vue'
import Vuex from 'vuex'
// import Constant from './../assets/js/constant'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    title: 'FINSET',
    header: {
      type: 'sub',
      active: ''
    },
    user: {
      noPerson: '',
      nmPerson: '',
      hp: '',
      cntFailPwd: 0,
      cntFailFinger: 0,
      ynFingerprint: 'N',
      dt_basic: '',
      isEventPush: false,
      authToken: '',
      noManageInfo: ''
    },
    returnUrl: '',
    proxyUrl: '',
    accessToken: null,
    isLoggedIn: false,
    site: null,
    bankCode: '',
    cardCode: '',
    etcCode: '',
    isLoading: false,
    isScrap: true
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
    },
    SET_NO_MANAGE_INFO (state, noManageInfo) {
      state.user.noManageInfo = noManageInfo
    }
  }
})

export default store
