<template>
  <section>
    <div class="container">
      <div class="memo-list">
        <ul>
          <li v-for="(memoVO, index) in memoList" :key="index">
            <a @click="detailMemo(memoVO.seq_memo_info)">
              <p v-text="memoVO.memo_head"></p>
              <!-- <p>이번달 월급이 늦게 들어올거 같아서 이자납입일을 낮춤</p> -->
              <!-- <p>{{memoVO.memo_text}}</p> -->
              <p v-text="memoVO.memo_text"></p>
            </a>
          </li>
        </ul>
      </div>
      <button class="btn-spend-add" @click="registerMemo"></button>
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "MemoList",
  data() {
    return {
      memoList: new Array(),
      page: 1
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모";
  },
  created() {
    Common.pagination(this.listMemo);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listMemo: function(callback) {
      var _this = this;
      this.$http
        .get("/m/memo/listMemo.json", {
          params: {
            no_manage_info: _this.$route.query.no_manage_info,
            page: _this.page
          }
        })
        .then(function(response) {
          var list = response.data.listMemo;
          if ((list || "") == "") {
            callback();
            return;
          } else {
            for (var idx in list) {
              list[idx].memo_head = _this.getMemoHead(list[idx].memo_text);
            }
            _this.memoList = _this.memoList.concat(list);
            _this.page++;
          }
        });
    },
    detailMemo: function(seq_memo_info) {
      this.$router.push({
        path: "/memo/detail",
        query: { seq_memo_info: seq_memo_info }
      });
    },
    registerMemo: function() {
      var _this = this;
      this.$router.push({
        path: "/memo/register",
        query: { no_manage_info: _this.$route.query.no_manage_info }
      });
    },
    getMemoHead: function(str) {
      var tempStr = str || "";
      if (tempStr.indexOf("\n") > -1) {
        if (tempStr.indexOf("\n") > 30) {
          return tempStr.substr(0, 30) + "...";
        } else {
          return tempStr.substr(0, tempStr.indexOf("\n"));
        }
      } else {
        if (tempStr.length > 30) {
          return tempStr.substr(0, 30) + "...";
        } else {
          return tempStr;
        }
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
