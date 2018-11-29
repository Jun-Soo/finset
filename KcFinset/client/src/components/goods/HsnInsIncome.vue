<template>
  <section>
    <div class="container">
      <p>소득정보를 입력해 주세요</p>
      <ul class="debt-modify">
        <li>
          <p class="key">구분</p>
          <p>
            <multiselect v-model="job_class" ref="job_class" label="text" :show-labels="false" :options="options_job_class" placeholder="직업 구분" :searchable="false" :allow-empty="false" @select="selectJobClass" v-validate="'required'" data-vv-name='직업'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('직업')">{{errors.first('직업')}}</p>
        <li v-if="job_class.value == '1'">
          <p class="key">연소득</p>
          <p><input type="text" v-model="amt_year_income" v-validate="'required'" data-vv-name='연소득'>만원</p>
        </li>
        <p class="warn" v-if="errors.has('연소득')">{{errors.first('연소득')}}</p>

        <li v-else-if="job_class.value == '2'">
          <p class="key">연매출</p>
          <p><input type="text" v-model="amt_year_sale" v-validate="'required'" data-vv-name='연매출'>만원</p>
        </li>
        <p class="warn" v-if="errors.has('연매출')">{{errors.first('연매출')}}</p>
      </ul>
      <div class="btn-wrap float">
        <a class="solid blue box" @click="clickNext()">다음</a>
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
      options_job_class: [
        { text: "직장인", value: "1" },
        { text: "개인사업자", value: "2" }
      ],
      job_class: "",
      amt_year_income: "",
      amt_year_sale: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "소득 정보";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    selectJobClass: function() {},
    clickNext: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          formData.append("no_bunch", _this.no_bunch);
          formData.append("cd_job_class_l", _this.job_class.value);
          formData.append("amt_year_income", _this.amt_year_income);
          formData.append("amt_year_sale", _this.amt_year_sale);
          this.$http
            .post("/m/loanhomemortgage/modifyLoanREIncomeInfo.json", formData, {
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
                  name: "GoodsHsnInsRepay",
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
