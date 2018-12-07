<template>
  <section>
    <div class="container">
      <ul class="debt-modify">
        <li>
          <div>
            <p class="key">상환일</p>
            <p>
              <datepicker ref="datepicker" v-model="req_yyyymm" :language="ko" :format="formatDate" :opend="Common.datepickerInit('div-date', this)"></datepicker>
              <button class="cal" @click="openDatepicker"></button>
            </p>
          </div>
        </li>
        <li>
          <div>
            <p class="key">금액</p>
            <p><input type="text" v-model="amt_repay" placeholder="상환금액을 입력하세요"><em>만원</em></p>
          </div>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a @click="createRepayment" class="solid blue box">저장</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

export default {
  name: "DebtRepayment",
  data() {
    return {
      Common: Common,
      ko: ko,
      req_yyyymm: new Date(),
      amt_repay: ""
    };
  },
  components: {
    datepicker
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상환정보 입력";
  },
  created() {
    ko.ymd = true;
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    createRepayment: function() {},
    formatDate: function(date) {
      return Common.formatDate(date, "yyyymmdd");
    },
    openDatepicker: function() {
      this.$refs.datepicker.showCalendar();
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
</style>
