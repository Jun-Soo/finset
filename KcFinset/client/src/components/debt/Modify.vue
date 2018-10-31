<template>
  <section>
    <div class="container">
      <p>금리 등 부채 정보를 수정합니다.</p>
      <ul class="debt-modify">
        <li>
          <p class="key">금리</p>
          <p><input type="text"><em>%</em></p>
        </li>
        <li>
          <p class="key">상환방법</p>
          <p>
            <select>
              <option>원금분할상환</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">거치기간</p>
          <p>
            <select>
              <option>없음</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">이자납입주기</p>
          <p>
            <select>
              <option>매월</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">이자납입일</p>
          <p>
            <select>
              <option>25일</option>
            </select>
          </p>
        </li>
      </ul>
      <div class="btn-wrap">
        <a href="#" class="solid blue">확인</a>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "DebtModify",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보수정";
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
