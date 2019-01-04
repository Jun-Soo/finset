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
// import DebtDetail2 from '@/components/debt/Detail_2'
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
// import DebtUpdate from '@/components/debt/Update'

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
import GoodsStock1 from '@/components/goods/Stock1'
import GoodsStock2 from '@/components/goods/Stock2'
import GoodsStock3 from '@/components/goods/Stock3'
import GoodsStock4 from '@/components/goods/Stock4'
import GoodsStock5 from '@/components/goods/Stock5'
import GoodsStock6 from '@/components/goods/Stock6'
import GoodsStock7 from '@/components/goods/Stock7'
import GoodsStock8 from '@/components/goods/Stock8'
import GoodsStock9 from '@/components/goods/Stock9'

import ConsumeHome from '@/components/consume/Home'
import ConsumeMain from '@/components/consume/Main'
import ConsumeRegGoal from '@/components/consume/RegGoal'
import ConsumeConsumeDetail from '@/components/consume/ConsumeDetail'
import ConsumeAnalyze from '@/components/consume/Analyze'
import ConsumeSetting from '@/components/consume/Setting'
import ConsumeConsumeClass from '@/components/consume/ConsumeClass'
import ConsumeIncomeClass from '@/components/consume/IncomeClass'
import ConsumePayment from '@/components/consume/Payment'
import ConsumePaymentDetail from '@/components/consume/PaymentDetail'
import ConsumeSettlement from '@/components/consume/Settlement'
import ConsumeIncomeStats from '@/components/consume/IncomeStats'
// import ConsumeConsumeStats from '@/components/consume/ConsumeStats'
// import ConsumePeriodStats from '@/components/consume/PeriodStats'

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
import AssetsStockAnalysis from '@/components/assets/StockAnalysis'
import AssetsStockSpcfAnls from '@/components/assets/StockSpcfAnls'
import AssetsStockIvtmBhvr from '@/components/assets/StockIvtmBhvr'
import AssetsStockGoalSet from '@/components/assets/StockGoalSet'

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
import Select from '@/components/_sample/Select'
import Datepicker from '@/components/_sample/Datepicker'
import Money from '@/components/_sample/Money'

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
    path: '/spinner',
    name: 'spinner',
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
        name: 'commonLoading',
        component: Loading,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'monthCal',
        name: 'commonMonthCal',
        component: CommonMonthCal,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'yearCal',
        name: 'commonYearCal',
        component: CommonYearCal,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'calSetting',
        name: 'commonCalSetting',
        component: CommonCalSetting,
        meta: {
          allowPath: true,
          requiresAuth: true
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
        name: 'memberTerms1',
        component: MemberTerms1,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms2',
        name: 'memberTerms2',
        component: MemberTerms2,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms3',
        name: 'memberTerms3',
        component: MemberTerms3,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms4',
        name: 'memberTerms4',
        component: MemberTerms4,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms5',
        name: 'memberTerms5',
        component: MemberTerms5,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms6',
        name: 'memberTerms6',
        component: MemberTerms6,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms7',
        name: 'memberTerms7',
        component: MemberTerms7,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms8',
        name: 'memberTerms8',
        component: MemberTerms8,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms9',
        name: 'memberTerms9',
        component: MemberTerms9,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'terms10',
        name: 'memberTerms10',
        component: MemberTerms10,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certStep1',
        name: 'memberCertStep1',
        component: MemberCertStep1,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certStep2',
        name: 'memberCertStep2',
        component: MemberCertStep2,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certCode',
        name: 'memberCertCode',
        component: MemberCertCode,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certFinger',
        name: 'memberCertFinger',
        component: MemberCertFinger,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certCodeLogin',
        name: 'memberCertCodeLogin',
        component: MemberCertCodeLogin,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'certFingerLogin',
        name: 'memberCertFingerLogin',
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
        name: 'creditMain',
        component: CreditMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'detail',
        name: 'creditDetail',
        component: CreditDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'cardInfo',
        name: 'creditCardInfo',
        component: CreditCardInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'loanInfo',
        name: 'creditLoanInfo',
        component: CreditLoanInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'overdueInfo',
        name: 'creditOverdueInfo',
        component: CreditOverdueInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'guaranteeInfo',
        name: 'creditGuaranteeInfo',
        component: CreditGuaranteeInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'smartReport',
        name: 'creditSmartReport',
        component: CreditSmartReport,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseInsPersonInfo',
        name: 'CreditRaiseInsPersonInfo',
        component: CreditRaiseInsPersonInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseInsPersonInfoNts',
        name: 'creditRaiseInsPersonInfoNts',
        component: CreditRaiseInsPersonInfoNts,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'loading',
        name: 'creditLoading',
        component: CreditLoading,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseMain',
        name: 'creditRaiseMain',
        component: CreditRaiseMain,
        meta: {
          allowPath: true,
          requiresAuth: true,
          backCasePath: true
        }
      },
      {
        path: 'raiseInfo',
        name: 'creditRaiseInfo',
        component: CreditRaiseInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseNhis',
        name: 'creditRaiseNhis',
        component: CreditRaiseNhis,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseNps',
        name: 'creditRaiseNps',
        component: CreditRaiseNps,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseNts',
        name: 'creditRaiseNts',
        component: CreditRaiseNts,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseRetry',
        name: 'creditRaiseRetry',
        component: CreditRaiseRetry,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'raiseResult',
        name: 'creditRaiseResult',
        component: CreditRaiseResult,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselMain',
        name: 'creditCounselMain',
        component: CreditCounselMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselInfo',
        name: 'creditCounselInfo',
        component: CreditCounselInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselReqStep1',
        name: 'creditCounselReqStep1',
        component: CreditCounselReqStep1,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselReqStep2',
        name: 'creditCounselReqStep2',
        component: CreditCounselReqStep2,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselReqStep3',
        name: 'creditCounselReqStep3',
        component: CreditCounselReqStep3,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselReqStep4',
        name: 'creditCounselReqStep4',
        component: CreditCounselReqStep4,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselReqStep5',
        name: 'creditCounselReqStep5',
        component: CreditCounselReqStep5,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'counselResult',
        name: 'creditCounselResult',
        component: CreditCounselResult,
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
        name: 'debtMain',
        component: DebtMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'detail',
        name: 'debtDetail',
        component: DebtDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'repayment',
        name: 'debtRepayment',
        component: DebtRepayment,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'modify',
        name: 'debtModify',
        component: DebtModify,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'calc',
        name: 'debtCalc',
        component: DebtCalc,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'calcSearch',
        name: 'debtCalcSearch',
        component: DebtCalcSearch,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'register',
        name: 'debtRegister',
        component: DebtRegister,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'regDetail',
        name: 'debtRegDetail',
        component: DebtRegDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'reqIntrCut',
        name: 'debtReqIntrCut',
        component: DebtReqIntrCut,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/memo',
    component: MemoHome,
    children: [{
        path: 'register',
        name: 'memoRegister',
        component: MemoRegister,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'list',
        name: 'memoList',
        component: MemoList,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'detail',
        name: 'memoDetail',
        component: MemoDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/consume',
    component: ConsumeHome,
    children: [{
        path: 'main',
        name: 'consumeMain',
        component: ConsumeMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'regGoal',
        name: 'consumeRegGoal',
        component: ConsumeRegGoal,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'consumeDetail',
        name: 'consumeConsumeDetail',
        component: ConsumeConsumeDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'analyze',
        name: 'consumeAnalyze',
        component: ConsumeAnalyze,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'setting',
        name: 'consumeSetting',
        component: ConsumeSetting,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'consumeClass',
        name: 'consumeConsumeClass',
        component: ConsumeConsumeClass,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'incomeClass',
        name: 'consumeIncomeClass',
        component: ConsumeIncomeClass,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'payment',
        name: 'consumePayment',
        component: ConsumePayment,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'paymentDetail',
        name: 'consumePaymentDetail',
        component: ConsumePaymentDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'settlement',
        name: 'consumeSettlement',
        component: ConsumeSettlement,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'consumeIncomeStats',
        name: 'consumeIncomeStats',
        component: ConsumeIncomeStats,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/assets',
    component: AssetsHome,
    children: [{
        path: 'main',
        name: 'assetsMain',
        component: AssetsMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'bankMain',
        name: 'assetsBankMain',
        component: AssetsBankMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'bankActDetail',
        name: 'assetsBankActDetail',
        component: AssetsBankActDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'bankDepWdrlList',
        name: 'assetsBankDepWdrlList',
        component: AssetsBankDepWdrlList,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'bankDepWdrlDetail',
        name: 'assetsBankDepWdrlDetail',
        component: AssetsBankDepWdrlDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockMain',
        name: 'assetsStockMain',
        component: AssetsStockMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockBalcDetail',
        name: 'assetsStockBalcDetail',
        component: AssetsStockBalcDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockActDetail',
        name: 'assetsStockActDetail',
        component: AssetsStockActDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockShrDetail',
        name: 'assetsStockShrDetail',
        component: AssetsStockShrDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockFndDetail',
        name: 'assetsStockFndDetail',
        component: AssetsStockFndDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'etcMain',
        name: 'assetsEtcMain',
        component: AssetsEtcMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'dirInput',
        name: 'assetsDirInput',
        component: AssetsDirInput,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockAnalysis',
        name: 'assetsStockAnalysis',
        component: AssetsStockAnalysis,
        meta: {
          allowPath: true
          // requiresAuth: true
        }
      },
      {
        path: 'stockSpcfAnls',
        name: 'assetsStockSpcfAnls',
        component: AssetsStockSpcfAnls,
        meta: {
          allowPath: true
          // requiresAuth: true
        }
      },
      {
        path: 'stockIvtmBhvr',
        name: 'assetsStockIvtmBhvr',
        component: AssetsStockIvtmBhvr,
        meta: {
          allowPath: true
          // requiresAuth: true
        }
      },
      {
        path: 'stockGoalSet',
        name: 'assetsStockGoalSet',
        component: AssetsStockGoalSet,
        meta: {
          allowPath: true
          // requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/goods',
    component: GoodsHome,
    children: [{
        path: 'list',
        name: 'goodsList',
        component: GoodsList,
        meta: {
          allowPath: true,
          requiresAuth: true,
          backCasePath: true
        }
      },
      {
        path: 'detail',
        name: 'GoodsDetail',
        component: GoodsDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stockDetail',
        name: 'GoodsStockDetail',
        component: GoodsStockDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'certStep1',
        name: 'GoodsCertStep1',
        component: GoodsCertStep1,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'certStep2',
        name: 'GoodsCertStep2',
        component: GoodsCertStep2,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'creditReqInfo',
        name: 'GoodsCreditReqInfo',
        component: GoodsCreditReqInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'creditJobIncome',
        name: 'GoodsCreditJobIncome',
        component: GoodsCreditJobIncome,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'creditSrcJobNm',
        name: 'GoodsCreditSrcJobNm',
        component: GoodsCreditSrcJobNm,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'creditInsJobNm',
        name: 'GoodsCreditInsJobNm',
        component: GoodsCreditInsJobNm,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'hsnInsReqInfo',
        name: 'GoodsHsnInsReqInfo',
        component: GoodsHsnInsReqInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'hsnInsHsnInfo',
        name: 'GoodsHsnInsHsnInfo',
        component: GoodsHsnInsHsnInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'hsnInsIncome',
        name: 'GoodsHsnInsIncome',
        component: GoodsHsnInsIncome,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'hsnInsRepay',
        name: 'GoodsHsnInsRepay',
        component: GoodsHsnInsRepay,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'loading',
        name: 'GoodsLoading',
        component: GoodsLoading,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'result',
        name: 'GoodsResult',
        component: GoodsResult,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'reqDone',
        name: 'GoodsReqDone',
        component: GoodsReqDone,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'workergoods',
        name: 'workergoods',
        component: GoodsWorkerGoods,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock1',
        name: 'goodsStock1',
        component: GoodsStock1,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock2',
        name: 'goodsStock2',
        component: GoodsStock2,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock3',
        name: 'goodsStock3',
        component: GoodsStock3,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock4',
        name: 'goodsStock4',
        component: GoodsStock4,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock5',
        name: 'goodsStock5',
        component: GoodsStock5,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock6',
        name: 'goodsStock6',
        component: GoodsStock6,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock7',
        name: 'goodsStock7',
        component: GoodsStock7,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock8',
        name: 'goodsStock8',
        component: GoodsStock8,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'stock9',
        name: 'goodsStock9',
        component: GoodsStock9,
        meta: {
          allowPath: true,
          requiresAuth: true
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
        name: 'shareMain',
        component: ShareMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'history',
        name: 'shareHistory',
        component: ShareHistory,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'newRequest',
        name: 'shareNewRequest',
        component: ShareNewRequest,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'reqSetting',
        name: 'shareReqSetting',
        component: ShareReqSetting,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'offerSetting',
        name: 'shareOfferSetting',
        component: ShareOfferSetting,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'detail',
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
        name: 'etcAlarmHistory',
        component: EtcAlarmHistory,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'noticeMain',
        name: 'etcNoticeMain',
        component: EtcNoticeMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'noticeDetail',
        name: 'etcNoticeDetail',
        component: EtcNoticeDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'eventDetail',
        name: 'etcEventDetail',
        component: EtcEventDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'faqMain',
        name: 'etcFaqMain',
        component: EtcFaqMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'faqList',
        name: 'etcFaqList',
        component: EtcFaqList,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'faqDetail',
        name: 'etcFaqDetail',
        component: EtcFaqDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'term',
        name: 'etcTerm',
        component: EtcTerm,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/scrap',
    component: ScrapHome,
    children: [{
        path: 'certStep',
        name: 'scrapCertStep',
        component: ScrapCertStep,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'fcLink',
        name: 'scrapFcLink',
        component: ScrapFcLink,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'resultFcLink',
        name: 'scrapResultFcLink',
        component: ScrapResultFcLink,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'ctrlFcLink',
        name: 'scrapCtrlFcLink',
        component: ScrapCtrlFcLink,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'loading',
        name: 'scrapLoading',
        component: ScrapLoading,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'regFcLink',
        name: 'scrapRegFcLink',
        component: ScrapRegFcLink,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'selFcLink',
        name: 'scrapSelFcLink',
        component: ScrapSelFcLink,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'terms',
        name: 'scrapTerms',
        component: ScrapTerms,
        meta: {
          allowPath: true,
          requiresAuth: true
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
        name: 'mypageInfo',
        component: MypageInfo,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'email',
        name: 'mypageEmail',
        component: MypageEmail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'logout',
        name: 'mypageLogout',
        component: MypageLogout,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'regAlarm',
        name: 'mypageRegAlarm',
        component: MypageRegAlarm,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'drop',
        name: 'mypageDrop',
        component: MypageDrop,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'dropDone',
        name: 'mypageDropDone',
        component: MypageDropDone,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'cert',
        name: 'mypageCert',
        component: MypageCert,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'regCertLogin',
        name: 'mypageRegCertLogin',
        component: MypageRegCertLogin,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'certPerson',
        name: 'mypageCertPerson',
        component: MypageCertPerson,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'chgPwd',
        name: 'mypageChgPwd',
        component: MypageChgPwd,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'favGoods',
        name: 'mypageFavGoods',
        component: MypageFavGoods,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'favDetail',
        name: 'MypageFavDetail',
        component: MypageFavDetail,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'rstlInqGoods',
        name: 'mypageRstlInqGoods',
        component: MypageRstlInqGoods,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'rstlInqSucc',
        name: 'mypageRstlInqSucc',
        component: MypageRstlInqSucc,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'rstlInqSucc',
        name: 'mypageRstlInqSucc',
        component: MypageRstlInqSucc,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'state',
        name: 'mypageState',
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
    path: '/sample/select',
    name: 'select',
    component: Select,
    meta: {
      allowPath: true
    }
  },
  {
    path: '/sample/datepicker',
    name: 'datepicker',
    component: Datepicker,
    meta: {
      allowPath: true
    }
  },
  {
    path: '/sample/money',
    name: 'money',
    component: Money,
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
        name: 'main',
        component: TemplateMain,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'chartSingleLine',
        name: 'chartSingleLine',
        component: TemplateChartSingleLine,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'chartSingleLine2',
        name: 'chartSingleLine2',
        component: TemplateChartSingleLine2,
        meta: {
          allowPath: true
        }
      },
      {
        path: 'chartMultipleBar',
        name: 'chartMultipleBar',
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
        name: 'newsMain',
        component: NewsMain,
        meta: {
          allowPath: true,
          requiresAuth: true
        }
      },
      {
        path: 'detail',
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
  if (from.path !== '/proxy' && to.path !== '/proxy') Store.state.proxyBackUrl = from.path
  if (from.path === '/proxy') Store.state.header.backPath = Store.state.proxyBackUrl
  else Store.state.header.backPath = ''
  if (to.path === '/index.html') {
    next('/home?hp=' + to.query.hp)
  } else {
    const hp = localStorage.getItem('hp')
    if (to.meta.allowPath) {
      if (to.meta.requiresAuth) {
        if (to.meta.backCasePath && !Store.state.header.fromPath) {
          Store.state.header.fromPath = from.fullPath
        }

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
