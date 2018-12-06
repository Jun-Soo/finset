<template>
  <section v-if="seen">
    <div class="container">
      <div class="history">
      <div v-if="histList.length == 0" class="nodata">등록 내역이 없습니다</div>
      <template v-if="this.type_list == 'req'">
        <div v-for="histInfo in histList" :key="histInfo.index" class="item pb10">
          <p class="name">{{histInfo.offer_nm_person}}</p>
          <p class="result">{{histInfo.share_status=='04'? '공유종료' : '요청취소'}}</p>
          <p class="number">{{histInfo.offer_hp}}</p>
          <p v-if="histInfo.share_status=='04'" class="date">{{formatDateDot(histInfo.dt_stt_offer)}}~{{formatDateDot(histInfo.dt_end_offer)}}</p>
          <p v-else class="date">{{formatDateDot(histInfo.dt_frt)}}</p>
          <p class="cate">
            <em v-if="histInfo.yn_credit_info=='Y'">신용등급</em>
            <em v-if="histInfo.cd_share=='01' && histInfo.yn_debt_info=='Y'">대출</em>
            <em v-if="histInfo.yn_asset_info=='Y'">자산</em>
            <em v-if="histInfo.yn_consume_info=='Y'">소비</em>
            <em v-if="histInfo.cd_share=='02' && histInfo.yn_debt_info=='Y'">부채</em>
          </p>
        </div>
      </template>
      <template v-else> 
        <div v-for="histInfo in histList" :key="histInfo.index" class="item pb10">
          <p class="name">{{histInfo.req_nm_person}}</p>
          <p class="result">{{histInfo.share_status=='03'? '거절' : '공유취소'}}</p>
          <p class="number">{{histInfo.req_hp}}</p>
          <p v-if="histInfo.share_status=='03'" class="date">{{formatDateDot(histInfo.dt_frt)}}</p>
          <p v-else class="date">{{formatDateDot(histInfo.dt_stt_offer)}}~{{formatDateDot(histInfo.dt_end_offer)}}</p>
          <p class="cate">
            <em v-if="histInfo.yn_credit_info=='Y'">신용등급</em>
            <em v-if="histInfo.cd_share=='01' && histInfo.yn_debt_info=='Y'">대출</em>
            <em v-if="histInfo.yn_asset_info=='Y'">자산</em>
            <em v-if="histInfo.yn_consume_info=='Y'">소비</em>
            <em v-if="histInfo.cd_share=='02' && histInfo.yn_debt_info=='Y'">부채</em>
          </p>
        </div>
      </template>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "shareHistory",
  data() {
    return {
      seen: false,
      cd_share: "01", //공유구분
      type_list: "req", //요청(req) / 정보제공(offer)
      histList: [] //list
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이전 내역 보기";
  },
  created() {
    this.cd_share = this.$route.query.cd_share;
    this.type_list = this.$route.query.type_list;

    console.log(this.cd_share);
    console.log(this.type_list);

    this.getHistInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //이전내역보기 정보
    getHistInfo: function() {
      var _this = this;
      this.$http
        .get("/m/customercenter/listShareInfoHist.json", {
          params: { cd_share: _this.cd_share, type_list: _this.type_list }
        })
        .then(response => {
          _this.histList = response.data.histList;
          console.log(_this.histList);

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
