<template>
  <section>
    <div class="detail-list mt30 pb90">
      <dl>
        <dd>
          <p>이름</p>
          <p><input type="text" value="홍길동"></p>
        </dd>
        <dd>
          <p>나이</p>
          <p><input type="text" value="홍길동"></p>
        </dd>
      </dl>
      <dl>
        <dt>신용</dt>
        <dd>
          <p>신용등급</p>
          <p><input type="text" value="5 등급"></p>
        </dd>
        <dd>
          <p>연체금액</p>
          <p><input type="text" value="5000 만원"></p>
        </dd>
      </dl>
      <dl>
        <dt>부채</dt>
        <dd>
          <p>대출원금</p>
          <p><input type="text" value="5000 만원"></p>
        </dd>
        <dd>
          <p>대출잔액</p>
          <p><input type="text" value="5000 만원"></p>
        </dd>
        <dd>
          <p>월상환액</p>
          <p><input type="text" value="5000 만원"></p>
        </dd>
      </dl>
      <dl>
        <dt>신용카드</dt>
        <dd>
          <p>이용카드수</p>
          <p><input type="text" value="3 개"></p>
        </dd>
        <dd>
          <p>월이용액</p>
          <p><input type="text" value="5000 만원"></p>
        </dd>
      </dl>
      <div class="btn-wrap float">
        <a @click="$router.push('')" class="solid blue box">추가정보입력</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "CreditCounselInfo2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상담신청(기본정보입력)";
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
