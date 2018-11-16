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
      </div>
    </div>

    <div class="accodion" style="margin-bottom:100px;">
      <div class="info-massage">아래의 기준은 은행별로 상이 할 수 있습니다.</div>
      <ul>
        <li :class="{on: ynCredit}">
          <div class="top"><a @click="changeItems('credit')">최근에 신용등급이 오르셨나요 ?</a></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <input type="text" v-model="creditFixDate" readonly="readonly">
              </p>
            </div>
            <p class="text">최근 신용등급이 2등급 이상 오른 경우 상승하기 전에 개설 했던 대출에 대해서 금리인하권을 요구 할 수 있습니다.</p>
          </div>
        </li>
        <li :class="{on: ynTurnover}">
          <div class="top"><a @click="changeItems('turnover')">최근 직장 변동이 있으셨나요 ?</a></div>
          <div class="con">
            <div class="flex">
              <p>이직일</p>
              <p>
                <input type="text" v-model="turnoverDate" readonly="readonly">
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
        <li :class="{on: ynIncome}">
          <div class="top"><a @click="changeItems('income')">최근 연소득이 증가하셨나요 ?</a></div>
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
                          <p><input type="text" placeholder="원"></p>
                      </div>
                      -->
            <div class="flex">
              <p>연소득</p>
              <!-- <p><input type="number" v-model="income" placeholder="원" v-validate="'required'" data-vv-name='연소득'></p>
              <p class="warn" v-if="errors.has('연소득')">{{errors.first('연소득')}}</p> -->
              <p><input type="number" v-model="income" placeholder="원" autocomplete="off"></p>
            </div>
            <p class="text">신규 및 연장 시점 대비 연소득이 15% 이상 상승된 경우 요구가 가능합니다.</p>
          </div>
        </li>
        <li :class="{on: ynDebt}">
          <div class="top"><a @click="changeItems('debt')">최근 부채가 감소하셨나요 ?</a></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <input type="text" v-model="debtFixDate" readonly="readonly">
              </p>
            </div>
            <div class="flex">
              <p>변경전</p>
              <!-- <p><input type="number" v-model="debtBfAmt" placeholder="원" v-validate="'required'" data-vv-name='변경전 금액'></p>
              <p class="warn" v-if="errors.has('변경전 금액')">{{errors.first('변경전 금액')}}</p> -->
              <p><input type="number" v-model="debtBfAmt" placeholder="원" autocomplete="off"></p>
            </div>
            <div class="flex">
              <p>변경후</p>
              <!-- <p><input type="number" v-model="debtAtAmt" placeholder="원" v-validate="'required'" data-vv-name='변경후 금액'></p>
              <p class="warn" v-if="errors.has('변경후 금액')">{{errors.first('변경후 금액')}}</p> -->
              <p><input type="number" v-model="debtAtAmt" placeholder="원" autocomplete="off"></p>
            </div>
            <p class="text">신규 및 연장 시점 대비 부채가 현저히 낮아진 경우(15%) 요구가 가능합니다.</p>
          </div>
        </li>
        <li :class="{on: ynPos}">
          <div class="top"><a @click="changeItems('pos')">동일 직장내에서 직위가 상승 하셨나요 ?</a></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <input type="text" v-model="posFixDate" readonly="readonly">
              </p>
            </div>
            <p class="text">동일 직장내 직위가 상승한 경우 요구가 가능합니다.</p>
          </div>
        </li>
        <li :class="{on: ynCert}">
          <div class="top"><a @click="changeItems('cert')">전문 자격증을 취득하셨나요 ?</a></div>
          <div class="con">
            <div class="flex">
              <p>기준일</p>
              <p>
                <input type="text" v-model="certFixDate" readonly="readonly">
              </p>
            </div>
            <div class="flex">
              <p>자격증</p>
              <p>
                <select v-model="certification" v-validate="'required'" data-vv-name='자격증'>
                  <option v-for="cert in certList" :key="cert.index" :value="cert.value">
                    {{ cert.text }}
                  </option>
                </select>
              </p>
              <p class="warn" v-if="errors.has('자격증')">{{errors.first('자격증')}}</p>
            </div>
            <p class="text">은행에서 정하는 직업평가기준표에 의한 전문자격증을 취득한 경우 요구가 가능합니다.</p>
          </div>
        </li>
      </ul>
    </div>

    <!--
      <div class="btn-wrap">
          <a href="#" class="solid blue box">검색</a>
      </div>
      -->
    <div class="btn-wrap col2">
      <a class="stroke" @click="init()">초기화</a>
      <a class="solid" @click="getReqIntrCut()">검색</a>
    </div>

    <vue-modal transitionName="zoom-in" name="info-modal" v-on:popclose="closeInfo()">
      <ReqIntrCutInfo slot="body" v-on:popclose="closeInfo()"></ReqIntrCutInfo>
    </vue-modal>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

