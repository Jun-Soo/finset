import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/main/Home'
import FinsetIntro from '@/components/main/Intro'
import FinsetMain from '@/components/main/FinsetMain'
// import Logout from '@/components/member/Logout'

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
import ScrapFcLink from '@/components/scrap/FcLink'
import ScrapResultFcLink from '@/components/scrap/ResultFcLink'
import ScrapCtrlFcLink from '@/components/scrap/CtrlFcLink'
import ScrapLoading from '@/components/scrap/Loading'
import ScrapRegFcLink from '@/components/scrap/RegFcLink'
import ScrapSelFcLink from '@/components/scrap/SelFcLink'
import ScrapTerms from '@/components/scrap/Terms'

import MemoHome from '@/components/memo/Home'
import MemoRegister from '@/components/memo/Register'
import MemoList from '@/components/memo/List'
import MemoDetail from '@/components/memo/Detail'
import MemoMain from '@/components/memo/Main'
import MemoCreate from '@/components/memo/Create'

import NewsHome from '@/components/news/Home'
import NewsMain from '@/components/news/Main'
import NewsDetail from '@/components/news/Detail'

import CreditHome from '@/components/credit/Home'
import CreditMain from '@/components/credit/Main'
import CreditDetail from '@/components/credit/Detail'
import CreditCardInfo from '@/components/credit/CardInfo'
import CreditLoanInfo from '@/components/credit/LoanInfo'
import CreditOverdueInfo from '@/components/credit/OverdueInfo'
import CreditGuaranteeInfo from '@/components/credit/GuaranteeInfo'
import CreditSmartReport from '@/components/credit/SmartReport'
import CreditRaiseMain from '@/components/credit/RaiseMain'
import CreditRaiseInfo from '@/components/credit/RaiseInfo'
import CreditRaiseInsPersonInfo from '@/components/credit/RaiseInsPersonInfo'
import CreditRaiseInsPersonInfoNts from '@/components/credit/RaiseInsPersonInfoNts'
import CreditLoading from '@/components/credit/Loading'
import CreditRaiseNhis from '@/components/credit/RaiseNhis'
import CreditRaiseNps from '@/components/credit/RaiseNps'
import CreditRaiseNts from '@/components/credit/RaiseNts'
import CreditRaiseRetry from '@/components/credit/RaiseRetry'
// import CreditRaiseMobile from '@/components/credit/RaiseMobile'
import CreditRaiseResult from '@/components/credit/RaiseResult'
// import CreditGradeAnalysis from '@/components/credit/GradeAnalysis'
import CreditCounselMain from '@/components/credit/CounselMain'
import CreditCounselInfo from '@/components/credit/CounselInfo'
import CreditCounselReqStep1 from '@/components/credit/CounselReqStep1'
import CreditCounselReqStep2 from '@/components/credit/CounselReqStep2'
import CreditCounselReqStep3 from '@/components/credit/CounselReqStep3'
import CreditCounselReqStep4 from '@/components/credit/CounselReqStep4'
import CreditCounselReqStep5 from '@/components/credit/CounselReqStep5'
import CreditCounselResult from '@/components/credit/CounselResult'

import DebtHome from '@/components/debt/Home'
import DebtMain from '@/components/debt/Main'
import DebtDetail from '@/components/debt/Detail'
import DebtDetail2 from '@/components/debt/Detail_2'
import DebtRepayment from '@/components/debt/Repayment'
import DebtModify from '@/components/debt/Modify'
import DebtCalc from '@/components/debt/Calc'
// import DebtCalc2 from '@/components/debt/Calc2'
// import DebtCalc3 from '@/components/debt/Calc3'
import DebtCalcSearch from '@/components/debt/CalcSearch'
import DebtRegister from '@/components/debt/Register'
import DebtRegDetail from '@/components/debt/RegDetail'
// import DebtIntrInfo from '@/components/debt/IntrInfo'
// import DebtCreditLoan from '@/components/debt/CreditLoan'
// import DebtMortgageLoan from '@/components/debt/MortgageLoan'
import DebtReqIntrCut from '@/components/debt/ReqIntrCut'
import DebtCalendar from '@/components/debt/Calendar'
import DebtUpdate from '@/components/debt/Update'

