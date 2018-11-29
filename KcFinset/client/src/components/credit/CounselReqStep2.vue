<template>
  <section v-if="seen">
    <div class="detail-list mt30 pb90">
      <dl>
        <dd>
          <p>이름</p>
          <p><input
              type="text"
              :value="counselInfo.nm_person"
              readonly="readonly"
            ></p>
        </dd>
        <dd>
          <p>나이</p>
          <p><input
              type="text"
              :value="'만 '+counselInfo.age+'세('+counselInfo.sex+')'"
              readonly="readonly"
            ></p>
        </dd>
      </dl>
      <dl>
        <dt>신용</dt>
        <dd>
          <p>신용등급</p>
          <p><input
              type="text"
              :value="counselInfo.grade_credit+' 등급'"
              readonly="readonly"
            ></p>
        </dd>
        <dd>
          <p>연체금액</p>
          <p><input
              type="text"
              :value="counselInfo.bal_overdue+' 만원'"
              readonly="readonly"
            ></p>
        </dd>
      </dl>
      <dl>
        <dt>부채</dt>
        <dd>
          <p>대출원금</p>
          <p><input
              type="text"
              :value="counselInfo.amt_contract+' 만원'"
              readonly="readonly"
            ></p>
        </dd>
        <dd>
          <p>대출잔액</p>
          <p><input
              type="text"
              :value="counselInfo.amt_remain+' 만원'"
              readonly="readonly"
            ></p>
        </dd>
        <dd>
          <p>월상환액</p>
          <p><input
              type="text"
              :value="counselInfo.cur_mm_amt_repay+' 만원'"
              readonly="readonly"
            ></p>
        </dd>
      </dl>
      <dl>
        <dt>신용카드</dt>
        <dd>
          <p>이용카드수</p>
          <p><input
              type="text"
              :value="counselInfo.cnt_card_use+' 개'"
              readonly="readonly"
            ></p>
        </dd>
        <dd>
          <p>월이용액</p>
          <p><input
              type="text"
              :value="counselInfo.amt_card_total+' 만원'"
              readonly="readonly"
            ></p>
        </dd>
      </dl>
      <div class="btn-wrap float">
        <a
          @click="$router.push('/credit/counselReqStep3')"
          class="solid blue box"
        >추가정보입력</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditCounselReqStep2",
  data() {
    return {
      seen: false,
      counselInfo: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상담신청(기본정보입력)";
  },
  created() {
    this.getCounselReqStep2Info();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //정보조회
    getCounselReqStep2Info: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditCounselReqStep2Info.json", {
          params: {}
        })
        .then(response => {
          _this.counselInfo = response.data.counselInfo;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
