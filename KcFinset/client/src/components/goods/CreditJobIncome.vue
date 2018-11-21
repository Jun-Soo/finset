<template>
  <section id="content">
    <div class="container">
      <div class="lead">
        <p>직장/소득정보를 입력해주세요.</p>
      </div>
      <form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
        <div class="form-inline">
          <div class="form-group">
            <label for="cd_occupational">직군</label>
            <multiselect placeholder="직군선택" track-by="text" label="text" :options="option_occupational" :searchable="false" :allow-empty="false" @select="selectOccupational" v-validate="'required'" data-vv-name='직군'>
            </multiselect>
            <p class=" warn" v-if="errors.has('직군')">{{errors.first('직군')}}</p>
          </div>
          <div class="form-group has-feedback" id="jobSearch">
            <label for="nm_comp">직장명</label>
            <input type="text" class="form-control" name="nm_comp" id="nm_comp" placeholder="직장명을 검색하세요" @focus="searchJob" value="" readonly="readonly">
            <button type="button" class="sch-btn" id="btnNm_comp" @click="searchJob"><span class="form-control-feedback btn-search">검색</span></button>
          </div>
          <div class="form-group has-feedback" id="jobDetail" style="display: none">
            <label for="cd_occupational_detail">상세직군</label>
            <multiselect placeholder="상세직군선택" track-by="text" label="text" :options="option_occupational_detail" :searchable="false" :allow-empty="false" @select="selectOccupationalDetail" v-validate="'required'" data-vv-name='상세직군'>
            </multiselect>
            <p class=" warn" v-if="errors.has('상세직군')">{{errors.first('상세직군')}}</p>
          </div>
          <div class="form-group" id="worker_position" style="display: none">
            <label for="cd_worker_position">직위</label>
            <select class="selectpicker" data-header="직위선택" name="cd_worker_position" id="cd_worker_position">
            </select>
          </div>
          <div class="form-group" id="employee_type" style="display: none">
            <label for="cd_employee_type">고용형태</label>
            <select class="selectpicker" data-header="고용형태선택" name="cd_employee_type" id="cd_employee_type">
              ${ufn:makeOptions("cd_employee_type", "고용형태선택", "")}
            </select>
          </div>
          <div class="form-group" id="dt_join_view" style="display: none">
            <label for="jb_dt_join_view">입사년월</label>
            <input type="month" name="jb_dt_join_view" id="jb_dt_join_view" class="form-control slt-date" placeholder="" />
            <input type="hidden" name="jb_dt_join" id="jb_dt_join" class="form-control slt-date" placeholder="" />
          </div>
          <div class="form-group">
            <label for="amt_year_income">연소득</label>
            <input type="number" class="form-control" name="amt_year_income" id="amt_year_income" maxlength="6" autocomplete="off" value="" />
            <span class="form-control-feedback" aria-hidden="true">만원</span>
          </div>
        </div>
      </form>
    </div>
    <div class="btn-fixed-bottom" id="confirmed_div">
      <a role="button" class="btn btn-lg btn-primary btn-block" onclick="loanApplyStepStep();">확인</a>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
export default {
  name: "",
  data() {
    return {
      no_person: this.$store.state.user.noPerson,
      nm_person: this.$store.state.user.nmPerson,
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person,

      option_occupational: "",
      cd_occupational: "",
      nm_comp: "",
      cd_occupational_detail: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "직장/소득정보";
  },
  created() {
    this.option_occupational = Common.makeOptions("cd_occupational", "");
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    selectOccupational: function(option) {
      this.cd_occupational = option.value;
      this.cateChgMid();
    },
    selectOccupationalDetail: function(option) {
      //this.cateChgMid(option.value);
    },
    searchJob: function() {
      if (this.cd_occupational.value == "") {
        return false;
      }
      if (
        frmloanApplyStep.amt_year_income.value == "" ||
        frmloanApplyStep.amt_year_income.value == null
      ) {
        $("#amt_year_income").prop("disabled", true);
      }
      // frmloanApplyStep.action =
      //   "<c:url value='/m/loanworker/frameLoanWorkerStep10.crz'/>";
      // // alert("searchJob:action=/m/loanworker/frameLoanWorkerStep10.crz");

      // frmloanApplyStep.submit();
    },
    //중분류 스크립트
    cateChgMid: function() {
      var value = this.cd_occupational;
      if (value == "" || value == "1") {
        $("#jobSearch").show();
        $("#jobDetail").hide();

        if (this.nm_comp == "" && is_start == "") {
          $("#nm_comp").click();
        }
      } else {
        $("#jobSearch").hide();
        $("#jobDetail").show();
      }
      var cate_mid_1 = ["중분류", "일반 기업"];
      var cate_mid_1v = ["", "111"];
      var cate_mid_2 = [
        "중분류",
        "중앙정부/지자체공무원",
        "기능직",
        "법조/법무",
        "경찰",
        "소방",
        "군인",
        "군무원",
        "교육",
        "별정직",
        "정무의원직"
      ];
      var cate_mid_2v = [
        "",
        "121",
        "122",
        "123",
        "124",
        "125",
        "126",
        "127",
        "128",
        "129",
        "130"
      ];
      var cate_mid_3 = [
        "중분류",
        "법조/법무",
        "의료",
        "세무/회계",
        "기술",
        "부동산",
        "항공"
      ];
      var cate_mid_3v = ["", "131", "132", "133", "134", "135", "136"];
      var cate_mid_4 = [
        "중분류",
        "정부투자/산하기관",
        "주한외국관공서/미군군무원",
        "지방자치산하기관",
        "기타 공공기관"
      ];
      var cate_mid_4v = ["", "141", "142", "143", "144"];
      var cate_mid_5 = [
        "중분류",
        "대학교",
        "전문대학",
        "초/중/고교",
        "기타학교",
        "유치원/유아원/보육원",
        "일반학원"
      ];
      var cate_mid_5v = ["", "151", "152", "153", "154", "155", "156"];
      var cate_mid_6 = ["중분류", "종합/대학병원", "일반병원", "개인/요양병원"];
      var cate_mid_6v = ["", "161", "162", "163"];
      var d = eval("cate_mid_" + value);
      var k = eval("cate_mid_" + value + "v");
      var target = document.getElementById("cd_occupational_detail");
      target.options.length = 0;
      for (var x in d) {
        var opt = document.createElement("option");
        opt.value = k[x];
        opt.innerHTML = d[x];
        target.appendChild(opt);
      }
      /**
       * 일반기업
       */
      if (value == "1") {
        this.cd_occupational_detail = "111";
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
