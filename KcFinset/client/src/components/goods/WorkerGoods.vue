<template>
  <div id="wrapper">
    <!-- Header -->
    <header id="header">
      <div class="input-group">
        <div class="input-group-btn">
          <button type="button" class="ui-nav nav-back" v-on:click="goBack()">뒤로가기</button>
        </div>
        <h1>신용대출(직장인)</h1>
        <div class="g-menu" id="src_menu">
          <button type="button" class="btn btn-gmenu" v-on:click="goCondition();">조건검색</button>
        </div>
      </div>
    </header>
    <!-- Content -->
    <section id="content">
      <div class="affix-fixed top-fixed-item">
        <ul class="nav nav-outline nav-justified tabs">
          <!-- <li><a id='liaffiliates'>제휴사</a></li> -->
          <li><a id='libank' v-on:click="tabOnClick('bank')">은행</a></li>
          <li><a id='lisavingBank' v-on:click="tabOnClick('savingBank')">저축은행</a></li>
          <li><a id='licapital' v-on:click="tabOnClick('capital')">캐피탈/카드</a></li>
        </ul>
        <div class="sort-block" id="divTop">
          <div class="ctrl-item">
            <div class="checkbox chk-square" id="divAllCheck">
              <label><input type="checkbox" id="allcheck" v-on:change="allcheckOnChange()"> 선택 <em id="checked_count">(0개)</em></label>
            </div>
            <div class="result-item">
              <div class="result-txt">
                <p class="prd-ea">총 <em id="list_count" name="list_count">0</em>개의 상품</p>
              </div>
              <label for="" class="sr-only">select</label>
              <select class="pull-right" id="orderby" v-on:change="orderbyOnChange()">
                <option value="01">금리낮은순</option>
                <option value="02">한도높은순</option>
                <option value="03">기간높은순</option>
              </select>
            </div>
          </div>
          <div class="ctrl-item">
          </div>
        </div>
      </div>
      <div id="listLoanGoods">
        <!-- <swiper direction="horizontal"
        :mousewheel-control="true"
        :performance-mode="false"
        :pagination-visible="true"
        :pagination-clickable="true"
        :loop="true"> -->
        <listLoanNoAffiliates />
        <!-- </swiper> -->
      </div>
      <div class="btn-fixed-bottom affix-bottom" id="next_div">
        <a role="button" class="btn btn-lg btn-block btn-disabled" onclick="loanWorkerNextStep();">금리/한도 조회하기</a>
      </div>
    </section>
    <!-- //Content -->
  </div>
</template>

<script>
import listLoanNoAffiliates from "./sub/listLoanNoAffiliates";
import Common from "./../../assets/js/common.js";
var pageCnt = 1;
var curTab = "";
var isSearching = false;

export default {
  name: "WorkerGoods",
  data() {
    return {
      show: true,
      curTab: "bank",
      totalPage: "",
      page: 1,
      cd_fin: "",
      cd_goods_class_l: "01",
      cd_goods_class_m: "01,03,08,09",
      orderby: "01"
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
    allcheckOnChange() {
      var checked = $(this).is(":checked");
      $("#listLoanGoods")
        .find("div[id=loan_product]")
        .each(function(i, item) {
          var disabled = $(this)
            .find("input:checkbox[name='goods_choice']")
            .prop("disabled");
          if (disabled != true) {
            $(this)
              .find("input:checkbox[name='goods_choice']")
              .prop("checked", checked);
          }
        });
      this.setCheckedHtml();
      this.buttonClassRemove();
    },
    setCheckedHtml() {
      var checkedCount = this.getCheckedCount();
      var htmlText = "(" + checkedCount + "개)";
      $("#checked_count").html(htmlText);
    },
    getCheckedCount() {
      var count = 0;
      $("#listLoanGoods")
        .find("div[id=loan_product]")
        .each(function(i, item) {
          if (
            $(this)
              .find("input:checkbox[name='goods_choice']")
              .is(":checked")
          ) {
            count++;
          }
        });
      return count;
    },
    buttonClassRemove() {
      if ($("input:checkbox[name='goods_choice']:checked").length > 0) {
        enableBottom("true");
      } else {
        enableBottom("false");
      }
    },
    setListCount() {
      $("#list_count").html(this.count);
    },
    orderbyOnChange() {
      this.page = 1;
      this.loadGoodsTab(this.curTab);
    },
    loadGoodsTab(type) {
      //isSearching = true;
      if (type == undefined || type == "") {
        type = "bank";
      }

      this.curTab = type;

      if (this.curTab == "affiliates") {
        $("#src_menu").hide();
      } else {
        $("#src_menu").show();
      }

      if ("bank" == this.curTab) {
        this.cd_fin = "B";
      } else if ("savingBank" == type) {
        this.cd_fin = "S";
      } else if ("capital" == type) {
        this.cd_fin = "C";
      } else if ("card" == type) {
        this.cd_fin = "D";
      }
      Common.pagination(this.$children[0].listGoods);

      if (this.page == 1) {
        //isSearching = true;
        $(document).scrollTop(0);
      }
    },
    tabOnClick(type) {
      if (this.curTab != type) {
        this.page = 1;
      }
      this.loadGoodsTab(type);
    },
    goGoodsMain() {
      this.$router.push("/goods/main");
    },
    goBack() {
      this.$router.push("/goods/main");
    },
    goCondition() {
      this.$router.push("/goods/frameLoanWorkerStep");
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
