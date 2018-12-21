<template>
  <section>
    <div v-if="isInit" class="con-top cut-top">
      <p><em>아래의 조건 중 일부 부합이 되면</em><br>금리인하요구권을<br>신청할 수 있습니다</p>
      <a @click="openInfo()">금리인하요구권이란?</a>
    </div>

    <div v-if="!isInit && debtList.length==0" class="con-top cut-top">
      <p><em>조건에 맞는 부채가 존재하지 않습니다.</em></p>
    </div>
    <div v-if="!isInit && debtList.length!=0" class="cut-result-top">
      <div v-for="debtInfo in debtList" :key="debtInfo.index" class="item">
        <div class="top">
          <p class="symbol"><img :src="debtInfo.fcImg" alt="" />{{debtInfo.nm_fc}}</p>
          <p class="date">{{formatDateDot(debtInfo.ymd_loan)}}</p>
        </div>
        <div class="goods-benefit">
          <div>{{debtInfo.ever_interest}}<em> %</em></div>
          <div>{{formatNumber(debtInfo.amt_contract)}}<em> 만원</em></div>
        </div>
        <a v-if="'' != debtInfo.tel && debtInfo.tel != null" @click="callFc(debtInfo.tel)" class="btn">전화로 문의하기</a>
        <div class="cate-tag">
          <em v-for="cutItem in debtInfo.cutItems" :key="cutItem.index">{{getCodeName(cutItem)}}</em>
        </div>
      </div>
    </div>

    <div class="accodion">
      <div class="info-massage">아래의 기준은 은행별로 상이 할 수 있습니다.</div>
      <ul>
        <li>
          <div class="top"><span :class="{on: ynCredit}">최근에 신용등급이 오르셨나요 ?</span><em @click="closeItem('credit')"></em></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <datepicker v-model="creditFixDate" ref="creditFixOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
                <button @click="openCreditFixPicker" class="cal"></button>
              </p>
            </div>
            <p class="text">최근 신용등급이 2등급 이상 오른 경우 상승하기 전에 개설 했던 대출에 대해서 금리인하권을 요구 할 수 있습니다.</p>
          </div>
        </li>
        <li>
          <div class="top"><span :class="{on: ynTurnover}">최근 직장 변동이 있으셨나요 ?</span><em @click="closeItem('turnover')"></em></div>
          <div class="con">
            <div class="flex">
              <p>이직일</p>
              <p>
                <datepicker v-model="turnoverDate" ref="turnoverOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
                <button @click="openTurnoverPicker" class="cal"></button>
              </p>
            </div>
            <!--
                      <div class="flex">
                          <p>이전직장</p>
                          <p><button class="search"></button></p>
                      </div>
                      <div class="flex">
                          <p>현재직장</p>
                          <p><button class="search"></button></p>
                      </div>
                      -->
            <!-- <p class="text">신용등급이 향상된 직장으로 이직한 경우 요구 가능합니다.</p> -->
          </div>
        </li>
        <li>
          <div class="top"><span :class="{on: ynIncome}">최근 연소득이 증가하셨나요 ?</span><em @click="closeItem('income')"></em></div>
          <div class="con">
            <!--
                      <div class="flex">
                          <p>기준일</p>
                          <p>
                              <select>
                                  <option>2018-08-01</option>
                              </select>
                          </p>
                      </div>
                      <div class="flex">
                          <p>변경전</p>
                          <p><input type="text"><em class="pl5">원</em></p>
                      </div>
                      -->
            <div class="flex">
              <p>연소득</p>
              <!-- <p><input type="number" v-model="income" v-validate="'required'" data-vv-name='연소득'><em class="pl5">원</em></p>
              <p class="warn" v-if="errors.has('연소득')">{{errors.first('연소득')}}</p> -->
              <!-- <p><input type="number" v-model="income" autocomplete="off"><em class="pl5">원</em></p> -->
              <p>
                <money v-model="income" /><em class="pl5">원</em></p>
            </div>
            <p class="text">신규 및 연장 시점 대비 연소득이 15% 이상 상승된 경우 요구가 가능합니다.</p>
          </div>
        </li>
        <li>
          <div class="top"><span :class="{on: ynDebt}">최근 부채가 감소하셨나요 ?</span><em @click="closeItem('debt')"></em></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <datepicker v-model="debtFixDate" ref="debtFixOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
                <button @click="openDebtFixPicker" class="cal"></button>
              </p>
            </div>
            <div class="flex">
              <p>변경전</p>
              <!-- <p><input type="number" v-model="debtBfAmt" v-validate="'required'" data-vv-name='변경전 금액'><em class="pl5">원</em></p>
              <p class="warn" v-if="errors.has('변경전 금액')">{{errors.first('변경전 금액')}}</p> -->
              <!-- <p><input type="number" v-model="debtBfAmt" autocomplete="off"><em class="pl5">원</em></p> -->
              <p>
                <money v-model="debtBfAmt" /><em class="pl5">원</em></p>
            </div>
            <div class="flex">
              <p>변경후</p>
              <!-- <p><input type="number" v-model="debtAtAmt" v-validate="'required'" data-vv-name='변경후 금액'><em class="pl5">원</em></p>
              <p class="warn" v-if="errors.has('변경후 금액')">{{errors.first('변경후 금액')}}</p> -->
              <!-- <p><input type="number" v-model="debtAtAmt" autocomplete="off"><em class="pl5">원</em></p> -->
              <p>
                <money v-model="debtAtAmt" /><em class="pl5">원</em></p>
            </div>
            <p class="text">신규 및 연장 시점 대비 부채가 현저히 낮아진 경우(15%) 요구가 가능합니다.</p>
          </div>
        </li>
        <li>
          <div class="top"><span :class="{on: ynPos}">동일 직장내에서 직위가 상승 하셨나요 ?</span><em @click="closeItem('pos')"></em></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <datepicker v-model="posFixDate" ref="posFixOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
                <button @click="openPosFixPicker" class="cal"></button>
              </p>
            </div>
            <p class="text">동일 직장내 직위가 상승한 경우 요구가 가능합니다.</p>
          </div>
        </li>
        <li>
          <div class="top"><span :class="{on: ynCert}">전문 자격증을 취득하셨나요 ?</span><em @click="closeItem('cert')"></em></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <datepicker v-model="certFixDate" ref="certFixOpen" :language="ko" :format="formatDate" class="div-date"></datepicker>
                <button @click="openCertFixPicker" class="cal"></button>
              </p>
            </div>
            <div class="flex">
              <p>자격증</p>
              <p>
                <multiselect :id="'certification'" v-model="certification" :title="'자격증'" placeholder="자격증선택" :options="certList" :onClose="onSelectCert">
                </multiselect>
              </p>
            </div>
            <p class="text">은행에서 정하는 직업평가기준표에 의한 전문자격증을 취득한 경우 요구가 가능합니다.</p>
          </div>
        </li>
      </ul>
    </div>

    <div class="btn-wrap">
      <a @click="init()" class="stroke blue box">초기화</a>
      <a @click="getReqIntrCut()" class="solid blue box mt10">검색</a>
    </div>

    <vue-modal transitionName="zoom-in" name="info-modal" v-on:popclose="closeInfo()">
      <ReqIntrCutInfo slot="body" v-on:popclose="closeInfo()"></ReqIntrCutInfo>
    </vue-modal>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import korean from "vee-validate/dist/locale/ko.js";

