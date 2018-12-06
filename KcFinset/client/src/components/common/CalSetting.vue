<template>
  <section>
    <dl class="setting-wrap">
      <dt>보기</dt>
      <dd>
        <p class="key">달력</p>
        <p class="value checks round">
          <input type="radio" id="rds1" name="RadioGroup1"><label for="rds1">월별</label>
          <input type="radio" id="rds2" name="RadioGroup1"><label for="rds2">년별</label>
        </p>
      </dd>
      <dd class="bobtm">
        <p class="key">월별</p>
        <p class="value checks round">
          <input type="radio" id="rds3" name="RadioGroup2"><label for="rds3">요약</label>
          <input type="radio" id="rds4" name="RadioGroup2"><label for="rds4">상세</label>
        </p>
      </dd>
      <dd>
        <p class="key">모든 공유 정보 보기</p>
        <p class="value"><button class="btn-onoff"></button></p>
      </dd>
      <dd>
        <p class="key"><em class="red"></em>준수</p>
        <p class="value"><button class="btn-onoff"></button></p>
      </dd>
      <dd class="bobtm">
        <p class="key"><em class="orange"></em>현중</p>
        <p class="value"><button class="btn-onoff"></button></p>
      </dd>
      <dd>
        <p class="key">월 시작일 가계부 연동</p>
        <p class="value"><button class="btn-onoff"></button></p>
      </dd>
    </dl>

    <div class="btn-wrap float">
      <a href="#" class="solid blue box">저장하기</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "CommonCalSetting",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "설정";
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
