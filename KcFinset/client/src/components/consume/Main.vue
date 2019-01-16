<template>
  <div class="main" v-if="seen">
    <section>
      <div class="spend-top">
        <div class="date-wrap">
          <button class="prev" @click="setPrevMM"></button>
          <p @click="openDatepicker">{{ymText}}</p>
          <button class="next" @click="setNextMM"></button>
          <button class="setting" @click="clickSetting"></button>
        </div>
        <div class="wrap">
          <div class="item">
            <p class="key">지출<em>(원)</em></p>
            <p class="value">{{Common.formatNumber(consume)}}</p>
          </div>
          <div class="item">
            <p class="key">수입<em>(원)</em></p>
            <p class="value">{{Common.formatNumber(income)}}</p>
          </div>
        </div>
        <Progress :max="progressMax" :text="progressText" :click="clickProgress" />
      </div>

      <div class="banner-wrap owl-carousel">
        <carousel :perPage=1 :autoplay=true :autoplayTimeout=4000 :speed=700 :loop=true>
          <slide class="item">
            <a @click="clickBanner('calendar')">
              <div class="banner">
                <div class="left">
                  <p class="key">캘린더</p>
                  <p class="value">수입과 지출 내역이 확인가능한<br>습관 달력을 이용해 보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/consume/main_banner3.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a @click="clickBanner('payment')">
              <div class="banner">
                <div class="left">
                  <p class="key">카드 대금</p>
                  <p class="value">이전에 지출한 카드 대금을<br>한눈에 확인 하세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/consume/main_banner1.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
          <slide class="item">
            <a @click="clickBanner('settlement')">
              <div class="banner">
                <div class="left">
                  <p class="key">소비통계분석</p>
                  <p class="value">지금까지 사용한 소비와 수입<br>통계를 확인해 보세요</p>
                </div>
                <div class="right">
                  <img src="../../assets/images/consume/main_banner2.png" alt="" />
                </div>
              </div>
            </a>
          </slide>
        </carousel>
      </div>

      <div class="tab">
        <div class="wrap col3">
          <a :class="{'on':curTab === '00'}" @click="clickTab('00')">전체</a>
          <a :class="{'on':curTab === '02'}" @click="clickTab('02')">지출</a>
          <a :class="{'on':curTab === '01'}" @click="clickTab('01')">수입</a>
        </div>
      </div>

      <div class="spend-list box-list noMG" :class="{'pt0':shareList.length == 1}">
        <div class="list-wrap" v-if="shareList.length != 1">
          <div class="filter-wrap">
            <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
              <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{formatSharePerson(person)}}</label>
            </div>
          </div>
        </div>
        <div v-if="(consumeList||'')==''">
          <div class="nodata">수입, 소비 내역이 없습니다</div>
        </div>
        <div v-else v-for="(subList, index) in consumeList" :key="index" class="list-wrap">
          <p class="date">{{Common.formatDate(subList[0].dt_trd,"mmdd")}}</p>
          <div v-for="vo in subList" :key="vo.index" class="item" @click="clickConsumeList(vo.seq_consume, vo.no_person, vo.type_in_out, vo.yn_person_regist)">
            <div class="left">
              <p class="name">{{vo.contents}}</p>
              <p class="cate">
                <img :src="getConsumeIconSrc(vo.type_in_out, vo.cd_class)" alt="" />
                <span v-text="vo.type_in_out == '02'?vo.nm_class+' - '+vo.nm_type:vo.nm_class"></span>
              </p>
            </div>
            <div class="right">
              <p :class="chkType(vo.type_in_out)" class="number">{{Common.formatNumber(vo.amt_in_out,vo.type_in_out=='02',vo.type_in_out=='01')}}<em>원</em></p>
              <p class="circle red" v-if="shareList.length != 1"><span :class="settingList[shareList.findIndex(person => person.no_person === vo.no_person)].color"></span></p>
              <p class="text">{{formatMeansConsume(vo.means_consume)}}</p>
            </div>
          </div>
        </div>
        <button @click="regConsume" class="btn-spend-add"></button>
      </div>
    </section>
    <div>
      <div>
        <datepicker v-model="standardDt" @selected="selectDate" :minimum-view="'month'" :language="ko" :format="Common.formatDateDot" :hideInput="true" class="div-date" ref="datepicker" />
      </div>
    </div>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";
import Progress from "@/components/plugins/progress/Progress.vue";
import { ko } from "vuejs-datepicker/dist/locale";
import korean from "vee-validate/dist/locale/ko.js";