import GoodsHome from '@/components/goods/Home'
import GoodsList from '@/components/goods/List'
import GoodsDetail from '@/components/goods/Detail'
import GoodsStockDetail from '@/components/goods/StockDetail'
import GoodsCertStep1 from '@/components/goods/CertStep1'
import GoodsCertStep2 from '@/components/goods/CertStep2'
import GoodsCreditReqInfo from '@/components/goods/CreditReqInfo'
import GoodsCreditJobIncome from '@/components/goods/CreditJobIncome'
import GoodsCreditSrcJobNm from '@/components/goods/CreditSrcJobNm'
import GoodsCreditInsJobNm from '@/components/goods/CreditInsJobNm'
import GoodsLoading from '@/components/goods/Loading'
import GoodsResult from '@/components/goods/Result'
import GoodsReqDone from '@/components/goods/ReqDone'
// import GoodsHsnTerm from '@/components/goods/HsnTerm'
// import GoodsHsnCertPerson from '@/components/goods/HsnCertPerson'
import GoodsHsnInsReqInfo from '@/components/goods/HsnInsReqInfo'
import GoodsHsnInsHsnInfo from '@/components/goods/HsnInsHsnInfo'
import GoodsHsnInsIncome from '@/components/goods/HsnInsIncome'
import GoodsHsnInsRepay from '@/components/goods/HsnInsRepay'
// import GoodsHsnResult from '@/components/goods/HsnResult'
import GoodsWorkerGoods from '@/components/goods/WorkerGoods'

import ConsumeHome from '@/components/consume/Home'
import ConsumeMain from '@/components/consume/Main'
import ConsumeRegGoal from '@/components/consume/RegGoal'
import ConsumeConsumeDetail from '@/components/consume/ConsumeDetail'
import ConsumeIncomeAnalyze from '@/components/consume/IncomeAnalyze'
// import ConsumeConsumeAnalyze from '@/components/consume/ConsumeAnalyze'
import ConsumeSetting from '@/components/consume/Setting'
import ConsumeConsumeClass from '@/components/consume/ConsumeClass'
import ConsumeIncomeClass from '@/components/consume/IncomeClass'
import ConsumePayment from '@/components/consume/Payment'
import ConsumePaymentDetail from '@/components/consume/PaymentDetail'
import ConsumeSettlement from '@/components/consume/Settlement'
import ConsumeIncomeStats from '@/components/consume/IncomeStats'
// import ConsumeConsumeStats from '@/components/consume/ConsumeStats'
// import ConsumePeriodStats from '@/components/consume/PeriodStats'

import ConsumeDraggable from '@/components/consume/Draggable'

import AssetsHome from '@/components/assets/Home'
import AssetsMain from '@/components/assets/Main'
import AssetsBankMain from '@/components/assets/BankMain'
import AssetsBankActDetail from '@/components/assets/BankActDetail'
import AssetsBankDepWdrlList from '@/components/assets/BankDepWdrlList'
import AssetsBankDepWdrlDetail from '@/components/assets/BankDepWdrlDetail'
import AssetsStockMain from '@/components/assets/StockMain'
import AssetsStockBalcDetail from '@/components/assets/StockBalcDetail'
import AssetsStockActDetail from '@/components/assets/StockActDetail'
import AssetsStockShrDetail from '@/components/assets/StockShrDetail'
import AssetsStockFndDetail from '@/components/assets/StockFndDetail'
import AssetsEtcMain from '@/components/assets/etcMain'
import AssetsDirInput from '@/components/assets/DirInput'

import MypageHome from '@/components/mypage/Home'
// import MypageMain from '@/components/mypage/Main'
import MypageInfo from '@/components/mypage/Info'
import MypageEmail from '@/components/mypage/Email'
import MypageLogout from '@/components/mypage/Logout'
import MypageRegAlarm from '@/components/mypage/RegAlarm'
import MypageCert from '@/components/mypage/Cert'
import MypageRegCertLogin from '@/components/mypage/RegCertLogin'
import MypageCertPerson from '@/components/mypage/CertPerson'
import MypageChgPwd from '@/components/mypage/ChgPwd'
import MypageFavGoods from '@/components/mypage/FavGoods'
// import MypageFavList from '@/components/mypage/FavList'
import MypageFavDetail from '@/components/mypage/FavDetail'
import MypageRstlInqGoods from '@/components/mypage/RstlInqGoods'
import MypageRstlInqSucc from '@/components/mypage/RstlInqSucc'
// import MypageGoodsList from '@/components/mypage/GoodsList'
// import MypageGoodsDetail from '@/components/mypage/GoodsDetail'
// import MypageRstlReqGoods from '@/components/mypage/RstlReqGoods'
import MypageState from '@/components/mypage/State'
// import MypageStateList from '@/components/mypage/StateList'
import MypageDrop from '@/components/mypage/Drop'
import MypageDropDone from '@/components/mypage/DropDone'
// import MypageMySet from '@/components/mypage/MySet'

