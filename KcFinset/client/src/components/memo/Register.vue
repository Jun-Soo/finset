<template>
  <section>
    <div class="container">
      <textarea id="memo_text" v-model="memo_text" wrap="hard" :style="'resize:none;wrap:physical'"></textarea>
    </div>

    <div class="btn-wrap float">
      <a @click="createMemo" class="blue box solid">등록</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "MemoRegister",
  data() {
    return {
      memo_text: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모등록";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    createMemo: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("memo_text", _this.memo_text);
      formData.append("no_manage_info", _this.$route.query.no_manage_info);
      this.$http
        .post("/m/memo/createMemo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
