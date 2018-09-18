<template>
  <div id="wrapper" class="bg-white">
    <!-- Header -->
    <header id="header">
      <div class="input-group">
        <div class="input-group-btn">
          <button type="button" class="ui-nav nav-back" @click="history.back();">뒤로가기</button>
        </div>
        <h1>정보공유</h1>
      </div>
    </header>
    <!-- //Header -->
    <!-- Content -->
    <section id="content">
      <div class="visual-banner middle-banner">
        <p class="msg">finset 정보 공유를</p>
        <p class="msg">통해 가족간의 흩어진 정보를</p>
        <p class="msg">한눈에</p>
        <br/>
        <p class="msg">비대면 거래에 필요한 정보를</p>
        <p class="msg">안전하게 제공</p>
        <br/>
        <p class="msg">한번의 클릭으로 간편하게</p>
        <p class="msg">정보를 공유 및 통합 관리할 수 있습니다.</p>
      </div>
      <div class="container">
        <table class="table tbl_status">
          <thead>
            <tr>
              <th colspan="3">타인 정보 열람</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="offerItem in offerList" :key="offerItem.index"
              @click="offerItemClick(offerItem.seq_share, offerItem.share_status, offerItem.yn_offer
                              , offerItem.yn_itgt_mngm, offerItem.dt_end_offer);">
              <td>{{offerItem.offer_nm_person}}</td>
              <td v-if="offerItem.share_status === '01'">
                대기
              </td>
              <td v-else-if="offerItem.share_status === '02'">
                완료
              </td>
              <td>{{offerItem.offer_hp}}</td>
            </tr>
          </tbody>
        </table>
        <table class="table tbl_status">
          <thead>
            <tr>
              <th colspan="3">내 정보 공유</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="reqItem in reqList" :key="reqItem.index"
            @click="reqItemClick(reqItem.seq_share, reqItem.share_status, reqItem.yn_offer
                      , reqItem.yn_itgt_mngm, reqItem.dt_end_offer);">
              <td>{{reqItem.req_nm_person}}</td>
              <td v-if="reqItem.share_status === '01'">
                대기
              </td>
              <td v-else-if="reqItem.share_status === '02'">
                완료
              </td>
              <td>{{reqItem.req_hp}}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="btn-fixed-bottom affix-bottom">
        <a role="button" class="btn btn-lg btn-block btn-primary" @click="createNewRequest();">신규 요청하기</a>
      </div>
    </section>
    <!-- //Content -->
  </div>
</template>

<script>

// import Common from './../../assets/js/common.js'
import router from '@/comm/router.js'

export default {
  name: 'helloWorld',
  data() {
    return {
      errors: [],
      errMsg: '',
      offerList: [],
      reqList: [],
      currentDate: '',
      seq_share: ''
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
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
    getSumMainInfo(){
       var _this = this;
      this.$http.get('/m/customercenter/shareInfoSummary.json', {
        params: {}
      }).then(function (response) {
        _this.offerList = response.data.offerList
        _this.reqList = response.data.reqList
        _this.currentDate = response.data.currentDate
      })
    },
    offerItemClick(seq_share, share_status, yn_offer, yn_itgt_mngm, dt_end_offer){
      var _this = this;
      _this.seq_share = seq_share

      if(share_status === "01"){ //재요청화면으로 이동
        this.goSetting('01')
      }else if(share_status === "02"){ //상세화면으로이동
        if(yn_offer === 'Y' && yn_itgt_mngm === 'N'){  //정보제공항목만 있는경우
          if(dt_end_offer >= _this.currentDate){ //정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음
            this.viewDetail();
          }
        }

        if(yn_itgt_mngm === 'Y'){ //정보통합항목이 있는경우
          this.viewDetail();
        }
      }
    },
    reqItemClick(seq_share, share_status, yn_offer, yn_itgt_mngm, dt_end_offer){
      var _this = this;
      _this.seq_share = seq_share
      if(share_status === '01'){ //허용/거절 화면으로 이동
       this.goSetting('02');
      }else if(share_status === '02'){ //변경/해지 화면으로 이동
        if(yn_offer === 'Y' && yn_itgt_mngm === 'N'){  //정보제공항목만 있는경우
          if(dt_end_offer >= _this.currentDate){ //정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음
            this.goSetting('03');
          }
        }

        if(yn_itgt_mngm === 'Y'){ //정보통합항목이 있는경우
          this.goSetting('03');
        }
      }
    },
    goSetting(setting_mode){ //설정화면 이동
      router.push({
                  name:"shareSeeting",
                  params: {
                          setting_mode: setting_mode,
                          seq_share: _this.seq_share
                          }
                  });
    },
    viewDetail(){ //상세화면 이동
       router.push({
                  name:"shareDetail",
                  params: {
                          seq_share: _this.seq_share
                          }
                  });
    },
    createNewRequest(){
      location.href = "/share/newRequest"
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
