<template>
  <section v-if="seen">
    <div class="inout-list">
      <ul class="flex">
        <li class="symbol"><img :src="depWdrlInfo.fcImg" alt="" />{{depWdrlInfo.nm_fc}}</li>
        <li class="num">{{depWdrlInfo.an}}</li>
      </ul>
      <ul class="flex">
        <li>거래시각</li>
        <li>{{formatDateDot(depWdrlInfo.dt_trd)}} {{depWdrlInfo.tm_trd}}</li>
      </ul>
      <ul class="flex">
        <li>거래구분</li>
        <li>{{depWdrlInfo.nm_trns}}</li>
      </ul>
      <ul class="flex">
        <li>적요</li>
        <li>{{depWdrlInfo.dealway1}}</li>
      </ul>
      <ul class="flex">
        <li>내용</li>
        <li>{{depWdrlInfo.doc1}}</li>
      </ul>
      <ul class="flex">
        <li>거래금액</li>
        <li>{{(depWdrlInfo.cd_trns=='01')? formatNumber(depWdrlInfo.amt_dep) : formatNumber(depWdrlInfo.amt_wdrl)}}</li>
      </ul>
      <ul class="flex">
        <li>거래후 금액</li>
        <li>{{formatNumber(depWdrlInfo.balance)}}</li>
      </ul>
    </div>

    <div class="inout-setting">
      <ul class="flex">
        <li>지출 설정</li>
        <li v-if="rk!='0'">
          {{csCategoryText}}
        </li>
        <li v-else>
          <a v-if="consumeInfo != null" @click="goConsumeDetail();">{{csCategoryText}}</a>
          <button v-else @click="goConsumeDetail();">등록하기</button>
        </li>
      </ul>
      <!--
      <ul class="flex">
        <li>공유차단</li>
        <li><button class="btn-onoff"></button></li>
      </ul>
      <p>차단시 상대방에게 입출금 내역을 보이지 않습니다.</p>
      -->
    </div>

    <div class="cs-top">
      <div class="links">
        <a @click="fnShare();" class="share">공유하기</a>
        <a @click="fnCopy();" class="copy">복사</a>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "assetsBankDepWdrlDetail",
  data() {
    return {
      seen: false,
      seq_tran: "", //seq
      rk: "", //colorIndex
      depWdrlInfo: "", //입출금 정보
      consumeInfo: "", //소비정보
      csCategoryText: "", //소비카테고리text
      isNew: true //소비 신규등록여부
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "입출금상세";
  },
  created() {
    this.seq_tran = this.$route.query.seq_tran;
    this.rk = this.$route.query.rk;

    this.getDepWdrlDetail();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //상세정보 조회
    getDepWdrlDetail: function() {
      var _this = this;

      console.log("seq_tran" + _this.seq_tran);

      var formData = new FormData();
      formData.append("seq_tran", _this.seq_tran);

      this.$http
        .post("/m/assets/getAssetsBankDepWdrlDetail.json", formData)
        .then(response => {
          var depWdrlInfo = response.data.depWdrlInfo;
          depWdrlInfo.fcImg =
            "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + depWdrlInfo.cd_fc;
          console.log(depWdrlInfo.tm_trd);
          depWdrlInfo.tm_trd =
            depWdrlInfo.tm_trd.substr(0, 2) +
            ":" +
            depWdrlInfo.tm_trd.substr(2, 2);
          console.log(depWdrlInfo.tm_trd);
          _this.depWdrlInfo = depWdrlInfo;

          //소비카테고리 셋팅
          var consumeInfo = response.data.consumeInfo;
          if (consumeInfo != null) {
            if (_this.depWdrlInfo.cd_trns == "02") {
              _this.csCategoryText =
                consumeInfo.nm_class + " - " + consumeInfo.nm_type;
            } else {
              _this.csCategoryText = consumeInfo.nm_class;
            }
          }
          _this.consumeInfo = consumeInfo;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //소비form이동
    goConsumeDetail: function() {
      var _this = this;

      this.$router.push({
        name: "consumeDetail",
        query: {
          seq_tran: _this.consumeInfo != null ? _this.seq_tran : "",
          type_in_out: _this.depWdrlInfo.cd_trns
        }
      });
    },
    //복사내용
    getCopyContents: function() {
      var _this = this;
      var depWdrlInfo = _this.depWdrlInfo;
      var contents = "";
      contents +=
        Common.formatDateDot(depWdrlInfo.dt_trd) +
        " " +
        depWdrlInfo.tm_trd +
        "\n";
      contents += depWdrlInfo.nm_fc + " " + depWdrlInfo.an + "\n\n";
      contents += depWdrlInfo.nm_trns + " ";
      contents +=
        depWdrlInfo.amt_dep != "0"
          ? Common.formatNumber(depWdrlInfo.amt_dep)
          : Common.formatNumber(depWdrlInfo.amt_wdrl);
      contents += " " + depWdrlInfo.doc1;

      return contents;
    },
    //공유하기
    fnShare: function() {
      var _this = this;
      var contents = _this.getCopyContents();

      if (Constant.userAgent == "iOS") {
        Jockey.send("shareText", {
          text: contents
        });
      } else if (Constant.userAgent == "Android") {
        window.Android.shareText(contents);
      }
    },
    //클립보드복사
    fnCopy: function() {
      var _this = this;
      var contents = _this.getCopyContents();
      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("내역을 복사하시겠습니까?\n\n" + contents, Constant.options)
        .then(res => {
          if (res.ok) {
            if (Constant.userAgent == "iOS") {
              Jockey.send("copyToClipBoard", {
                text: contents
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.copyToClipBoard(contents);
            }
          }
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