import ReqIntrCutInfo from "./ReqIntrCutInfo.vue";

export default {
  name: "debtReqIntrCut",
  data() {
    return {
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
    ReqIntrCutInfo: ReqIntrCutInfo
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "금리인하요구";
  },
  created() {
    this.setCurrentDate();
    this.changeItems("init");
  },
  beforeMount() {},
  mounted() {},
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
      console.log("creditFixDate" + _this.creditFixDate);
      console.log("turnoverDate" + _this.turnoverDate);
      console.log("income" + _this.income);
      console.log("debtFixDate" + _this.debtFixDate);
      console.log("posFixDate" + _this.posFixDate);
      console.log("certFixDate" + _this.certFixDate);

      var formData = new FormData();
      formData.append("cutItems", cutItems);
      formData.append("creditFixDate", _this.creditFixDate);
      formData.append("turnoverDate", _this.turnoverDate);
      formData.append("income", _this.income);
      formData.append("debtFixDate", _this.debtFixDate);
      formData.append("posFixDate", _this.posFixDate);
      formData.append("certFixDate", _this.certFixDate);

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
          _this.$toast.center(ko.messages.error);
        });
    },
    validate: function() {
      var _this = this;
      if (_this.ynIncome) {
        //연소득
        if ("" == _this.income) {
          _this.$toast.center("연소득을 입력해 주세요");
          return false;
        }
      }
      if (_this.ynDebt) {
        //부채
        if ("" == _this.debtBfAmt) {
          _this.$toast.center("변경전 금액을 입력해 주세요");
          return false;
        }
        if ("" == _this.debtAtAmt) {
          _this.$toast.center("변경후 금액을 입력해 주세요");
          return false;
        }
        if (Number(_this.debtAtAmt) >= Number(_this.debtBfAmt) * 1.15) {
          _this.$toast.center("부채 감소 기준에 부합하지 않습니다");
          return false;
        }
      }
      //자격증
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
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
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
    //항목변경
    changeItems: function(item) {
      var _this = this;
      var currentDate = _this.currentDate;

      if ("credit" == item) {
        _this.ynCredit = !_this.ynCredit;
      } else if ("turnover" == item) {
        _this.ynTurnover = !_this.ynTurnover;
      } else if ("income" == item) {
        _this.ynIncome = !_this.ynIncome;
      } else if ("debt" == item) {
        _this.ynDebt = !_this.ynDebt;
      } else if ("pos" == item) {
        _this.ynPos = !_this.ynPos;
      } else if ("cert" == item) {
        _this.ynCert = !_this.ynCert;
      } else if ("init" == item) {
        _this.ynCredit = false;
        _this.ynTurnover = false;
        _this.ynIncome = false;
        _this.ynDebt = false;
        _this.ynPos = false;
        _this.ynCert = false;
      }

      if (!_this.ynCredit) {
        this.creditFixDate = currentDate;
      }
      if (!_this.ynTurnover) {
        _this.turnoverDate = currentDate;
      }
      if (!_this.ynIncome) {
        _this.income = "";
      }
      if (!_this.ynDebt) {
        _this.debtFixDate = currentDate;
        _this.debtBfAmt = "";
        _this.debtAtAmt = "";
      }
      if (!_this.ynPos) {
        _this.posFixDate = currentDate;
      }
      if (!_this.ynCert) {
        _this.certFixDate = currentDate;
        _this.certification = "";
      }
    },
    //초기화
    init: function() {
      var _this = this;

      _this.isInit = true;
      _this.changeItems("init");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
