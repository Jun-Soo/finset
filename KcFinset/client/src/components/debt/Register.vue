<template>
  <section>
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>친구에게 빌려준 돈을 등록하고</em><br>편리하게 관리하세요</p>
        <div class="list">
          <ul class="flex">
            <li class="key">이름</li>
            <li class="value">
              <input type="text" placeholder="친구를 검색하세요" v-model="debtVO.name">
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
              <input type="text" placeholder="이자를 입력하세요" v-model="debtVO.interest">
              <button @click="changeInterType('percent')" class="onoff per" :class="{'on':inter_type == 'percent'}">%</button>
              <button @click="changeInterType('won')" class="onoff won" :class="{'on':inter_type == 'won'}">원</button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">계약일</li>
            <li class="value">
              <input type="text" placeholder="계약일을 입력하세요" v-model="debtVO.ymd_loan">
              <button class="cal"></button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">만기일</li>
            <li class="value">
              <input type="text" placeholder="만기일을 입력하세요" v-model="debtVO.ymd_loanend">
              <button class="cal"></button>
            </li>
          </ul>
        </div>

        <div class="debt-acco">
          <ul>
            <li>
              <div class="top">
                <a href="#" data-acco="0">추가 항목</a>
              </div>
              <div class="body">
                <div class="flex">
                  <p>상환방법</p>
                  <p>
                    <select v-model="debtVO.rep_method">
                      <option value="00" disabled="disabled">선택</option>
                      <option value="01">만기일시상환</option>
                      <option value="02">원리금분할상환</option>
                      <option value="03">원금분할상환</option>
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

export default {
  name: "DebtRegister",
  data() {
    return {
      debtVO: {
        rep_method: "00",
        inter_pay_cycle: "00",
        inter_pay_day: "00"
      },
      inter_type: "percent"
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "기타부채등록";
  },
  created() {
    window.resultAddress = this.resultAddress;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    saveDebt: function() {
      console.log(this.debtVO);
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
