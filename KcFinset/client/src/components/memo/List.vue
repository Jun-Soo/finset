<template>
  <section>
    <div class="container">
      <div class="memo-list">
        <ul>
          <li>
            <a href="#">
              <p>메모 샘플</p>
              <p>이번달 월급이 늦게 들어올거 같아서 이자납입일을 낮춤</p>
            </a>
          </li>
          <li>
            <a href="#">
              <p>메모 샘플</p>
              <p>이번달 월급이 늦게 들어올거 같아서 이자납입일을 낮춤</p>
            </a>
          </li>
          <li>
            <a href="#">
              <p>메모 샘플</p>
              <p>이번달 월급이 늦게 들어올거 같아서 이자납입일을 낮춤</p>
            </a>
          </li>
        </ul>
      </div>
      <button class="btn-spend-add"></button>
    </div>

  </section>
</template>

<script>
export default {
  name: "MemoList",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모";
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