import ShareHome from '@/components/share/Home'
import ShareMain from '@/components/share/Main'
import ShareHistory from '@/components/share/History'
import ShareNewRequest from '@/components/share/NewRequest'
import ShareReqSetting from '@/components/share/ReqSetting'
import ShareOfferSetting from '@/components/share/OfferSetting'
import ShareDetail from '@/components/share/Detail'

import EtcHome from '@/components/etc/Home'
import EtcAlarmHistory from '@/components/etc/AlarmHistory'
// import EtcCustomerCenter from '@/components/etc/CustomerCenter'
import EtcNoticeMain from '@/components/etc/NoticeMain'
import EtcNoticeDetail from '@/components/etc/NoticeDetail'
import EtcEventDetail from '@/components/etc/EventDetail'
import EtcFaqMain from '@/components/etc/FaqMain'
import EtcFaqList from '@/components/etc/FaqList'
import EtcFaqDetail from '@/components/etc/FaqDetail'
import EtcTerm from '@/components/etc/Term'
// import EtcTermDetail from '@/components/etc/TermDetail'
// import EtcVersion from '@/components/etc/Version'

import ErrorPage from '@/components/common/Error'
import ProxyPage from '@/components/common/ProxyPage'
import Spinner from '@/components/common/Spinner'
import Loading from '@/components/common/Loading'
import CommonMonthCal from '@/components/common/MonthCal'
import CommonYearCal from '@/components/common/YearCal'
import CommonCalSetting from '@/components/common/CalSetting'

import Swiper from '@/components/_sample/Swiper'
import Gauge from '@/components/_sample/Gauge'
import Progress from '@/components/_sample/Progress'
import Calendar from '@/components/_sample/Calendar'

import TemplateHome from '@/components/template/Home'
import TemplateMain from '@/components/template/Main'
import TemplateChartSingleLine from '@/components/template/ChartsingleLine'
import TemplateChartSingleLine2 from '@/components/template/ChartSingleLine2'
import TemplateChartMultipleBar from '@/components/template/ChartMultipleBar'

import Store from '@/comm/store'
Vue.use(Router)

