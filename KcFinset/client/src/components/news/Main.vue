<template>
  <section v-if="seen">
    <div class="container">
      <div class="search">
        <div class="left checks search-check">
          <template v-for="scKeywordInfo in scKeywordList">
            <input type="checkbox" v-model="scKeyword" :key="scKeywordInfo.index" :id="'news'+scKeywordInfo.code_value" :value="scKeywordInfo.code_value" @change="searchList()">
            <label :key="scKeywordInfo.index" :for="'news'+scKeywordInfo.code_value">{{scKeywordInfo.nm_code}}</label>
          </template>
        </div>
        <div class="right">
          <select v-model="orderby" @change="searchList()">
            <option v-for="option in orderByOptions" :key="option.index" :value="option.value">
              {{ option.text }}
            </option>
          </select>
          <multiselect v-model="orderbyNm" track-by="text" label="text" placeholder="정렬기준" :options="orderByOptions" :searchable="false" :allow-empty="false" @change="searchList()" @select="onSelect">
            <template slot="singleLabel" slot-scope="{ option }">{{ option.text }}</template>
          </multiselect>
        </div>
      </div>
      <div v-if="newsList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <div v-else class="board-list">
        <div v-for="newsInfo in newsList" :key="newsInfo.index" class="list">
          <div class="left">
            <p class="title"><a @click="viewDetail(newsInfo.seq_news)" v-html="newsInfo.title"></a></p>
            <div class="sub">
              <p>{{newsInfo.news_company}}</p>
              <p>{{newsInfo.pub_date}}</p>
            </div>
          </div>
          <div class="right">
            <img :src="newsInfo.thumImg" alt="" />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";
import defThumImg from "./../../assets/images/common/news_dummy.png";

export default {
  name: "NewsMain",
  data() {
    return {
      seen: false,
      scKeywordList: [],
      scKeyword: [],
      orderByOptions: [
        { text: "최신날짜", value: "01" },
        { text: "많이 본 뉴스", value: "02" }
      ],
      orderby: "01",
      newsList: [],
      totalPage: "",
      page: 1
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "뉴스";
    this.listSearchKeyword();

    if (this.$store.state.scListParam.scKeyword.length != 0) {
      for (var i = 0; i < this.$store.state.scListParam.scKeyword.length; i++) {
        this.scKeyword.push(this.$store.state.scListParam.scKeyword[i]);
      }
    } else if (this.$route.query.scKeyword != null) {
      for (var i = 0; i < this.$route.query.scKeyword.length; i++) {
        this.scKeyword.push(this.$route.query.scKeyword[i]);
      }
    }

    if ("" != this.$store.state.scListParam.orderby) {
      this.orderby = this.$store.state.scListParam.orderby;
    } else if (
      "" != this.$route.query.orderby &&
      this.$route.query.orderby != null
    ) {
      this.orderby = this.$route.query.orderby;
    }

    //store 검색조건 초기화
    this.$store.state.scListParam.scKeyword = [];
    this.$store.state.scListParam.orderby = "";
  },
  beforeMount() {},
  mounted() {
    this.searchList();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    onSelect: function(option) {
      var _this = this;
      _this.orderby = option.value;
      console.log(this.orderby);
    },
    //검색키워드list 조회
    listSearchKeyword: function() {
      var _this = this;
      _this.scKeywordList = Common.getCodeList("news_search_query");
    },
    //뉴스목록 조회
    searchList: function() {
      var _this = this;
      _this.page = 1;
      _this.newsList = [];
      Common.pagination(_this.listNews);
    },
    listNews: function(callback) {
      var _this = this;

      console.log("orderby" + _this.orderby);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("orderby", _this.orderby);
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/news/listNews.json", formData)
        .then(function(response) {
          //썸네일 이미지 셋팅
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            if (list[i].seq_thum_file != null) {
              list[i].thumImg =
                "/m/news/getApiNewsImg.json?seq_news=" +
                list[i].seq_news +
                "&file_type=01";
            }
          }

          //pagination
          if (list.length === 0) {
            callback();
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.newsList = list;
          } else {
            for (var key in list) {
              _this.newsList.push(list[key]);
            }
          }
          _this.totalPage = response.data.pagedList.pageCount;
          _this.page++;
          //pagination

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    //상세페이지로 이동
    viewDetail: function(seq_news) {
      var _this = this;

      this.$store.state.scListParam.scKeyword = _this.scKeyword;
      this.$store.state.scListParam.orderby = _this.orderby;

      this.$router.push({
        name: "newsDetail",
        query: { seq_news: seq_news }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
