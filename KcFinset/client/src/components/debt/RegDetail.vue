<template>
  <section>
    <div class="detail-list">
      <dl>
        <dt>거래정보 확인</dt>
        <dd>
          <p>빌려준 사람</p>
          <p>채권자</p>
        </dd>
        <dd>
          <p>빌린 사람</p>
          <p>채권자</p>
        </dd>
        <dd>
          <p>금액</p>
          <p>100 만원</p>
        </dd>
        <dd>
          <p>계약일</p>
          <p>2018년 6월 18일</p>
        </dd>
        <dd>
          <p>만기일</p>
          <p>2018년 12월 31일</p>
        </dd>
        <dd>
          <p>금리</p>
          <p>100만원 (금액)</p>
        </dd>
        <dd>
          <p>상환방식</p>
          <p>만기 일시 상환</p>
        </dd>
      </dl>
      <dl>
        <dt>상환 예정일</dt>
        <dd>
          <p>2018/07/18<em>이자</em></p>
          <p>50,000원</p>
        </dd>
        <dd>
          <p>2018/08/18<em>이자</em></p>
          <p>60,000원</p>
        </dd>
        <dd>
          <p>2018/12/31<em>이자+원금</em></p>
          <p>1005,000원</p>
        </dd>
      </dl>
      <div class="btn-wrap float">
        <a href="#" class="solid blue box">확인</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtRegDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "거래정보 확인";
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
