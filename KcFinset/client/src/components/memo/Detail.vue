<template>
  <section>
    <div class="container memo-detail pb90">
      <textarea v-model="memoVO.memo_text" :style="'resize:none;'"></textarea>
    </div>

    <!-- <div class="btn-wrap float"> -->
    <div class="btn-wrap float col2">
      <a @click="deleteMemo">삭제</a>
      <a @click="updateMemoText" class="btn-solid">수정</a>
    </div>
  </section>
</template>

<script>
import Constant from "@/assets/js/constant.js";

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
    },
    deleteMemo: function() {
      this.$dialogs.confirm("정말 삭제할까요?", Constant.options).then(res => {
        if (res.ok) {
          var _this = this;
          var formData = new FormData();
          formData.append("seq_memo_info", _this.memoVO.seq_memo_info);
          this.$http
            .post("/m/memo/deleteMemo.json", formData)
            .then(function(response) {
              _this.$router.go(-1);
            });
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
