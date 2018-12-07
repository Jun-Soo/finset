<template>
  <section v-if="seen">
    <div class="reg-counsel">
      <dl>
        <dt>
          <p>질문</p>
          <p>{{counselInfo.dt_apply}}</p>
        </dt>
        <dd>{{counselInfo.inquiry_contents}}</dd>
      </dl>
      <dl v-if="cd_counsel_status=='3'">
        <dt>
          <p>답변</p>
          <p>{{counselInfo.dt_counsel}}</p>
        </dt>
        <dd>{{counselContents}}</dd>
      </dl>
    </div>

    <div v-if="cd_counsel_status!='3'" class="btn-wrap col2">
      <a @click="deleteCounsel()">취소</a>
      <a @click="goUpdateForm()" class="btn-solid">수정</a>
    </div>
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
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    var cd_counsel_status = this.$route.query.cd_counsel_status;
    var title = "";
    if ("3" != cd_counsel_status) {
      title = "신청내용보기";
    } else {
      title = "상담결과보기";
    }
    this.$store.state.title = title;
    this.cd_counsel_status = cd_counsel_status;
    this.counsel_seq = this.$route.query.counsel_seq;
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
      var _this = this;
      this.$router.push({
        name: "creditCounselReqStep4",
        query: {
          counsel_seq: _this.counsel_seq
        }
      });
    },
    //삭제
    deleteCounsel: function() {
      var _this = this;

      Constant.options.title = "상담요청을 취소하시겠습니까?";
      this.$dialogs.confirm("", Constant.options).then(res => {
        if (res.ok) {
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
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
