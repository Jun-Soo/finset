<template>
  <section>
    <div class="container mt30 pb90">
      <div class="checks">
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk1"><label for="chk1">서비스를 탈퇴하시면 핀셋에서 제공하는 모든서비스를 사용할 수 없으며, 재가입하셔도 기존 서비스 이용내역을 조회하실 수 없습니다. </label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk2"><label for="chk2">서비스 이용시 등록한 회원님의 각종 인증정보는 더 이상 사용하실 수 없으며 재가입시 모든정보를 재등록 하셔야 합니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk3"><label for="chk3">금융거래를 하지 않으신 회원님의 회원정보는 서비스 탈퇴 즉시 삭제됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk4"><label for="chk4">금융거래가 진행된 고객님의 개인신용정보는 금융거래 종료 후 5년 이내 다른정보와 분리하여 안전하게 보관됩니다.</label></p>
        </div>
        <div class="box-agree drop">
          <p><input type="checkbox" id="chk5"><label for="chk5">서비스 탈퇴후 일정기간 재가입이 불가능합니다.</label></p>
        </div>
        <input type="checkbox" id="check-all"><label for="check-all" class="mt30">약관 전체 동의</label>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push(' ')" class="solid box blue">탈퇴하기</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageDrop",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "서비스 탈퇴";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    dropPerson : function(){
      let url = '/m/customercenter/frameCustomerQuit.crz';
    }

  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
