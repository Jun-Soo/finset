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
      isEventPush: false
    },
    accesToken: '',
    isLoggedIn: !!localStorage.getItem('lbUser')
  },
  mutations: {
  }
})

export default store
