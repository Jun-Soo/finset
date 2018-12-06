<template>
  <section>
    <div class="detail-list">
      <dl>
        <dt>거래정보 확인</dt>
        <dd>
          <p>빌려준 사람</p>
          <p>{{debtVO.creditor}}</p>
        </dd>
        <dd>
          <p>빌린 사람</p>
          <p>{{$store.state.user.nmPerson}}</p>
        </dd>
        <dd>
          <p>금액</p>
          <p>{{debtVO.amt_contract}} 만원</p>
        </dd>
        <dd>
          <p>계약일</p>
          <p>{{debtVO.ymd_loan}}</p>
        </dd>
        <dd>
          <p>만기일</p>
          <p>{{debtVO.ymd_loanend}}</p>
        </dd>
        <dd>
          <p v-text="debtVO.inter_type=='percent'?'금리(이자율)':'금리(이자금액)'"></p>
          <p v-text="debtVO.inter_type=='percent'?debtVO.interest + '%':debtVO.all_amt_repay_i + '원'"></p>
        </dd>
        <dd v-if="debtVO.rep_method != '00'">
          <p>상환방식</p>
          <p>만기 일시 상환</p>
        </dd>
      </dl>
      <dl v-if="debtVO.rep_method != '00' && debtVO.rep_method != '03'">
        <dt>상환 예정일</dt>
        <dd>
          <p>2018/07/18<em>이자</em></p>
          <p>50,000원</p>
        </dd>
        <dd>
          <p>2018/08/18<em>이자</em></p>
          <p>60,000원</p>
        </dd>
        <dd>
          <p>2018/12/31<em>이자+원금</em></p>
          <p>1005,000원</p>
        </dd>
      </dl>
      <div class="btn-wrap float">
        <a @click="saveDebt" class="solid blue box">확인</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "DebtRegDetail",
  data() {
    return {
      debtVO: new Object(),
      Common: Common
    };
  },
  components: {},
  computed: {
    chkRepMethod: function() {}
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "거래정보 확인";
  },
  created() {
    this.debtVO = this.$route.query.debtVO;
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
      var formData = new FormData();
      formData.append("creditor", this.debtVO.creditor);
      formData.append("ymd_loan", this.debtVO.ymd_loan.replace(/[-]/g, ""));
      formData.append(
        "ymd_loanend",
        this.debtVO.ymd_loanend.replace(/[-]/g, "")
      );
      if (this.debtVO.inter_pay_day != "00") {
        formData.append("inter_pay_day", this.debtVO.inter_pay_day);
      }
      if (this.debtVO.inter_pay_cycle != "00") {
        formData.append("inter_pay_cycle", this.debtVO.inter_pay_cycle);
      }
      formData.append("amt_contract", this.debtVO.amt_contract + "0000");
      formData.append("amt_remain", this.debtVO.amt_contract + "0000");
      if (this.debtVO.inter_type == "percent") {
        formData.append("interest", this.debtVO.interest);
      } else if (this.debtVO.inter_type == "won") {
        formData.append("all_amt_repay_i", this.debtVO.all_amt_repay_i);
      }
      if (this.debtVO.rep_method == "03" || this.debtVO.rep_method == "00") {
        formData.append("rep_method", null);
      } else {
        formData.append("rep_method", this.debtVO.rep_method);
      }
      if (this.debtVO.rep_method == "00" || this.debtVO.rep_method == "03") {
        formData.append("cd_type_deal", "2");
      } else {
        formData.append("cd_type_deal", "1");
      }
      formData.append("yn_credit", "Y");
      formData.append("yn_loan", "N");
      formData.append("yn_guarantor", "N");
      formData.append("debt_yn", "Y");

      this.$http
        .post("/m/debt/createDebtPersonalInfo.json", formData)
        .then(function(response) {
          _this.$router.push("/debt/main");
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
