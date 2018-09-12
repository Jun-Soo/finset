import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/main/Home'
import FinsetIntro from '@/components/main/Intro'
import FinsetMain from '@/components/main/FinsetMain'

import MemberHome from '@/components/member/Home'
import CertStep1 from '@/components/member/CertStep1'
import CertStep2 from '@/components/member/CertStep2'
import CertCodeConfirm from '@/components/member/CertCodeConfirm'

import CreditHome from '@/components/credit/Home'
import CreditMain from '@/components/credit/Main'

Vue.use(Router)

export const routes = [
  {
    path: '/home',
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
    component: FinsetMain,
    meta: { requiresAuth: true }
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
      },
      {
        path: 'certCodeConfirm',
        alias: '/certCodeConfirm',
        component: CertCodeConfirm
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
        component: CreditMain,
        meta: { requiresAuth: true }
      }
    ]
  }
]

const router = new Router({routes, mode: 'history'})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const accessToken = localStorage.getItem('accessToken')
    if (!accessToken) {
      const hp = localStorage.getItem('hp')
      next('/home?hp=' + hp)
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
