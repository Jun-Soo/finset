<template>
  <section v-if="seen">
    <div class="board event">
      <ul class="view">
        <li>
          <p class="subject" v-html="title" />
          <p class="date"><em>{{newsCompany}}</em>{{pubDate}}</p>
        </li>
        <li>
          <!-- <img :src="bodyImg" /> -->
          <div class="mt10 mb30" v-html="contents" />
        </li>
      </ul>
    </div>
    <div v-if="localLink!=null" class="btn-wrap float">
      <a @click="goLocalLink(localLink)" class="solid blue box">{{localLinkText!=null? localLinkText : "연계링크이동"}}</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "helloWorld",
  data() {
    return {
      errMsg: "",
      seen: false,
      title: "", //제목
      newsCompany: "", //뉴스원
      pubDate: "", //일자
      contents: "", //내용
      localLinkText: "", //연계링크메세지
      localLink: "" //연계링크
      // bodyImg: "" //본문이미지
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "뉴스";
    this.getNewsInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getNewsInfo: function() {
      var _this = this;
      this.$http
        .get("/m/news/getNewsInfo.json", {
          params: { seq_news: this.$route.query.seq_news }
        })
        .then(response => {
          var newsInfo = response.data.newsInfo;
          _this.title = newsInfo.title;
          _this.newsCompany = newsInfo.news_company;
          _this.pubDate = newsInfo.pub_date;
          //banker단의 이미지URL주소를 client주소로 변경
          _this.contents = _this.replaceAll(
            newsInfo.contents,
            "/contents/getApiNewsImg.crz",
            "/m/news/getApiNewsImg.json"
          );
          _this.localLinkText = newsInfo.local_link_text;
          _this.localLink = newsInfo.local_link;
          // //본문이미지 셋팅
          // if (newsInfo.seq_body_file != null) {
          //   _this.bodyImg =
          //     "/m/news/getApiNewsImg.json?seq_news=" +
          //     newsInfo.seq_news +
          //     "&file_type=02";
          // }

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    replaceAll: function(str, searchStr, replaceStr) {
      return str.split(searchStr).join(replaceStr);
    },
    //연계링크 이동
    goLocalLink: function(link) {
      this.$router.push(link);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
