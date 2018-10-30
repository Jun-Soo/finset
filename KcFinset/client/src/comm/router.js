import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/main/Home'
import FinsetIntro from '@/components/main/Intro'
import FinsetMain from '@/components/main/FinsetMain'
import Logout from '@/components/member/Logout'

import MemberHome from '@/components/member/Home'
import MemberCertStep1 from '@/components/member/CertStep1'
import MemberTerms1 from '@/components/member/Terms1'
import MemberTerms2 from '@/components/member/Terms2'
import MemberTerms3 from '@/components/member/Terms3'
import MemberTerms4 from '@/components/member/Terms4'
import MemberTerms5 from '@/components/member/Terms5'
import MemberTerms6 from '@/components/member/Terms6'
import MemberTerms7 from '@/components/member/Terms7'
import MemberTerms8 from '@/components/member/Terms8'
import MemberTerms9 from '@/components/member/Terms9'
import MemberTerms10 from '@/components/member/Terms10'
import MemberCertStep2 from '@/components/member/CertStep2'
import MemberCertCode from '@/components/member/CertCode'
import MemberCertFinger from '@/components/member/CertFinger'
import MemberCertCodeLogin from '@/components/member/CertCodeLogin'
import MemberCertFingerLogin from '@/components/member/CertFingerLogin'

import ScrapHome from '@/components/scrap/Home'
import ScrapCertStep from '@/components/scrap/CertStep'
// import ScrapFcLink from '@/components/scrap/FcLink'
// import ScrapSelectFcLink from '@/components/scrap/SelectFcLink'
// import ScrapResultFcLink from '@/components/scrap/ResultFcLink'
import ScrapCtrlFcLink from '@/components/scrap/CtrlFcLink'
import ScrapLoading from '@/components/scrap/Loading'
import ScrapRegFcLink from '@/components/scrap/RegFcLink'

import MemoHome from '@/components/memo/Home'
import MemoRegister from '@/components/memo/Register'
import MemoList from '@/components/memo/List'
// import MemoDetail from '@/components/memo/Detail'
import MemoMain from '@/components/memo/Main'
import MemoCreate from '@/components/memo/Create'

import NewsHome from '@/components/news/Home'
import NewsMain from '@/components/news/Main'
// import NewsDetail from '@/components/news/Detail'

import CreditHome from '@/components/credit/Home'
import CreditMain from '@/components/credit/Main'
import CreditDetail from '@/components/credit/Detail'
import CreditCardInfo from '@/components/credit/CardInfo'
import CreditLoanInfo from '@/components/credit/LoanInfo'
import CreditOverdueInfo from '@/components/credit/OverdueInfo'
import CreditGuaranteeInfo from '@/components/credit/GuaranteeInfo'
import CreditSmartReport from '@/components/credit/SmartReport'
// import CreditRaiseMain from '@/components/credit/RaiseMain'
// import CreditRaiseInfo from '@/components/credit/RaiseInfo'
import CreditRaiseInsPersonInfo from '@/components/credit/RaiseInsPersonInfo'
import CreditRaiseInsPersonInfoNts from '@/components/credit/RaiseInsPersonInfoNts'
import CreditLoading from '@/components/credit/Loading'
import CreditRaiseNhis from '@/components/credit/RaiseNhis'
import CreditRaiseNps from '@/components/credit/RaiseNps'
import CreditRaiseNts from '@/components/credit/RaiseNts'
// import CreditRaiseMobile from '@/components/credit/RaiseMobile'
import CreditRaiseResult from '@/components/credit/RaiseResult'
// import CreditGradeAnalysis from '@/components/credit/GradeAnalysis'
import CreditRegCounsel from '@/components/credit/RegCounsel'
import CreditCounselResult from '@/components/credit/CounselResult'

import DebtHome from '@/components/debt/Home'
import DebtMain from '@/components/debt/Main'
import DebtDetail from '@/components/debt/Detail'
// import DebtRepayment from '@/components/debt/Repayment'
// import DebtModify from '@/components/debt/Modify'
// import DebtCalc from '@/components/debt/Calc'
// import DebtCalcSearch from '@/components/debt/CalcSearch'
// import DebtRegister from '@/components/debt/Register'
// import DebtRegDetail from '@/components/debt/RegDetail'
// import DebtIntrInfo from '@/components/debt/IntrInfo'
// import DebtCreditLoan from '@/components/debt/CreditLoan'
// import DebtMortgageLoan from '@/components/debt/MortgageLoan'
import DebtReqIntrCut from '@/components/debt/ReqIntrCut'
// import DebtReqIntrCutInfo from '@/components/debt/ReqIntrCutInfo'
import DebtCalendar from '@/components/debt/Calendar'
import DebtUpdate from '@/components/debt/Update'

