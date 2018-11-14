<template>
  <aside :class="{on: this.eventShow}" v-if="this.$store.state.isLoggedIn && this.$store.state.header.type == 'main'">
    <div class="top">
      <button @click="showListEvent()">이벤트</button>
    </div>
    <div class="wrap">
      <div class="list-wrap">
        <a @click="viewDetail(eventInfo.seq)" v-for="eventInfo in listEvent" :key="eventInfo.index">
          <div class="banner">
            <div class="left">
              <p class="key">{{formatDate(eventInfo.ymd_post_strt)}} ~ {{formatDate(eventInfo.ymd_post_end)}}</p>
              <p class="value">{{eventInfo.title}}</p>
            </div>
            <div class="right">
              <img :src=eventInfo.thumImg alt="" />
            </div>
          </div>
        </a>
      </div>
    </div>
  </aside>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "FinsetBottom",
  data() {
    return {
      listEvent: [],
      eventShow: false
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {
    this.getListEvent();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //이벤트list 조회
    getListEvent: function() {
      var _this = this;
      this.$http
        .get("/m/main/listMainEvnetBoard.json", {
          params: {}
        })
        .then(response => {
          var list = response.data.listEvent;
          for (var i = 0; i < list.length; i++) {
            list[i].thumImg =
              "/m/board/getBoardImg.json?id_board=" +
              list[i].id_board +
              "&seq=" +
              list[i].seq +
              "&file_type=01";
          }
          _this.listEvent = list;
        })
        .catch(e => {
          // this.$toast.center(ko.messages.error);
        });
    },
    //이벤트show,hide
    showListEvent: function() {
      var _this = this;
      _this.eventShow = !_this.eventShow;
    },
    //이벤트 기간 date format
    formatDate: function(formDate) {
      var rtnDate = "";
      if (formDate != null) {
        rtnDate = Common.formatDate(formDate);
      }
      return rtnDate;
    },
    //이벤트 상세
    viewDetail: function(formDate) {
      //TODO 상세페이지 이동
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
