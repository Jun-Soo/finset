<template>
  <section>
      <div class="container">
          <div class="search">
              <div class="left">
                <select v-model="orderby" @change="orderbyOnChange()">
                    <option v-for="option in options" :key="option.index" :value="option.value">
                        {{ option.text }}
                    </option>
                </select>
              </div>
              <div class="right">
                  <button class="btn-search" @click="clickSearch()"></button>
              </div>
          </div>
          <div class="board-list">
              <div v-for="newsInfo in newsList" :key="newsInfo.index" class="list">
                  <div class="left">
                      <p class="title"><a @click="viewDetail(newsInfo.seq_news)">{{newsInfo.title}}</a></p>
                      <div class="sub">
                          <p>{{newsInfo.news_company}}</p>
                          <p>{{newsInfo.pub_date}}</p>
                      </div>
                  </div>
                  <div class="right">
                      <img :src="newsInfo.thumImg" alt=""/>
                  </div>
              </div>
          </div>
      </div>
      <aside class="search-wrap" :class="{'on':isSearch}">
          <div class="top" @click="clickSearch()">
              <button>검색</button>
          </div>
          <div class="wrap">
            <div class="btns">
              <template v-for="scKeywordInfo in scKeywordList">
                  <button :key="scKeywordInfo.index" @click="setSearchKeyword(scKeywordInfo.code_value)">{{scKeywordInfo.nm_code}}</button>
              </template>
            </div>
        </div>
      </aside>
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
      options: [
        { text: "최신날짜", value: "01" },
        { text: "많이 본 뉴스", value: "02" }
      ],
      orderby: "01",
      scKeywordList: [],
      scKeyword: "",
      isSearch: false,
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
  },
  beforeMount() {},
  mounted() {
    this.orderbyOnChange();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //검색키워드 목록조회
    listSearchKeyword: function() {
      var _this = this;
      _this.scKeywordList = Common.getCodeList("news_search_query");
    },
    //검색버튼 클릭
    clickSearch: function() {
      var _this = this;
      _this.isSearch = !_this.isSearch;
    },
    //정렬순서 변경
    orderbyOnChange: function() {
      var _this = this;
      _this.page = 1;
      _this.listNews();
      Common.pagination(_this.listNews);
    },
    //검색키워드setting
    setSearchKeyword: function(codeVal) {
      var _this = this;
      _this.scKeyword = codeVal;
      _this.page = 1;
      _this.listNews();
      Common.pagination(_this.listNews);
    },
    //뉴스목록 조회
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
            } else {
              list[i].thumImg = defThumImg;
            }
          }

          //pagination
          if (list.length === 0) {
            callback();
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

        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //상세페이지로 이동
    viewDetail: function(seq_news) {
      this.$router.push({
        name: "newsDetail",
        params: { seq_news: seq_news }
      });
    },
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
