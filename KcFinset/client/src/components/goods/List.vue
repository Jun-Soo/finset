<template>
  <div>
    <section>
      <div class="goods-wrap goods" v-if="seen && goodsList.length">
        <carousel :perPage=1>
          <slide class="item" v-for="goods in goodsList" :key="goods.index">
            <a @click="loanGoodsDetail(goods)">
              <div class="top">
                <p class="symbol"><img :src="goods.icon" alt="" />{{goods.nm_fc}}</p>
                <!-- <p class="text blue">{{goods.nm_goods}} <button class="btn-star" :class="{'on':goods.isChecked}" @click="loanGoodsChoice(goods, $event)"></button></p> -->
                <p class="text blue">{{goods.nm_goods}}</p>
              </div>
              <div class="goods-benefit">
                <div>{{goods.rto_interest_from}}~{{goods.rto_interest_to}}<em> %</em></div>
                <div><em>최대 </em>{{Common.formatNumber(goods.amt_limit)}}<em> 만원</em></div>
              </div>
              <p class="goods-text1" v-html=goods.desc_feature></p>
              <p class="goods-text2" v-html=goods.deliberate></p>
            </a>
          </slide>
        </carousel>
      </div>
      <div class="nodata" v-else-if="seen">
        신청 가능한 상품이 없습니다.
      </div>

      <div class="tab mt40">
        <div class="wrap">
          <a :class="{'on':curTab === 'loanWorker'}" @click="tabOnClick('loanWorker')">신용대출</a>
          <a :class="{'on':curTab === 'loanHome'}" @click="tabOnClick('loanHome')">주택담보</a>
          <!-- <a :class="{'on':curTab === 'loanStock'}" @click="tabOnClick('loanStock')">스탁론</a> -->
        </div>
      </div>
      <!-- <div class="banner-wrap" :style="{'display-none':curTab == 'loanStock'}" v-if="curTab == 'loanStock'"> -->
      <div v-show="curTab == 'loanStock'">
        <div class="banner-wrap ">
          <div class="item">
            <a>
              <div class="banner">
                <div class="left">
                  <p class="key">숨겨진 증권계좌를 한곳에</p>
                  <p class="value">클릭만으로 흩어져 있는<br>증권 계좌를 확인해 보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/goods/banner1.png" alt="" />
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
      <div v-show="curTab != 'loanStock'">
        <div class="box-list goods goods-list">
          <div class="select">
            <div class="left">
              <multiselect class="multiselect-basic" v-model="orderby" :title="'정렬'" label="text" :options="options" :onClose="orderbyOnChange">
              </multiselect>
              <!-- <select v-model="orderby" @change="orderbyOnChange()">
                <option v-for="option in options" :key="option.index" v-bind:value="option.value">
                  {{ option.text }}
                </option>
              </select> -->
            </div>
            <div class="right">
              <button class="btn-search" @click="clickSearch()"></button>
            </div>
          </div>
          <listLoanNoAffiliates :item="item" ref="form" />
        </div>
      </div>
    </section>
    <aside class="search-wrap" :class="{'on':isSearch}" v-if="'loanWorker' === this.curTab">
      <div class="top" @click="clickSearch()">
        <button>검색</button>
      </div>
      <div class="wrap">
        <!-- <div class="item">
          <div class="key">신용등급</div>
          <div class="bar">
            <p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p>
          </div>
          <div class="num">3</div>
        </div> -->
        <div class="check-wrap">
          <div class="key">종류</div>
          <div class="search-check">
            <div><input type="checkbox" id="chk1" :checked="isCheckWorker" @click="clickCheckWorker()"><label for="chk1">직장인</label></div>
            <div><input type="checkbox" id="chk2" :checked="isCheckSelf" @click="clickCheckSelf()"><label for="chk2">자영업</label></div>
          </div>
        </div>
      </div>
      <div class="action">
        <a class="stroke" @click="clickStroke()">초기화</a>
        <a class="solid" @click="clickSolid()">적용</a>
      </div>
    </aside>
    <aside class="search-wrap" :class="{'on':isSearch}" v-if="'loanHome' === this.curTab">
      <div class="top" @click="clickSearch()">
        <button>검색</button>
      </div>
      <div class="wrap">
        <!-- <div class="check-wrap">
          <div class="key">종류</div>
          <div class="search-check">
            <div><input type="checkbox" id="chk1" :checked="isCheckApart" @click="clickCheckApart()"><label for="chk1">아파트</label></div>
            <div><input type="checkbox" id="chk2" :checked="isCheckEtc" @click="clickCheckEtc()"><label for="chk2">아파트외</label></div>
          </div>
        </div> -->
        <div class="check-wrap">
          <div class="key">금리방식</div>
          <div class="search-check">
            <div><input type="checkbox" id="chk3" :checked="isCheckFixed" @click="clickCheckFixed()"><label for="chk3">고정금리</label></div>
            <div><input type="checkbox" id="chk4" :checked="isCheckFloating" @click="clickCheckFloating()"><label for="chk4">변동금리</label></div>
          </div>
        </div>
        <div class="check-wrap">
          <div class="key">상환방식</div>
          <div class="search-check">
            <div><input type="checkbox" id="chk5" :checked="isCheckDiv" @click="clickCheckDiv()"><label for="chk5">분할상환</label></div>
            <div><input type="checkbox" id="chk6" :checked="isCheckBullet" @click="clickCheckBullet()"><label for="chk6">만기일시</label></div>
          </div>
        </div>
      </div>
      <div class="action">
        <a class="stroke" @click="clickStroke()">초기화</a>
        <a class="solid" @click="clickSolid()">적용</a>
      </div>
    </aside>
  </div>
