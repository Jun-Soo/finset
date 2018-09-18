import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/main/Home'
import FinsetIntro from '@/components/main/Intro'
import FinsetMain from '@/components/main/FinsetMain'
import Logout from '@/components/member/Logout'

import MemberHome from '@/components/member/Home'
import CertStep1 from '@/components/member/CertStep1'
import CertStep2 from '@/components/member/CertStep2'
import CertCode from '@/components/member/CertCode'
import CertFinger from '@/components/member/CertFinger'
import CertCodeLogin from '@/components/member/CertCodeLogin'
import CertFingerLogin from '@/components/member/CertFingerLogin'

import CreditHome from '@/components/credit/Home'
import CreditMain from '@/components/credit/Main'

import ErrorPage from '@/components/common/Error'
import ProxyPage from '@/components/common/ProxyPage'

Vue.use(Router)

export const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home,
    meta: { allowPath: true }
  },
  {
    path: '/intro',
    name: 'intro',
    component: FinsetIntro,
    meta: { allowPath: true }
  },
  {
    path: '/client/logout',
    name: 'logout',
    component: Logout,
    meta: { allowPath: true }
  },
  {
    path: '/error',
    name: 'error',
    component: ErrorPage,
    meta: { allowPath: true }
  },
  {
    path: '/main',
    name: 'main',
    component: FinsetMain,
    meta: { allowPath: true, requiresAuth: true }
  },
  {
    path: '/proxy',
    name: 'proxy',
    component: ProxyPage,
    meta: { allowPath: true }
  },
  {
    path: '/member',
    name: 'member',
    component: MemberHome,
    children: [
      {
        path: 'certStep1',
        alias: '/certStep1',
        component: CertStep1,
        meta: { allowPath: true }
      },
      {
        path: 'certStep2',
        alias: '/certStep2',
        component: CertStep2,
        meta: { allowPath: true }
      },
      {
        path: 'certCode',
        alias: '/certCode',
        component: CertCode,
        meta: { allowPath: true }
      },
      {
        path: 'certFinger',
        alias: '/certFinger',
        component: CertFinger,
        meta: { allowPath: true }
      },
      {
        path: 'certCodeLogin',
        alias: '/certCodeLogin',
        component: CertCodeLogin,
        meta: { allowPath: true }
      },
      {
        path: 'certFingerLogin',
        alias: '/certFingerLogin',
        component: CertFingerLogin,
        meta: { allowPath: true }
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
        meta: { allowPath: true, requiresAuth: true }
      }
    ]
  }
]

const router = new Router({routes, mode: 'history'})

router.beforeEach((to, from, next) => {
  const hp = localStorage.getItem('hp')
  if (to.meta.allowPath) {
    if (to.meta.requiresAuth) {
      const accessToken = localStorage.getItem('accessToken')
      if (!accessToken) {
        alert('잘못된 접근입니다.')
        setTimeout(function () {
          next('/home?hp=' + hp)
        }, 1000)
      } else {
        next()
      }
    } else {
      next()
    }
  } else {
    alert('잘못된 접근입니다.')
    setTimeout(function () {
      next('/home?hp=' + hp)
    }, 1000)
  }
})

export default router