import GoodsHome from '@/components/goods/Home'
import GoodsList from '@/components/goods/List'
import GoodsDetail from '@/components/goods/Detail'
// import GoodsStockDetail from '@/components/goods/StockDetail'
// import GoodsCreditTerm1 from '@/components/goods/CreditTerm1'
// import GoodsCreditTerm2 from '@/components/goods/CreditTerm2'
// import GoodsCreditCertPerson from '@/components/goods/CreditCertPerson'
// import GoodsCreditReqInfo from '@/components/goods/CreditReqInfo'
// import GoodsCreditJobIncome from '@/components/goods/CreditJobIncome'
// import GoodsCreditSrcJobNm from '@/components/goods/CreditSrcJobNm'
// import GoodsCreditInsJobNm from '@/components/goods/CreditInsJobNm'
// import GoodsCreditInsJobIncome from '@/components/goods/CreditInsJobIncome'
// import GoodsCreditResult from '@/components/goods/CreditResult'
// import GoodsCreditReqDone from '@/components/goods/CreditReqDone'
// import GoodsHsnTerm from '@/components/goods/HsnTerm'
// import GoodsHsnCertPerson from '@/components/goods/HsnCertPerson'
// import GoodsHsnInsReqInfo from '@/components/goods/HsnInsReqInfo'
// import GoodsHsnInsHsnInfo from '@/components/goods/HsnInsHsnInfo'
// import GoodsHsnInsIncome from '@/components/goods/HsnInsIncome'
// import GoodsHsnInsRepay from '@/components/goods/HsnInsRepay'
// import GoodsHsnResult from '@/components/goods/HsnResult'
import GoodsWorkerGoods from '@/components/goods/WorkerGoods'

import ConsumeHome from '@/components/consume/Home'
import ConsumeMain from '@/components/consume/Main'
import ConsumeRegGoal from '@/components/consume/RegGoal'
// import ConsumeConsumeDetail from '@/components/consume/ConsumeDetail'
// import ConsumeIncomeStat from '@/components/consume/IncomeStat'
// import ConsumeConsumeStat from '@/components/consume/ConsumeStat'
import ConsumeSetting from '@/components/consume/Setting'
// import ConsumeConsumeClass from '@/components/consume/ConsumeClass'
// import ConsumeIncomeClass from '@/components/consume/IncomeClass'
import ConsumePayment from '@/components/consume/Payment'
import ConsumePaymentDetail from '@/components/consume/PaymentDetail'
// import ConsumeSettlement from '@/components/consume/Settlement'
// import ConsumeStats from '@/components/consume/Stats'
// import ConsumePeriodStats from '@/components/consume/PeriodStats'
// import ConsumeAnalysis from '@/components/consume/Analysis'
import ConsumeDraggable from '@/components/consume/Draggable'

import AssetsHome from '@/components/assets/Home'
import AssetsMain from '@/components/assets/Main'
// import AssetsAccountInfo from '@/components/assets/AccountInfo'
// import AssetsDepWdrlDetail from '@/components/assets/DepWdrlDetail'
// import AssetsAccountWdrlDetail from '@/components/assets/AccountWdrlDetail'
// import AssetsAccountDepDetail from '@/components/assets/AccountDepDetail'
// import AssetsStockInfo from '@/components/assets/StockInfo'
// import AssetsStockBalcDetail from '@/components/assets/StockBalcDetail'
// import AssetsStockDelDetail from '@/components/assets/StockDelDetail'
// import AssetsStockAnsDetail from '@/components/assets/StockAnsDetail'

