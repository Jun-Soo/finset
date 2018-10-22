<template>
  <section>
    <div class="credit-top">
        <div class="time">{{ currentDate }}</div>
        <div class="wrap">
            <div class="item">
                <p class="key">신용등급</p>
                <p class="value">{{ baseInfo!=null ? baseInfo.grade_credit : "" }}</p>
            </div>
            <div class="item">
                <p class="key">신용점수</p>
                <p class="value">{{ baseInfo!=null ? baseInfo.rating_credit : "" }}</p>
                <p v-if="baseInfo!=null && baseInfo.rating_diff > 0" class="plus">+{{baseInfo.rating_diff}}</p>
                <p v-else-if="baseInfo!=null && baseInfo.rating_diff < 0" class="minus">{{baseInfo.rating_diff}}</p>
            </div>
            <div class="item">
                <p class="key">상위</p>
                <p class="value">{{ baseInfo!=null ? baseInfo.percentage : "" }}%</p>
            </div>
        </div>
    </div>
    
    <swiper direction="horizontal"
        :mousewheel-control="true"
        :performance-mode="false"
        :pagination-visible="true"
        :pagination-clickable="true"
        :loop="true">
          <div class="item">
            <a href="#">
                <div class="banner">
                  <div class="left">
                    <p class="key">우리가족 가계부</p>
                    <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                 </div>
                 <div class="right">
                  <img src="./../../assets/images/main/banner_ico.png" alt=""/>
                </div>
              </div>
            </a>
          </div>
          <div class="item">
            <a href="#">
                <div class="banner">
                  <div class="left">
                    <p class="key">우리가족 가계부</p>
                    <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                 </div>
                 <div class="right">
                  <img src="./../../assets/images/main/banner_ico.png" alt=""/>
                </div>
              </div>
            </a>
          </div>
          <div class="item">
            <a href="#">
                <div class="banner">
                  <div class="left">
                    <p class="key">우리가족 가계부</p>
                    <p class="value">가족이 사용한 지출을<br>한꺼번에 관리하세요</p>
                 </div>
                 <div class="right">
                  <img src="./../../assets/images/main/banner_ico.png" alt=""/>
                </div>
              </div>
            </a>
          </div>
    </swiper>

    <div class="credit-change">
		    <div class="top">
		          <p class="title">나의 신용정보 변동</p>
		      </div>
		    <div class="link">
		       <a href="#">{{ changeInfo!=null ? changeInfo.dt_info+" "+changeInfo.nm_fc+""+changeInfo.change_contents+"되었습니다" : "" }}</a>
		    </div>
		</div>

    <div class="credit-status">
       <p class="title">나의 신용거래 현황</p>
       <div class="wrap">
           <div class="item">
               <p class="key">8월 카드이용</p>
               <p class="value">{{cardSumAmt}}<em>원</em></p>
           </div>
           <div class="item">
               <p class="key">대출 잔액</p>
               <p class="value">{{debtSumAmtRemain}}<em>원</em></p>
           </div>
           <div class="item">
               <p class="key">연체 원금</p>
               <p class="value">{{overdueSumAmt}}<em>원</em></p>
           </div>
           <div class="item">
               <p class="key">연대보증 원금</p>
               <p class="value">{{guaranteeSumAmt}}<em>원</em></p>
           </div>
       </div>
   </div>
        
    

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: 'FinsetMain',
  data() {
    return {
      currentDate: "", //현재일자
      baseInfo: "", //신용등급, 신용점수, 상위%
      changeInfo: "", //나의 신용정보 변동내역
      cardSumAmt: "", //카드이용금액
      debtSumAmtRemain: "", //대출잔액
      overdueSumAmt: "", //연체원금
      guaranteeSumAmt: "", //연대보증원금
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
    this.$store.state.header.type = 'main'
    this.$store.state.header.active = 'credit'
    this.getCreditInfoMain()
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {

    // 신용정보 조회
    getCreditInfoMain: function() {
      var _this = this
      this.$http.get('/m/credit/getCreditMainInfo.json', {
        params: {}
      })
      .then(response => {
        _this.currentDate = response.data.currentDate

        _this.baseInfo = response.data.baseInfo
        // console.log("baseInfo : "+JSON.stringify(_this.baseInfo))

        _this.changeInfo = response.data.changeInfo
        // console.log("changeInfo : "+JSON.stringify(_this.changeInfo))

        _this.cardSumAmt = response.data.cardSumAmt
        _this.debtSumAmtRemain = response.data.debtSumAmtRemain
        _this.overdueSumAmt = response.data.overdueSumAmt
        _this.guaranteeSumAmt = response.data.guaranteeSumAmt
      })
      .catch(e => {
        this.$toast.center(ko.messages.error)
      })
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
