<template>
  <div v-if="seen">
    <section>
      <div class="con-top credit-up">
        <p><em>몇번의 클릭으로</em><br><em class="red">신용점수를 UP하세요</em></p>
        <a @click="openPop()">제도 설명 보기</a>
      </div>

      <div class="box-list list01">
        <div class="item" @click="clickItem('nhis')">
          <div class="top">
            <p class="symbol"><img src="/m/fincorp/getFinCorpIcon.crz?cd_fc=0010471" alt="" />국민건강보험</p>
            <p v-if="(nhis_status||'')!=''" class="text" :class="nps_status=='완료'?'red':'blue'"> {{nhis_status}}<em>{{ formatDate(nhis_date) }}</em></p>
          </div>
          <p class="text-result">최근 1년간 건강보험료 납부 내역 등록</p>
        </div>
        <div class="item" @click="clickItem('nps')">
          <div class="top">
            <p class="symbol"><img src="/m/fincorp/getFinCorpIcon.crz?cd_fc=0010471" alt="" />국민연금</p>
            <p v-if="(nps_status||'')!=''" class="text" :class="nps_status=='완료'?'red':'blue'"> {{ nps_status }}<em>{{ formatDate(nps_date) }}</em></p>
          </div>
          <p class="text-result">최근 1년간 국민연금 납부 내역 등록</p>
        </div>
        <div class="item" @click="clickItem('nts')">
          <div class="top">
            <p class="symbol"><img src="/m/fincorp/getFinCorpIcon.crz?cd_fc=0010471" alt="" />국세청 HomeTax</p>
            <p v-if="(nts_status||'')!=''" class="text" :class="nps_status=='완료'?'red':'blue'"> {{ nts_status }}<em>{{ formatDate(nts_date) }}</em></p>
          </div>
          <p class="text-result">직전 귀속년도 소득 등록</p>
        </div>
      </div>
    </section>
    <vue-modal transitionName="zoom-in" name="my-modal" v-on:popclose="closePop()">
      <raiseInfo slot="body" v-on:popclose="closePop()"></raiseInfo>
    </vue-modal>
  </div>
</template>

<script>
import raiseInfo from "./RaiseInfo.vue";
import Common from "./../../assets/js/common.js";
export default {
  name: "CreditRaiseMain",
  data() {
    return {
      seen: false,
      nts_button: "",
      nhis_button: "",
      nps_button: "",
      nts_status: "",
      nhis_status: "",
      nps_status: "",
      nts_date: "",
      nhis_date: "",
      nps_date: ""
    };
  },
  components: {
    raiseInfo: raiseInfo
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용 등급 올리기";
  },
  created() {
    this.getCreditRaiseInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // 신용정보 조회
    getCreditRaiseInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditRaiseInfo.json", {
          params: {}
        })
        .then(response => {
          var result = response.data;
          if (result != null) {
            _this.nts_button = result.nts_button;
            _this.nhis_button = result.nhis_button;
            _this.nps_button = result.nps_button;

            _this.nts_status = result.nts_status;
            _this.nhis_status = result.nhis_status;
            _this.nps_status = result.nps_status;

            _this.nts_date = result.nts_date;
            _this.nhis_date = result.nhis_date;
            _this.nps_date = result.nps_date;
          } else {
            this.$toast.center(ko.messages.error);
          }
          _this.seen = true;
        });
    },
    clickItem: function(type) {
      var button, status, url;
      switch (type) {
        case "nhis":
          status = this.nhis_status;
          button = this.nhis_button;
          url = "/credit/RaiseNhis";
          break;
        case "nts":
          status = this.nts_status;
          button = this.nts_button;
          url = "/credit/RaiseNts";
          break;
        case "nps":
          status = this.nps_status;
          button = this.nps_button;
          url = "/credit/RaiseNps";
          break;
      }

      if (status == "대기") {
        this.$router.push(url);
      } else if (button == "false") {
        //do nothing
        return;
      } else {
        this.$router.push({
          name: "CreditRaiseInsPersonInfo",
          params: { scrap_code: type }
        });
      }
    },
    openPop: function() {
      this.$modals.show("my-modal");
    },
    closePop: function() {
      this.$modals.hide("my-modal");
    },
    formatDate: function(data) {
      return Common.formatDate(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
