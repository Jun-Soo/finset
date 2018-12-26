<template>
  <section v-if="seen">
    <div class="container">
      <ul class="debt-modify">
        <li>
          <div>
            <p class="key">결혼여부</p>
            <p>
              <multiselect :id="'yn_wedding'" v-model="yn_wedding" ref="yn_wedding" placeholder="결혼여부선택" :title="'결혼여부'" :options="ynWeddingOptions" :onClose="selectYnWedding" v-validate="'required'" data-vv-name='결혼여부'>
              </multiselect>
            </p>
          </div>
          <p class="warn" v-if="errors.has('결혼여부')">{{errors.first('결혼여부')}}</p>
        </li>
        <li>
          <div>
            <p class="key">부양가족</p>
            <p>
              <multiselect :id="'cd_family_cnt'" v-model="cd_family_cnt" ref="cd_family_cnt" placeholder="부양가족선택" :title="'부양가족'" :options="options_family_cnt" :onClose="selectFamilyCnt" v-validate="'required'" data-vv-name='부양가족'>
              </multiselect>
            </p>
          </div>
          <p class="warn" v-if="errors.has('부양가족')">{{errors.first('부양가족')}}</p>
        </li>
        <li>
          <div>
            <p class="key">주거형태</p>
            <p>
              <multiselect :id="'cd_living'" v-model="cd_living" ref="cd_living" placeholder="주거형태선택" :title="'주거형태'" :options="options_living" :onClose="selectLiving" v-validate="'required'" data-vv-name='주거형태'>
              </multiselect>
            </p>
          </div>
          <p class="warn" v-if="errors.has('주거형태')">{{errors.first('주거형태')}}</p>
        </li>
        <li>
          <div>
            <p class="key">직업</p>
            <p>
              <multiselect :id="'cd_job'" v-model="cd_job" ref="cd_job" placeholder="직군선택" :title="'직군'" :options="options_job" :onClose="selectJob" v-validate="'required'" data-vv-name='직업'>
              </multiselect>
            </p>
          </div>
          <p class="warn" v-if="errors.has('직업')">{{errors.first('직업')}}</p>
        </li>
        <li>
          <div>
            <p class="key">월소득</p>
            <!-- <p><input type="number" id="amt_mm_income" v-model="amt_mm_income" placeholder="월소득" v-html="counselInfo.amt_income_total" @change="changeIncome()" v-validate="'required|numeric|max:10'" data-vv-name='월소득'></p> -->
            <p>
              <money :id="'amt_mm_income'" v-model="amt_mm_income" placeholder="월소득" :value="counselInfo.amt_income_total" :change="changeIncome()" v-validate="'required|numeric|max:10'" data-vv-name='월소득' />원</p>
          </div>
          <p class="warn" v-if="errors.has('월소득')">{{errors.first('월소득')}}</p>
        </li>
        <li>
          <div>
            <p class="key">월지출</p>
            <!-- <p><input type="number" id="amt_mm_expense" v-model="amt_mm_expense" placeholder="월지출" v-html="counselInfo.amt_expense_total" @change="changeExpense()" v-validate="'required|numeric|max:10'" data-vv-name='월지출'></p> -->
            <p>
              <money :id="'amt_mm_expense'" v-model="amt_mm_expense" placeholder="월지출" :value="counselInfo.amt_expense_total" :change="changeExpense()" v-validate="'required|numeric|max:10'" data-vv-name='월지출' />원</p>
          </div>
          <p class="warn" v-if="errors.has('월지출')">{{errors.first('월지출')}}</p>
        </li>
      </ul>
      <div v-if="isShowBtn" class="btn-wrap float">
        <a @click="goCounselReqStep4()" class="solid blue box">상담내용입력</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "creditCounselReqStep3",
  data() {
    return {
      seen: false,
      ynWeddingOptions: [
        { text: "미혼", value: "N" },
        { text: "기혼", value: "Y" }
      ],
      yn_wedding: "",
      options_family_cnt: [],
      cd_family_cnt: "",
      options_living: [],
      cd_living: "",
      options_job: [],
      cd_job: "",
      amt_mm_income: "",
      amt_mm_expense: "",
      counselInfo: "",
      isShowBtn: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상담신청(부가정보입력)";
  },
  created() {
    this.options_family_cnt = Common.makeOptions("cd_family_cnt", "");
    this.options_living = Common.makeOptions("cd_living", "");
    this.options_job = Common.makeOptions("cd_job", "");
    this.getCounselReqStep3Info();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //정보조회
    getCounselReqStep3Info: function() {
      var _this = this;
      this.$http
        .get("/m/credit/getCreditCounselReqStep3Info.json", {
          params: {}
        })
        .then(response => {
          var counselInfo = response.data.counselInfo;
          _this.amt_mm_income = counselInfo.amt_income_total;
          _this.amt_mm_expense = counselInfo.amt_expense_total;
          _this.counselInfo = counselInfo;

          _this.seen = true;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    //자동선택
    selectYnWedding: function() {
      var _this = this;
      // setTimeout(function() {
      //   _this.$refs.cd_family_cnt.$el.focus();
      // }, 100);
      this.$refs.cd_family_cnt.open();
    },
    selectFamilyCnt: function() {
      var _this = this;
      // setTimeout(function() {
      //   _this.$refs.cd_living.$el.focus();
      // }, 100);
      this.$refs.cd_living.open();
    },
    selectLiving: function() {
      var _this = this;
      // setTimeout(function() {
      //   _this.$refs.cd_job.$el.focus();
      // }, 100);
      this.$refs.cd_job.open();
    },
    selectJob: function() {
      var _this = this;
      setTimeout(function() {
        $("#amt_mm_income").focus();
        _this.showBtn();
      }, 100);
    },
    changeIncome: function() {
      var _this = this;
      setTimeout(function() {
        _this.showBtn();
      }, 100);
    },
    changeExpense: function() {
      var _this = this;
      setTimeout(function() {
        _this.showBtn();
      }, 100);
    },
    showBtn: function() {
      var _this = this;
      if ("" != _this.amt_mm_income && "" != _this.amt_mm_expense) {
        _this.isShowBtn = true;
      } else {
        _this.isShowBtn = false;
      }
    },
    //상담내용입력화면으로 이동
    goCounselReqStep4: function() {
      var _this = this;

      this.$validator.validateAll().then(res => {
        if (res) {
          this.$router.push({
            name: "creditCounselReqStep4",
            query: {
              yn_wedding: _this.yn_wedding.value,
              cd_family_cnt: _this.cd_family_cnt.value,
              cd_living: _this.cd_living.value,
              cd_job: _this.cd_job.value,
              amt_mm_income: _this.amt_mm_income,
              amt_mm_expense: _this.amt_mm_expense
            }
          });
        } else {
          this.$toast.center(ko.messages.require);
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
