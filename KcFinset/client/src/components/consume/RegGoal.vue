<template>
  <section v-if="seen">
    <div class="con-top goal-top">
      <p><em>잠깐 !!</em><br>예산 사용 통계를 확인해<br>보시고 설정하세요</p>
    </div>

    <div class="tab">
      <div class="wrap">
        <a @click="clickTab('01')" :class="{'on':curTab=='01'}">분류별</a>
        <a @click="clickTab('02')" :class="{'on':curTab=='02'}">수단별</a>
      </div>
    </div>

    <div class="box-list noMG">
      <div class="filter-wrap">
        <div class="filter blue">
          <input type="checkbox" id="chk1" :checked="curLabel==='average'"><label @click="clickLabel('average')">3개월평균</label>
        </div>
        <div class="filter blue">
          <input type="checkbox" id="chk2" :checked="curLabel==='prevMon'"><label @click="clickLabel('prevMon')">전월사용</label>
        </div>
        <div class="filter blue">
          <input type="checkbox" id="chk3" :checked="curLabel==='custom'"><label @click="clickLabel('custom')">사용자 지정</label>
        </div>
      </div>

      <div class="goal-list-wrap" v-if="curTab=='01'">
        <form id="frmRegGoal">
          <dl>
            <dt class="sum">
              <p class="title">합계</p>
              <p><input readonly type="text" :value="sumGoal1"> 원</p>
            </dt>
            <div v-for="(vo, index) in listDetailGoal1" :key="vo.cd_class">
              <dd>
                <p>{{vo.nm_class}}</p>
                <input type="hidden" :name="'list['+index+'].cd_class'" :value="vo.cd_class" />
                <!-- <p><input :name="'list['+index+'].amt_budget'" class="each_amt money" type="text" inputmode="numeric" pattern="[0-9]*" v-model="vo.amt_budget" v-validate="'required|numeric'" :data-vv-name="vo.nm_class"> 원</p> -->
                <p>
                  <money :name="'list['+index+'].amt_budget'" :theme="'each_amt money'" v-model="vo.amt_budget" v-validate="'required|numeric'" :data-vv-name="vo.nm_class" /> 원</p>
              </dd>
              <p class="warn" v-if="errors.has(vo.nm_class)">{{errors.first(vo.nm_class)}}</p>
            </div>
          </dl>
        </form>
      </div>

      <div class="goal-list-wrap" v-if="curTab=='02'">
        <form id="frmRegGoal">
          <dl>
            <dt class="sum">
              <p class="title">합계</p>
              <p><input readonly type="text" :value="sumGoal2"> 원</p>
            </dt>
            <div v-for="(vo, index) in listDetailGoal2" :key="index">
              <dd>
                <p>{{formatNmCard(vo.nm_card)}}</p>
                <input v-if="vo.cd_type != '02'" type="hidden" :name="'list['+index+'].cd_fc'" :value="vo.cd_fc" />
                <input type="hidden" :name="'list['+index+'].cd_type'" :value="vo.cd_type" />
                <input v-if="vo.cd_type == '01' || vo.cd_type == '04'" type="hidden" :name="'list['+index+'].no_card'" :value="vo.no_card" />
                <input v-if="vo.cd_type == '01' || vo.cd_type == '04'" type="hidden" :name="'list['+index+'].nm_card'" :value="vo.nm_card" />
                <!-- <p><input :name="'list['+index+'].amt_budget'" class="each_amt money" type="text" inputmode="numeric" pattern="[0-9]*" v-model="vo.amt_budget" v-validate="'required|numeric'" :data-vv-name="vo.nm_card"> 원</p> -->
                <p>
                  <money :name="'list['+index+'].amt_budget'" :theme="'each_amt money'" v-model="vo.amt_budget" v-validate="'required|numeric'" :data-vv-name="vo.nm_card" /> 원
                </p>
              </dd>
              <p class="warn" v-if="errors.has(vo.nm_card)">{{errors.first(vo.nm_card)}}</p>
            </div>
          </dl>
        </form>
      </div>
    </div>

    <div class="btn-wrap float">
      <a @click="createGoal" class="solid blue box">저장</a>
    </div>
  </section>
</template>

