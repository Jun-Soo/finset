<template>
  <section>
    <div class="container">
      <p>용도 및 부가 정보를 입력해주세요</p>
      <ul class="debt-modify">
        <li>
          <p class="key">대출용도</p>
          <p>
            <multiselect v-model="cd_loan_use" ref="cd_loan_use" placeholder="대출용도선택" track-by="text" label="text" :options="options_loan" :searchable="false" :allow-empty="false" @select="selectLoan()" v-validate="'required'" data-vv-name='대출용도'>
            </multiselect>
            <a class=" warn" v-if="errors.has('대출용도')">{{errors.first('대출용도')}}</a>
          </p>
        </li>
        <li>
          <p class="key">주거형태</p>
          <p>
            <multiselect v-model="cd_house_type" ref="cd_house_type" placeholder="주거형태선택" track-by="text" label="text" :options="options_house" :searchable="false" :allow-empty="false" @select="selectHouse()" v-validate="'required'" data-vv-name='주거형태'>
            </multiselect>
            <a class=" warn" v-if="errors.has('주거형태')">{{errors.first('주거형태')}}</a>
          </p>
        </li>
        <li>
          <p class="key">소유형태</p>
          <p>
            <multiselect v-model="cd_live_type_home" ref="cd_live_type_home" placeholder="소유형태선택" track-by="text" label="text" :options="options_live" :searchable="false" :allow-empty="false" @select="selectLive()" v-validate="'required'" data-vv-name='소유형태'>
            </multiselect>
            <a class=" warn" v-if="errors.has('소유형태')">{{errors.first('소유형태')}}</a>
          </p>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a class="solid blue box" @click="clickConfirm()">추가정보입력</a>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      Common: Common,
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person,
      cd_loan_use: {},
      cd_house_type: {},
      cd_live_type_home: {},
      isShowButton: false,
      options_loan: "",
      options_house: "",
      options_live: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "부가정보";
  },
  created() {
    this.options_loan = Common.makeOptions("cd_loan_use", "");
    this.options_house = Common.makeOptions("cd_house_type", "");
    this.options_live = Common.makeOptions("cd_live_type_home", "");
    //console.log(result);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    selectLoan: function() {
      var _this = this;
      setTimeout(function() {
        _this.$refs.cd_house_type.$el.focus();
      }, 100);
    },
    selectHouse: function() {
      var _this = this;
      setTimeout(function() {
        _this.$refs.cd_live_type_home.$el.focus();
      }, 100);
    },
    selectLive: function() {
      this.isShowButton = true;
    },
    clickConfirm: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          formData.append("no_bunch", _this.no_bunch);
          formData.append("cd_loan_use", _this.cd_loan_use.value);
          formData.append("cd_house_type", _this.cd_house_type.value);
          formData.append("cd_live_type_home", _this.cd_live_type_home.value);
          this.$http
            .post("/m/loanworker/modifyLoanAdditional.json", formData, {
              headers: {
                async: false,
                "Content-Type":
                  "application/x-www-form-urlencoded; charset=UTF-8"
              }
            })
            .then(response => {
              var result = response.data;
              console.log(result);
              if (result.result == "00") {
                this.$router.push({
                  name: "GoodsCreditJobIncome",
                  params: {
                    cd_fc: _this.cd_fc,
                    cd_goods: _this.cd_goods,
                    no_bunch: _this.no_bunch,
                    kcb_di: _this.kcb_di,
                    ssn_person: _this.ssn_person
                  }
                });
              } else {
                this.$toast.center(result.message);
              }
            })
            .catch(e => {
              this.$toast.center(ko.messages.error);
            });
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
