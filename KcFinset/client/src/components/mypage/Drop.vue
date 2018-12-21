<template>
  <section>
    <div class="container pb90">
      <div class="drop-top">서비스 탈퇴를 위해<br>아래 안내를 읽고 동의해 주세요</div>
      <div class="checks">
        <input type="checkbox" id="check-all" @click="chkall" v-model="chkall"><label for="check-all" class="mt30">동의합니다</label>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk1" disabled="true" v-model="chk1"><label for="chk1">서비스를 탈퇴하시면 핀셋에서 제공하는 모든서비스를 사용할 수 없으며, 재가입하셔도 기존 서비스 이용내역을 조회하실 수 없습니다. </label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk2" disabled="true" v-model="chk2"><label for="chk2">서비스 이용시 등록한 회원님의 각종 인증정보는 더 이상 사용하실 수 없으며 재가입시 모든정보를 재등록 하셔야 합니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk3" disabled="true" v-model="chk3"><label for="chk3">금융거래를 하지 않으신 회원님의 회원정보는 서비스 탈퇴 즉시 삭제됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk4" disabled="true" v-model="chk4"><label for="chk4">금융거래가 진행된 고객님의 개인신용정보는 금융거래 종료 후 5년 이내 다른정보와 분리하여 안전하게 보관됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk5" disabled="true" v-model="chk5"><label for="chk5">서비스 탈퇴후 일정기간 재가입이 불가능합니다.</label></p>
        </div>
      </div>
    </div>

    <div class="btn-wrap float" v-if="chkall">
      <a @click="quitChkYn" class="solid box blue">탈퇴하기</a>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
export default {
  name: "MypageDrop",
  data() {
    return {
      chk1: false,
      chk2: false,
      chk3: false,
      chk4: false,
      chk5: false,
      chkall: false,
      checked: ""
    };
  },
  components: {},
  computed: {},
  watch: {
    chkall: function() {
      if (this.chkall) {
        this.checked = "check";
        this.chk1 = true;
        this.chk2 = true;
        this.chk3 = true;
        this.chk4 = true;
        this.chk5 = true;
        window.scrollTo(0, window.innerHeight);
      } else {
        this.checked = "";
        this.chk1 = false;
        this.chk2 = false;
        this.chk3 = false;
        this.chk4 = false;
        this.chk5 = false;
      }
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "서비스 탈퇴";
  },
  created() {},
  beforeMount() {},
  mounted() {
    $("#input").on("click", function(e) {
      e.preventDefault();
    });
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    quitChkYn: function() {
      let _this = this;

      this.$dialogs
        .confirm("정말로 탈퇴 하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            _this.dropPerson();
          } else {
            return false;
          }
        });
    },
    dropPerson: function() {
      let url = "/m/customercenter/customerQuitComp.json";
      let _this = this;

      var frm = new FormData();
      _this.$http.post(url, frm).then(response => {
        if (response.data.result == "00") {
          //정상
          _this.$router.push("/mypage/dropDone");
        } else {
          _this.$toast.center(response.data.message);
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
