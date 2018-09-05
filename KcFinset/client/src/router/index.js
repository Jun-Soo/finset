import Vue from 'vue'
import Router from 'vue-router'
import FinsetMain from '@/components/main/FinsetMain'

import CreditHome from '@/components/credit/CreditHome'
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
      path: '/credit',
      component: CreditHome,
      children: [
        {
          path: 'main',
          alias: '/main',
          component: CreditMain
        }
      ]
    }
  ]
})
