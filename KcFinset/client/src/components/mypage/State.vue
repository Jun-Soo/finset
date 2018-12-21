<template>
  <section v-if="seen">
    <div class="goods-state pb90" v-if="goodsList.length">
      <!-- <p class="number">신청번호 : 2017101500023</p> -->

      <div class="item" v-for="goods in goodsList" :key="goods.index">
        <p class="num">신청번호 : {{goods.no_fc_req}}</p>
        <div class="wrap">
          <div class="top " :class="getClass(goods.cd_apply_doc_box)">
            <a>
              <p class="title">{{Common.getCodeName("cd_apply_doc_box", goods.cd_apply_doc_box)}}</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '10'">대출 신청이 완료되었습니다.</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '20'">대출 심사 진행중입니다.</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '50'">대출 심사가 완료되었습니다.</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '60'">대출금 입금이 완료되었습니다.</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '70'">대출 심사가 부결(거절)되었습니다.</p>
              <p class="text" v-if="goods.cd_apply_doc_box == '99'">대출 신청이 취소되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p :class="{'on':goods.cd_apply_doc_box == '10'}">신청</p>
            <p :class="{'on':goods.cd_apply_doc_box == '20'}">상담/접수</p>
            <p :class="{'on':goods.cd_apply_doc_box == '50'}">심사</p>
            <p class="on" v-if="goods.cd_apply_doc_box == '60'">입금</p>
            <p class="on" v-else-if="goods.cd_apply_doc_box == '70'">거절</p>
            <p class="on" v-else-if="goods.cd_apply_doc_box == '99'">취소</p>
            <p v-else>입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img :src="goods.icon" alt="" />{{Common.getNmFc(goods.cd_fc)}}</p>
          <p><a @click="clickLink(goods.fc_telno)">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">{{goods.nm_goods}}</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">{{Common.formatNumber(goods.amt_limit/10000)}} 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value">
            <em class="state" v-if="goods.type_interest_length > '2'">변동,고정</em>
            <em class="state" v-else-if="goods.type_interest_length == '2'">{{Common.getCodeName("cd_ratio_type", goods.cd_type_interest)}}</em>
            {{goods.rto_loan}} %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">{{goods.cd_loan_term * 12}}개월</p>

        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">{{goods.typePay}}</p>
        </div>
      </div>
    </div>
    <div class="nodata" v-else-if="seen">
      <p>신청한 상품이 없습니다.</p>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "MypageState",
  data() {
    return {
      seen: false,
      Common: Common,
      page: 1,
      goodsList: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상품신청현황";
    this.$store.state.isSetting = true;
  },
  created() {
    Common.pagination(this.listGoodsStatus);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getClass: function(type) {
      if (type == "70") {
        return "reject";
      } else if (type == "99") {
        return "cancel";
      } else {
        return "deposit";
      }
    },
    listGoodsStatus: function(callback) {
      var _this = this;
      this.$store.state.isLoading = true;

      this.$http
        .get("/m/customercenter/listGoodsStatus.json", {
          params: {
            page: _this.page
          }
        })
        .then(response => {
          var list = response.data.pagedListPastHis.source;
          if ((list || "") == "" || list.length === 0) {
            _this.$store.state.isLoading = false;
            _this.seen = true;
            callback();
            return;
          }
          for (var i = 0; i < list.length; i++) {
            list[i].type_interest_length = 0;
            if ((list[i].cd_type_interest || "") != "") {
              list[i].type_interest_length = list[i].cd_type_interest.length;
            }
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            list[i].typePay = "";
            if ((list[i].cd_type_pay || "") != "") {
              var cdTypePay = list[i].cd_type_pay.split(",");
              for (var j = 0; j < cdTypePay.length; j++) {
                if (j) {
                  list[i].typePay += ", ";
                }
                list[i].typePay += Common.getCodeName(
                  "cd_type_pay",
                  cdTypePay[j]
                );
              }
            }
            list[i].isSelect = false;
          }
          if (_this.page == 1) {
            _this.goodsList = list;
          } else {
            for (var key in list) {
              _this.goodsList.push(list[key]);
            }
          }
          _this.page++;
          _this.seen = true;
          _this.$store.state.isLoading = false;
        })
        .catch(e => {
          _this.$store.state.isLoading = false;
          _this.$toast.center(ko.messages.error);
        });
    },
    clickLink: function(fc_telno) {
      this.$dialogs
        .confirm(
          "해당금융사의 고객센터로 연결됩니다. 연결하시겠습니까?",
          Constant.options
        )
        .then(res => {
          if (res.ok) {
            if (Constant.userAgent == "iOS") {
              Jockey.send("phoneCall", {
                phNum: fc_telno
              });
            } else if (Constant.userAgent == "Android") {
              window.Android.phoneCall(fc_telno);
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
