<template>
  <section>
    <div class="container">
      <ul class="debt-modify">
        <li>
          <p class="key">결혼여부</p>
          <p>
            <select>
              <option>결혼여부선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">부양가족</p>
          <p>
            <select>
              <option>부양가족선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">주거형태</p>
          <p>
            <select>
              <option>주거형태선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">직업</p>
          <p>
            <select>
              <option>직군선택</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">월소득</p>
          <p><input type="text"></p>
        </li>
        <li>
          <p class="key">월지출</p>
          <p><input type="text"></p>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a @click="$router.push('')" class="solid blue box">추가정보입력</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "CreditCounselInfo3",
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
