<template>
  <section>
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>자산을 직접 입력하여</em><br>관리하세요</p>
        <div class="list">
          <ul class="flex">
            <li class="key">이름</li>
            <li class="value">
              <select>
                <option>부동산</option>
              </select>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">보유</li>
            <li class="value">
              <select>
                <option>자가</option>
              </select>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">주소</li>
            <li class="value"><button class="search"></button></li>
          </ul>
          <ul class="flex">
            <li class="key">가격</li>
            <li class="value">154,000 원</li>
          </ul>
          <ul class="flex">
            <li class="key">보유량</li>
            <li class="value">g</li>
          </ul>
          <ul class="block">
            <li class="key">메모</li>
            <li class="value"><input type="text"></li>
          </ul>
        </div>

      </div>
      <div class="btn-wrap float">
        <a class="solid blue box">등록하기</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "assetsDirInput",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "직접 입력";
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
