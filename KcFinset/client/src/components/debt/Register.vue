<template>
  <section>
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>친구에게 빌려준 돈을 등록하고</em><br>편리하게 관리하세요</p>
        <div class="list">
          <ul class="flex">
            <li class="key">이름</li>
            <li class="value">
              <input type="text" placeholder="친구를 검색하세요" v-model="debtVO.creditor">
              <button @click="getAddressList" class="human"></button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">금액</li>
            <li class="value">
              <input type="text" placeholder="거래금액을 입력하세요" v-model="debtVO.amt_contract">
              <em>만원</em>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">이자</li>
            <li class="value">
              <input v-if="inter_type == 'percent'" type="text" placeholder="이자를 입력하세요" v-model="debtVO.interest">
              <input v-if="inter_type == 'won'" type="text" placeholder="이자를 입력하세요" v-model="debtVO.all_amt_repay_i">
              <button @click="changeInterType('percent')" class="onoff per" :class="{'on':inter_type == 'percent'}">%</button>
              <button @click="changeInterType('won')" class="onoff won" :class="{'on':inter_type == 'won'}">원</button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">계약일</li>
            <li class="value">
              <!-- <input type="text" placeholder="계약일을 입력하세요" v-model="debtVO.ymd_loan"> -->
              <datepicker v-model="debtVO.ymd_loan" :language="ko" :format="Common.formatDateDot" class="div-date" :placeholder="'계약일을 입력하세요'"></datepicker>
              <button class="cal"></button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">만기일</li>
            <li class="value">
              <!-- <input type="text" placeholder="만기일을 입력하세요" v-model="debtVO.ymd_loanend"> -->
              <datepicker v-model="debtVO.ymd_loanend" :language="ko" :format="Common.formatDateDot" class="div-date" :placeholder="'계약일을 입력하세요'"></datepicker>
              <button class="cal"></button>
            </li>
          </ul>
        </div>

        <div class="debt-acco">
          <ul>
            <li>
              <div class="top">
                <a @click="toggleAcco">추가 항목</a>
              </div>
              <div class="body">
                <div class="flex">
                  <p>상환방법</p>
                  <p>
                    <select @change="chgRepMethod" v-model="debtVO.rep_method">
                      <option value="00" disabled="disabled">선택</option>
                      <option value="03">만기일시상환</option>
                      <option value="01">원리금분할상환</option>
                      <option value="02">원금분할상환</option>
                    </select>
                  </p>
                </div>
                <div class="flex">
                  <p>이자납입주기</p>
                  <p>
                    <select v-model="debtVO.inter_pay_cycle">
                      <option value="00" disabled="disabled">선택</option>
                      <option value="01">매월</option>
                      <option value="02">분기</option>
                      <option value="03">년</option>
                      <option value="04">만기시</option>
                      <option value="05">특정일</option>
                    </select>
                  </p>
                </div>
                <div class="flex">
                  <p>이자납입일</p>
                  <p>
                    <select v-model="debtVO.inter_pay_day">
                      <option value="00" disabled="disabled">선택</option>
                      <option :value="(n+'').length==1?'0'+n:n" v-for="n in 31" :key="n" v-text="n + '일'"></option>
                    </select>
                  </p>
                </div>
              </div>
            </li>
          </ul>
        </div>

      </div>
      <div class="btn-wrap float">
        <a @click="saveDebt" class="solid blue box">등록하기</a>
      </div>
    </div>

  </section>
</template>

<script>
import Constant from "@/assets/js/constant.js";
import Common from "@/assets/js/common.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

export default {
  name: "DebtRegister",
  data() {
    return {
      ko: ko,
      debtVO: {
        rep_method: "00",
        inter_pay_cycle: "00",
        inter_pay_day: "00"
      },
      inter_type: "percent",
      Common: Common
    };
  },
  components: {
    datepicker
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "기타부채등록";
  },
  created() {
    window.resultAddress = this.resultAddress;
    this.ko.ymd = true;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    saveDebt: function() {
      var _this = this;

      this.debtVO.ymd_loan = Common.formatDate(this.debtVO.ymd_loan);
      this.debtVO.ymd_loanend = Common.formatDate(this.debtVO.ymd_loanend);
      this.debtVO.inter_type = this.inter_type;

      this.$router.push({
        path: "/debt/regDetail",
        query: { debtVO: _this.debtVO }
      });
    },
    changeInterType: function(type) {
      if (type == this.inter_type) {
        return;
      }
      this.inter_type = type;
    },
    getAddressList: function() {
      if (Constant.userAgent == "iOS") {
        Jockey.on("getAddressList", function(param) {
          resultAddress(param.src_nm_person, param.src_hp);
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.getAddressList();
      }
    },
    resultAddress: function(nm_person, hp) {
      this.debtVO.name = nm_person;
    },
    toggleAcco: function(param) {
      var btn = $(param.target);
      if (btn.hasClass("on")) {
        btn
          .closest("li")
          .find(".body")
          .slideUp(500, "easeInOutExpo");
        btn.removeClass("on");
      } else {
        btn
          .closest("li")
          .find(".body")
          .slideDown(500, "easeInOutExpo");
        btn.addClass("on");
      }
      param.preventDefault();
    },
    chgRepMethod: function() {
      var key = this.debtVO.rep_method;
      if (key == "03") {
        this.debtVO.inter_pay_cycle = "04";
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.vdp-datepicker__calendar {
  position: fixed;
  font-size: 13px;
  line-height: 40px;
}
.vdp-datepicker__calendar header {
  position: static;
}
.div-date {
  text-align: right;
}
</style>
