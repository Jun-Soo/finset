<template>
<div>
  <section>
    <!-- <div class="sub-top">
      <a href="#" class="btn-back"  @click="goBack"></a>
      <p class="title">추천상품</p>
    </div> -->
    <div class="goods-wrap goods" v-if="goodsList.length">
      <carousel :perPage=1>
        <slide class="item" v-for="goods in goodsList" :key="goods.index">
          <a href="#">
            <div class="top">
              <p class="symbol"><img :src="goods.icon" alt=""/>{{goods.nm_fc}}</p>
              <p class="text blue" v-html=goods.nm_goods></p>
            </div>
            <div class="goods-benefit">
              <div>{{goods.rto_interest_from}}~{{goods.rto_interest_to}}<em> %</em></div>
              <div><em>최대 </em>{{Common.formatNumber(goods.amt_limit)}}<em> 만원</em></div>
            </div>
            <p class="goods-text1" v-html=goods.desc_feature></p>
            <p class="goods-text2">저축은행중앙회 심의필 2018-00404호(2018.8.12)</p>
          </a>
        </slide>
      </carousel>
    </div>
    <div v-else class="data-none">
	    <p>신청 가능한 상품이 없습니다.</p>
    </div>
    <div class="tab mt40">
      <div class="wrap">
        <a href="#" :class="{'on':curTab === 'loanWorker'}" @click="tabOnClick('loanWorker')">신용대출</a>
        <a href="#" :class="{'on':curTab === 'loanHome'}" @click="tabOnClick('loanHome')">주택담보</a>
        <a href="#" :class="{'on':curTab === 'loanStock'}" @click="tabOnClick('loanStock')">스탁론</a>
      </div>
    </div>
    <div class="box-list goods goods-list">
      <div class="select">
        <div class="left">
          <select v-model="orderby" @change="orderbyOnChange()">
            <option v-for="option in options" :key="option.index" v-bind:value="option.value">
              {{ option.text }}
            </option>
          </select>
        </div>
        <div class="right">
          <button class="btn-search"  @click="clickSearch()"></button>
        </div>
      </div>
      <listLoanNoAffiliates :item="item" ref="form"/>
    </div>
  </section>
  <aside class="search-wrap" :class="{'on':isSearch}" v-if="'loanWorker' === this.curTab">
        <div class="top" @click="clickSearch()">
            <button>검색</button>
        </div>
        <div class="wrap">
            <div class="item">
                <div class="key">신용등급</div>
                <div class="bar"><p><span class="active" style="width: 20%;"></span><span class="circle" style="left: 20%;"></span></p></div>
                <div class="num">3</div>
            </div>
            <div class="check-wrap">
                <div class="key">종류</div>
                <div class="search-check">
                    <div><input type="checkbox" id="chk1" :checked="isCheckWorker" @click="clickCheckWorker()"><label for="chk1">직장인</label></div>
                    <div><input type="checkbox" id="chk2" :checked="isCheckSelf" @click="clickCheckSelf()"><label for="chk2">자영업</label></div>
                </div>
            </div>
        </div>
        <div class="action">
            <a href="#" class="stroke" @click="clickStroke()">초기화</a>
            <a href="#" class="solid" @click="clickSolid()">적용</a>
        </div>
    </aside>
</div>
</template>

<script>
import listLoanNoAffiliates from "./sub/listLoanNoAffiliates";
import Common from "./../../assets/js/common.js";

export default {
  name: "List",
  data() {
    return {
      isSearch: false,
      isCheckWorker: true,
      isCheckSelf: true,
      item: {},
      Common: Common,
      urlPath: "/m/loanworker/",
      show: true,
      curTab: "loanWorker",
      totalPage: "",
      page: 1,
      cd_goods_class_l: "01",
      cd_goods_class_m: "01,03,08,09",
      options: [
        { text: "금리순", value: "01" },
        { text: "한도순", value: "02" },
        { text: "기간순", value: "03" }
      ],
      orderby: "01",
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
  },
  beforeMount() {},
  mounted() {
    this.tabOnClick(this.curTab);
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
      }
    },
    clickSolid: function() {
      if ("loanWorker" == this.curTab) {
        this.cd_goods_class_m = "03,08,09";
        if (this.isCheckWorker)
          this.cd_goods_class_m = this.cd_goods_class_m + ",01";
        if (this.isCheckSelf)
          this.cd_goods_class_m = this.cd_goods_class_m + ",02";
        this.listGoods();
        Common.pagination(this.$refs.form.listGoods);
        this.clickSearch();
      } else if ("loanHome" == this.curTab) {
      }
    },
    orderbyOnChange: function() {
      this.page = 1;
      this.loadGoodsTab(this.curTab);
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
      this.listGoods();
      Common.pagination(this.$refs.form.listGoods);
    },
    listGoods: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("cd_goods_class_l", this.cd_goods_class_l);
      formData.append("cd_goods_class_m", this.cd_goods_class_m);
      formData.append("orderby", this.orderby);
      this.$http
        .post(_this.urlPath + "listLoanAffiliates.json", formData)
        .then(function(response) {
          var list = response.data.goodsList;
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            list[i].checkId = "z" + list[i].cd_fc + list[i].cd_non_goods;
          }
          _this.goodsList = list;
          // Timeout for the animation complete before destroying
        });
    },
    tabOnClick: function(type) {
      if (this.curTab != type) {
        this.page = 1;
      }
      this.loadGoodsTab(type);
    },
    goBack: function() {
      this.$router.push("/goods/main");
    },
    showSpinner: async function() {
      this.spinnerIsVisible = true; // 시작시 Spinner 보여주기

      this.secondsLeft = 3;
      var interval = setInterval(() => {
        this.secondsLeft--;
        if (this.secondsLeft <= 0) {
          clearInterval(interval);
          this.spinnerIsVisible = false; // 0초되면 숨기기
        }
      }, 1000);
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
