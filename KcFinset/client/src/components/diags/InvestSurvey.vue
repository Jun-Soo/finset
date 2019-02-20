<template>
  <section>
    <div class="gray-box">
      <p class="title">귀하가 보유한 포트폴리오의 분석을 위해 설문조사를 진행합니다.</p>
      <p class="text">개인 투자 성향에 맞는 정확한 진단을 위함이니 아래 질문을 꼼꼼하게 읽어 보시고 솔직하게 답변을 선택해 주시길 바랍니다.</p>
    </div>

    <div class="basic-list noMG">
      <div class="body">
        <p class="key">1. 귀하의 투자 경험은 몇 년입니까?</p>
        <p>
          <multiselect :id="'investYr'" :title="'투자경험'" v-model="investYr" :options="investYrs" :onClose="selectClose" v-validate="'required'" data-vv-name="투자경험" />
        </p>
      </div>
      <div class="body">
        <p class="key">2. 투자 종목 선정 방법을 선택하세요.</p>
        <p>
          <multiselect :id="'stockSelect'" :title="'종목선정방법'" v-model="stockSelect" :options="stockSelects" :onClose="selectClose" v-validate="'required'" data-vv-name="종목선정방법" />
        </p>
      </div>
      <div class="body">
        <p class="key">3. 감당할 수 있는 최대한의 손실은 얼마인가요?</p>
        <p>
          <multiselect :id="'riskLimit'" :title="'리스크한계'" v-model="riskLimit" :options="riskLimits" :onClose="selectClose" v-validate="'required'" data-vv-name="리스크한계" />
        </p>
      </div>
      <div class="body">
        <p class="key">4. 보유 주식의 평균 보유 예정 기간은 얼마인가요?</p>
        <p>
          <multiselect :id="'holdPeriod'" :title="'평균보유예정기간'" v-model="holdPeriod" :options="holdPeriods" :onClose="selectClose" v-validate="'required'" data-vv-name="평균보유예정기간" />
        </p>
      </div>
      <div class="body">
        <p class="key">5. 목표 수익률은 얼마인가요? (1종목 당)
          &nbsp;&nbsp;&nbsp;<input type="text" style="height:20px; width:30%;" v-model="stockProfit">%
        </p>
      </div>
      <div class="body">
        <p class="key">6. 목표 수익률은 얼마인가요? (연간)</p>
        <p>
          <multiselect :id="'yrProfit'" :title="'연간목표수익률'" v-model="yrProfit" :options="yrProfits" :onClose="selectClose" v-validate="'required'" data-vv-name="연간목표수익률" />
        </p>
      </div>
      <div class="body">
        <p class="key">7. 주식 투자에 할애하는 시간은 얼마나 되나요? (1주일 당)</p>
        <p>
          <multiselect :id="'investTime'" :title="'주당투자시간'" v-model="investTime" :options="investTimes" :onClose="selectClose" v-validate="'required'" data-vv-name="주당투자시간" />
        </p>
      </div>
      <div class="body">
        <p class="key">8. 손절매를 실행하시나요?</p>
        <p>
          <multiselect :id="'losscut'" :title="'로스컷'" v-model="losscut" :options="losscuts" :onClose="selectClose" v-validate="'required'" data-vv-name="로스컷" />
        </p>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="saveInvestSurvey()" class="blue box solid">다음</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "investSurvey",
  data() {
    return {
      investYrs: [
        { text: "선택", value: "" },
        { text: "6개월이하", value: "11" },
        { text: "6개월~1년", value: "12" },
        { text: "1년~3년", value: "13" },
        { text: "3년~5년", value: "14" },
        { text: "5년~10년", value: "15" },
        { text: "10년~20년", value: "16" },
        { text: "20년이상", value: "17" }
      ],
      investYr: "",
      stockSelects: [
        { text: "선택", value: "" },
        { text: "본인 스스로 연구", value: "18" },
        { text: "지인추천", value: "19" },
        { text: "증권사등 기관추천", value: "20" }
      ],
      stockSelect: "",
      riskLimits: [
        { text: "선택", value: "" },
        { text: "손실감당불가", value: "21" },
        { text: "원금의 0~1%", value: "22" },
        { text: "원금의 1~1%", value: "23" },
        { text: "원금의 5~10%", value: "24" },
        { text: "원금의 10~20%", value: "25" },
        { text: "원금의 20~30%", value: "26" },
        { text: "원금의 30~50%", value: "27" },
        { text: "원금의 50~80%", value: "28" },
        { text: "원금의 80%이상", value: "29" }
      ],
      riskLimit: "",
      holdPeriods: [
        { text: "선택", value: "" },
        { text: "1일이내", value: "30" },
        { text: "1~3일", value: "31" },
        { text: "3일~1주", value: "32" },
        { text: "1~2주", value: "33" },
        { text: "2주~1개월", value: "34" },
        { text: "1~3개월", value: "35" },
        { text: "3~6개월", value: "36" },
        { text: "6개월~1년", value: "37" },
        { text: "1~2년", value: "38" },
        { text: "2~5년", value: "39" },
        { text: "5~10년", value: "40" },
        { text: "10년이상", value: "41" }
      ],
      holdPeriod: "",
      yrProfits: [
        { text: "선택", value: "" },
        { text: "3%미만", value: "42" },
        { text: "3~5%", value: "43" },
        { text: "5~10%", value: "44" },
        { text: "10~20%", value: "45" },
        { text: "20~30%", value: "46" },
        { text: "30%이상", value: "47" }
      ],
      yrProfit: "",
      investTimes: [
        { text: "선택", value: "" },
        { text: "1시간이내", value: "48" },
        { text: "1~2시간", value: "49" },
        { text: "2~5시간", value: "50" },
        { text: "5~20시간", value: "51" },
        { text: "20~40시간", value: "52" }
      ],
      investTime: "",
      losscuts: [
        { text: "선택", value: "" },
        { text: "원금손실즉시", value: "53" },
        { text: "원금5%이상손실시", value: "54" },
        { text: "원금10%이상손실시", value: "55" },
        { text: "원금20%이상손실시", value: "56" },
        { text: "원금50%이상손실시", value: "57" },
        { text: "손절매하지않음", value: "58" }
      ],
      losscut: "",
      stockProfit: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "투자성향 알아보기";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    validate: function() {
      var _this = this;

      if (_this.investYr == null || "" == _this.investYr.value) {
        _this.$toast.center("투자경험을 선택해 주세요");
        return false;
      } else if (_this.stockSelect == null || "" == _this.stockSelect.value) {
        _this.$toast.center("종목선정방법을 선택해 주세요");
        return false;
      } else if (_this.riskLimit == null || "" == _this.riskLimit.value) {
        _this.$toast.center("리스크한계를 선택해 주세요");
        return false;
      } else if (_this.holdPeriod == null || "" == _this.holdPeriod.value) {
        _this.$toast.center("평균보유예정기간을 선택해 주세요");
        return false;
      } else if ("" == _this.stockProfit) {
        _this.$toast.center("종목목표수익률을 선택해 주세요");
        return false;
      } else if (_this.yrProfit == null || "" == _this.yrProfit.value) {
        _this.$toast.center("연간목표수익율을 선택해 주세요");
        return false;
      } else if (_this.investTime == null || "" == _this.investTime.value) {
        _this.$toast.center("주당투자시간을 선택해 주세요");
        return false;
      } else if (_this.losscut == null || "" == _this.losscut.value) {
        _this.$toast.center("로스컷을 선택해 주세요");
        return false;
      }
      return true;
    },
    saveInvestSurvey: function() {
      var _this = this;
      if (!_this.validate()) return false;

      console.log("investYr=" + _this.investYr.value);

      var formData = new FormData();
      formData.append("investYr", _this.investYr.value);
      formData.append("stockSelect", _this.stockSelect.value);
      formData.append("riskLimit", _this.riskLimit.value);
      formData.append("holdPeriod", _this.holdPeriod.value);
      formData.append("stockProfit", _this.stockProfit);
      formData.append("yrProfit", _this.yrProfit.value);
      formData.append("investTime", _this.investTime.value);
      formData.append("losscut", _this.losscut.value);

      this.$http
        .post("/m/diags/saveInvestSurvey.json", formData)
        .then(response => {
          this.$router.push("/assets/diagsStart");
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    nextOpen: function(option) {
      this.$refs.category.open();
    },
    selectClose: function(option) {}
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
