<template>
  <section v-if="seen">
    <div class="con-top counsel-info">
      <p class="text">신용등급을 올리고 싶으세요?<br>나에게 맞는 소비,지출이 궁금하세요?</p>
      <p class="pt10">지금 FINSET<br>신용상담서비스를 만나보세요</p>
      <a @click="openInfo()">신용상담이란?</a>
    </div>
    <div
      v-if="counselList.length == 0"
      class="nodata"
    >등록 내역이 없습니다</div>
    <div
      v-else
      class="box-list counsel-list noMG"
    >
      <template v-for="counselInfo in counselList">
        <!-- 상담신청접수 -->
        <div
          v-if="counselInfo.cd_counsel_status=='1'"
          :key="counselInfo.index"
          class="item"
        >
          <div class="top">
            <p>{{formatDateDot(counselInfo.dt_apply)}}</p>
            <!-- <p><a @click="$router.push('')">신청내역</a></p> -->
          </div>
          <div class="result apply">
            <p class="title">상담 신청 접수</p>
            <p class="text">{{counselInfo.nm_person}}님의 신용상담 신청이 접수되었습니다.</p>
          </div>
        </div>
        <!-- 상담 준비중 -->
        <div
          v-if="counselInfo.cd_counsel_status=='2'"
          :key="counselInfo.index"
          class="item"
        >
          <div class="top">
            <p>{{formatDateDot(counselInfo.dt_pre_counsel)}}</p>
            <!-- <p><a @click="$router.push('')">신청내역</a></p> -->
          </div>
          <div class="result ing">
            <p class="title">상담 대기중</p>
            <p class="text">{{counselInfo.nm_person}}님의 신용상담을 준비중입니다.</p>
          </div>
        </div>
        <!-- 상담완료 -->
        <div
          v-else
          :key="counselInfo.index"
          class="item"
        >
          <div class="top">
            <p>{{formatDateDot(counselInfo.dt_counsel)}}</p>
            <p><a @click="viewResult(counselInfo.counsel_seq, counselInfo.cd_counsel_status)">상담결과</a></p>
          </div>
          <div class="result done">
            <p class="title">상담 완료</p>
            <p class="text">{{counselInfo.nm_person}}님의 신용상담이 완료되었습니다.</p>
          </div>
        </div>
      </template>
    </div>

    <div class="btn-wrap float">
      <a
        @click="$router.push('/credit/counselReqStep1')"
        class="solid blue box"
      >상담 신청하기</a>
    </div>

    <vue-modal
      transitionName="zoom-in"
      name="info-modal"
      v-on:popclose="closeInfo()"
    >
      <CounselInfo
        slot="body"
        v-on:popclose="closeInfo()"
      ></CounselInfo>
    </vue-modal>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

import CounselInfo from "./CounselInfo.vue";

export default {
  name: "creditCounselMain",
  data() {
    return {
      seen: false,
      counselList: []
    };
  },
  components: {
    CounselInfo: CounselInfo
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "신용상담";
  },
  created() {
    this.listCreditCounselMain();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    openInfo: function() {
      var _this = this;
      _this.$modals.show("info-modal");
    },
    closeInfo: function() {
      var _this = this;
      _this.$modals.hide("info-modal");
    },
    //list조회
    listCreditCounselMain: function() {
      var _this = this;
      this.$http
        .get("/m/credit/listCreditCounselMain.json", {
          params: {}
        })
        .then(response => {
          _this.counselList = response.data.counselList;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    //상담결과보기
    viewResult: function(counsel_seq, cd_counsel_status) {
      this.$router.push({
        name: "creditCounselResult",
        query: {
          counsel_seq: counsel_seq,
          cd_counsel_status: cd_counsel_status
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
