<template>
  <section>
    <div class="con-top share-top">
      <p>홍길동님<em>에게 요청한 항목입니다.</em></p>
      <div class="request">
        <ul>
          <li>
            <em class="key">요청일</em>
            <em>2018.08.19</em>
          </li>
          <li>
            <em class="key">허용일</em>
            <em>-</em>
          </li>
          <li>
            <em class="key">상태</em>
            <em class="state">대기중</em>
          </li>
        </ul>
      </div>
    </div>

    <div class="box-list noMG list02 pb90">
      <p class="header">통합관리용 정보 항목</p>
      <div class="item">
        <div class="flex">
          <p class="corp big">신용등급(연체정보 포함)</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">대출정보</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
        <div class="checks">
          <div class="small">
            <p><input type="checkbox" id="chk1"><label for="chk1">신한은행 (잔액 : 21,120,000)</label></p>
            <p><input type="checkbox" id="chk2"><label for="chk2">카카오뱅크 (잔액 : 5,000,000)</label></p>
            <p><input type="checkbox" id="chk3"><label for="chk3">국민은행 (잔액 : 1,000,000)</label></p>
          </div>
        </div>
      </div>
      <div class="item">
        <div class="flex">
          <p class="corp big">자산정보 (계좌번호)</p>
          <p><button class="btn-onoff on"></button></p>
        </div>
        <div class="checks">
          <div class="small">
            <p><input type="checkbox" id="chk4"><label for="chk4">신한은행 (110-353-009212)</label></p>
            <p><input type="checkbox" id="chk5"><label for="chk5">대우증권 (1234-****-****-1021)</label></p>
            <p><input type="checkbox" id="chk6"><label for="chk6">부동산 (경기 고양시 덕양구 ***)</label></p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">요청 취소</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareSetting_3",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용정보 제공항목";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
