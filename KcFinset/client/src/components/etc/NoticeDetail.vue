<template>
  <section>
    <div class="board">
      <ul class="view" v-if="seen">
        <li>
          <p class="subject">{{boardInfo.title}}</p>
          <p class="date">{{formatDate(boardInfo.dt_frt)}}</p>
        </li>
        <li>
          <p v-html="boardInfo.content"></p>
          <!-- {{boardInfo.content}} -->
        </li>
      </ul>
    </div>
  </section>
</template>
<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "EtcNoticeDetail",
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
    this.$store.state.title = "공지";
  },
  created() {
    this.seq = this.$route.query.seq;
    this.id_board = this.$route.query.id_board;
    this.getEventDatail(this.seq, this.id_board);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getEventDatail: function(seq, id_board) {
      let _this = this;
      let url = "/m/customercenter/getCustomerNoticeDetail.json";
      let frm = new FormData();
      frm.append("seq", seq);
      frm.append("id_board", id_board);
      this.$http.post(url, frm).then(response => {
        _this.boardForm = response.data.boardForm;
        _this.boardInfo = response.data.boardInfo;
        _this.content = _this.seen = true;
      });
    },
    formatDate: function(date) {
      return Common.formatDate(date);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
