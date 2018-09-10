import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/main/Home'
import FinsetIntro from '@/components/main/Intro'
import FinsetMain from '@/components/main/FinsetMain'

import MemberHome from '@/components/member/Home'
import CertStep1 from '@/components/member/CertStep1'
import CertStep2 from '@/components/member/CertStep2'

import CreditHome from '@/components/credit/Home'
import CreditMain from '@/components/credit/Main'

Vue.use(Router)

export const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/intro',
    name: 'intro',
    component: FinsetIntro
  },
  {
    path: '/main',
    name: 'main',
    component: FinsetMain
  },
  {
    path: '/member',
    name: 'member',
    component: MemberHome,
    children: [
      {
        path: 'certStep1',
        alias: '/certStep1',
        component: CertStep1
      },
      {
        path: 'certStep2',
        alias: '/certStep2',
        component: CertStep2
      }
    ]
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

const router = new Router({routes, mode: 'history'})

// router.beforeEach((to, from, next) => {
//   if (to.meta.requiresAuth) {
//     const authUser = JSON.parse(window.localStorage.getItem('lbUser'))
//     if (!authUser || !authUser.token) {
//       next({name: 'login'})
//     } else {
//       console.log('Im in admin')
//       next('/admin')
//     }
//   } else {
//     next()
//   }
// })

export default router
