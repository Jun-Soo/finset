<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a id="00" name="notice" :class="{'on':curTab === '00'}" @click="clickTab">공지</a>
        <a id="01" name="event" :class="{'on':curTab === '01'}" @click="clickTab">이벤트</a>
      </div>
    </div>
    <div v-if="seen">
      <!-- 공지사항 tab -->
      <div v-if="curTab=='00'" class="board">
        <ul class="list">
          <li v-for="page in pagedList" :key="page.seq">
            <p class="date">{{page.dt_frt}}</p>
            <p class="subject"><a @click="$router.push('/etc/noticeDetail?seq='+page.seq+'&id_board='+page.id_board)">{{page.title}}</a></p>
          </li>
        </ul>
      </div>
      <!-- 이벤트 tab -->
      <div v-if="curTab=='01'" class="board event">
        <ul class="list">
          <li v-for="page in pagedList" :key="page.seq">
            <a @click="$router.push('/etc/eventDetail?seq='+page.seq+'&id_board='+page.id_board)">
              <div>
                <p class="subject">{{page.title}}</p>
                <p class="date">{{page.ymd_post_strt}} ~{{page.ymd_post_end}}</p>
                <p class="state pre" v-if="page.cd_event_proc==='01'">예정</p>
                <p class="state ing" v-else-if="page.cd_event_proc==='02'">진행중</p>
                <p class="state done" v-else>종료</p>
              </div>
              <div>
                <img :src="getImgUrl(page)" v-bind:alt="page" width="80" />
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "EtcNoticeMain",
  data() {
    return {
      curTab: "00",
      curTabName: "notice",
      boardForm: "",
      pagedList: new Array(),
      seen: false,
      id_board: "",
      page: 1,
      totalPage: 1,
      seq: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "공지/이벤트";
  },
  created() {
    let _this = this;
    // $("a:first").trigger("click");
    _this.getListNotice(_this.curTabName);
  },
  beforeMount() {},
  mounted() {
    let _this = this;
    $(window).scroll(function() {
      if ($(document).height() <= $(window).scrollTop() + $(window).height()) {
        console.log(
          _this.curTabName + " " + _this.totalPage + " " + _this.page
        );
        if (_this.totalPage > _this.page) {
          _this.jumpPage(_this.curTabName);
          // _this.page++;
        }
      }
    });
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickTab: function(tab) {
      let _this = this;
      _this.curTab = tab.srcElement.id;
      _this.curTabName = tab.srcElement.name;
      _this.initList();
      _this.getListNotice(_this.curTabName);
    },
    initList: function() {
      let _this = this;
      _this.boardForm = "";
      _this.pagedList = new Array();
      _this.page = 1;
    },
    getListNotice: function(gubun) {
      let _this = this;
      let url = "/m/customercenter/listCustomerNotice.json";
      let frm = new FormData();
      frm.append("id_board", gubun);
      frm.append("page", _this.page);
      // frm.append('seq', gubun);
      _this.$http.post(url, frm).then(response => {
        _this.boardForm = response.data.boardForm;
        _this.pagedList = response.data.pagedList.source;
        _this.page = response.data.pagedList.page;
        _this.totalPage = response.data.pagedList.pageCount;
        _this.seen = true;
      });
    },
    getImgUrl: function(page) {
      return (
        "/m/board/getBoardImg.json?id_board=" +
        page.id_board +
        "&seq=" +
        page.seq +
        "&file_type=01"
      );
    },
    jumpPage: function(gubun) {
      var _this = this;
      var url = "/m/customercenter/listCustomerNotice.json";
      var pageIndex = Number(_this.page);
      _this.page = pageIndex + 1;

      var frm = new FormData();
      frm.append("id_board", gubun);
      frm.append("page", _this.page);
      _this.$http.post(url).then(response => {
        _this.pagedList.concat(response.data.pagedList.source);
        _this.page = response.data.pagedList.page;
        _this.totalPage = response.data.pagedList.pageCount;
      });
    }
  }
};
</script>
