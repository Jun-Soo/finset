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
      <!--no data일때 처리 필요한지 물어봐야함-->
      <div v-if="curTab=='00'" class="board">
        <div v-if="noticeList.length==0" class="container nodata">공지사항이 없습니다.</div>
        <ul v-else class="list">
          <li v-for="page in noticeList" :key="page.seq">
            <p class="date">{{formatDateDot(page.dt_frt)}}</p>
            <p class="subject"><a @click="$router.push('/etc/noticeDetail?seq='+page.seq+'&id_board='+page.id_board)">{{page.title}}</a></p>
          </li>
        </ul>
      </div>
      <!-- 이벤트 tab -->
      <div v-if="curTab=='01'" class="board event">
        <div v-if="eventList.length==0" class="container nodata">이벤트가 없습니다.</div>
        <ul v-else class="list">
          <li v-for="page in eventList" :key="page.seq">
            <a @click="$router.push('/etc/eventDetail?seq='+page.seq+'&id_board='+page.id_board)">
              <div>
                <p class="subject">{{page.title}}</p>
                <p class="date">{{page.ymd_post_strt}} ~ {{page.ymd_post_end}}</p>
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
import Common from "./../../assets/js/common.js";
export default {
  name: "EtcNoticeMain",
  data() {
    return {
      curTab: "00",
      curTabName: "notice",
      /**혹시 나중에 리스트 분리시 필요하면 추가 */
      noticeList: new Array(),
      eventList: new Array(),
      noticeForm: "",
      eventForm: "",
      eventPage: 1,
      noticePage: 1,
      eTotalPage: 1,
      nTotalPage: 1,

      boardForm: "",
      pagedList: new Array(),
      id_board: "",
      page: 1,
      totalPage: 1,
      seq: "",
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "공지/이벤트";
    this.$store.state.header.backPath = "/main";
  },
  created() {
    if (this.$route.query.tab == "01") {
      this.curTab = this.$route.query.tab;
      this.curTabName = "event";
    } else if (this.$route.query.tab == "00") {
      this.curTab = this.$route.query.tab;
      this.curTabName = "notice";
    }
  },
  beforeMount() {},
  mounted() {
    let _this = this;
    Common.pagination(this.getListNotice);
  },
  beforeUpdate() {},
  updated() {
    // let _this = this;
    // Common.pagination(this.getListNotice);
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickTab: function(tab) {
      let _this = this;
      _this.curTab = tab.srcElement.id;
      _this.curTabName = tab.srcElement.name;
      _this.seen = false;
      // _this.initList();
      _this.getListNotice();
    },
    initList: function() {
      let _this = this;
      _this.boardForm = "";
      _this.pagedList = new Array();
      _this.page = 1;
      _this.seen = false;
    },
    getListNotice: function() {
      let _this = this;
      let url = "/m/customercenter/listCustomerNotice.json";
      let frm = new FormData();
      frm.append("id_board", _this.curTabName);
      if (_this.curTabName == "notice") {
        frm.append("page", _this.noticePage);
      } else {
        frm.append("page", _this.eventPage);
      }
      _this.$http.post(url, frm).then(response => {
        if (_this.curTabName == "notice") {
          let plist = response.data.pagedList.source;
          if (plist.length != 0) {
            _this.noticeForm = response.data.boardForm;
            _this.noticePage = response.data.pagedList.page;
            _this.nTotalPage = response.data.pagedList.pageCount;
            if (_this.noticePage === 1) {
              _this.noticeList = plist;
            } else {
              for (var p in plist) {
                _this.noticeList.push(plist[p]);
              }
            }
            _this.noticePage++;
          }
        } else {
          let plist = response.data.pagedList.source;
          if (plist.length != 0) {
            _this.eventForm = response.data.boardForm;
            _this.eventPage = response.data.pagedList.page;
            _this.eTotalPage = response.data.pagedList.pageCount;
            if (_this.eventPage === 1) {
              _this.eventList = plist;
            } else {
              for (var p in plist) {
                _this.eventList.push(plist[p]);
              }
            }
            _this.eventPage++;
          }
        }
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
    formatDateDot: function(date) {
      return Common.formatDateDot(date);
    }
  }
};
</script>
