<template>
  <section v-if="seen">
    <div class="cs-top">
      <div class="cs-search">
        <input type="search" id="txtDetail" v-on:keyup.enter="goSearch" v-model="txt_detail" placeholder="자주 묻는 질문 검색">
      </div>
    </div>

    <div class="faq-list" v-if="pageGubun != 'search'">
      <p class="title">{{nm_board}}</p>
      <div v-if="pagedList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="list" v-for="page in pagedList" :key="page.board_idx">
        <p><a href="#" v-html="page.title"></a></p>
        <p v-html="page.content"></p>
      </div>
    </div>

    <div class="faq-list" v-else>
      <div v-if="pagedList.length == 0" class="nodata">검색 내역이 없습니다</div>
      <div v-else class="list" v-for="page in pagedList" :key="page.board_idx">
        <p><a href="#" v-html="page.title"></a></p>
        <p v-html="page.content"></p>
      </div>
    </div>
  </section>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
// import Common from "./../../assets/js/common.js";

export default {
  name: "EtcFaqDetail",
  data() {
    return {
      id_board: "",
      boardForm: "",
      nm_board: "",
      pagedList: [],
      seq: "",
      txt_detail: "",
      pageGubun: "",
      page: 1,
      totalPage: "",
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "자주 묻는 질문";
  },
  created() {
    this.init();
    if (this.$route.query.txt_detail != null) {
      this.txt_detail = this.$route.query.txt_detail;
      this.goSearch();
      // Common.pagination();
    } else {
      this.id_board = this.$route.query.id_board;
      this.getIdBoardDetail();
    }
  },
  beforeMount() {},
  mounted() {
    let _this = this;
    $(window).scroll(function() {
      if ($(document).height() <= $(window).scrollTop() + $(window).height()) {
        console.log(
          _this.txt_detail + " " + _this.totalPage + " " + _this.page
        );
        if (_this.totalPage > _this.page) {
          Common.pagination(_this.jumpPage);
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
    init: function() {
      let _this = this;
      _this.id_board = "";
      _this.boardForm = "";
      _this.nm_board = "";
      _this.pagedList = [];
      _this.seq = "";
      _this.txt_detail = "";
      _this.page = 1;
      _this.totalPage = 1;
    },
    getIdBoardDetail: function() {
      this.pageGubun = "getList";
      this.$http
        .get(
          "/m/customercenter/getCustomerFAQDetail.json?id_board=" +
            this.id_board
        )
        .then(response => {
          this.boardForm = response.data.boardForm;
          this.nm_board = response.data.nm_board;
          this.pagedList = response.data.pagedList.source;
          this.totalPage = response.data.pagedList.pageCount;
          this.page = response.data.pagedList.page;
          this.seq = response.data.seq;

          this.seen = true;
        });
    },
    goSearch: function() {
      this.pageGubun = "search";
      let url = "/m/customercenter/getCustomerFAQSearch.json";
      let _this = this;
      if (_this.txt_detail.length >= 2) {
        _this.$router.push("/etc/faqDetail?txt_detail=" + _this.txt_detail);
      } else {
        this.$toast.center("검색어를 두자이상 입력하세요.");
      }
      let boardForm = new FormData();
      boardForm.append("txt_detail", _this.txt_detail);
      boardForm.append("page", _this.page);
      _this.$http.post(url, boardForm).then(response => {
        //list init
        _this.nm_board = "";
        _this.pagedList = [];
        _this.totalPage = response.data.pagedList.pageCount;
        _this.pagedList = response.data.pagedList.source;
        _this.page = response.data.pagedList.page;
        // Common.pagination();
        _this.seen = true;
      });
    },
    jumpPage: function() {
      //test 필요
      var _this = this;
      var pageIndex = Number(_this.page);
      var url = "/m/customercenter/listFaqSearch.json";
      _this.page = pageIndex + 1;
      var data = new FormData();
      data.append("txt_detail", _this.txt_detail);
      data.append("page", _this.page);
      data.append("totalPage", _this.totalPage);
      if (data == null) {
        //test 필요
        return false;
      }
      _this.$store.state.isLoading = true;

      _this.$http.post(url, data).then(response => {
        _this.totalPage = response.data.pagedList.pageCount;
        //append
        var plist = response.data.pagedList.source;
        if (plist.length != 0) {
          for (var k in plist) {
            _this.pagedList.push(plist[k]);
          }
        }
        _this.page = response.data.pagedList.page;

        _this.seen = true;
        _this.$store.state.isLoading = false;
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
