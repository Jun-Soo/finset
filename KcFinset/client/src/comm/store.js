import Vue from 'vue'
import Vuex from 'vuex'
// import Constant from './../assets/js/constant'
import Common from './../assets/js/common.js'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    title: 'FINSET',
    header: {
      type: '',
      active: '',
      async: false,
      backPath: '',
      fromPath: ''
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
      dtConsume: null
    },
    scListParam: {
      query1: undefined,
      query2: undefined,
      query3: undefined,
      query4: undefined,
      query5: undefined,
      query6: undefined
    },
    returnUrl: '',
    proxyUrl: '',
    proxyBackUrl: '',
    accessToken: null,
    isLoggedIn: false,
    site: null,
    bankCode: '',
    cardCode: '',
    etcCode: '',
    isLoading: false,
    isScrap: true, // 스크래핑 여부
    loginPath: '/check/j_spring_security_check',
    linkUrl: '',
    ynReload: 'N',
    uuid: '',
    ajaxState: true
  },
  mutations: {
    INIT (state, data) {
      state.user.noPerson = data.no_person
      state.user.nmPerson = data.nm_person
      state.user.cntFailPwd = Number(data.cnt_fail_pwd)
      state.user.cntFailFinger = Number(data.cnt_fail_finger)
      state.user.ynFingerprint = Common.nvl(data.yn_fingerprint, 'N')
      state.user.dt_basic = data.dt_basic

      state.bankCode = data.bank_code
      state.cardCode = data.card_code
      state.site = data.site
      if (data.rtnPath === '/member/certFingerLogin') {
        state.user.authToken = data.authToken
      }
      if (data.site !== 'LOCAL') { // dev, real login path
        state.loginPath = '/j_spring_security_check'
      }
      localStorage.setItem('site', data.site)
    },
    LOGIN (state, data) {
      localStorage.setItem('accessToken', data.accessToken)
      state.accessToken = data.accessToken
      state.nmPerson = data.nmPerson
      state.isLoggedIn = true
      state.header.type = 'sub'
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
    },
    AJAX_INIT (state) {
      state.ajaxState = true
    },
    AJAX_ERROR (state) {
      state.ajaxState = false
    }
  },
  getters: {
    getError: state => {
      return state.ajaxState
    }
  }
})

export default store
