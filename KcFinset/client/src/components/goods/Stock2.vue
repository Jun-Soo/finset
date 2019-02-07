<template>
  <section>
    <div class="con-top credit-up">
      <p><em>{{nm_person}}님이 신청 가능한<br>상품과 금리입니다<br>신청은</em> 증권사 HTS<em>를 <br>접속하시어 이용하시기 <br>바랍니다</em></p>
    </div>

    <div class="box-list list01 noMG pb90">

      <div class="item" v-for="(item, idx) in gridListAdd" :key="idx" @click="goDetail(item.mbrCd,item.crdtIttCd,item.prdtCd)">
        <div class="top">
          <p class="symbol checks">
            <img src="../../assets/images/common/bu_city.png" alt="" />{{item.crdtIttNm}}</p>
          <p v-if="item.loanAblYn=='0'" class="text blue">가능</p>
          <p v-else-if="item.loanAblYn=='1'" class="text red">불가능</p>
          <p v-else class="text red">잘못된값</p>
        </div>
        <p class="goods-name">{{item.prdtNm}}</p>
        <div class="hide-con show">
          <div class="list">
            <p class="left">금리</p>
            <p class="right">{{item.loanIntrstRt}}%</p>
          </div>
          <div class="list">
            <p class="left">한도</p>
            <p class="right">{{item.loanMaxLmtAmt}} 만원</p>
          </div>
          <div class="list">
            <p class="left">기간</p>
            <p class="right">{{item.loanTerm}}개월</p>
          </div>
        </div>
      </div>
    </div>
    <div class="btn-wrap float">
      <a @click="$router.push('/main')" class="box blue solid">홈으로</a>
    </div>

  </section>
</template>

<script>
export default {
  name: "GoodStock2",
  data() {
    return {
      nm_person: this.$store.state.user.nmPerson,
      classLoanAblYn: "text blue",
      gridListAdd: [],
      inVO: {},
      outVOList: {},
      inFieldJson: {}
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신청 가능한 상품";
  },
  created() {
    //(데이터 노출 괜춘?) 안괜찮으면 다른방식의 데이터 전달 필요 
    this.inVO = this.$route.query.inVO;
    this.outVOList = this.$route.query.outVOList;
    this.inFieldJson = this.$route.query.inFieldJson;
    this.init();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //초기화면
    init: function(orderVal) {
      //기본 정렬은 금리적은순
      var _this = this;

      if (orderVal == undefined) orderVal = "sortLoanIntrstRt";
      //Json으로 parsing 하여 정렬
      // _this.inVOJson = "";
      // _this.outVOList = "";
      _this.inVOJson = JSON.parse(_this.inVOJson);
      _this.outVOList = JSON.parse(_this.outVOList);

      // var gridArrStr = "";
      var html = "";
      var gridCnt = 0;
      // var gridVOJson = null;
      // var gridListAdd = null;
      var outVOJson = null;
      for (var j = 0; j < _this.outVOList.length; j++) {
        outVOJson = _this.outVOList[j];
        //부보키를 세팅해준다
        for (var k = 0; k < outVOJson.gridList.length; k++) {
          outVOJson.gridList[k].parentIdx = j;
        }
        //console.log(outVOJson);
        //상품리스트를 하나의 json으로 합침
        if (_this.gridListAdd == null) {
          _this.gridListAdd = _this.outVOList[j].gridList;
        } else {
          _this.gridListAdd = $.merge(
            _this.gridListAdd,
            _this.outVOList[j].gridList
          );
        }
        console.log(_this.gridListAdd);
        //다 합쳐지면 화면에 뿌림
        if (j == _this.outVOList.length - 1) {
          //정렬
          if (orderVal == "sortLoanIntrstRt") {
            _this.gridListAdd.sort(sortLoanIntrstRt);
          } else {
            _this.gridListAdd.sort(sortLoanMaxLmtAmt);
          }
        }
      }
      //정렬된 data 화면에 뿌려줌
      for (var i = 0; i < _this.gridListAdd.length; i++) {
        //부모리스트 index
        var parentIdx = _this.gridListAdd[i].parentIdx;
        _this.gridListAdd[i].mbrCd = _this.inVOJson.inVOList[parentIdx].mbrCd; //증권사코드
        _this.gridListAdd[i].crdtIttCd; //여신사코드 goDetail
        _this.gridListAdd[i].prdtCd; //상품코드 goDetail
        _this._this.outVOList[parentIdx].mbrNm; //증권사명 goDetail
        //html+='<span class="thumb-logo" style="background-image:url("/fincorp/getFinCorpIcon.crz?cd_fc=2000016");"></span>   ';
        //html+='+outVOList[parentIdx].mbrNm+'&nbsp;/&nbsp;'+gridListAdd[i].crdtIttNm                                                       ;
        //html+='<span class="thumb-logo"><img src="/fincorp/getFinCorpIcon.crz?cd_fc=2000016" alt="이미지 하드코딩"></span>   ';
        _this.gridListAdd[i].mbrNm = _this.outVOList[parentIdx].mbrNm; // 증권사이름
        _this.gridListAdd[i].crdtIttNm; //여신사명
        _this.gridListAdd[i].prdtNm; //상품명
        _this.gridListAdd[i].loanIntrstRt = parseFloat(
          _this.gridListAdd[i].loanIntrstRt
        );
        _this.gridListAdd[i].loanMaxLmtAmt = numberWithCommas(
          parseInt(_this.gridListAdd[i].loanMaxLmtAmt) / 10000
        ); //한도
      }
      //상품갯수
      // _this.gridCnt = _this.gridListAdd.length;
      // $("#list_count").text(gridCnt);
    },
    //대출금높은순 정렬
    sortLoanMaxLmtAmt: function(a, b) {
      if (a.loanMaxLmtAmt == b.loanMaxLmtAmt) return 0;
      return parseInt(a.loanMaxLmtAmt) < parseInt(b.loanMaxLmtAmt) ? 1 : -1;
    },
    //이율낮은순 정렬
    sortLoanIntrstRt: function(a, b) {
      if (a.loanIntrstRt == b.loanIntrstRt) return 0;
      return parseFloat(a.loanIntrstRt) > parseFloat(b.loanIntrstRt) ? 1 : -1;
    },
    //상세보기
    goDetail: function(mbrCd, crdtIttCd, prdtCd) {
      var _this = this;
      //alert(mbrCd+"/"+crdtIttCd+"/"+prdtCd)
      //test용
      if (mbrCd == "" || crdtIttCd == "" || prdtCd == "") {
        _this.$toast.center("해당 상품의 코드명이 없습니다.");
      }
      _this.$router.push({
        name: "goodsStock3",
        query: {
          mbrCd: mbrCd,
          crdtIttCd: crdtIttCd,
          prdtCd: prdtCd
        }
      });
    },
    //숫자 3자리마다 , 찍기
    numberWithCommas: function(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
