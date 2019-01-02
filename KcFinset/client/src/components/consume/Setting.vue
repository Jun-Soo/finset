<template>
  <section v-if="seen">
    <dl class="setting-wrap">
      <dd>
        <p class="key">월 시작일</p>
        <p class="value">
          <multiselect v-model="dt_basic" label="text" :show-labels="false" :options="dt_basic_option" :searchable="false" :allow-empty="false" :onClose="modifyDt_basic" :title="'월 시작일'" class="multiselect-basic">
          </multiselect>
        </p>
      </dd>
      <dt>지출</dt>
      <dd>
        <p class="key">지출예산설정</p>
        <p class="value"><a @click="clickSetting('regGoal')">설정</a></p>
      </dd>
      <dd>
        <p class="key">카테고리설정</p>
        <p class="value"><a @click="clickSetting('consumeClass')">설정</a></p>
      </dd>
      <dd>
        <p class="key">할부개월수 분할</p>
        <p class="value"><button class="btn-onoff" :class="{'on': yn_installment === 'Y'}" @click="clickSetting"></button></p>
      </dd>
      <dt>수입</dt>
      <dd>
        <p class="key">카테고리설정</p>
        <p class="value"><a @click="clickSetting('incomeClass')">설정</a></p>
      </dd>
    </dl>
  </section>
</template>

<script>
export default {
  name: "ConsumeSetting",
  data() {
    return {
      seen: false, // 화면 표출 여부
      yn_installment: "N", // 할부 적용 여부
      dt_basic: { text: "1일", value: "1" }, // 기준일
      dt_basic_option: [] // 기준일 리스트
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "설정";
  },
  created() {
    this.getPersonSetInfo();
    for (var i = 1; i <= 31; i++) {
      this.dt_basic_option.push({
        text: i + "일",
        value: (i + "").length == 1 ? "0" + i : i + ""
      });
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------화면 컨트롤---------------------
    // 버튼 클릭 시
    clickSetting: function(key) {
      // 할부 분할 여부만 object가 떨어짐
      if (typeof key == "object") {
        if (this.yn_installment == "Y") {
          this.yn_installment = "N";
        } else {
          this.yn_installment = "Y";
        }
        this.modifyYn_installment();
      } else {
        this.$router.push("/consume/" + key);
      }
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 사용자 설정 정보 조회
    getPersonSetInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/getPersonSetInfo.json")
        .then(function(response) {
          var personInfo = response.data.personInfo;
          _this.yn_installment = personInfo.yn_installment;
          _this.dt_basic = {
            text: personInfo.dt_basic + "일",
            value: personInfo.dt_basic + ""
          };
          _this.seen = true;
        });
    },
    // 할부 적용 여부 업데이트
    modifyYn_installment: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("yn_installment", this.yn_installment);
      this.$http
        .post("/m/consume/modifyYn_installment.json", formData)
        .then(function(response) {});
    },
    // 기준일 업데이트
    modifyDt_basic: function(param) {
      var _this = this;
      var formData = new FormData();
      formData.append("dt_basic", param.value);
      this.$http
        .post("/m/consume/modifyDt_basic.json", formData)
        .then(function(response) {
          _this.$store.state.user.dt_basic = param.value;
        });
    }
    // ---------------------//데이터 이동---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
