<template>
  <section>
    <div class="container">
      <p>대출 상환정보를 입력해 주세요</p>
      <ul class="debt-modify">
        <li>
          <p class="key">거치여부</p>
          <p>
            <multiselect v-model="yn_loan_mount" ref="yn_loan_mount" label="text" :show-labels="false" :options="options_yn_loan_mount" placeholder="거치여부 선택" :searchable="false" :allow-empty="false" @select="selectYnLoanMount" v-validate="'required'" data-vv-name='거치여부'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('거치여부')">{{errors.first('거치여부')}}</p>
        <li>
          <p class="key">거치기간</p>
          <p>
            <multiselect v-model="cd_loan_mount" ref="cd_loan_mount" label="text" :show-labels="false" :options="options_cd_loan_mount" placeholder="거치기간 선택" :searchable="false" :allow-empty="false" @select="selectCdLoanMount" v-validate="'required'" data-vv-name='거치기간'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('거치기간')">{{errors.first('거치기간')}}</p>
        <li>
          <p class="key">상환방식</p>
          <p>
            <multiselect v-model="cd_type_pay" ref="cd_type_pay" label="text" :show-labels="false" :options="options_cd_type_pay" placeholder="상환방식 선택" :searchable="false" :allow-empty="false" @select="selectCdTypePay" v-validate="'required'" data-vv-name='상환방식'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('상환방식')">{{errors.first('상환방식')}}</p>
        <li>
          <p class="key">상환기간</p>
          <p>
            <multiselect v-model="cd_loan_term" ref="cd_loan_term" label="text" :show-labels="false" :options="options_cd_loan_term" placeholder="상환기간 선택" :searchable="false" :allow-empty="false" @select="selectCdLoanTerm" v-validate="'required'" data-vv-name='상환기간'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('상환기간')">{{errors.first('상환기간')}}</p>
      </ul>
      <div class="btn-wrap float">
        <a class="solid blue box" @click="clickSearch()">조회</a>
      </div>
    </div>

  </section>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person,
      options_yn_loan_mount: [
        { text: "거치", value: "1" },
        { text: "비거치", value: "2" }
      ],
      options_cd_loan_mount: [
        { text: "1년", value: "1" },
        { text: "2년", value: "2" },
        { text: "3년", value: "3" },
        { text: "4년", value: "4" },
        { text: "5년", value: "5" }
      ],
      options_cd_type_pay: [
        { text: "원리금분할상환", value: "1" },
        { text: "원금분할상환", value: "2" },
        { text: "만기일시상환", value: "3" }
      ],
      options_cd_loan_term: [
        { text: "1년", value: "1" },
        { text: "2년", value: "2" },
        { text: "3년", value: "3" },
        { text: "4년", value: "4" },
        { text: "5년", value: "5" },
        { text: "10년", value: "10" },
        { text: "20년", value: "20" }
      ],
      yn_loan_mount: null,
      cd_loan_mount: null,
      cd_type_pay: null,
      cd_loan_term: null
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상환 정보";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    selectYnLoanMount: function() {
      var _this = this;
      setTimeout(function() {
        _this.$refs.cd_loan_mount.$el.focus();
      }, 100);
    },
    selectCdLoanMount: function() {
      var _this = this;
      setTimeout(function() {
        _this.$refs.cd_type_pay.$el.focus();
      }, 100);
    },
    selectCdTypePay: function() {
      var _this = this;
      setTimeout(function() {
        _this.$refs.cd_loan_term.$el.focus();
      }, 100);
    },
    selectCdLoanTerm: function() {},
    clickSearch: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          formData.append("no_bunch", _this.no_bunch);
          formData.append("yn_loan_mount", _this.yn_loan_mount.value);
          formData.append("cd_loan_mount", _this.cd_loan_mount.value);
          formData.append("cd_type_pay", _this.cd_type_pay.value);
          formData.append("cd_loan_term", _this.cd_loan_term.value);
          this.$http
            .post(
              "/m/loanhomemortgage/modifyLoanRERepaymentInfo.json",
              formData,
              {
                headers: {
                  async: false,
                  "Content-Type":
                    "application/x-www-form-urlencoded; charset=UTF-8"
                }
              }
            )
            .then(response => {
              var result = response.data;
              console.log(result);
              if (result.result == "00") {
                this.$router.push({
                  name: "GoodsLoading",
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
