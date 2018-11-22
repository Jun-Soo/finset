<template>
  <section>
    <div class="container">
      <p>금리 등 부채 정보를 수정합니다.</p>
      <ul class="debt-modify">
        <li>
          <p class="key">금리</p>
          <p><input type="text" v-model="debtVO.interest" @blur="autoOpen(0)"><em>%</em></p>
        </li>
        <li>
          <p class="key">상환방법</p>
          <p>
            <!-- <select v-model="debtVO.rep_method">
              <option value="01">만기일시상환</option>
              <option value="02">원리금분할상환</option>
              <option value="03">원금분할상환</option>
            </select> -->
            <multiselect v-model="debtVO.rep_method" ref="rep_method" label="text" :show-labels="false" :options="rep_method_option" placeholder="상환방법" :searchable="false" :allow-empty="false" @close="autoOpen(1)">
              <!-- <template slot="singleLabel" slot-scope="{ option }">{{ option.text }}</template> -->
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">거치기간</p>
          <p>
            <!-- <select v-model="debtVO.loan_mount">
              <option value="00">없음</option>
              <option v-for="(n, index) in 10" :key="index" :value="(n+'').length == 1?'0'+n:n">{{n}} 년</option>
            </select> -->
            <multiselect v-model="debtVO.loan_mount" ref="loan_mount" label="text" :show-labels="false" :options="loan_mount_option" placeholder="거치기간" :searchable="false" :allow-empty="false" @close="autoOpen(2)"></multiselect>
          </p>
        </li>
        <li>
          <p class="key">이자납입주기</p>
          <p>
            <!-- <select v-model="debtVO.inter_pay_cycle">
              <option value="01">매월</option>
              <option value="02">분기</option>
              <option value="03">년</option>
              <option value="04">만기시</option>
              <option value="05">특정일</option>
            </select> -->
            <multiselect v-model="debtVO.inter_pay_cycle" ref="inter_pay_cycle" label="text" :show-labels="false" :options="inter_pay_cycle_option" placeholder="이자납입주기" :searchable="false" :allow-empty="false" @close="autoOpen(3)"></multiselect>
          </p>
        </li>
        <li>
          <p class="key">이자납입일</p>
          <p>
            <!-- <select v-model="debtVO.inter_pay_day">
              <option v-for="(n, index) in 31" :key="index" :value="(n+'').length == 1? '0'+ n : n">{{n}} 일</option>
            </select> -->
            <multiselect v-model="debtVO.inter_pay_day" ref="inter_pay_day" label="text" placeholder="이자납입일" :show-labels="false" :options="inter_pay_day_option" :searchable="false" :allow-empty="false" @close="autoOpen(4)"></multiselect>
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
      debtVO: {},
      rep_method_option: [
        { text: "만기일시상환", value: "03" },
        { text: "원리금분할상환", value: "01" },
        { text: "원금분할상환", value: "02" }
      ],
      loan_mount_option: new Array(),
      inter_pay_cycle_option: [
        { text: "매월", value: "01" },
        { text: "분기", value: "02" },
        { text: "년", value: "03" },
        { text: "만기시", value: "04" },
        { text: "특정일", value: "05" }
      ],
      inter_pay_day_option: new Array()
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
    this.loan_mount_option = this.getLoan_mount_option();
    this.inter_pay_day_option = this.getInter_pay_day_option();
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
          // debtVO.rep_method = debtVO.rep_method ? debtVO.rep_method : "01";
          // debtVO.loan_mount = debtVO.loan_mount ? debtVO.loan_mount : "00";
          // debtVO.inter_pay_day = debtVO.inter_pay_day
          //   ? debtVO.inter_pay_day
          //   : "01";
          debtVO.rep_method = _this.getDefault("rep_method", debtVO.rep_method);
          debtVO.loan_mount = _this.getDefault("loan_mount", debtVO.loan_mount);
          debtVO.inter_pay_cycle = _this.getDefault(
            "inter_pay_cycle",
            debtVO.inter_pay_cycle
          );
          debtVO.inter_pay_day = _this.getDefault(
            "inter_pay_day",
            debtVO.inter_pay_day
          );
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
    },
    autoOpen: function(idx) {
      switch (idx) {
        case 0:
          this.$refs.rep_method.$el.focus();
          break;
        case 1:
          this.$refs.loan_mount.$el.focus();
          break;
        case 2:
          this.$refs.inter_pay_cycle.$el.focus();
          break;
        case 3:
          this.$refs.inter_pay_day.$el.focus();
          break;
      }
    },
    getLoan_mount_option: function() {
      var arr = new Array();
      for (var idx = 1; idx <= 10; idx++) {
        arr.push({
          text: idx + "년",
          value: (idx + "").length == 1 ? "0" + idx : idx
        });
      }
      return arr;
    },
    getInter_pay_day_option: function() {
      var arr = new Array();
      for (var idx = 1; idx <= 31; idx++) {
        arr.push({
          text: idx + "일",
          value: (idx + "").length == 1 ? "0" + idx : idx
        });
      }
      return arr;
    },
    getDefault: function(type, value) {
      switch (type) {
        case "rep_method":
          return this.rep_method_option.filter(
            eachOption => eachOption.value == value
          )[0];
        case "loan_mount":
          return this.loan_mount_option.filter(
            eachOption => eachOption.value == value
          )[0];
        case "inter_pay_cycle":
          return this.inter_pay_cycle_option.filter(
            eachOption => eachOption.value == value
          )[0];
        case "inter_pay_day":
          return this.inter_pay_day_option.filter(
            eachOption => eachOption.value == value
          )[0];
        default:
          break;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
