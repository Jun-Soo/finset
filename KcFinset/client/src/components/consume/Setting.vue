<template>
  <section v-if="seen">
    <div class="cert-check-wrap">
      <p class="title">회원정보</p>
      <multiselect v-model="dt_basic" label="text" :show-labels="false" :options="dt_basic_option" :searchable="false" :allow-empty="false" @select="modifyDt_basic" class="multiselect-basic mt10">
      </multiselect>
    </div>

    <dl class="setting-wrap">
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
      seen: false,
      yn_installment: "N",
      dt_basic: { text: "1일", value: "1" },
      dt_basic_option: []
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
      this.dt_basic_option.push({ text: i + "일", value: i + "" });
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickSetting: function(key) {
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
    getPersonSetInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/getPersonSetInfo.json")
        .then(function(response) {
          var personInfo = response.data.personInfo;
          _this.yn_installment = personInfo.yn_installment;
          // _this.dt_basic = personInfo.dt_basic;
          _this.dt_basic = {
            text: personInfo.dt_basic + "일",
            value: personInfo.dt_basic + ""
          };
          _this.seen = true;
        });
    },
    modifyYn_installment: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("yn_installment", this.yn_installment);
      this.$http
        .post("/m/consume/modifyYn_installment.json", formData)
        .then(function(response) {});
    },
    modifyDt_basic: function(param) {
      var _this = this;
      var formData = new FormData();
      formData.append("dt_basic", param.value);
      this.$http
        .post("/m/consume/modifyDt_basic.json", formData)
        .then(function(response) {
          this.$store.state.user.dt_basic = param.value;
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
