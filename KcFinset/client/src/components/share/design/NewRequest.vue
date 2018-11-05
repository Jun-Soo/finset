<template>
  <section>
    <div class="container">
      <div class="info-share">
        <p class="title">공유를 요청할 사용자를 선택하세요</p>
        <p class="text">하루동안 응답이 없으면 자동으로 취소 됩니다</p>
        <a href="#">홍길동</a>
      </div>
    </div>

    <div class="box-list noMG list02">
      <div class="item pb10">
        <div class="checks">
          <p class="title">정보 제공 항목</p>
          <div class="small">
            <p><input type="checkbox" id="chk1"><label for="chk1">신용등급</label></p>
            <p><input type="checkbox" id="chk2"><label for="chk2">대출개설 및 잔고 현황</label></p>
          </div>
        </div>
      </div>
      <div class="item pb10">
        <div class="checks">
          <p class="title">정보 통합 관리 항목</p>
          <div class="small">
            <p><input type="checkbox" id="chk4"><label for="chk4">자산</label></p>
            <p><input type="checkbox" id="chk5"><label for="chk5">소비</label></p>
            <p><input type="checkbox" id="chk6"><label for="chk6">부채</label></p>
          </div>
        </div>
      </div>
    </div>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">공유요청</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "ShareNewRequest",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보공유 요청";
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
