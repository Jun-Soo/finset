<template>
  <section>
    <div class="goods-state pb90">
      <p class="number">신청번호 : 2017101500023</p>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top deposit">
            <a href="#">
              <p class="title">대출금 입금</p>
              <p class="text">대출금 입금이 완료되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top cancel">
            <a href="#">
              <p class="title">대출 신청취소</p>
              <p class="text">대출 신청이 취소되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>

      <div class="item">
        <p class="num">신청번호 : 2017101500023</p>
        <div class="wrap">
          <div class="top reject">
            <a href="#">
              <p class="title">대출 심사 부결</p>
              <p class="text">대출 심사가 부결(거절)되었습니다.</p>
            </a>
          </div>
        </div>
        <div class="step">
          <div class="step-wrap">
            <p>신청</p>
            <p>상담/접수</p>
            <p>심사</p>
            <p class="on">입금</p>
          </div>
        </div>
        <div class="flex mt20">
          <p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt="" />삼성생명보험</p>
          <p><a href="#">문의하기</a></p>
        </div>
        <div class="flex">
          <p class="goods-name">KB 우량직장인 신용대출</p>
        </div>
        <div class="flex">
          <p class="key">대출한도</p>
          <p class="value">5,000 만원</p>
        </div>
        <div class="flex">
          <p class="key">대출금리</p>
          <p class="value"><em>변동</em>3.57 %</p>
        </div>
        <div class="flex">
          <p class="key">대출기간</p>
          <p class="value">12개월</p>
        </div>
        <div class="flex">
          <p class="key">상환방식</p>
          <p class="value">만기일시상환</p>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "MypageState",
  data() {
    return {
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
    //Common.pagination(this.listGoodsStatus);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
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
          _this.$store.state.isLoading = false;
          _this.$toast.center(ko.messages.error);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
