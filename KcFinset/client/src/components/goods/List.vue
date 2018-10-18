<template>
 	<div>
	<section>
   <div class="sub-top">
        <a href="#" class="btn-back"  @click="goBack"></a>
        <p class="title">추천상품</p>
        </div>
        
        <div class="owl-carousel goods-wrap goods">
          <swiper direction="horizontal"
            :mousewheel-control="true"
            :performance-mode="false"
            :pagination-visible="true"
        :pagination-clickable="true"
        :loop="true">
            <div class="item">
                <a href="#">
                    <div class="top">
                        <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt=""/>삼성생명보험</p>
                        <p class="text blue">직장인 신용대출</p>
                    </div>
                    <div class="goods-benefit">
                        <div>5.0~8.0<em> %</em></div>
                        <div><em>최대 </em>5,000<em> 만원</em></div>
                    </div>
                    <p class="goods-text1">특징 및 간략설명 특징 및 간략설명 특징 및 간략설명</p>
                    <p class="goods-text2">저축은행중앙회 심의필 2018-00404호(2018.8.12)</p>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="top">
                        <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt=""/>삼성생명보험</p>
                        <p class="text blue">직장인 신용대출</p>
                    </div>
                    <div class="goods-benefit">
                        <div class="left">5.0~8.0<em> %</em></div>
                        <div class="right"><em>최대 </em>5,000<em> 만원</em></div>
                    </div>
                    <p class="goods-text1">특징 및 간략설명 특징 및 간략설명 특징 및 간략설명</p>
                    <p class="goods-text2">저축은행중앙회 심의필 2018-00404호(2018.8.12)</p>
                </a>
            </div>
            <div class="item">
                <a href="#">
                    <div class="top">
                        <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt=""/>삼성생명보험</p>
                        <p class="text blue">직장인 신용대출</p>
                    </div>
                    <div class="goods-benefit">
                        <div class="left">5.0~8.0<em> %</em></div>
                        <div class="right"><em>최대 </em>5,000<em> 만원</em></div>
                    </div>
                    <p class="goods-text1">특징 및 간략설명 특징 및 간략설명 특징 및 간략설명</p>
                    <p class="goods-text2">저축은행중앙회 심의필 2018-00404호(2018.8.12)</p>
                </a>
            </div>
            </swiper>
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
                    <button></button>
                 </div>
             </div>
            <listLoanNoAffiliates/>
        </div>
    </section>
</div>
</template>

<script>
import listLoanNoAffiliates from "./sub/listLoanNoAffiliates";
import Common from "./../../assets/js/common.js";
var pageCnt = 1;
var curTab = "";
var isSearching = false;

export default {
  name: "List",
  data() {
    return {
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
  // computed () {
  // },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    this.tabOnClick(this.curTab);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    orderbyOnChange: function() {
      this.page = 1;
      this.loadGoodsTab(this.curTab);
    },
    loadGoodsTab: function(type) {
      //isSearching = true;
      if (type == undefined || type == "") {
        type = "loanWorker";
      }
      this.curTab = type;

      if ("loanWorker" == this.curTab) {
        this.cd_goods_class_l = "01";
        this.cd_goods_class_m = "01,03,08,09";
      } else if ("loanHome" == this.curTab) {
        this.cd_goods_class_l = "02";
        this.cd_goods_class_m = "05, 08";
      } else if ("loanStock" == this.curTab) {
        //do nothing
      }
      Common.pagination(this.$children[1].listGoods);

      if (this.page == 1) {
        //isSearching = true;
        $(document).scrollTop(0);
      }
    },
    listGoods: function(callback) {
      var _this = this;
      var _parent = this.$parent;
      var formData = new FormData();
      formData.append("cd_goods_class_l", _parent.cd_goods_class_l);
      formData.append("cd_goods_class_m", _parent.cd_goods_class_m);
      formData.append("orderby", _parent.orderby);
      this.$http
        .post("/m/loanworker/listLoanAffiliates.json", formData)
        .then(function(response) {
          var list = response.data.goodsList;
          for (var i = 0; i < list.length; i++) {
            list[i].icon =
              "/m/fincorp/getFinCorpIcon.crz?cd_fc=" + list[i].cd_fc;
            list[i].checkId = "z" + list[i].cd_fc + list[i].cd_non_goods;
          }
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
</style>
