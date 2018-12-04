<template>
  <section v-if="seen">
    <!-- <div class="tab">
      <div class="wrap">
        <a class="on">신용대출</a>
        <a>담보대출</a>
      </div>
    </div> -->

    <div class="box-list list01">
      <div class="guide-box" id="info">
        <p><strong>대출 신청은 하나의 상품만 선택해 신청 가능합니다.</strong></p>
        <p class="mt10">대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도의 변동되거나 대출이 불가능 할 수 있습니다.</p>
        <a class="close" @click="clickClose"></a>
      </div>

      <div class="item" v-for="goods in goodsList" :key="goods.index">
        <div class="top">
          <p class="symbol checks">
            <input type="checkbox" id="chk1" :checked="goods.isSelect"><label v-if="goods.yn_loan == 'Y' && goods.apply_cnt == 0" @click="clickCheck(goods)"></label>
            <img src="../../assets/images/assets/ico_x.png" class="x" alt="" v-else />
            <img :src="goods.icon" alt="" />{{goods.nm_fc}}</p>
          <p class="text blue" v-if="goods.yn_loan == 'Y' && goods.apply_cnt == 0">신청가능</p>
          <p class="text" v-else-if="goods.cd_status != '04' && goods.yn_loan == 'N' && (goods.yn_receive == 'Y' || goods.yn_receive == null)">신청불가능</p>
          <p class="text blue" v-else-if="goods.yn_loan == 'Y' && goods.apply_cnt > 0">신청완료</p>
          <p class="text red" v-else-if="goods.cd_status == '02'">신청불가능</p>
          <p class="text red" v-else-if="goods.cd_status == '03'">조회실패</p>
          <p class="text red" v-else-if="goods.cd_status == '04'">조회중</p>
        </div>
        <p class="goods-name">{{goods.nm_goods}}</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">대출한도</p>
            <p class="right">{{Common.formatNumber(goods.amt_limit)}} 만원</p>
          </div>
          <div class="list">
            <p class="left">대출금리</p>
            <p class="right">
              <em class="state" v-if="goods.type_interest_length > '2'">변동,고정</em>
              <em class="state" v-else-if="goods.type_interest_length == '2'">{{Common.getCodeName("cd_ratio_type", goods.cd_type_interest)}}</em>
              {{goods.rto_loan}} %</p>
          </div>
          <div class="list">
            <p class="left">대출기간</p>
            <p class="right">{{goods.year_term * 12}}개월</p>
          </div>
          <div class="list">
            <p class="left">상환방식</p>
            <p class="right">{{goods.typePay}}</p>
          </div>
        </div>
      </div>
      <div class="btn-wrap float" v-if="selectedCount > 0">
        <a class="solid box blue" @click="clickRequest()">대출 신쳥하기</a>
      </div>

    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

import "@/assets/js/jquery.easing.1.3.js";
export default {
  name: "MypageRstlInqGoods",
  data() {
    return {
      seen: false,
      Common: Common,
      page: 1,
      selectedCount: 0,
      goodsList: []
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상품조회 결과";
  },
  created() {
    Common.pagination(this.listGoodsResults);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listGoodsResults: function(callback) {
      var _this = this;
      this.$store.state.isLoading = true;
      var formData = new FormData();
      formData.append("page", this.page);
      this.$http
        .post("/m/customercenter/listGoodsResults.json", formData, {
          headers: {
            async: false,
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {
          var list = response.data.pagedList.source;
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
          _this.$toast.center(ko.messages.error);
        });
    },
    clickClose: function() {
      $("#info").slideUp(500, "easeInOutExpo");
    },
    clickCheck: function(goods) {
      goods.isSelect = !goods.isSelect;
      if (goods.isSelect) {
        this.selectedCount++;
      } else {
        this.selectedCount--;
      }
    },
    clickRequest: function() {
      var count = 0;
      for (var i = 0; i < this.goodsList.length; i++) {
        if (this.goodsList[i].isSelect) {
          count++;
        }
      }
      if (this.selectedCount > 0) {
        this.$dialogs.alert(
          "대출은 한건만 신청할 수 있습니다.",
          Constant.options
        );
      }
      //alert(count);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
