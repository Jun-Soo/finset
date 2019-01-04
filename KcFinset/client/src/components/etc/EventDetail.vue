<template>
  <section>
    <div class="board event">
      <ul class="view" v-if="seen">
        <li>
          <p class="subject">{{boardInfo.title}}</p>
          <div class="flex">
            <div>
              <p v-if="boardInfo.ymd_post_strt!=null" class="date">{{formatDateDot(boardInfo.ymd_post_strt)}} ~ {{formatDateDot(boardInfo.ymd_post_end)}}</p>
            </div>
            <div>
              <p class="state pre" v-if="boardInfo.cd_event_proc==='01'">예정</p>
              <p class="state ing" v-else-if="boardInfo.cd_event_proc==='02'">진행중</p>
              <p class="state done" v-else>종료</p>
            </div>
          </div>
        </li>
        <li>
          <img v-if="boardImgInfo.img_files!=null" :src="getImgUrl(boardImgInfo)" v-bind:alt="boardImgInfo" />
          <div v-html="boardInfo.content"></div>
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "EtcEventDetail",
  data() {
    return {
      seq: "",
      id_board: "",
      boardInfo: {},
      boardImgInfo: {},
      boardForm: {},
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이벤트";
  },
  created() {
    this.seq = this.$route.query.seq;
    this.id_board = this.$route.query.id_board;
    this.$store.state.header.backPath = "/etc/noticeMain?tab=01";
    this.getEventDatail(this.seq, this.id_board);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    formatDateDot: function(date) {
      return Common.formatDateDot(date);
    },
    getEventDatail: function(seq, id_board) {
      let _this = this;
      let url = "/m/customercenter/getCustomerNoticeDetail.json";
      let frm = new FormData();
      frm.append("seq", seq);
      frm.append("id_board", id_board);
      this.$http.post(url, frm).then(response => {
        _this.boardForm = response.data.boardForm;
        if (response.data.boardImgInfo) {
          _this.boardImgInfo = response.data.boardImgInfo;
        } else {
          _this.boardImgInfo.img_files = null;
        }
        _this.boardInfo = response.data.boardInfo;
        _this.seen = true;
      });
    },
    getImgUrl: function(page) {
      return (
        "/m/board/getBoardImg.json?id_board=" +
        page.id_board +
        "&seq=" +
        page.seq +
        "&file_type=02"
      );
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
