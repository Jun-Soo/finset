<template>
  <section>
    <div class="container memo-detail pb90">
      <textarea v-model="memoVO.memo_text"></textarea>
    </div>

    <div class="btn-wrap float">
      <a @click="updateMemoText" class="blue box solid">수정</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MemoDetail",
  data() {
    return {
      memoVO: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모";
  },
  created() {
    this.getMemo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getMemo: function() {
      var _this = this;
      this.$http
        .get("/m/memo/getMemo.json", {
          params: { seq_memo_info: _this.$route.query.seq_memo_info }
        })
        .then(function(response) {
          _this.memoVO = response.data.memoVO;
        });
    },
    updateMemoText: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("seq_memo_info", _this.memoVO.seq_memo_info);
      formData.append("memo_text", _this.memoVO.memo_text);
      this.$http
        .post("/m/memo/updateMemoText.json", formData)
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