<script>
import Common from "@/assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "ConsumeRegGoal",
  data() {
    return {
      seen: false, // 화면 표출 여부
      curTab: "01", // 현재 탭(01: 분류별, 02: 수단별)
      curLabel: "custom", // 현재 라벨(average: 3개월 평균, prevMon: 전 월, custom: 사용자 지정)
      listDetailGoal1: {}, // 분류별 예산 리스트
      listDetailGoal2: {} // 수단별 예산 리스트
    };
  },
  components: {},
  computed: {
    // 분류 별 예산 합
    sumGoal1: function() {
      var sum = 0;
      for (var idx in this.listDetailGoal1) {
        if (isNaN(parseInt(this.listDetailGoal1[idx].amt_budget))) {
          return "숫자를 입력해주세요";
        }
        sum += parseInt(this.listDetailGoal1[idx].amt_budget);
      }
      return Common.formatNumber(sum);
    },
    // 수단 별 예산 합
    sumGoal2: function() {
      var sum = 0;
      for (var idx in this.listDetailGoal2) {
        if (isNaN(parseInt(this.listDetailGoal2[idx].amt_budget))) {
          return "숫자를 입력해주세요";
        }
        sum += parseInt(this.listDetailGoal2[idx].amt_budget);
      }
      return Common.formatNumber(sum);
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "예산설정";
  },
  created() {
    this.listGoal();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포멧---------------------
    // 20자 이상인 카드 명은 잘라내기
    formatNmCard: function(nm_card) {
      if (nm_card.length > 20) {
        return nm_card.substring(0, 20) + "...";
      } else {
        return nm_card;
      }
    },
    // ---------------------//데이터 포멧---------------------
    // ---------------------화면 컨트롤---------------------
    // 탭 클릭 시
    clickTab: function(key) {
      if (this.curTab == key) {
        return;
      }
      this.curTab = key;
      this.curLabel = "custom";
      this.listGoal();
    },
    // 라벨 클릭 시
    clickLabel: function(key) {
      if (this.curLabel == key) {
        return;
      }
      this.curLabel = key;
      switch (key) {
        case "average":
          this.listAverageConsume();
          break;

        case "prevMon":
          this.listPrevMonthConsume();
          break;

        case "custom":
          this.listGoal();
          break;

        default:
          break;
      }
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 사용자 지정 예산 리스트 조회
    listGoal: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listDetailGoal.json", {
          params: { cd_set: _this.curTab }
        })
        .then(function(response) {
          if (_this.curTab == "01") {
            _this.listDetailGoal1 = response.data.listDetailGoal;
          } else {
            _this.listDetailGoal2 = response.data.listDetailGoal;
          }
          _this.seen = true;
        });
    },
    // 전월 사용 리스트 조회
    listPrevMonthConsume: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPrevMonthConsume.json", {
          params: { cd_set: _this.curTab }
        })
        .then(function(response) {
          var list = response.data.listPrevMonthConsume;
          if (_this.curTab == "01") {
            for (var idx in list) {
              _this.listDetailGoal1.filter(
                goal => goal.cd_class == list[idx].cd_class
              )[0].amt_budget = list[idx].amt_expense;
            }
          } else {
            for (var idx in list) {
              _this.listDetailGoal2.filter(
                goal =>
                  goal.cd_type == list[idx].cd_type &&
                  goal.cd_fc == list[idx].cd_fc &&
                  goal.no_card == list[idx].no_card &&
                  goal.nm_card == list[idx].nm_card
              )[0].amt_budget = list[idx].amt_expense;
            }
          }
        });
    },
    // 3개월 평균 리스트 조회
    listAverageConsume: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listAverageConsume.json", {
          params: { cd_set: _this.curTab }
        })
        .then(function(response) {
          var list = response.data.listAverageConsume;
          if (_this.curTab == "01") {
            for (var idx in list) {
              _this.listDetailGoal1.filter(
                goal => goal.cd_class == list[idx].cd_class
              )[0].amt_budget = list[idx].amt_expense;
            }
          } else {
            for (var idx in list) {
              _this.listDetailGoal2.filter(
                goal =>
                  goal.cd_type == list[idx].cd_type &&
                  goal.cd_fc == list[idx].cd_fc &&
                  goal.no_card == list[idx].no_card &&
                  goal.nm_card == list[idx].nm_card
              )[0].amt_budget = list[idx].amt_expense;
            }
          }
        });
    },
    // 예산 등록
    createGoal: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var frmRegGoal = document.getElementById("frmRegGoal");
          var formData = new FormData(frmRegGoal);
          formData.append("cd_set", this.curTab);
          this.$http
            .post("/m/consume/createGoal.json", formData)
            .then(function(response) {
              _this.$toast.center("저장되었습니다");
            });
        }
      });
    }
    // ---------------------//데이터 이동---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
