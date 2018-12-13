<template>
  <section>
    <div class="container">
      <textarea v-model="inquiry_contents" placeholder="상담내용을 입력합니다."></textarea>
      <div class="btn-wrap float">
        <a v-if="counsel_seq==''" @click="createCounsel()" class="solid blue box">신청하기</a>
        <a v-else @click="updateCounsel()" class="solid blue box">수정하기</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditCounselReqStep4",
  data() {
    return {
      inquiry_contents: "",
      counsel_seq: "",
      counselInfo: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청내용";
  },
  created() {
    if (
      "" != this.$route.query.counsel_seq &&
      this.$route.query.counsel_seq != null
    ) {
      this.counsel_seq = this.$route.query.counsel_seq;
      this.getCounselReqStep4Info();
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //답변내용 가져오기
    getCounselReqStep4Info: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditCounselReqStep4Info.json", {
          params: { counsel_seq: _this.counsel_seq }
        })
        .then(response => {
          var counselInfo = response.data.counselInfo;
          _this.inquiry_contents = counselInfo.inquiry_contents;
          _this.counselInfo = counselInfo;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //등록
    createCounsel: function() {
      var _this = this;

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("상담을 신청하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            var formData = new FormData();
            formData.append("yn_wedding", this.$route.query.yn_wedding);
            formData.append("cd_family_cnt", this.$route.query.cd_family_cnt);
            formData.append("cd_living", this.$route.query.cd_living);
            formData.append("cd_job", this.$route.query.cd_job);
            formData.append("amt_mm_income", this.$route.query.amt_mm_income);
            formData.append("amt_mm_expense", this.$route.query.amt_mm_expense);
            formData.append("inquiry_contents", _this.inquiry_contents);

            this.$http
              .post("/m/credit/createCreditCounselInfo.json", formData)
              .then(response => {
                this.$toast.center(response.data.message);
                if ("00" == response.data.result) {
                  _this.$router.push("/credit/counselReqStep5");
                }
              })
              .catch(e => {
                this.$toast.center(ko.messages.error);
              });
          }
        });
    },
    //수정
    updateCounsel: function() {
      var _this = this;

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("상담내용을 수정하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            var formData = new FormData();
            formData.append("counsel_seq", _this.counsel_seq);
            formData.append("inquiry_contents", _this.inquiry_contents);

            this.$http
              .post("/m/credit/updateCreditCounselInfo.json", formData)
              .then(response => {
                this.$toast.center(response.data.message);
                if ("00" == response.data.result) {
                  _this.$router.push("/credit/counselMain");
                }
              })
              .catch(e => {
                this.$toast.center(ko.messages.error);
              });
          }
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
