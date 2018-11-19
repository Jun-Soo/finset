<template>
  <section>
    <div class="container">
      <p>금리 등 부채 정보를 수정합니다.</p>
      <ul class="debt-modify">
        <li>
          <p class="key">금리</p>
          <p><input type="text" v-model="debtVO.interest"><em>%</em></p>
        </li>
        <li>
          <p class="key">상환방법</p>
          <p>
            <select v-model="debtVO.rep_method">
              <option value="01">만기일시상환</option>
              <option value="02">원리금분할상환</option>
              <option value="03">원금분할상환</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">거치기간</p>
          <p>
            <select v-model="debtVO.loan_mount">
              <option value="00">없음</option>
              <option v-for="(n, index) in 10" :key="index" :value="(n+'').length == 1?'0'+n:n">{{n}} 년</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">이자납입주기</p>
          <p>
            <select v-model="debtVO.inter_pay_cycle">
              <option value="01">매월</option>
              <option value="02">분기</option>
              <option value="03">년</option>
              <option value="04">만기시</option>
              <option value="05">특정일</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">이자납입일</p>
          <p>
            <select v-model="debtVO.inter_pay_day">
              <option v-for="(n, index) in 31" :key="index" :value="(n+'').length == 1? '0'+ n : n">{{n}} 일</option>
              <!-- <option v-for="(n, index) in 31" :key="index" :value="(n+'').length">{{n}} 일</option> -->
            </select>
          </p>
        </li>
      </ul>
      <div class="btn-wrap">
        <a @click="updateDebtInfo" class="solid blue">확인</a>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "DebtModify",
  data() {
    return {
      debtVO: {}
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보수정";
  },
  created() {
    this.getdebtInfoForUpdate();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getdebtInfoForUpdate: function() {
      var _this = this;
      this.$http
        .get("/m/debt/getDebtInfoForUpdate.json", {
          params: {
            no_person: _this.$route.query.no_person,
            no_manage_info: _this.$route.query.no_manage_info
          }
        })
        .then(function(response) {
          var debtVO = response.data.debtVO;
          debtVO.interest =
            _this.$route.query.interest == "-"
              ? 0
              : _this.$route.query.interest;
          debtVO.rep_method = debtVO.rep_method ? debtVO.rep_method : "01";
          debtVO.loan_mount = debtVO.loan_mount ? debtVO.loan_mount : "00";
          debtVO.inter_pay_day = debtVO.inter_pay_day
            ? debtVO.inter_pay_day
            : "01";
          _this.debtVO = debtVO;
        });
    },
    updateDebtInfo: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("interest", this.$route.query.interest);
      formData.append("rep_method", this.debtVO.rep_method);
      formData.append("loan_mount", this.debtVO.loan_mount);
      formData.append("inter_pay_day", this.debtVO.inter_pay_day);
      formData.append("inter_pay_cycle", this.debtVO.inter_pay_cycle);
      formData.append("no_person", this.$route.query.no_person);
      formData.append("no_manage_info", this.$route.query.no_manage_info);
      this.$http
        .post("/m/debt/updateDebtInfo.json", formData)
        .then(function(response) {
          _this.$router.push({
            path: "/debt/detail",
            query: {
              no_person: _this.$route.query.no_person,
              no_manage_info: _this.$route.query.no_manage_info
            }
          });
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
