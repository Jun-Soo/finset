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
    component: Home
  },
  {
    path: '/intro',
    name: 'intro',
    component: FinsetIntro
  },
  {
    path: '/client/logout',
    name: 'logout',
    component: Logout
  },
  {
    path: '/error',
    name: 'error',
    component: ErrorPage
  },
  {
    path: '/main',
    name: 'main',
    component: FinsetMain
  },
  {
    path: '/proxy',
    name: 'proxy',
    component: ProxyPage
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
        path: 'certCode',
        alias: '/certCode',
        component: CertCode
      },
      {
        path: 'certFinger',
        alias: '/certFinger',
        component: CertFinger
      },
      {
        path: 'certCodeLogin',
        alias: '/certCodeLogin',
        component: CertCodeLogin
      },
      {
        path: 'certFingerLogin',
        alias: '/certFingerLogin',
        component: CertFingerLogin
      }
    ]
  },
  {
    path: '/credit',
    name: 'credit',
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
export default router
