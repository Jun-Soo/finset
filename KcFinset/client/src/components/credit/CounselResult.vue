<template>
  <section v-if="seen">
    <div class="counsel-result">
      <p class="title">질문</p>
      {{counselInfo.dt_apply}}<br />
      <p>{{counselInfo.inquiry_contents}}</p>
      <template v-if="cd_counsel_status=='3'">
        <p class="title">답변</p>
        {{counselInfo.dt_counsel}}<br />
        <p>{{counselContents}}</p>
      </template>
    </div>
    <button
      v-if="cd_counsel_status!='3'"
      @click="goUpdateForm()"
    >수정</button>
    <button
      v-if="cd_counsel_status!='3'"
      @click="deleteCounsel()"
    >취소</button>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditCounselResult",
  data() {
    return {
      seen: false,
      counsel_seq: "",
      cd_counsel_status: "",
      counselInfo: "",
      counselContents: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상담결과보기";
  },
  created() {
    this.counsel_seq = this.$route.query.counsel_seq;
    this.cd_counsel_status = this.$route.query.cd_counsel_status;
    this.getCreditCounselResultInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //정보조회
    getCreditCounselResultInfo: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditCounselResultInfo.json", {
          params: {
            counsel_seq: _this.counsel_seq,
            cd_counsel_status: _this.cd_counsel_status
          }
        })
        .then(response => {
          _this.counselInfo = response.data.counselInfo;
          //상담완료시 답변내용 셋팅
          if ("3" == _this.cd_counsel_status) {
            _this.counselContents = response.data.counselContents;
          }

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //수정화면 이동
    goUpdateForm: function() {
      this.$router.push({
        name: "creditCounselReqStep4",
        query: {
          counsel_seq: counsel_seq
        }
      });
    },
    //삭제
    deleteCounsel: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("counsel_seq", _this.counsel_seq);

      this.$http
        .post("/m/credit/deleteCreditCounselInfo.json", formData)
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
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