</template>

<script>
import listLoanNoAffiliates from "./sub/listLoanNoAffiliates";

import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "List",
  data() {
    return {
      seen: false,
      isSearch: false,
      isCheckWorker: true,
      isCheckSelf: true,
      isCheckApart: true,
      isCheckEtc: true,
      isCheckFixed: true,
      isCheckFloating: true,
      isCheckDiv: true,
      isCheckBullet: true,
      item: {},
      Common: Common,
      urlPath: "/m/loanworker/",
      show: true,
      curTab: "loanWorker",
      totalPage: "",
      page: 1,
      cd_goods_class_l: "01",
      cd_goods_class_m: "01,03,08,09",
      cd_ratio_type: "01,02,03",
      cd_type_pay: "01,02,03",
      options: [
        { text: "금리순", value: "01" },
        { text: "한도순", value: "02" },
        { text: "기간순", value: "03" }
      ],
      orderby: { text: "금리순", value: "01" },
      goodsList: []
    };
  },
  components: {
    listLoanNoAffiliates
  },
  computed: {},
  beforeCreate() {},
  created() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "추천상품";
    window.resultCheckCert = this.resultCheckCert;
    window.resultCheckPasswordCert = this.resultCheckPasswordCert;
  },
  beforeMount() {},
  mounted() {
    // if (this.$store.state.header.fromPath) {
    //   this.$store.state.header.backPath = this.$store.state.header.fromPath;
    // }
    this.page = 1;
    this.listGoods();
    Common.pagination(this.$refs.form.listBankGoods);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickSearch: function() {
      this.isSearch = !this.isSearch;
    },
    clickCheckWorker: function() {
      this.isCheckWorker = !this.isCheckWorker;
    },
    clickCheckSelf: function() {
      this.isCheckSelf = !this.isCheckSelf;
    },
    clickStroke: function() {
      if ("loanWorker" == this.curTab) {
        this.isCheckWorker = true;
        this.isCheckSelf = true;
      } else if ("loanHome" == this.curTab) {
        this.isCheckApart = true;
        this.isCheckEtc = true;
        this.isCheckFixed = true;
        this.isCheckFloating = true;
        this.isCheckDiv = true;
        this.isCheckBullet = true;
      }
    },
    clickSolid: function() {
      if ("loanWorker" == this.curTab) {
        this.cd_goods_class_m = "03,08,09";
        if (this.isCheckWorker)
          this.cd_goods_class_m = this.cd_goods_class_m + ",01";
        if (this.isCheckSelf)
          this.cd_goods_class_m = this.cd_goods_class_m + ",02";
      } else if ("loanHome" == this.curTab) {
        this.cd_ratio_type = "03";
        this.cd_type_pay = "01";
        // if (this.isCheckApart)
        // if (this.isCheckEtc)
        if (this.isCheckFixed) this.cd_ratio_type = this.cd_ratio_type + ",01";
        if (this.isCheckFloating)
          this.cd_ratio_type = this.cd_ratio_type + ",02";
        if (this.isCheckDiv) this.cd_type_pay = this.cd_type_pay + ",02";
        if (this.isCheckBullet) this.cd_type_pay = this.cd_type_pay + ",03";
      }
      this.page = 1;
      this.loadGoodsTab(this.curTab);
      this.clickSearch();
    },
    clickCheckApart: function() {
      this.isCheckApart = !this.isCheckApart;
    },
    clickCheckEtc: function() {
      this.isCheckEtc = !this.isCheckEtc;
    },
    clickCheckFixed: function() {
      this.isCheckFixed = !this.isCheckFixed;
    },
    clickCheckFloating: function() {
      this.isCheckFloating = !this.isCheckFloating;
    },
    clickCheckDiv: function() {
      this.isCheckDiv = !this.isCheckDiv;
    },
    clickCheckBullet: function() {
      this.isCheckBullet = !this.isCheckBullet;
    },
    clickSearch: function() {
      this.isSearch = !this.isSearch;
    },
    orderbyOnChange: function(option) {
      this.orderby = option;
      this.page = 1;
      this.listGoods();
      Common.pagination(this.$refs.form.listBankGoods);
    },
    loadGoodsTab: function(type) {
      if (type == undefined || type == "") {
        type = "loanWorker";
      }
      this.curTab = type;
      if ("loanWorker" == this.curTab) {
        this.cd_goods_class_l = "01";
        this.cd_goods_class_m = "03,08,09";
        if (this.isCheckWorker)
          this.cd_goods_class_m = this.cd_goods_class_m + ",01";
        if (this.isCheckSelf)
          this.cd_goods_class_m = this.cd_goods_class_m + ",02";
        this.urlPath = "/m/loanworker/";
      } else if ("loanHome" == this.curTab) {
        this.cd_goods_class_l = "02";
        this.cd_goods_class_m = "05, 08";
        this.urlPath = "/m/loanhomemortgage/";
      } else if ("loanStock" == this.curTab) {
        //do nothing
      }
      //this.listGoods();
      Common.pagination(this.$refs.form.listBankGoods);
    },
    listGoods: function() {
      var _this = this;
      var formData = new FormData();
      // formData.append("cd_goods_class_l", this.cd_goods_class_l);
      // formData.append("cd_goods_class_m", this.cd_goods_class_m);
      // formData.append("cd_ratio_type", this.cd_ratio_type);
      // formData.append("cd_type_pay", this.cd_type_pay);

      // 전체 제휴 상품 조회
      formData.append("orderby", this.orderby.value);
      this.$http
        //.post(_this.urlPath + "listLoanAffiliates.json", formData)
        .post("/m/loan/listLoanAffiliatesAll.json", formData)
        .then(function(response) {
          var list = response.data.goodsList;
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            if (list[i].yn_favorite == "Y") {
              list[i].isChecked = true;
            } else {
              list[i].isChecked = false;
            }
          }
          _this.goodsList = list;
          _this.seen = true;
          // Timeout for the animation complete before destroying
        });
    },
    tabOnClick: function(type) {
      if (this.curTab != type) {
        this.page = 1;
      }
      this.loadGoodsTab(type);
    },
    loanGoodsDetail: function(goods) {
      var type;
      if (goods.cd_goods_class_l == "01") {
        type = "01";
      } else if ((goods.cd_goods_class_l = "02")) {
        type = "03";
      }

      this.$router.push({
        name: "GoodsDetail",
        query: {
          type: type,
          cd_fc: goods.cd_fc,
          cd_goods: goods.cd_goods,
          urlPath: this.urlPath,
          isAffiliates: true
        }
      });
    },
    loanGoodsChoice: function(goods, event) {
      var _this = this;
      var url = "";
      event.stopPropagation();
      goods.isChecked = !goods.isChecked;
      var formData = new FormData();
      formData.append("cd_fc", goods.cd_fc);
      formData.append("cd_goods", goods.cd_goods);
      formData.append("yn_alliance", "Y");
      if (goods.isChecked) {
        url = "/m/loan/insertLoanGoodsChoice.json";
      } else {
        url = "/m/loan/deleteLoanGoodsChoice.json";
      }
      this.$http.post(url, formData).then(function(response) {
        var returnData = response.data.returnData;
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
.test {
  margin: 0 21px;
}
</style>
