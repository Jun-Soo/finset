<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">검색</a>
        <a href="#">직접입력</a>
      </div>
    </div>

    <div class="container">
      <ul class="debt-modify">
        <li>
          <p class="key">종류</p>
          <p>
            <select>
              <option>종류선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">시/도</p>
          <p>
            <select>
              <option>시/도 선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">시/군/구</p>
          <p>
            <select>
              <option>시/군/구 선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">읍/면/동</p>
          <p>
            <select>
              <option>읍/면/동 선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">아파트명</p>
          <p>
            <select>
              <option>아파트명 선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">공급면적/전용면적</p>
          <p>
            <select>
              <option>면적 선택</option>
            </select>
          </p>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a href="#" class="solid blue box">확인</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtCalcSearch",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "주택 입력";
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