export default {
  name: "ConsumeMain",
  data() {
    return {
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      ko: ko, // 데이터피커 한글
      consumeList: [], // 소비지출 내역
      shareList: [], // 공유된 사용자 리스트
      isScrap: false, // 스크래핑 여부
      isGoal: false, // 예산설정 여부
      curDate: new Date(), // 현재 일자
      curTab: "00", // 현재 탭(00: 전체, 01: 수입, 02: 지출)
      standardDt: new Date(), // 기준일(현재 일자와 비교)
      income: "", // 수입 합계
      consume: "", // 지출 합계
      // 공유된 사용자의 색 class 및 id
      settingList: [
        { color: "red", id: "chk1" },
        { color: "orange", id: "chk2" },
        { color: "green", id: "chk3" },
        { color: "blue", id: "chk4" },
        { color: "purple", id: "chk5" }
      ],
      progressText: "설정된 예산이 없습니다", // progressbar 에 표출될 텍스트
      progressMax: 0 // progressbar 에 채워질 길이(0 ~ 1)
    };
  },
  components: {
    Progress
  },
  computed: {
    // 조건에 따른 progressbar 변형
    // progressOption: function() {
    //   if (!this.isScrap) {
    //     return {
    //       text: "공인 인증서를 등록하여 소비내역을 관리하세요",
    //       max: 0
    //     };
    //   } else if (!this.isGoal) {
    //     if (
    //       this.curDate.getFullYear() == this.standardDt.getFullYear() &&
    //       this.curDate.getMonth() == this.standardDt.getMonth()
    //     ) {
    //       return {
    //         text: "예산을 설정하여 목표를 이루세요",
    //         max: 0
    //       };
    //     } else {
    //       return {
    //         text: "설정된 예산이 없습니다",
    //         max: 0
    //       };
    //     }
    //   } else {
    //     return {
    //       text: this.progressText,
    //       max: this.progressMax
    //     };
    //   }
    // },
    ymText: function() {
      return this.formatHead(this.getYm(this.standardDt));
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "main";
    this.$store.state.header.active = "consume";
  },
  created() {
    if (this.$store.state.user.dt_basic > this.standardDt.getDate()) {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.curDate.setMonth(this.curDate.getMonth() - 1);
    }
    if (this.$store.state.user.dtConsume) {
      this.standardDt = this.$store.state.user.dtConsume;
    }
    // this.ym = this.formatHead(this.getYm(this.standardDt));
    this.listConsumeShareInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {
    this.$store.state.user.dtConsume = this.standardDt;
  },
  destroyed() {},
  methods: {
    // ---------------------데이터 포멧---------------------
    // 상단에 표출될 년 월 텍스트
    formatHead: function(dateStr) {
      return dateStr.substr(0, 4) + "." + dateStr.substr(4, 6);
    },
    // 이름 포맷 변경
    formatSharePerson: function(person) {
      if ((person || "") == "") {
        return "";
      } else if (
        (person.no_person || "") == "" ||
        (person.nm_person || "") == ""
      ) {
        return "";
      }

      var myNoPerson = this.$store.state.user.noPerson;
      if (person.no_person == myNoPerson) {
        return "나";
      } else {
        if (person.nm_person.length > 3) {
          return person.nm_person.substring(0, 3);
        } else {
          return person.nm_person;
        }
      }
    },
    // 소비수단코드에 따른 소비수단명
    formatMeansConsume: function(means_consume) {
      switch (means_consume) {
        case "01":
          return "카드";
          break;

        case "02":
          return "현금";
          break;

        case "03":
          return "현금영수증";
          break;

        case "04":
          return "입출금계좌";
          break;

        default:
          return "기타";
          break;
      }
    },
    // ---------------------//데이터 포멧---------------------
    // ---------------------화면 컨트롤---------------------
    // 탭 클릭 시
    clickTab: function(code) {
      this.curTab = code;
      this.listConsumeInfo();
    },
    // 공유된 사용자 버튼 클릭 시
    clickShare: function(params) {
      var no_person_list = this.filterShareList();
      if (no_person_list.length <= 1 && this.shareList[params].isShow == true) {
        return;
      }
      this.shareList[params].isShow = !this.shareList[params].isShow;
      this.listConsumeInfo();
    },
    // carousel 배너 클릭 시
    clickBanner: function(key) {
      var _this = this;
      switch (key) {
        case "payment":
          _this.$router.push("/consume/payment");
          break;
        case "calendar":
          _this.$router.push({
            path: "/common/monthCal",
            query: { type: "consume" }
          });
          break;
        case "settlement":
          _this.$router.push("/consume/settlement");
          break;
        default:
          break;
      }
    },
    // 설정 버튼 클릭 시
    clickSetting: function() {
      this.$router.push("/consume/setting");
    },
    // 각 소비 지출 리스트 클릭 시
    clickConsumeList: function(
      seq_consume,
      no_person,
      type_in_out,
      yn_person_regist
    ) {
      var _this = this;

      this.$router.push({
        path: "/consume/consumeDetail",
        query: {
          seq_consume: seq_consume,
          no_person: no_person,
          type_in_out: type_in_out,
          isMine:
            _this.shareList.findIndex(
              person => person.no_person === no_person
            ) == 0,
          isPersonRegist: yn_person_regist == "Y"
        }
      });
    },
    // 우측 하단 + 버튼 클릭 시
    regConsume: function() {
      this.$router.push("/consume/consumeDetail");
    },
    // progressbar 클릭 시
    clickProgress: function() {
      if (!this.isScrap) {
        this.$router.push("/scrap/CtrlFcLink");
      } else {
        if (
          this.curDate.getFullYear() == this.standardDt.getFullYear() &&
          this.curDate.getMonth() == this.standardDt.getMonth()
        ) {
          this.$router.push("/consume/regGoal");
        }
      }
    },
    openDatepicker: function() {
      this.$refs.datepicker.showCalendar();
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 공유된 사용자 정보 리스트 조회
    listConsumeShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeSharePersonInfo.json", { params: {} })
        .then(function(response) {
          var list = response.data.listConsumeSharePersonInfo;

          for (var idx in list) {
            list[idx].isShow = true;
          }
          _this.shareList = list;
          _this.listConsumeInfo();
        });
    },
    // 소비지출 리스트 조회
    listConsumeInfo: function(date) {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeInfo.json", {
          params: {
            ym:
              (date || "") == ""
                ? _this.getYm(this.standardDt)
                : _this.getYm(date),
            type_in_out: _this.curTab,
            no_person_list: _this.filterShareList()
          }
        })
        .then(function(response) {
          _this.income = response.data.income;
          _this.consume = response.data.consume;
          var consumeGoal = response.data.consumeGoal;
          if ((consumeGoal || "") == "") {
            if (
              _this.curDate.getFullYear() == _this.standardDt.getFullYear() &&
              _this.curDate.getMonth() == _this.standardDt.getMonth()
            ) {
              _this.progressText = "예산을 설정하여 목표를 이루세요";
              _this.progressMax = 0;
            } else {
              _this.progressText = "설정된 예산이 없습니다";
              _this.progressMax = 0;
            }
            _this.isGoal = false;
          } else {
            _this.isGoal = true;
            _this.progressText =
              consumeGoal.amt_expense / consumeGoal.amt_budget > 1
                ? Common.formatNumber(
                    consumeGoal.amt_expense - consumeGoal.amt_budget + ""
                  ) +
                  "원 초과 (" +
                  Common.formatNumber(consumeGoal.amt_budget) +
                  "원)"
                : Common.formatNumber(
                    consumeGoal.amt_expense - consumeGoal.amt_budget
                  ) +
                  "원 (" +
                  Common.formatNumber(consumeGoal.amt_budget) +
                  "원)";
            _this.progressMax =
              consumeGoal.amt_expense / consumeGoal.amt_budget > 1
                ? 1
                : consumeGoal.amt_expense / consumeGoal.amt_budget;
          }
          _this.isScrap = response.data.isScrap;
          _this.consumeList = response.data.listConsumeInfo;

          _this.seen = true;
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 공유된 사용자 중 on 처리 되어 있는 사용자 리스트
    filterShareList: function() {
      var shareList = new Array();
      var _this = this;
      for (var idx in _this.shareList) {
        if (_this.shareList[idx].isShow) {
          shareList.push(_this.shareList[idx].no_person);
        }
      }
      return shareList;
    },
    // Date 에서 YYYYMM 형식의 String으로 데이터 변형
    getYm: function(date) {
      return (
        date.getFullYear() +
        ((date.getMonth() + 1 + "").length == 1 ? "0" : "") +
        (date.getMonth() + 1)
      );
    },
    // 이전 달 세팅
    setPrevMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() - 1);
      this.standardDt = new Date(this.standardDt.getTime());
      this.listConsumeInfo();
    },
    // 다음 달 세팅
    setNextMM: function() {
      this.standardDt.setMonth(this.standardDt.getMonth() + 1);
      this.standardDt = new Date(this.standardDt.getTime());
      this.listConsumeInfo();
    },
    // 수입인지 지출인지 확인해서 해당하는 class 리턴
    chkType: function(type) {
      if (type === "01") {
        return "blue";
      } else {
        return "red";
      }
    },
    // 아이콘 url
    getConsumeIconSrc: function(type_in_out, cd_class) {
      let cd;
      if ((cd_class || "") == "" || (type_in_out || "") == "") {
        cd = "99";
      } else if (type_in_out == "01") {
      } else {
        //default로 설정된 cd_class가 24까지밖에 없음
        if (parseInt(cd_class) > 24) {
          cd = "99";
        } else {
          cd = cd_class;
        }
      }
      if (cd == undefined) {
        return require("@/assets/images/consume/icon/99.png");
      }
      return require("@/assets/images/consume/icon/" + cd + ".png");
    },
    // 데이트피커 선택
    selectDate: function(date) {
      this.listConsumeInfo(date);
    }
    // ---------------------//기타---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
