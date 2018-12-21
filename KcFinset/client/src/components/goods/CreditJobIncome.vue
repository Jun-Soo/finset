<template>
  <div>
    <section>
      <div class="container">
        <p>직장 / 소득 정보를 입력해 주세요</p>
        <ul class="debt-modify">
          <li>
            <div>
              <p class="key">직군</p>
              <p>
                <multiselect placeholder="직군선택" v-model="occupational" :title="'직군'" :options="option_occupational" :onClose="selectOccupational" v-validate="'required'" data-vv-name='직군' />
              </p>
            </div>
            <p class=" warn" v-if="errors.has('직군')">{{errors.first('직군')}}</p>
          </li>
          <li v-if="show_jobSearch">
            <div>
              <p class="key">직장명</p>
              <p>
                <input type="text" style="width: 60%" placeholder="직장명을 검색하세요" @focus="searchJob" v-model="korentrnm" v-validate="'required'" data-vv-name='직장명'>
                <button class="btn-search" @click="searchJob"></button>
              </p>
            </div>
            <p class=" warn" v-if="errors.has('직장명')">{{errors.first('직장명')}}</p>
          </li>
          <li v-if="show_occupational_detail">
            <div>
              <p class="key">상세직군</p>
              <p>
                <multiselect ref="occupational_detail" placeholder="상세직군선택" :title="'상세직군'" v-model="occupational_detail" :options="option_occupational_detail" :onClose="selectOccupationalDetail" v-validate="'required'" data-vv-name='상세직군' />
              </p>
            </div>
            <p class=" warn" v-if="errors.has('상세직군')">{{errors.first('상세직군')}}</p>
          </li>
          <li v-if="show_worker_position">
            <div>
              <p class="key">직위</p>
              <p>
                <multiselect ref="worker_position" placeholder="직위선택" :title="'직위'" v-model="worker_position" :options="option_worker_position" :onClose="selectWorkerPosition" v-validate="'required'" data-vv-name='직위' />
              </p>
            </div>
            <p class=" warn" v-if="errors.has('직위')">{{errors.first('직위')}}</p>
          </li>
          <li v-if="show_employee_type">
            <div>
              <p class="key">고용형태</p>
              <p>
                <multiselect ref="employee_type" placeholder="고용형태선택" :title="'고용형태'" v-model="employee_type" :options="option_employee_type" :onClose="selectEmployeeType" v-validate="'required'" data-vv-name='고용형태' />
              </p>
            </div>
            <p class=" warn" v-if="errors.has('고용형태')">{{errors.first('고용형태')}}</p>
          </li>
          <li v-if="show_dt_join_view">
            <div>
              <p class="key">입사년월</p>
              <p><input id="dt_join_view" type="month" laceholder="입사년월" v-model="dt_join_view" @change="changeDtJoinView" v-validate="'required'" data-vv-name='입사년월'></p>
            </div>
            <p class=" warn" v-if="errors.has('입사년월')">{{errors.first('입사년월')}}</p>
          </li>
          <li>
            <div>
              <p class="key">연소득</p>
              <p>
                <money id="amt_year_income" v-model="amt_year_income" :change="changeAmtYearIncome" :disabled="disable_amt_year_income" v-validate="'required'" data-vv-name='연소득' /> 만원</p>
            </div>
            <p class=" warn" v-if="errors.has('연소득')">{{errors.first('연소득')}}</p>
          </li>
        </ul>
        <div class="btn-wrap float" v-if="show_next">
          <a class="solid blue box" @click="clickNext">다음</a>
        </div>
      </div>
    </section>
    <vue-modal transitionName="zoom-in" name="CreditSrcJobNm" v-on:popclose="closePop('CreditSrcJobNm')">
      <CreditSrcJobNm slot="body" v-on:popclose="closePop('CreditSrcJobNm')" :no_bunch="no_bunch" ref="form"></CreditSrcJobNm>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="CreditInsJobNm" v-on:popclose="closePop('CreditInsJobNm')">
      <CreditInsJobNm slot="body" v-on:popclose="closePop('CreditInsJobNm')" :no_bunch="no_bunch" ref="test"></CreditInsJobNm>
    </vue-modal>
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import CreditSrcJobNm from "./CreditSrcJobNm.vue";
import CreditInsJobNm from "./CreditInsJobNm.vue";
import ko from "vee-validate/dist/locale/ko.js";
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
      //display 여부
      show_jobSearch: false,
      show_occupational_detail: false,
      show_worker_position: false,
      show_employee_type: false,
      show_dt_join_view: false,
      show_next: false,
      //disable 여부
      disable_amt_year_income: true,
      //select option
      option_occupational: [],
      option_occupational_detail: [],
      option_worker_position: [],
      option_employee_type: [],
      //연동 값
      occupational: null,
      occupational_detail: null,
      worker_position: null,
      employee_type: null,
      dt_join_view: "",
      amt_year_income: "",
      //기업정보
      kiscode: "", //대상기업 KISCODE
      korentrnm: "", //회사명
      bizno: "", //사업자번호
      crpno: "", //법인번호
      ltgmktdivcd: "", //상장여부
      eprmdydivcd: "", //기업주체구분
      // scl: "",       //기업규모 -- 데이터 없음
      etl_ipc_yn: "" //외부감사여부
      // chulja: "",    //상호출자제한집단(그룹사)여부  -- 데이터 없음
    };
  },
  components: {
    CreditSrcJobNm: CreditSrcJobNm,
    CreditInsJobNm: CreditInsJobNm
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "직장/소득정보";
  },
  created() {
    this.option_occupational = Common.makeOptions("cd_occupational", "");
    this.option_employee_type = Common.makeOptions("cd_employee_type", "");
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    selectOccupational: function(option) {
      this.occupational = option;
      this.hide2ndDepth();
      if (option.value == "1") {
        this.korentrnm = "";
      } else {
        this.korentrnm = option.text;
      }
      this.occupational_detail = {};
      this.worker_position = {};
      this.cateChgMid(option);
    },
    selectOccupationalDetail: function(option) {
      var _this = this;
      this.cateChgLow(option);
      this.show2ndDepth();
      setTimeout(function() {
        _this.$refs.worker_position.open();
      }, 100);
    },
    selectWorkerPosition: function(option) {
      var _this = this;
      setTimeout(function() {
        _this.$refs.employee_type.open();
      }, 100);
    },
    selectEmployeeType: function(option) {
      var _this = this;
      setTimeout(function() {
        $("#dt_join_view").click();
      }, 100);
    },
    changeDtJoinView: function(option) {
      var _this = this;
      this.disable_amt_year_income = false;
      setTimeout(function() {
        $("#amt_year_income").focus();
      }, 100);
    },
    changeAmtYearIncome: function(option) {
      if (this.amt_year_income != "") {
        this.show_next = true;
      }
    },
    searchJob: function() {
      if (this.occupational.value == "") {
        return false;
      }
      if (this.amt_year_income == "") {
        this.disable_amt_year_income = true;
      }
      this.openPop("CreditSrcJobNm");
      // frmloanApplyStep.action =
      //   "<c:url value='/m/loanworker/frameLoanWorkerStep10.crz'/>";
      // // alert("searchJob:action=/m/loanworker/frameLoanWorkerStep10.crz");

      // frmloanApplyStep.submit();
    },
    clickNext: function() {
      var _this = this;

      alert(this.amt_year_income);
      return;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          formData.append("no_bunch", _this.no_bunch);
          formData.append("nm_comp", _this.korentrnm);
          formData.append("kiscode", _this.kiscode);
          formData.append("cd_occupational", _this.occupational.value);
          formData.append(
            "cd_occupational_detail",
            _this.occupational_detail.value
          );
          formData.append("cd_worker_position", _this.worker_position.value);
          formData.append("cd_employee_type", _this.employee_type.value);
          formData.append("dt_join_view", _this.dt_join_view);
          formData.append("amt_year_income", _this.amt_year_income);

          this.$http
            .post("/m/loanworker/updateTxFc.json", formData, {
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
            });
          // .catch(e => {
          //   this.$toast.center(ko.messages.error);
          // });
        }
      });
    },
    hide2ndDepth: function() {
      this.show_worker_position = false;
      this.show_employee_type = false;
      this.show_dt_join_view = false;
    },
    show2ndDepth: function() {
      this.show_worker_position = true;
      this.show_employee_type = true;
      this.show_dt_join_view = true;
    },
    //중분류 스크립트
    cateChgMid: function(option) {
      var value = option.value;
      var _this = this;

      var cate_mid_1 = [{ text: "일반 기업", value: "111" }];
      var cate_mid_2 = [
        { text: "중앙정부/지자체공무원", value: "121" },
        { text: "기능직", value: "122" },
        { text: "법조/법무", value: "123" },
        { text: "경찰", value: "124" },
        { text: "소방", value: "125" },
        { text: "군인", value: "126" },
        { text: "군무원", value: "127" },
        { text: "교육", value: "128" },
        { text: "별정직", value: "129" },
        { text: "정무의원직", value: "130" }
      ];
      var cate_mid_3 = [
        { text: "법조/법무", value: "131" },
        { text: "의료", value: "132" },
        { text: "세무/회계", value: "133" },
        { text: "기술", value: "134" },
        { text: "부동산", value: "135" },
        { text: "항공", value: "136" }
      ];
      var cate_mid_4 = [
        { text: "정부투자/산하기관", value: "141" },
        { text: "주한외국관공서/미군군무원", value: "142" },
        { text: "지방자치산하기관", value: "143" },
        { text: "기타 공공기관", value: "132" }
      ];
      var cate_mid_5 = [
        { text: "대학교", value: "151" },
        { text: "전문대학", value: "152" },
        { text: "초/중/고교", value: "153" },
        { text: "기타학교", value: "154" },
        { text: "유치원/유아원/보육원", value: "155" },
        { text: "일반학원", value: "156" }
      ];
      var cate_mid_6 = [
        { text: "종합/대학병원", value: "161" },
        { text: "일반병원", value: "162" },
        { text: "개인/요양병원", value: "163" }
      ];

      this.option_occupational_detail = eval("cate_mid_" + value);

      if (value == "" || value == "1") {
        this.show_jobSearch = true;
        this.show_occupational_detail = false;
        this.occupational_detail = { text: "일반 기업", value: "111" };
        this.searchJob();
      } else {
        this.show_jobSearch = false;
        this.show_occupational_detail = true;
        setTimeout(function() {
          _this.$refs.occupational_detail.open();
        }, 100);
      }

      // var k = eval("cate_mid_" + value + "v");
      // var target = document.getElementById("cd_occupational_detail");
      // console.log(target);
      // target.options.length = 0;
      // for (var x in d) {
      //   var opt = document.createElement("option");
      //   opt.value = k[x];
      //   opt.innerHTML = d[x];
      //   target.appendChild(opt);
      // }
    },
    cateChgLow: function(option) {
      var value = option.value;
      var cate_low_111 = [
        { text: "대표이사", value: "11111" },
        { text: "임원", value: "11112" },
        { text: "부장", value: "11113" },
        { text: "차장", value: "11114" },
        { text: "과장", value: "11115" },
        { text: "대리", value: "11116" },
        { text: "주임", value: "11117" },
        { text: "사원", value: "11118" },
        { text: "기타", value: "11119" }
      ];
      var cate_low_121 = [
        { text: "1급", value: "12111" },
        { text: "2급", value: "12112" },
        { text: "3급", value: "12113" },
        { text: "4급", value: "12114" },
        { text: "5급", value: "12115" },
        { text: "6급", value: "12116" },
        { text: "7급", value: "12117" },
        { text: "8급", value: "12118" },
        { text: "9급", value: "12119" },
        { text: "10급", value: "1211A" }
      ];
      var cate_low_122 = [
        { text: "1급", value: "12211" },
        { text: "2급", value: "12212" },
        { text: "3급", value: "12213" },
        { text: "4급", value: "12214" },
        { text: "5급", value: "12215" },
        { text: "6급", value: "12216" },
        { text: "7급", value: "12217" },
        { text: "8급", value: "12218" },
        { text: "9급", value: "12219" },
        { text: "10급", value: "1221A" }
      ];
      var cate_low_123 = [
        { text: "대법원장", value: "12311" },
        { text: "대법관", value: "12312" },
        { text: "고등법원장", value: "12313" },
        { text: "일반법관", value: "12314" },
        { text: "고시합격자", value: "12315" }
      ];
      var cate_low_124 = [
        { text: "치안총감/치안정감/치안감", value: "12411" },
        { text: "경무관", value: "12412" },
        { text: "총경", value: "12413" },
        { text: "경정", value: "12414" },
        { text: "경감", value: "12415" },
        { text: "경위", value: "12416" },
        { text: "경사", value: "12417" },
        { text: "경장", value: "12418" },
        { text: "순경", value: "12419" }
      ];
      var cate_low_125 = [
        { text: "소방총감/소방정감", value: "12511" },
        { text: "소방감", value: "12512" },
        { text: "소방준감", value: "12513" },
        { text: "소방정", value: "12514" },
        { text: "소방령", value: "12515" },
        { text: "소방경", value: "12516" },
        { text: "소방위", value: "12517" },
        { text: "소방장", value: "12518" },
        { text: "소방교", value: "12519" },
        { text: "소방사", value: "1251A" }
      ];
      var cate_low_126 = [
        { text: "대장/중장/소장/준장", value: "12611" },
        { text: "대령/중령/소령", value: "12612" },
        { text: "대위", value: "12613" },
        { text: "중위", value: "12614" },
        { text: "소위", value: "12615" },
        { text: "준위", value: "12616" },
        { text: "원사", value: "12617" },
        { text: "상사", value: "12618" },
        { text: "중사", value: "12619" },
        { text: "하사", value: "1261A" }
      ];
      var cate_low_127 = [
        { text: "1급", value: "12711" },
        { text: "2급", value: "12712" },
        { text: "3급", value: "12713" },
        { text: "4급", value: "12714" },
        { text: "5급", value: "12715" },
        { text: "6급", value: "12716" },
        { text: "7급", value: "12717" },
        { text: "8급", value: "12718" },
        { text: "9급", value: "12719" },
        { text: "10급", value: "1271A" }
      ];
      var cate_low_128 = [
        { text: "장학관", value: "12811" },
        { text: "연구관", value: "12812" },
        { text: "장학사", value: "12813" },
        { text: "연구사", value: "12814" }
      ];
      var cate_low_129 = [
        { text: "1급", value: "12911" },
        { text: "2급", value: "12912" },
        { text: "3급", value: "12913" },
        { text: "4급", value: "12914" },
        { text: "5급", value: "12915" },
        { text: "6급", value: "12916" },
        { text: "7급", value: "12917" },
        { text: "8급", value: "12918" },
        { text: "9급", value: "12919" },
        { text: "10급", value: "1291A" }
      ];
      var cate_low_12A = [
        { text: "국회의원", value: "12A11" },
        { text: "광역단체장", value: "12A12" },
        { text: "광역의원", value: "12A13" },
        { text: "기초단체장", value: "12A14" },
        { text: "기초단체의원", value: "12A15" }
      ];
      var cate_low_131 = [
        { text: "변호사", value: "13111" },
        { text: "법무사", value: "13112" },
        { text: "변리사", value: "13113" },
        { text: "공인노무사", value: "13114" },
        { text: "손해사정인", value: "13115" }
      ];
      var cate_low_132 = [
        { text: "의사", value: "13211" },
        { text: "한의사", value: "13212" },
        { text: "수의사", value: "13213" },
        { text: "약사", value: "13214" }
      ];
      var cate_low_133 = [
        { text: "공인회계사", value: "13311" },
        { text: "세무사", value: "13312" },
        { text: "관세사", value: "13313" }
      ];
      var cate_low_134 = [
        { text: "기술사", value: "13411" },
        { text: "건축사", value: "13412" }
      ];
      var cate_low_135 = [{ text: "감정평가사", value: "13511" }];
      var cate_low_136 = [
        { text: "기장", value: "13611" },
        { text: "부기장", value: "13612" }
      ];
      var cate_low_141 = [
        { text: "대표이사", value: "14111" },
        { text: "임원", value: "14112" },
        { text: "부장", value: "14113" },
        { text: "차장", value: "14114" },
        { text: "과장", value: "14115" },
        { text: "대리", value: "14116" },
        { text: "주임", value: "14117" },
        { text: "사원", value: "14118" },
        { text: "임시/파견", value: "14119" },
        { text: "계약", value: "1411A" },
        { text: "특수직해당", value: "14121" },
        { text: "임시/파견", value: "14129" },
        { text: "계약", value: "1412A" }
      ];
      var cate_low_142 = [
        { text: "대표이사", value: "14211" },
        { text: "임원", value: "14212" },
        { text: "부장", value: "14213" },
        { text: "차장", value: "14214" },
        { text: "과장", value: "14215" },
        { text: "대리", value: "14216" },
        { text: "주임", value: "14217" },
        { text: "사원", value: "14218" },
        { text: "임시/파견", value: "14219" },
        { text: "계약", value: "1421A" },
        { text: "특수직해당", value: "14221" },
        { text: "임시/파견", value: "14229" },
        { text: "계약", value: "1422A" }
      ];
      var cate_low_143 = [
        { text: "대표이사", value: "14311" },
        { text: "임원", value: "14312" },
        { text: "부장", value: "14313" },
        { text: "차장", value: "14314" },
        { text: "과장", value: "14315" },
        { text: "대리", value: "14316" },
        { text: "주임", value: "14317" },
        { text: "사원", value: "14318" },
        { text: "임시/파견", value: "14319" },
        { text: "계약", value: "1431A" },
        { text: "특수직해당", value: "14321" },
        { text: "아마운동선수", value: "14322" },
        { text: "예술단원", value: "14323" },
        { text: "도서관원", value: "14324" },
        { text: "미술관원", value: "14325" },
        { text: "공원관리", value: "14326" },
        { text: "기타", value: "14327" },
        { text: "임시/파견", value: "14329" },
        { text: "계약", value: "1432A" }
      ];
      var cate_low_144 = [
        { text: "대표이사", value: "14411" },
        { text: "임원", value: "14412" },
        { text: "부장", value: "14413" },
        { text: "차장", value: "14414" },
        { text: "과장", value: "14415" },
        { text: "대리", value: "14416" },
        { text: "주임", value: "14417" },
        { text: "사원", value: "14418" },
        { text: "임시/파견", value: "14419" },
        { text: "계약", value: "1441A" },
        { text: "특수직해당", value: "14421" },
        { text: "임시/파견", value: "14429" },
        { text: "계약", value: "1442A" }
      ];
      var cate_low_151 = [
        { text: "대표이사", value: "15111" },
        { text: "임원", value: "15112" },
        { text: "부장", value: "15113" },
        { text: "차장", value: "15114" },
        { text: "과장", value: "15115" },
        { text: "대리", value: "15116" },
        { text: "주임", value: "15117" },
        { text: "사원", value: "15118" },
        { text: "총장", value: "15121" },
        { text: "학장", value: "15122" },
        { text: "정교수", value: "15123" },
        { text: "부교수", value: "15124" },
        { text: "조교수", value: "15125" },
        { text: "전임강사", value: "15126" },
        { text: "시간강사", value: "15127" }
      ];
      var cate_low_152 = [
        { text: "대표이사", value: "15211" },
        { text: "임원", value: "15212" },
        { text: "부장", value: "15213" },
        { text: "차장", value: "15214" },
        { text: "과장", value: "15215" },
        { text: "대리", value: "15216" },
        { text: "주임", value: "15217" },
        { text: "사원", value: "15218" },
        { text: "총장", value: "15221" },
        { text: "학장", value: "15222" },
        { text: "정교수", value: "15223" },
        { text: "부교수", value: "15224" },
        { text: "조교수", value: "15225" },
        { text: "전임강사", value: "15226" },
        { text: "시간강사", value: "15227" }
      ];
      var cate_low_153 = [
        { text: "대표이사", value: "15311" },
        { text: "임원", value: "15312" },
        { text: "부장", value: "15313" },
        { text: "차장", value: "15314" },
        { text: "과장", value: "15315" },
        { text: "대리", value: "15316" },
        { text: "주임", value: "15317" },
        { text: "사원", value: "15318" },
        { text: "교장", value: "15321" },
        { text: "교감", value: "15322" },
        { text: "교사", value: "15323" },
        { text: "기간제교사", value: "15324" },
        { text: "보조/실험교사", value: "15325" },
        { text: "서무과장", value: "15326" },
        { text: "서무과직원", value: "15327" }
      ];
      var cate_low_154 = [
        { text: "대표이사", value: "15411" },
        { text: "임원", value: "15412" },
        { text: "부장", value: "15413" },
        { text: "차장", value: "15414" },
        { text: "과장", value: "15415" },
        { text: "대리", value: "15416" },
        { text: "주임", value: "15417" },
        { text: "사원", value: "15418" },
        { text: "교장", value: "15421" },
        { text: "교감", value: "15422" },
        { text: "교사", value: "15423" },
        { text: "병설유치원교사", value: "15424" },
        { text: "전임강사", value: "15425" },
        { text: "시간강사", value: "15426" }
      ];
      var cate_low_155 = [
        { text: "대표이사", value: "15511" },
        { text: "임원", value: "15512" },
        { text: "부장", value: "15513" },
        { text: "차장", value: "15514" },
        { text: "과장", value: "15515" },
        { text: "대리", value: "15516" },
        { text: "주임", value: "15517" },
        { text: "사원", value: "15518" },
        { text: "원장", value: "15521" },
        { text: "부원장", value: "15522" },
        { text: "주임강사", value: "15523" },
        { text: "강사", value: "15524" },
        { text: "보모", value: "15525" }
      ];
      var cate_low_156 = [
        { text: "대표이사", value: "15611" },
        { text: "임원", value: "15612" },
        { text: "부장", value: "15613" },
        { text: "차장", value: "15614" },
        { text: "과장", value: "15615" },
        { text: "대리", value: "15616" },
        { text: "주임", value: "15617" },
        { text: "사원", value: "15618" },
        { text: "원장", value: "15621" },
        { text: "부원장", value: "15622" },
        { text: "주임강사", value: "15623" },
        { text: "강사", value: "15624" },
        { text: "시간강사", value: "15625" }
      ];
      var cate_low_161 = [
        { text: "대표이사", value: "16111" },
        { text: "임원", value: "16112" },
        { text: "부장", value: "16113" },
        { text: "차장", value: "16114" },
        { text: "과장", value: "16115" },
        { text: "대리", value: "16116" },
        { text: "주임", value: "16117" },
        { text: "사원", value: "16118" },
        { text: "인턴", value: "16121" },
        { text: "간호과장 이상", value: "16131" },
        { text: "수간호사", value: "16132" },
        { text: "간호사", value: "16133" },
        { text: "간호조무사", value: "16134" },
        { text: "의료기사/물리치료사", value: "16141" }
      ];
      var cate_low_162 = [
        { text: "대표이사", value: "16211" },
        { text: "임원", value: "16212" },
        { text: "부장", value: "16213" },
        { text: "차장", value: "16214" },
        { text: "과장", value: "16215" },
        { text: "대리", value: "16216" },
        { text: "주임", value: "16217" },
        { text: "사원", value: "16218" },
        { text: "인턴", value: "16221" },
        { text: "간호과장 이상", value: "16231" },
        { text: "수간호사", value: "16232" },
        { text: "간호사", value: "16233" },
        { text: "간호조무사", value: "16234" },
        { text: "의료기사/물리치료사", value: "16241" }
      ];
      var cate_low_163 = [
        { text: "대표이사", value: "16311" },
        { text: "임원", value: "16312" },
        { text: "부장", value: "16313" },
        { text: "차장", value: "16314" },
        { text: "과장", value: "16315" },
        { text: "대리", value: "16316" },
        { text: "주임", value: "16317" },
        { text: "사원", value: "16318" },
        { text: "인턴", value: "16321" },
        { text: "간호과장 이상", value: "16331" },
        { text: "수간호사", value: "16332" },
        { text: "간호사", value: "16333" },
        { text: "간호조무사", value: "16334" },
        { text: "의료기사/물리치료사", value: "16341" }
      ];

      this.option_worker_position = eval("cate_low_" + value);
    },
    closePop: function(name) {
      var _this = this;
      // console.log(this.$refs.form.index);
      // var index = this.$refs.form.index;
      // console.log(this.$refs.form.jobList[index]);
      this.$modals.hide(name);
      if (this.korentrnm != "") {
        this.cateChgLow(this.occupational_detail);
        this.show2ndDepth();
        setTimeout(function() {
          _this.$refs.worker_position.open();
        }, 100);
      }
    },
    openPop: function(name) {
      this.$modals.show(name);
      if (this.korentrnm != "") {
        this.cateChgLow(this.occupational_detail);
        this.show2ndDepth();
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