export const routes = [{
  path: '/home',
  name: 'home',
  component: Home,
  meta: {
    allowPath: true
  }
},
{
  path: '/intro',
  name: 'intro',
  component: FinsetIntro,
  meta: {
    allowPath: true
  }
},
{
  path: '/error',
  name: 'error',
  component: ErrorPage,
  meta: {
    allowPath: true
  }
},
{
  path: '/main',
  name: 'main',
  component: FinsetMain,
  meta: {
    allowPath: true,
    requiresAuth: true
  }
},
{
  path: '/proxy',
  name: 'proxy',
  component: ProxyPage,
  meta: {
    allowPath: true
  }
},
{
  path: 'spinner',
  alias: '/spinner',
  component: Spinner,
  meta: {
    allowPath: true
  }
},
{
  path: '/common',
  name: 'common',
  component: MemberHome,
  children: [{
    path: 'loading',
    alias: '/loading',
    component: Loading,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'monthCal',
    alias: '/monthCal',
    component: CommonMonthCal,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'yearCal',
    alias: '/yearCal',
    component: CommonYearCal,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'calSetting',
    alias: '/calSetting',
    component: CommonCalSetting,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/member',
  name: 'member',
  component: MemberHome,
  children: [{
    path: 'terms1',
    alias: '/terms1',
    component: MemberTerms1,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms2',
    alias: '/terms2',
    component: MemberTerms2,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms3',
    alias: '/terms3',
    component: MemberTerms3,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms4',
    alias: '/terms4',
    component: MemberTerms4,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms5',
    alias: '/terms5',
    component: MemberTerms5,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms6',
    alias: '/terms6',
    component: MemberTerms6,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms7',
    alias: '/terms7',
    component: MemberTerms7,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms8',
    alias: '/terms8',
    component: MemberTerms8,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms9',
    alias: '/terms9',
    component: MemberTerms9,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms10',
    alias: '/terms10',
    component: MemberTerms10,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certStep1',
    alias: '/certStep1',
    component: MemberCertStep1,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certStep2',
    alias: '/certStep2',
    component: MemberCertStep2,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certCode',
    alias: '/certCode',
    component: MemberCertCode,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certFinger',
    alias: '/certFinger',
    component: MemberCertFinger,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certCodeLogin',
    alias: '/certCodeLogin',
    component: MemberCertCodeLogin,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certFingerLogin',
    alias: '/certFingerLogin',
    component: MemberCertFingerLogin,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/credit',
  component: CreditHome,
  children: [{
    path: 'main',
    alias: '/main',
    component: CreditMain,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    component: CreditDetail,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'cardInfo',
    alias: '/cardInfo',
    name: 'creditCardInfo',
    component: CreditCardInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'loanInfo',
    alias: '/loanInfo',
    name: 'creditLoanInfo',
    component: CreditLoanInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'overdueInfo',
    alias: '/overdueInfo',
    name: 'creditOverdueInfo',
    component: CreditOverdueInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'guaranteeInfo',
    alias: '/guaranteeInfo',
    name: 'creditGuaranteeInfo',
    component: CreditGuaranteeInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'smartReport',
    alias: '/smartReport',
    name: 'creditSmartReport',
    component: CreditSmartReport,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseInsPersonInfo',
    alias: '/raiseInsPersonInfo',
    name: 'CreditRaiseInsPersonInfo',
    component: CreditRaiseInsPersonInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseInsPersonInfoNts',
    alias: '/raiseInsPersonInfoNts',
    component: CreditRaiseInsPersonInfoNts,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'loading',
    alias: '/loading',
    component: CreditLoading,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseMain',
    alias: '/raiseMain',
    component: CreditRaiseMain,
    meta: {
      allowPath: true,
      requiresAuth: true,
      backCasePath: true
    }
  },
  {
    path: 'raiseInfo',
    alias: '/raiseInfo',
    component: CreditRaiseInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseNhis',
    alias: '/raiseNhis',
    component: CreditRaiseNhis,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseNps',
    alias: '/raiseNps',
    component: CreditRaiseNps,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseNts',
    alias: '/raiseNts',
    component: CreditRaiseNts,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseRetry',
    alias: '/raiseRetry',
    component: CreditRaiseRetry,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'raiseResult',
    alias: '/raiseResult',
    component: CreditRaiseResult,
    name: 'creditRaiseResult',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselMain',
    alias: '/counselMain',
    component: CreditCounselMain,
    name: 'creditCounselMain',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselInfo',
    alias: '/counselInfo',
    component: CreditCounselInfo,
    name: 'creditCounselInfo',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselReqStep1',
    alias: '/counselReqStep1',
    component: CreditCounselReqStep1,
    name: 'creditCounselReqStep1',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselReqStep2',
    alias: '/counselReqStep2',
    component: CreditCounselReqStep2,
    name: 'creditCounselReqStep2',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselReqStep3',
    alias: '/counselReqStep3',
    component: CreditCounselReqStep3,
    name: 'creditCounselReqStep3',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselReqStep4',
    alias: '/counselReqStep4',
    component: CreditCounselReqStep4,
    name: 'creditCounselReqStep4',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselReqStep5',
    alias: '/counselReqStep5',
    component: CreditCounselReqStep5,
    name: 'creditCounselReqStep5',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'counselResult',
    alias: '/counselResult',
    component: CreditCounselResult,
    name: 'creditCounselResult',
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  }
  ]
},
{
  path: '/debt',
  component: DebtHome,
  children: [{
    path: 'main',
    alias: '/main',
    component: DebtMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'calendar',
    alias: '/calendar',
    component: DebtCalendar,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    name: 'debtDetail',
    component: DebtDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'detail_2',
    alias: '/detail_2',
    name: 'debtDetail_2',
    component: DebtDetail2,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'repayment',
    alias: '/repayment',
    name: 'debtRepayment',
    component: DebtRepayment,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'modify',
    alias: '/modify',
    name: 'debtModify',
    component: DebtModify,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'calc',
    alias: '/calc',
    name: 'debtCalc',
    component: DebtCalc,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'calcSearch',
    alias: '/calcSearch',
    name: 'debtCalcSearch',
    component: DebtCalcSearch,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'register',
    alias: '/register',
    name: 'debtRegister',
    component: DebtRegister,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'regDetail',
    alias: '/regDetail',
    name: 'debtRegDetail',
    component: DebtRegDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'update',
    alias: '/update',
    name: 'debtUpdate',
    component: DebtUpdate,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'reqIntrCut',
    alias: '/reqIntrCut',
    name: 'debtReqIntrCut',
    component: DebtReqIntrCut,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/memo',
  component: MemoHome,
  children: [{
    path: 'register',
    alias: '/register',
    component: MemoRegister,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'list',
    alias: '/list',
    component: MemoList,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    component: MemoDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'main',
    alias: '/main',
    component: MemoMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'create',
    alias: '/create',
    component: MemoCreate,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/consume',
  component: ConsumeHome,
  children: [{
    path: 'main',
    alias: '/main',
    component: ConsumeMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'regGoal',
    alias: '/regGoal',
    component: ConsumeRegGoal,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'consumeDetail',
    alias: '/consumeDetail',
    component: ConsumeConsumeDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'incomeAnalyze',
    alias: '/incomeAnalyze',
    component: ConsumeIncomeAnalyze,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'setting',
    alias: '/setting',
    component: ConsumeSetting,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'consumeClass',
    alias: '/consumeClass',
    component: ConsumeConsumeClass,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'incomeClass',
    alias: '/incomeClass',
    component: ConsumeIncomeClass,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'payment',
    alias: '/payment',
    component: ConsumePayment,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'paymentDetail',
    alias: '/paymentDetail',
    component: ConsumePaymentDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'settlement',
    alias: '/settlement',
    component: ConsumeSettlement,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'consumeIncomeStats',
    alias: '/consumeIncomeStats',
    component: ConsumeIncomeStats,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'draggable',
    alias: '/draggable',
    component: ConsumeDraggable,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/assets',
  component: AssetsHome,
  children: [{
    path: 'main',
    alias: '/main',
    component: AssetsMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'bankMain',
    alias: '/bankMain',
    name: 'assetsBankMain',
    component: AssetsBankMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'bankActDetail',
    alias: '/bankActDetail',
    name: 'assetsBankActDetail',
    component: AssetsBankActDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'bankDepWdrlList',
    alias: '/bankDepWdrlList',
    name: 'assetsBankDepWdrlList',
    component: AssetsBankDepWdrlList,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'bankDepWdrlDetail',
    alias: '/bankDepWdrlDetail',
    name: 'assetsBankDepWdrlDetail',
    component: AssetsBankDepWdrlDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockMain',
    alias: '/stockMain',
    name: 'assetsStockMain',
    component: AssetsStockMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockBalcDetail',
    alias: '/stockBalcDetail',
    name: 'assetsStockBalcDetail',
    component: AssetsStockBalcDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockActDetail',
    alias: '/stockActDetail',
    name: 'assetsStockActDetail',
    component: AssetsStockActDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockShrDetail',
    alias: '/stockShrDetail',
    name: 'assetsStockShrDetail',
    component: AssetsStockShrDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockFndDetail',
    alias: '/stockFndDetail',
    name: 'assetsStockFndDetail',
    component: AssetsStockFndDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'etcMain',
    alias: '/etcMain',
    name: 'assetsEtcMain',
    component: AssetsEtcMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'dirInput',
    alias: '/dirInput',
    component: AssetsDirInput,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/goods',
  component: GoodsHome,
  children: [{
    path: 'list',
    alias: '/list',
    component: GoodsList,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    name: 'GoodsDetail',
    component: GoodsDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'stockDetail',
    alias: '/stockDetail',
    name: 'GoodsStockDetail',
    component: GoodsStockDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certStep1',
    alias: '/certStep1',
    name: 'GoodsCertStep1',
    component: GoodsCertStep1,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'certStep2',
    alias: '/certStep2',
    name: 'GoodsCertStep2',
    component: GoodsCertStep2,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'creditReqInfo',
    alias: '/creditReqInfo',
    name: 'GoodsCreditReqInfo',
    component: GoodsCreditReqInfo,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'creditJobIncome',
    alias: '/creditJobIncome',
    name: 'GoodsCreditJobIncome',
    component: GoodsCreditJobIncome,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'creditSrcJobNm',
    alias: '/creditSrcJobNm',
    name: 'GoodsCreditSrcJobNm',
    component: GoodsCreditSrcJobNm,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'creditInsJobNm',
    alias: '/creditInsJobNm',
    name: 'GoodsCreditInsJobNm',
    component: GoodsCreditInsJobNm,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'hsnInsReqInfo',
    alias: '/hsnInsReqInfo',
    name: 'GoodsHsnInsReqInfo',
    component: GoodsHsnInsReqInfo,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'hsnInsHsnInfo',
    alias: '/hsnInsHsnInfo',
    name: 'GoodsHsnInsHsnInfo',
    component: GoodsHsnInsHsnInfo,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'hsnInsIncome',
    alias: '/hsnInsIncome',
    name: 'GoodsHsnInsIncome',
    component: GoodsHsnInsIncome,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'hsnInsRepay',
    alias: '/hsnInsRepay',
    name: 'GoodsHsnInsRepay',
    component: GoodsHsnInsRepay,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'loading',
    alias: '/loading',
    name: 'GoodsLoading',
    component: GoodsLoading,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'result',
    alias: '/result',
    name: 'GoodsResult',
    component: GoodsResult,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'reqDone',
    alias: '/reqDone',
    name: 'GoodsReqDone',
    component: GoodsReqDone,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'workergoods',
    alias: '/workergoods',
    component: GoodsWorkerGoods,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/share',
  name: 'share',
  component: ShareHome,
  children: [{
    path: 'main',
    alias: '/main',
    name: 'shareMain',
    component: ShareMain,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'history',
    alias: '/history',
    name: 'shareHistory',
    component: ShareHistory,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'newRequest',
    alias: '/newRequest',
    name: 'shareNewRequest',
    component: ShareNewRequest,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'reqSetting',
    alias: '/reqSetting',
    name: 'shareReqSetting',
    component: ShareReqSetting,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'offerSetting',
    alias: '/offerSetting',
    name: 'shareOfferSetting',
    component: ShareOfferSetting,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    name: 'shareDetail',
    component: ShareDetail,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  }
  ]
},
{
  path: '/etc',
  component: EtcHome,
  children: [{
    path: 'alarmHistory',
    alias: '/alarmHistory',
    name: 'etcAlarmHistory',
    component: EtcAlarmHistory,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'noticeMain',
    alias: '/noticeMain',
    name: 'etcNoticeMain',
    component: EtcNoticeMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'noticeDetail',
    alias: '/noticeDetail',
    name: 'etcNoticeDetail',
    component: EtcNoticeDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'eventDetail',
    alias: '/eventDetail',
    name: 'etcEventDetail',
    component: EtcEventDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'faqMain',
    alias: '/faqMain',
    name: 'etcFaqMain',
    component: EtcFaqMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'faqList',
    alias: '/faqList',
    name: 'etcFaqList',
    component: EtcFaqList,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'faqDetail',
    alias: '/faqDetail',
    name: 'etcFaqDetail',
    component: EtcFaqDetail,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'term',
    alias: '/term',
    name: 'etcTerm',
    component: EtcTerm,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/scrap',
  component: ScrapHome,
  children: [{
    path: 'certStep',
    alias: '/certStep',
    name: 'scrapCertStep',
    component: ScrapCertStep,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'fcLink',
    alias: '/fcLink',
    name: 'scrapFcLink',
    component: ScrapFcLink,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'resultFcLink',
    alias: '/resultFcLink',
    name: 'scrapResultFcLink',
    component: ScrapResultFcLink,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'ctrlFcLink',
    alias: '/ctrlFcLink',
    name: 'scrapCtrlFcLink',
    component: ScrapCtrlFcLink,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'loading',
    alias: '/loadaing',
    name: 'scrapLoading',
    component: ScrapLoading,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'regFcLink',
    alias: '/regFcLink',
    name: 'scrapRegFcLink',
    component: ScrapRegFcLink,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'selFcLink',
    alias: '/selFcLink',
    name: 'scrapSelFcLink',
    component: ScrapSelFcLink,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'terms',
    alias: '/terms',
    name: 'scrapTerms',
    component: ScrapTerms,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/mypage',
  name: 'mypage',
  component: MypageHome,
  children: [{
    path: 'info',
    alias: '/info',
    component: MypageInfo,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'email',
    alias: '/email',
    component: MypageEmail,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'logout',
    alias: '/logout',
    component: MypageLogout,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'regAlarm',
    alias: '/regAlarm',
    component: MypageRegAlarm,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'drop',
    alias: '/drop',
    component: MypageDrop,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'dropDone',
    alias: '/dropDone',
    component: MypageDropDone,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'cert',
    alias: '/cert',
    component: MypageCert,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'regCertLogin',
    alias: '/regCertLogin',
    component: MypageRegCertLogin,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'certPerson',
    alias: '/certPerson',
    component: MypageCertPerson,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'chgPwd',
    alias: '/chgPwd',
    component: MypageChgPwd,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'favGoods',
    alias: '/favGoods',
    component: MypageFavGoods,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'favDetail',
    alias: '/favDetail',
    name: 'MypageFavDetail',
    component: MypageFavDetail,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'rstlInqGoods',
    alias: '/rstlInqGoods',
    component: MypageRstlInqGoods,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'rstlInqSucc',
    alias: '/rstlInqSucc',
    component: MypageRstlInqSucc,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'rstlInqSucc',
    alias: '/rstlInqSucc',
    component: MypageRstlInqSucc,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'state',
    alias: '/state',
    component: MypageState,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  }
  ]
},
{
  path: '/sample/swiper',
  name: 'swiper',
  component: Swiper,
  meta: {
    allowPath: true
  }
},
{
  path: '/sample/gauge',
  name: 'gauge',
  component: Gauge,
  meta: {
    allowPath: true
  }
},
{
  path: '/sample/progress',
  name: 'progress',
  component: Progress,
  meta: {
    allowPath: true
  }
},
{
  path: '/sample/calendar',
  name: 'calendar',
  component: Calendar,
  meta: {
    allowPath: true
  }
},
{
  path: '/template',
  name: 'template',
  component: TemplateHome,
  children: [{
    path: 'main',
    alias: '/main',
    component: TemplateMain,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'chartSingleLine',
    alias: '/chartSingleLine',
    component: TemplateChartSingleLine,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'chartSingleLine2',
    alias: '/chartSingleLine2',
    component: TemplateChartSingleLine2,
    meta: {
      allowPath: true
    }
  },
  {
    path: 'chartMultipleBar',
    alias: '/chartMultipleBar',
    component: TemplateChartMultipleBar,
    meta: {
      allowPath: true
    }
  }
  ]
},
{
  path: '/news',
  component: NewsHome,
  children: [{
    path: 'main',
    alias: '/main',
    name: 'newsMain',
    component: NewsMain,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  },
  {
    path: 'detail',
    alias: '/detail',
    name: 'newsDetail',
    component: NewsDetail,
    meta: {
      allowPath: true,
      requiresAuth: true
    }
  }
  ]
}
]

const router = new Router({
  routes,
  mode: 'history'
})

router.beforeEach((to, from, next) => {
  Store.state.header.backPath = ''
  if (to.path === '/index.html') {
    next('/home?hp=' + to.query.hp)
  } else {
    const hp = localStorage.getItem('hp')
    if (to.meta.allowPath) {
      if (to.meta.requiresAuth) {
        if (to.meta.backCasePath && !Store.state.header.fromPath) Store.state.header.fromPath = from.fullPath
        if (to.fullPath == Store.state.header.fromPath) Store.state.header.fromPath = ''

        const accessToken = localStorage.getItem('accessToken')
        if (!accessToken) {
          alert('접근 권한이 없습니다. 시작페이지로 이동합니다.')
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
      alert('잘못된 접근입니다. 시작페이지로 이동합니다.')
      setTimeout(function () {
        next('/home?hp=' + hp)
      }, 1000)
    }
  }
})

export default router
