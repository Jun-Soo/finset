<template>
  <section>
    <div class="container mt30 pb90">
      <div class="checks">
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk1" v-model="chk1"><label for="chk1">서비스를 탈퇴하시면 핀셋에서 제공하는 모든서비스를 사용할 수 없으며, 재가입하셔도 기존 서비스 이용내역을 조회하실 수 없습니다. </label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk2" v-model="chk2"><label for="chk2">서비스 이용시 등록한 회원님의 각종 인증정보는 더 이상 사용하실 수 없으며 재가입시 모든정보를 재등록 하셔야 합니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk3" v-model="chk3"><label for="chk3">금융거래를 하지 않으신 회원님의 회원정보는 서비스 탈퇴 즉시 삭제됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk4" v-model="chk4"><label for="chk4">금융거래가 진행된 고객님의 개인신용정보는 금융거래 종료 후 5년 이내 다른정보와 분리하여 안전하게 보관됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk5" v-model="chk5"><label for="chk5">서비스 탈퇴후 일정기간 재가입이 불가능합니다.</label></p>
        </div>
        <input type="checkbox" id="check-all" @click="chkAll" v-model="chkall"><label for="check-all" class="mt30">약관 전체 동의</label>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="quitChkYn" class="solid box blue">탈퇴하기</a>
    </div>
  </section>
</template>

<script>
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
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "서비스 탈퇴";
  },
  created() {},
  beforeMount() {},
  mounted() {
    var _this = this;
    $(":checkbox").change(function() {
      if (_this.chk1 && _this.chk2 && _this.chk3 && _this.chk4 && _this.chk5) {
        _this.chkall = true;
        _this.checked = "check";
      } else {
        _this.chkall = false;
        _this.checked = "";
      }
    });
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    test: function(){
      let _this = this;
       let bool = confirm("정말로 탈퇴 하시겠습니까?");
      if(bool){
        alert('true');
        //_this.dropPerson();
      }else{
        return false;
      }
    },
    quitChkYn: function() {
      let _this = this;
      debugger;
      if (!_this.chk1 || !_this.chk2 || !_this.chk3 || !_this.chk4 || !_this.chk5) {
        this.$toast.center("필수 약관을 모두 동의해주세요");
        setTimeout(function() {}, 2000);
        return false;
      }
      let bool = confirm("정말로 탈퇴 하시겠습니까?");
      if(bool){
        _this.dropPerson();
      }else{
        return false;
      }
    },

    dropPerson: function() {
      let url = "/m/customercenter/customerQuitComp.json";
      let _this = this;

      var frm = new FormData();
      _this.$http.post(url, frm).then(response => {
         if (response.data.result == "00") {
            //정상
            _this.$router.push('/mypage/dropDone');
          }else {
            _this.$toast.center(response.data.message);
          }
      }).catch(e => {
        _this.$toast.center(ko.messages.error);
      });
    },
    chkAll: function() {
      var _this = this;
      if (_this.chkall) {
        _this.chkall = false;
      } else {
        _this.chkall = true;
      }

      if (_this.chkall) {
        // _this.checked = "check";
        _this.chk1 = true;
        _this.chk2 = true;
        _this.chk3 = true;
        _this.chk4 = true;
        _this.chk5 = true;
      } else {
        // _this.checked = "";
        _this.chk1 = false;
        _this.chk2 = false;
        _this.chk3 = false;
        _this.chk4 = false;
        _this.chk5 = false;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