// import MypageHome from '@/components/mypage/Home'
// import MypageMain from '@/components/mypage/Main'
// import MypageInfo from '@/components/mypage/Info'
// import MypageEmail from '@/components/mypage/Email'
// import MypageLogout from '@/components/mypage/Logout'
// import MypageRegAlarm from '@/components/mypage/RegAlarm'
// import MypageCert from '@/components/mypage/Cert'
// import MypageRegCertLogin from '@/components/mypage/RegCertLogin'
// import MypageCertPerson from '@/components/mypage/CertPerson'
// import MypageChgPwd from '@/components/mypage/ChgPwd'
// import MypageFavGoods from '@/components/mypage/FavGoods'
// import MypageFavList from '@/components/mypage/FavList'
// import MypageFavDetail from '@/components/mypage/FavDetail'
// import MypageRstlInqGoods from '@/components/mypage/RstlInqGoods'
// import MypageGoodsList from '@/components/mypage/GoodsList'
// import MypageGoodsDetail from '@/components/mypage/GoodsDetail'
// import MypageRstlReqGoods from '@/components/mypage/RstlReqGoods'
// import MypageState from '@/components/mypage/State'
// import MypageStateList from '@/components/mypage/StateList'
// import MypageDrop from '@/components/mypage/Drop'
// import MypageDropDone from '@/components/mypage/DropDone'
// import MypageMySet from '@/components/mypage/MySet'

import ShareHome from '@/components/share/Home'
import ShareMain from '@/components/share/Main'
import ShareNewRequest from '@/components/share/NewRequest'
import ShareSetting from '@/components/share/Setting'
import ShareDetail from '@/components/share/Detail'
// import ShareHistory from '@/components/share/History'
import ShareSumMain from '@/components/share/SumMain'

// import EtcHome from '@/components/etc/Home'
// import EtcCustomerCenter from '@/components/etc/CustomerCenter'
// import EtcNoticeMain from '@/components/etc/NoticeMain'
// import EtcNoticeList from '@/components/etc/NoticeList'
// import EtcNoticeDetail from '@/components/etc/NoticeDetail'
// import EtcFaqMain from '@/components/etc/FaqMain'
// import EtcFaqList from '@/components/etc/FaqList'
// import EtcFaqDetail from '@/components/etc/FaqDetail'
// import EtcTerm from '@/components/etc/Term'
// import EtcTermDetail from '@/components/etc/TermDetail'
// import EtcVersion from '@/components/etc/Version'

import ErrorPage from '@/components/common/Error'
import ProxyPage from '@/components/common/ProxyPage'
import Spinner from '@/components/common/Spinner'

import Swiper from '@/components/_sample/Swiper'
import Gauge from '@/components/_sample/Gauge'
import Progress from '@/components/_sample/Progress'
import Calendar from '@/components/_sample/Calendar'

