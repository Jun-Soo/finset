<template>
  <newsDetail>
  <div style="font-size:20pt;">제목:{{title}}</div>
  <div style="font-size:20pt;">뉴스원:{{newsCompany}}</div>
  <div style="font-size:20pt;">일자:{{pubDate}}</div>
  <div style="font-size:20pt;">연계링크메세지:{{localLinkText}}</div>
  <div style="font-size:20pt;">연계링크:{{localLink}}</div>
  <div style="font-size:20pt;"><a @click="goLocalLink(localLink)">{{localLinkText}}</a></div>
  <div style="font-size:20pt;"><img :src="bodyImg" alt="본문img" /></div>
  <div style="font-size:20pt;">내용:{{contents}}</div>
  </newsDetail>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";
import defBodyImg from "./../../assets/images/common/news_dummy.png";

export default {
  name: "helloWorld",
  data() {
    return {
      errMsg: "",
      title: "", //제목
      newsCompany: "", //뉴스원
      pubDate: "", //일자
      contents: "", //내용
      localLinkText: "", //연계링크메세지
      localLink: "", //연계링크
      bodyImg: "" //본문이미지
    };
  },
  component: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "뉴스 상세";
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
          params: { seq_news: this.$route.params.seq_news }
        })
        .then(response => {
          var newsInfo = response.data.newsInfo;
          _this.title = newsInfo.title;
          _this.newsCompany = newsInfo.news_company;
          _this.pubDate = newsInfo.pub_date;
          _this.contents = newsInfo.contents;
          _this.localLinkText = newsInfo.local_link_text;
          _this.localLink = newsInfo.local_link;
          //본문이미지 셋팅
          if(newsInfo.seq_body_file != null){
           _this.bodyImg = "/m/news/getApiNewsImg.json?seq_news=" + newsInfo.seq_news + "&file_type=02";
          }else{
            _this.bodyImg = defBodyImg;
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //연계링크 이동
    goLocalLink: function(link) {
      this.$router.push(link);
    },
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