import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

import ReqIntrCutInfo from "./ReqIntrCutInfo.vue";

export default {
  name: "debtReqIntrCut",
  data() {
    return {
      Common: Common,
      ko: ko,
      isInit: true, //초기화면 여부
      currentDate: "", //오늘날짜
      ynCredit: false, //신용 항목
      ynTurnover: false, //이직 항목
      ynIncome: false, //연소득 항목
      ynDebt: false, //부채 항목
      ynPos: false, //직위 항목
      ynCert: false, //자격증 항목
      creditFixDate: "", //신용 기준일
      turnoverDate: "", //이직일
      income: "", //연소득
      debtFixDate: "", //부채 기준일
      debtBfAmt: "", //부채 변경전 금액
      debtAtAmt: "", //부채 변경후 금액
      posFixDate: "", //직위상승 기준일
      certFixDate: "", //자격증 기준일
      certList: [
        { text: "선택", value: "" },
        { text: "변호사", value: "01" },
        { text: "의사(치과의사 포함)", value: "02" },
        { text: "한의사", value: "03" },
        { text: "공인회계사", value: "04" },
        { text: "세무사", value: "05" },
        { text: "변리사", value: "06" },
        { text: "관세사", value: "07" },
        { text: "법무사", value: "08" },
        { text: "감정평가사", value: "09" },
        { text: "공인노무사", value: "10" },
        { text: "건축사", value: "11" },
        { text: "노무사", value: "12" },
        { text: "기술사", value: "13" },
        { text: "행정사", value: "14" },
        { text: "손해사정인", value: "15" }
      ], //자격증list
      certification: "", //자격증
      debtList: [] //부채list
    };
  },
  components: {
    datepicker: datepicker,
    ReqIntrCutInfo: ReqIntrCutInfo
  },
  watch: {
    //신용
    creditFixDate: function() {
      if ("" != this.creditFixDate) {
        this.ynCredit = true;
      } else {
        this.ynCredit = false;
      }
    },
    //이직
    turnoverDate: function() {
      if ("" != this.turnoverDate) {
        this.ynTurnover = true;
      } else {
        this.ynTurnover = false;
      }
    },
    //연소득
    income: function() {
      if ("" != this.income) {
        this.ynIncome = true;
      } else {
        this.ynIncome = false;
      }
    },
    //부채
    debtFixDate: function() {
      if (
        "" != this.debtFixDate &&
        "" != this.debtBfAmt &&
        "" != this.debtAtAmt
      ) {
        this.ynDebt = true;
      } else {
        this.ynDebt = false;
      }
    },
    debtBfAmt: function() {
      if (
        "" != this.debtFixDate &&
        "" != this.debtBfAmt &&
        "" != this.debtAtAmt
      ) {
        this.ynDebt = true;
      } else {
        this.ynDebt = false;
      }
    },
    debtAtAmt: function() {
      if (
        "" != this.debtFixDate &&
        "" != this.debtBfAmt &&
        "" != this.debtAtAmt
      ) {
        this.ynDebt = true;
      } else {
        this.ynDebt = false;
      }
    },
    //직위
    posFixDate: function() {
      if ("" != this.posFixDate) {
        this.ynPos = true;
      } else {
        this.ynPos = false;
      }
    },
    //자격증
    certFixDate: function() {
      if (
        "" != this.certFixDate &&
        ("" != this.certification && "" != this.certification.value)
      ) {
        this.ynCert = true;
      } else {
        this.ynCert = false;
      }
    },
    certification: function() {
      if (
        "" != this.certFixDate &&
        ("" != this.certification && "" != this.certification.value)
      ) {
        this.ynCert = true;
      } else {
        this.ynCert = false;
      }
    }
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금리인하요구";
  },
  created() {
    this.setCurrentDate();
  },
  beforeMount() {},
  mounted() {
    Common.datepickerInit("div-date", this);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //오늘날짜 셋팅
    setCurrentDate: function() {
      var _this = this;
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth() + 1; //January is 0!
      var yyyy = today.getFullYear();

      if (dd < 10) {
        dd = "0" + dd;
      }

      if (mm < 10) {
        mm = "0" + mm;
      }

      _this.currentDate = yyyy + "-" + mm + "-" + dd;
    },
    //datepicker
    openCreditFixPicker: function() {
      this.$refs.creditFixOpen.showCalendar();
    },
    openTurnoverPicker: function() {
      this.$refs.turnoverOpen.showCalendar();
    },
    openDebtFixPicker: function() {
      this.$refs.debtFixOpen.showCalendar();
    },
    openPosFixPicker: function() {
      this.$refs.posFixOpen.showCalendar();
    },
    openCertFixPicker: function() {
      this.$refs.certFixOpen.showCalendar();
    },
    //multiselect
    onSelectCert: function(option) {
      var _this = this;
      _this.certification = option;
    },
    //금리인하list 조회
    getReqIntrCut: function() {
      var _this = this;
      var cutItems = [];
      if (!_this.validate()) return false;

      if (_this.ynCredit) cutItems.push("01");
      if (_this.ynTurnover) cutItems.push("02");
      if (_this.ynIncome) cutItems.push("03");
      if (_this.ynDebt) cutItems.push("04");
      if (_this.ynPos) cutItems.push("05");
      if (_this.ynCert) cutItems.push("06");

      console.log("cutItems" + cutItems);
      console.log("creditFixDate" + Common.formatDateDB(_this.creditFixDate));
      console.log("turnoverDate" + Common.formatDateDB(_this.turnoverDate));
      console.log("income" + _this.income);
      console.log("debtFixDate" + Common.formatDateDB(_this.debtFixDate));
      console.log("posFixDate" + Common.formatDateDB(_this.posFixDate));
      console.log("certFixDate" + Common.formatDateDB(_this.certFixDate));

      var formData = new FormData();
      formData.append("cutItems", cutItems);
      formData.append(
        "creditFixDate",
        Common.formatDateDB(_this.creditFixDate)
      );
      formData.append("turnoverDate", Common.formatDateDB(_this.turnoverDate));
      formData.append("income", _this.income);
      formData.append("debtFixDate", Common.formatDateDB(_this.debtFixDate));
      formData.append("posFixDate", Common.formatDateDB(_this.posFixDate));
      formData.append("certFixDate", Common.formatDateDB(_this.certFixDate));

      this.$http
        .post("/m/debt/listReqIntrCut.json", formData)
        .then(function(response) {
          //금융사ICON 셋팅
          var list = response.data.debtList;
          for (var i = 0; i < list.length; i++) {
            list[i].fcImg =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
          }
          _this.debtList = list;

          _this.isInit = false;

          console.log(_this.isInit);
          console.log(_this.debtList);
        })
        .catch(e => {
          _this.$toast.center(korean.messages.error);
        });
    },
    validate: function() {
      var _this = this;
      if (
        !_this.ynCredit &&
        !_this.ynTurnover &&
        !_this.ynIncome &&
        !_this.ynDebt &&
        !_this.ynPos &&
        !_this.ynCert
      ) {
        _this.$toast.center("항목을 입력해 주세요");
        return false;
      }
      if (_this.ynDebt) {
        console.log(Number(_this.debtAtAmt));
        console.log(Number(_this.debtBfAmt));
        //부채
        if (Number(_this.debtBfAmt) * 0.85 < Number(_this.debtAtAmt)) {
          _this.$toast.center("부채 감소 기준에 부합하지 않습니다");
          return false;
        }
      }
      if (_this.ynCert) {
        if ("" == _this.certification) {
          _this.$toast.center("자격증을 선택해 주세요");
          return false;
        }
      }

      return true;
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDate: function(data) {
      return Common.formatDate(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    getCodeName: function(data) {
      return Common.getCodeName("debt_cut_items", data);
    },
    //info
    openInfo: function() {
      var _this = this;
      _this.$modals.show("info-modal");
    },
    closeInfo: function() {
      var _this = this;
      _this.$modals.hide("info-modal");
    },
    //금융사 연결
    callFc: function(tel) {
      console.log("tel" + tel);

      if (Constant.userAgent == "iOS") {
        Jockey.send("phoneCall", {
          phNum: tel
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.phoneCall(tel);
      }
    },
    closeItem: function(item) {
      var _this = this;
      // var currentDate = _this.currentDate;

      if ("credit" == item) {
        _this.creditFixDate = "";
      } else if ("turnover" == item) {
        _this.turnoverDate = "";
      } else if ("income" == item) {
        _this.income = "";
      } else if ("debt" == item) {
        _this.debtFixDate = "";
        _this.debtBfAmt = "";
        _this.debtAtAmt = "";
      } else if ("pos" == item) {
        _this.posFixDate = "";
      } else if ("cert" == item) {
        _this.certFixDate = "";
        _this.certification = "";
      } else if ("init" == item) {
        //신용
        _this.creditFixDate = "";
        //이직
        _this.turnoverDate = "";
        //연소득
        _this.income = "";
        //부채
        _this.debtFixDate = "";
        _this.debtBfAmt = "";
        _this.debtAtAmt = "";
        //직위
        _this.posFixDate = "";
        //자격증
        _this.certFixDate = "";
        _this.certification = "";
      }
    },
    //초기화
    init: function() {
      var _this = this;

      _this.isInit = true;
      $("li").removeClass("on");
      _this.closeItem("init");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