import TemplateHome from '@/components/template/Home'
import TemplateMain from '@/components/template/Main'
import TemplateChartSingleLine from '@/components/template/ChartsingleLine'
import TemplateChartSingleLine2 from '@/components/template/ChartSingleLine2'
import TemplateChartMultipleBar from '@/components/template/ChartMultipleBar'

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
    path: 'spinner',
    alias: '/spinner',
    component: Spinner,
    meta: { allowPath: true }
  },
  {
    path: '/member',
    name: 'member',
    component: MemberHome,
    children: [
      {
        path: 'Terms1',
        alias: '/Terms1',
        component: MemberTerms1,
        meta: { allowPath: true }
      },
      {
        path: 'Terms2',
        alias: '/Terms2',
        component: MemberTerms2,
        meta: { allowPath: true }
      },
      {
        path: 'Terms3',
        alias: '/Terms3',
        component: MemberTerms3,
        meta: { allowPath: true }
      },
      {
        path: 'Terms4',
        alias: '/Terms4',
        component: MemberTerms4,
        meta: { allowPath: true }
      },
      {
        path: 'Terms5',
        alias: '/Terms5',
        component: MemberTerms5,
        meta: { allowPath: true }
      },
      {
        path: 'Terms6',
        alias: '/Terms6',
        component: MemberTerms6,
        meta: { allowPath: true }
      },
      {
        path: 'Terms7',
        alias: '/Terms7',
        component: MemberTerms7,
        meta: { allowPath: true }
      },
      {
        path: 'Terms8',
        alias: '/Terms8',
        component: MemberTerms8,
        meta: { allowPath: true }
      },
      {
        path: 'Terms9',
        alias: '/Terms9',
        component: MemberTerms9,
        meta: { allowPath: true }
      },
      {
        path: 'Terms10',
        alias: '/Terms10',
        component: MemberTerms10,
        meta: { allowPath: true }
      },
      {
        path: 'certStep1',
        alias: '/certStep1',
        component: MemberCertStep1,
        meta: { allowPath: true }
      },
      {
        path: 'certStep2',
        alias: '/certStep2',
        component: MemberCertStep2,
        meta: { allowPath: true }
      },
      {
        path: 'certCode',
        alias: '/certCode',
        component: MemberCertCode,
        meta: { allowPath: true }
      },
      {
        path: 'certFinger',
        alias: '/certFinger',
        component: MemberCertFinger,
        meta: { allowPath: true }
      },
      {
        path: 'certCodeLogin',
        alias: '/certCodeLogin',
        component: MemberCertCodeLogin,
        meta: { allowPath: true }
      },
      {
        path: 'certFingerLogin',
        alias: '/certFingerLogin',
        component: MemberCertFingerLogin,
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
      },
      {
        path: 'detail',
        alias: '/detail',
        component: CreditDetail,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'cardInfo',
        alias: '/cardInfo',
        name: 'creditCardInfo',
        component: CreditCardInfo,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'loanInfo',
        alias: '/loanInfo',
        name: 'creditLoanInfo',
        component: CreditLoanInfo,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'overdueInfo',
        alias: '/overdueInfo',
        name: 'creditOverdueInfo',
        component: CreditOverdueInfo,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'guaranteeInfo',
        alias: '/guaranteeInfo',
        name: 'creditGuaranteeInfo',
        component: CreditGuaranteeInfo,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'smartReport',
        alias: '/smartReport',
        name: 'creditSmartReport',
        component: CreditSmartReport,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseInsPersonInfo',
        alias: '/raiseInsPersonInfo',
        component: CreditRaiseInsPersonInfo,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseInsPersonInfoNts',
        alias: '/raiseInsPersonInfoNts',
        component: CreditRaiseInsPersonInfoNts,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'loading',
        alias: '/loading',
        component: CreditLoading,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseNhis',
        alias: '/raiseNhis',
        component: CreditRaiseNhis,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseNps',
        alias: '/raiseNps',
        component: CreditRaiseNps,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseNts',
        alias: '/raiseNts',
        component: CreditRaiseNts,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'raiseResult',
        alias: '/raiseResult',
        component: CreditRaiseResult,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'regCounsel',
        alias: '/regCounsel',
        component: CreditRegCounsel,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'counselResult',
        alias: '/counselResult',
        component: CreditCounselResult,
        meta: { allowPath: true, requiresAuth: true }
      }
    ]
  },
  {
    path: '/debt',
    component: DebtHome,
    children: [
      {
        path: 'main',
        alias: '/main',
        component: DebtMain,
        meta: { allowPath: true }
      },
      {
        path: 'calendar',
        alias: '/calendar',
        component: DebtCalendar,
        meta: { allowPath: true }
      },
      {
        path: 'detail',
        alias: '/detail',
        name: 'debtDetail',
        component: DebtDetail,
        meta: { allowPath: true }
      },
      {
        path: 'update',
        alias: '/update',
        name: 'debtUpdate',
        component: DebtUpdate,
        meta: { allowPath: true }
      },
      {
        path: 'reqIntrCut',
        alias: '/reqIntrCut',
        name: 'debtReqIntrCut',
        component: DebtReqIntrCut,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/memo',
    component: MemoHome,
    children: [
      {
        path: 'register',
        alias: '/register',
        component: MemoRegister,
        meta: { allowPath: true }
      },
      {
        path: 'list',
        alias: '/list',
        component: MemoList,
        meta: { allowPath: true }
      },
      {
        path: 'main',
        alias: '/main',
        component: MemoMain,
        meta: { allowPath: true }
      },
      {
        path: 'create',
        alias: '/create',
        component: MemoCreate,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/consume',
    component: ConsumeHome,
    children: [
      {
        path: 'main',
        alias: '/main',
        component: ConsumeMain,
        meta: { allowPath: true }
      },
      {
        path: 'regGoal',
        alias: '/regGoal',
        component: ConsumeRegGoal,
        meta: { allowPath: true }
      },
      {
        path: 'setting',
        alias: '/setting',
        component: ConsumeSetting,
        meta: { allowPath: true }
      },
      {
        path: 'payment',
        alias: '/payment',
        component: ConsumePayment,
        meta: { allowPath: true }
      },
      {
        path: 'paymentDetail',
        alias: '/paymentDetail',
        component: ConsumePaymentDetail,
        meta: { allowPath: true }
      },
      {
        path: 'draggable',
        alias: '/draggable',
        component: ConsumeDraggable,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/assets',
    component: AssetsHome,
    children: [
      {
        path: 'main',
        alias: '/main',
        component: AssetsMain,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/goods',
    component: GoodsHome,
    children: [
      {
        path: 'list',
        alias: '/list',
        component: GoodsList,
        meta: { allowPath: true }
      },
      {
        path: 'detail',
        alias: '/detail',
        component: GoodsDetail,
        meta: { allowPath: true }
      },
      {
        path: 'workergoods',
        alias: '/workergoods',
        component: GoodsWorkerGoods,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/share',
    name: 'share',
    component: ShareHome,
    children: [
      {
        path: 'sumMain',
        alias: '/sumMain',
        name: 'shareSumMain',
        component: ShareSumMain,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'setting',
        alias: '/setting',
        name: 'shareSetting',
        component: ShareSetting,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'detail',
        alias: '/detail',
        name: 'shareDetail',
        component: ShareDetail,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'newRequest',
        alias: '/newRequest',
        name: 'shareNewRquest',
        component: ShareNewRequest,
        meta: { allowPath: true, requiresAuth: true }
      },
      {
        path: 'main',
        alias: '/main',
        name: 'shareMain',
        component: ShareMain,
        meta: { allowPath: true, requiresAuth: true }
      }
    ]
  },
  {
    path: '/scrap',
    component: ScrapHome,
    children: [
      {
        path: 'certStep',
        alias: '/certStep',
        name: 'scrapCertStep',
        component: ScrapCertStep,
        meta: { allowPath: true }
      },
      {
        path: 'loading',
        alias: '/lodaing',
        name: 'scrapLoading',
        component: ScrapLoading,
        meta: { allowPath: true }
      },
      {
        path: 'regFcLink',
        alias: '/regFcLink',
        name: 'scrapRegFcLink',
        component: ScrapRegFcLink,
        meta: { allowPath: true }
      },
      {
        path: 'ctrlFcLink',
        alias: '/ctrlFcLink',
        name: 'scrapCtrlFcLink',
        component: ScrapCtrlFcLink,
        meta: { allowPath: true }
      }
    ]
  },
  // {
  //   path: '/mypage',
  //   name: 'mypage',
  //   component: MypageHome,
  //   children: [
  //     {
  //       path: 'certPerson',
  //       alias: '/certPerson',
  //       component: CertPerson,
  //       meta: { allowPath: true, requiresAuth: true }
  //     }
  //   ]
  // },
  {
    path: '/sample/swiper',
    name: 'swiper',
    component: Swiper,
    meta: { allowPath: true }
  },
  {
    path: '/sample/gauge',
    name: 'gauge',
    component: Gauge,
    meta: { allowPath: true }
  },
  {
    path: '/sample/progress',
    name: 'progress',
    component: Progress,
    meta: { allowPath: true }
  },
  {
    path: '/sample/calendar',
    name: 'calendar',
    component: Calendar,
    meta: { allowPath: true }
  },
  {
    path: '/template',
    name: 'template',
    component: TemplateHome,
    children: [
      {
        path: 'main',
        alias: '/main',
        component: TemplateMain,
        meta: { allowPath: true }
      },
      {
        path: 'chartSingleLine',
        alias: '/chartSingleLine',
        component: TemplateChartSingleLine,
        meta: { allowPath: true }
      },
      {
        path: 'chartSingleLine2',
        alias: '/chartSingleLine2',
        component: TemplateChartSingleLine2,
        meta: { allowPath: true }
      },
      {
        path: 'chartMultipleBar',
        alias: '/chartMultipleBar',
        component: TemplateChartMultipleBar,
        meta: { allowPath: true }
      }
    ]
  },
  {
    path: '/news',
    component: NewsHome,
    children: [
      {
        path: 'main',
        alias: '/main',
        name: 'newsMain',
        component: NewsMain,
        meta: { allowPath: true }
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
