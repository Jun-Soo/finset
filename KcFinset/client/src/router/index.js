import Vue from 'vue'
import Router from 'vue-router'
import FinsetMain from '@/components/main/FinsetMain'

import CreditMain from '@/components/credit/CreditMain'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'FinsetMain',
      component: FinsetMain
    },
    {
      path: '/creditMain',
      component: CreditMain
    }
  ]
})
