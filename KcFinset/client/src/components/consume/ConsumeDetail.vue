<template>
  <div v-show="seen">
    <section>
      <div class="tab">
        <div class="wrap">
          <a @click="clickTab('02')" :class="{'on':curTab === '02'}">지출</a>
          <a @click="clickTab('01')" :class="{'on':curTab === '01'}">수입</a>
        </div>
      </div>
      <div class="container pb90">
        <ul class="consume-detail">
          <div>
            <li>
              <p class="key" v-text="curTab=='01'?'입금':'결제수단'"></p>
              <p>
                <multiselect v-validate="'required'" data-vv-name="수단" :disabled="!isNew||isTran" v-model="consumeVO.means_consume" :options="meansConsumeOption" :placeholder="meansConsumeText + ' 선택'" :title="'결제 수단 선택'" :onClose="selectMeans">
                </multiselect>
              </p>
            </li>
            <p class="warn" v-if="errors.has('수단')" v-text="meansConsumeText + ' 항목은 필수 정보입니다'"></p>
          </div>
          <div>
            <li>
              <p class="key">금액</p>
              <p>
                <money id="money" v-model="consumeVO.amt_in_out" :readonly="chkReadonly" v-validate="'required'" data-vv-name="금액" />
                <em>원</em>
              </p>
            </li>
            <p class="warn" v-if="errors.has('금액')">{{errors.first('금액')}}</p>
          </div>
          <div>
            <li>
              <p class="key">카테고리</p>
              <p>
                <input @click="showCategory" v-validate="'required'" data-vv-name="카테고리" :disabled="!isMine" type="button" :value="categoryText" class="btn-cate btn-search">
              </p>
            </li>
          </div>
          <p class="warn" v-if="errors.has('카테고리')">{{errors.first('카테고리')}}</p>
          <div>
            <li>
              <p class="key" v-text="curTab=='01'?'출처':'결제처'"></p>
              <p><input type="text" v-model="consumeVO.contents" :readonly="chkReadonly" v-validate="'required'" data-vv-name="출처"></p>
            </li>
            <p class="warn" v-if="errors.has('출처')" v-text="contentsText + ' 항목은 필수 정보입니다'"></p>
          </div>
          <div>
            <li>
              <p class="key">날짜</p>
              <p>
                <datepicker v-model="consumeVO.dt_trd" ref="datepicker" :language="ko" :format="Common.formatDateDot" class="div-date" :disabled="chkReadonly"></datepicker>
                <button class="cal" @click="openDatepicker"></button>
              </p>
            </li>
          </div>
          <div>
            <li class="memo">
              <p class="key">메모</p>
              <p><input type="text" v-model="consumeVO.memo" :readonly="!isMine"></p>
            </li>
          </div>
        </ul>

        <div v-if="!isNew" class="consume-comment">
          <a v-if="curTab=='01'" @click="goAnalyze">이번 달에 <em>{{nmBanner}}</em> 수입이<em> {{bannerData}}번</em> 있었습니다.</a>
          <a v-if="curTab=='02'" @click="goAnalyze">이번 달에 <em>{{nmBanner}}</em> 지출이<em> {{bannerData}}번</em> 있었습니다.</a>
        </div>

        <div v-if="isNew&&isMine&&chkNecessary" class="btn-wrap float">
          <a @click="clickSave" class="solid blue box">저장</a>
        </div>
        <div v-if="!isNew&&isMine&&chkNecessary" class="btn-wrap col2">
          <a @click="clickDelete">삭제</a>
          <a @click="clickSave" class="btn-solid">저장</a>
        </div>
      </div>

    </section>

    <aside class="search-wrap" :class="{'on':isShowCategory}">
      <div class="top" @click="closeCategory">
        <button>카테고리</button>
        <a class="btn-setting" @click="clickSetting"></a>
      </div>
      <div v-if="curTab == '02'" class="select-cate">
        <div class="cate-wrap">
          <ul>
            <li v-for="eachClass in consumeCategory" :key="eachClass.cd_class" :class="{'on':eachClass.cd_class==curClass}" @click="clickCategory(eachClass.cd_class,'class')">
              {{eachClass.nm_class}}
            </li>
          </ul>
        </div>
        <div class="cate-wrap" v-if="consumeCategory!={}">
          <ul v-if="consumeCategory[curClass] != undefined">
            <li v-for="(eachType, index) in consumeCategory[curClass]['listCdType']" :key="index" :class="{'on':eachType.cd_type==curType}" @click="clickCategory(eachType.cd_type,'type')">
              {{eachType.nm_type}}
            </li>
          </ul>
        </div>
      </div>
      <div v-if="curTab == '01'" class="select-cate one">
        <div class="cate-wrap">
          <ul>
            <li v-for="eachClass in consumeCategory" :key="eachClass.cd_class" :class="{'on':eachClass.cd_class==curClass}" @click="clickCategory(eachClass.cd_class,'class')">
              {{eachClass.nm_class}}
            </li>
          </ul>
        </div>
      </div>
      <div class="action btn1">
        <a @click="clickConfirm" class="solid">확인</a>
      </div>
    </aside>
    <vue-modal transitionName="fade" name="transModal" :onClose="closeTransModal">
      <div slot="body" class="container pop-wrap">
        <div class="pop-top">
          <p class="title" v-text="curTab=='01'?'입금내역':'출금내역'"></p>
          <a class="btn-close" @click="closeTransModal"></a>
        </div>
        <div v-if="(listTrans||'')==''">
          <div class="nodata" v-text="(curTab=='01'?'입금':'출금')+'내역이 없습니다'"></div>
        </div>
        <div v-else class="nobox-list">
          <div v-for="(subList, index) in listTrans" :key="index">
            <p class="date">{{Common.formatDateDot(listTrans[index][0].dt_trd,"mmdd")}}</p>
            <div v-for="(vo, subIndex) in subList" :key="subIndex" @click="selectTrans(index, subIndex)" class="item" :class="{'disabled':listRegSeqTran.filter(seq => seq == vo.seq_tran).length > 0}">
              <div class="flex">
                <p>{{vo.contents}}</p>
                <p><em class="number" :class="vo.type_in_out == '01'? 'blue':'red'">{{vo.amt}}</em>원</p>
              </div>
              <div class="flex">
                <p class="key">{{vo.nm_fc}}</p>
                <p class="key">{{vo.an}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </vue-modal>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";
import korean from "vee-validate/dist/locale/ko.js";

export default {
  name: "ConsumeConsumeDetail",
  data() {
    return {
      seen: false, // 화면 표출 여부
      Common: Common, // 공통
      curTab: "02", // 탭 코드(01: 수입, 02: 지출)
      consumeCategory: {}, // 분류, 항목
      orgClass: "", // 기존 분류
      orgType: "", // 기존 항목
      curClass: "", // 현재 컨트롤 되는 분류
      curType: "", // 현재 컨트롤 되는 항목
      // 소비지출 데이터
      consumeVO: {
        dt_trd: new Date()
      },
      isNew: true, // 신규 여부(아니면 수정)
      isMine: true, // 본인 데이터 여부(아니면 공유된 사용자)
      isPersonRegist: true, // 회원 등록 여부(현금으로 등록한 것만)
      isTran: false, // 자산에서 넘어온 데이터 여부
      isShowCategory: false, // 하단 카테고리 팝업 표시 여부
      isShowTrans: false, // 입출금내역 팝업 표시 여부
      listRegSeqTran: [], // 기등록된 입출금 내역 리스트
      listTrans: {}, // 입출금 내역 데이터
      ko: ko, // 데이트피커 한글
      // dt_trans: "", //
      // 소비 수단 옵션(멀티셀렉트)
      meansConsumeOption: [
        { text: "현금", value: "02", means_consume: "02" },
        { text: "입출금계좌", value: "04", means_consume: "04" }
      ],
      isAuto: false, // 자동 등록 여부
      nmBanner: "", // 배너명
      bannerData: "" // 이번 달에 contents에 해당하는 소비 지출 내역 수
    };
  },
  components: {
    datepicker
  },
  computed: {
    // 카테고리에 들어갈 텍스트
    categoryText: function() {
      if ((this.consumeVO.cd_class || "") == "") {
        return "카테고리를 선택하세요";
      } else {
        if (this.curTab == "02") {
          return this.consumeVO.nm_class + "-" + this.consumeVO.nm_type;
        } else {
          return this.consumeVO.nm_class;
        }
      }
    },
    // 금액, 결제처(출처), 날짜를 readonly 로 만들어 줄 조건
    chkReadonly: function() {
      if (!this.isMine) {
        return true;
      } else if (this.isNew && this.isPersonRegist) {
        return false;
      } else if (!this.isNew && this.isPersonRegist) {
        return false;
      } else {
        return true;
      }
    },
    // 탭 코드에 따라 다른 텍스트 출력
    meansConsumeText: function() {
      if (this.curTab == "01") {
        return "입금";
      } else {
        return "결제수단";
      }
    },
    // 탭 코드에 따라 다른 텍스트 출력
    contentsText: function() {
      if (this.curTab == "01") {
        return "출처";
      } else {
        return "결제처";
      }
    },
    // 하단 버튼 표시 여부
    chkNecessary: function() {
      if (
        (this.consumeVO.means_consume || "") != "" &&
        (this.consumeVO.amt_in_out || "") != "" &&
        (this.consumeVO.cd_class || "") != "" &&
        (this.consumeVO.contents || "") != ""
      ) {
        return true;
      } else {
        return false;
      }
    }
  },
  watch: {
    // 입출금내역 모달 표출 여부
    isShowTrans: function(key) {
      if (key == true) {
        this.$modals.show("transModal");
      } else {
        this.$modals.hide("transModal");
      }
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보수정";
  },
  created() {
    // ko에 ymd 를 true 로 바꿔주어야 yyyymmdd 의 형식으로 나옴
    this.ko.ymd = true;
    this.isNew = (this.$route.query.seq_consume || "") == "";
    this.isTran = (this.$route.query.seq_tran || "") != "";
    if (this.isTran) {
      this.curTab = this.$route.query.type_in_out;
      this.isMine = true;
      this.isPersonRegist = false;
    } else if (this.isNew) {
      this.isPersonRegist = true;
      this.curTab = "02";
      this.isMine = true;
    } else {
      this.curTab = this.$route.query.type_in_out;
      this.isMine =
        this.$route.query.isMine == "true" || this.$route.query.isMine == true;
      this.isPersonRegist =
        this.$route.query.isPersonRegist == "true" ||
        this.$route.query.isPersonRegist == true;
    }
    this.setDefault();
  },
  beforeMount() {},
  mounted() {
    Common.datepickerInit("div-date", this);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------데이터 포멧---------------------
    formatNmCard: function(nm_card) {
      if ((nm_card || "") == "") {
        return "-";
      } else {
        return nm_card.substr(0, 25);
      }
    },
    // ---------------------//데이터 포멧---------------------
    // ---------------------화면 컨트롤---------------------
    // 탭 클릭
    clickTab: function(key) {
      if (!this.isNew || !this.isPersonRegist || this.curTab == key) {
        return;
      }
      this.curTab = key;
      this.setDefault();
    },
    // 카테고리 팝업창에서 카테고리 선택
    clickCategory: function(code, key) {
      switch (key) {
        case "class":
          this.curClass = code;
          this.curType = "";
          break;
        case "type":
          this.curType = code;
          break;
        default:
          break;
      }
    },
    // 카테고리 팝업창을 확인 버튼이 아니라 그냥 닫을 시
    closeCategory: function() {
      if (!this.isNew) {
        this.isShowCategory = false;
        this.curClass = this.orgClass;
        this.curType = this.orgType;
        this.$set(
          this.consumeVO,
          "nm_class",
          this.consumeCategory[this.orgClass].nm_class
        );
        if (this.curTab == "02") {
          this.$set(
            this.consumeVO,
            "nm_type",
            this.consumeCategory[this.orgClass].listCdType.filter(
              eachType => eachType.cd_type == this.orgType
            )[0].nm_type
          );
        }
      } else {
        this.isShowCategory = false;
        this.curClass = this.orgClass;
        this.curType = this.orgType;
        if (this.orgClass == "") {
          return;
        }
        this.$set(
          this.consumeVO,
          "nm_class",
          this.consumeCategory[this.orgClass].nm_class
        );
        if (this.curTab == "02") {
          this.$set(
            this.consumeVO,
            "nm_type",
            this.consumeCategory[this.orgClass].listCdType.filter(
              eachType => eachType.cd_type == this.orgType
            )[0].nm_type
          );
        }
      }
    },
    // 카테고리 팝업 창 확인 버튼 클릭 시
    clickConfirm: function() {
      if (this.curTab == "02") {
        this.$set(this.consumeVO, "cd_class", this.curClass);
        this.$set(
          this.consumeVO,
          "nm_class",
          this.consumeCategory[this.curClass].nm_class
        );
        this.$set(this.consumeVO, "cd_type", this.curType);
        this.$set(
          this.consumeVO,
          "nm_type",
          this.consumeCategory[this.curClass].listCdType.filter(
            eachType => eachType.cd_type == this.curType
          )[0].nm_type
        );
        this.orgClass = this.curClass;
        this.orgType = this.curType;
        this.isShowCategory = false;
      } else {
        this.$set(this.consumeVO, "cd_class", this.curClass);
        this.$set(
          this.consumeVO,
          "nm_class",
          this.consumeCategory[this.curClass].nm_class
        );
        this.orgClass = this.curClass;
        this.isShowCategory = false;
      }
    },
    // 저장 버튼 클릭 시
    clickSave: function() {
      this.$validator.validateAll().then(res => {
        if (res) {
          if (this.isNew) {
            this.createConsume();
          } else {
            this.modifyConsume();
          }
        } else {
          this.$toast.center("정보가 아직 다 입력되지 않았습니다");
        }
      });
    },
    // 입출금 내역 선택 시
    selectTrans: function(index, subIndex) {
      // 기등록된 입출금 내역 확인
      var _this = this;
      if (
        this.listRegSeqTran.filter(
          seq => seq == this.listTrans[index][subIndex].seq_tran
        ).length > 0
      ) {
        return;
      }
      var transText =
        this.curTab == "01"
          ? "해당 항목과 동일한 입금을 수입으로 등록할까요?"
          : "해당 항목과 동일한 출금을 지출로 등록할까요?";
      this.$dialogs.confirm(transText, Constant.options).then(res => {
        if (res.ok) {
          _this.isAuto = true;
        } else {
          _this.isAuto = false;
        }
      });
      var transVO = this.listTrans[index][subIndex];
      this.makeConsumeVOByTran(transVO);
    },
    // 삭제 버튼 클릭 시
    clickDelete: function() {
      var _this = this;
      var text = "정말로 삭제하시겠습니까?";
      if (this.consumeVO.yn_pay_installment == "Y") {
        text += "\n\n동일 할부 내역도\n같이 삭제됩니다.";
      }
      this.$dialogs.confirm(text, Constant.options).then(res => {
        if (res.ok) {
          _this.deleteConsume();
        }
      });
    },
    // 결제 수단(입금) 선택 시
    selectMeans: function(meansOption) {
      this.setDefault();
      if (meansOption.means_consume != "02") {
        this.listPersonTransDetail();
      } else {
        this.isShowTrans = false;
        this.isPersonRegist = true;
      }
    },
    // 팝업창 띄우기
    showCategory: function() {
      this.isShowCategory = true;
    },
    // 입출금내역 모달 닫기
    closeTransModal: function() {
      this.setDefault();
      this.isShowTrans = false;
    },
    // 날짜 데이트피커 열기
    openDatepicker: function() {
      this.$refs.datepicker.showCalendar();
    },
    // 카테고리 팝업창 내부 세팅 버튼 킬릭 시
    clickSetting: function(event) {
      event.stopPropagation();
      if (this.curTab == "01") {
        this.$router.push("/consume/incomeClass");
      } else {
        this.$router.push("/consume/consumeClass");
      }
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 소비지출 세부내역 조회
    getConsumeInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/getConsumeInfo.json", {
          params: {
            seq_consume: this.$route.query.seq_consume,
            no_person: this.$route.query.no_person
          }
        })
        .then(function(response) {
          var consumeVO = response.data.consumeVO;
          _this.allocateConsume(consumeVO);
        });
    },
    // 소비 분류, 항목 조회
    listPersonConsumeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonConsumeClassInfo.json", {
          params: {
            no_person: this.$route.query.no_person
          }
        })
        .then(function(response) {
          var list = response.data.listPersonConsumeClassInfo;
          var listCdClass = new Object();
          for (var eachClass of list) {
            var cd_class = "";
            var nm_class = "";
            var listCdType = new Array();
            for (var idx in eachClass) {
              if (idx == 0) {
                cd_class = eachClass[idx].cd_class;
                nm_class = eachClass[idx].nm_class;
              }
              listCdType.push({
                cd_type: eachClass[idx].cd_type,
                nm_type: eachClass[idx].nm_type
              });
            }
            listCdClass[cd_class] = {
              cd_class: cd_class,
              nm_class: nm_class,
              listCdType: listCdType
            };
          }
          _this.consumeCategory = listCdClass;
          if (_this.isTran) {
            _this.chkTrans();
          } else if (!_this.isNew) {
            _this.getConsumeInfo();
          } else {
            _this.seen = true;
          }
        });
    },
    // 수입 분류 조회
    listPersonIncomeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonIncomeClassInfo.json", {
          params: {
            no_person: this.$route.query.no_person
          }
        })
        .then(function(response) {
          var list = response.data.listPersonIncomeClassInfo;
          var listCdClass = new Object();
          for (var eachClass of list) {
            listCdClass[eachClass.cd_class] = eachClass;
          }
          _this.consumeCategory = listCdClass;
          if (_this.isTran) {
            _this.chkTrans();
          } else if (!_this.isNew) {
            _this.getConsumeInfo();
          } else {
            _this.seen = true;
          }
        });
    },
    // 소비지출 데이터 생성
    createConsume: function() {
      this.consumeVO.type_in_out = this.curTab;

      var _this = this;
      var formData = new FormData();

      formData.append("type_in_out", this.consumeVO.type_in_out);
      var means_consume = this.consumeVO.means_consume.means_consume;

      formData.append("means_consume", means_consume);
      if (means_consume != "02") {
        formData.append("cd_fc", this.consumeVO.cd_fc);
      }
      formData.append(
        "nm_card",
        means_consume == "02" ? "현금" : this.consumeVO.nm_card
      );
      if (means_consume != "02") {
        formData.append("no_card", this.consumeVO.no_card);
      }
      formData.append(
        "dt_trd",
        Common.formatDate(this.consumeVO.dt_trd).replace(/[-]/g, "")
      );
      formData.append(
        "tm_trd",
        this.consumeVO.tm_trd == undefined ? "000000" : this.consumeVO.tm_trd
      );
      formData.append("cd_class", this.consumeVO.cd_class);
      formData.append(
        "cd_type",
        this.curTab == "02" ? this.consumeVO.cd_type : "000"
      );
      formData.append("contents", this.consumeVO.contents);
      formData.append(
        "memo",
        this.consumeVO.memo == undefined ? "" : this.consumeVO.memo
      );
      formData.append(
        "amt_in_out",
        this.consumeVO.amt_in_out.replace(/[,]/g, "")
      );
      formData.append("mon_installment", "0");
      formData.append("mon_remaining", "0");
      formData.append("yn_pay_installment", "N");
      formData.append("yn_cancel", "N");
      formData.append("yn_delete", "N");
      this.isAuto
        ? formData.append("yn_auto", "Y")
        : formData.append("yn_auto", "N");

      formData.append("yn_budget_except", "N");
      this.isPersonRegist
        ? formData.append("yn_person_regist", "Y")
        : formData.append("yn_person_regist", "N");
      if (this.isTran) {
        formData.append("seq_tran", this.consumeVO.seq_tran);
      }
      this.$http
        .post("/m/consume/createConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
    // 소비 지출 데이터 수정
    modifyConsume: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("seq_consume", this.consumeVO.seq_consume);
      formData.append("nm_card", this.consumeVO.nm_card);
      formData.append(
        "dt_trd",
        Common.formatDate(this.consumeVO.dt_trd).replace(/[-]/g, "")
      );
      formData.append("cd_class", this.consumeVO.cd_class);
      formData.append("cd_type", this.consumeVO.cd_type);
      formData.append("contents", this.consumeVO.contents);
      formData.append(
        "amt_in_out",
        this.consumeVO.amt_in_out.replace(/[,]/g, "")
      );
      formData.append(
        "memo",
        this.consumeVO.memo == undefined ? "" : this.consumeVO.memo
      );
      formData.append("no_approval", this.consumeVO.no_approval);
      formData.append("yn_pay_installment", this.consumeVO.yn_pay_installment);
      this.isPersonRegist
        ? formData.append("yn_person_regist", "Y")
        : formData.append("yn_person_regist", "N");

      this.$http
        .post("/m/consume/modifyConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
    // 입출금계좌 조회
    listPersonTransDetail: function() {
      //우선적으로 미리 등록된 입출금내역 시퀀스를 가져와야 한다
      var _this = this;
      this.$http
        .get("/m/consume/listRegisteredSeqTran.json", {
          params: {
            type_in_out: _this.curTab
          }
        })
        .then(function(response) {
          _this.listRegSeqTran = response.data.listRegisteredSeqTran;
          //등록된 입출금내역 시퀀스를 가져온 후 실제 입출금 내역 조회
          _this.$http
            .get("/m/consume/listPersonTransDetail.json", {
              params: {
                type_in_out: _this.curTab
              }
            })
            .then(function(response) {
              var list = response.data.listPersonTransDetail;

              for (var idx in list) {
                for (var subIdx in list[idx]) {
                  if (list[idx][subIdx].amt_dep == "0") {
                    list[idx][subIdx].type_in_out = "02";
                    list[idx][subIdx].amt = Common.formatNumber(
                      list[idx][subIdx].amt_wdrl,
                      true,
                      false
                    );
                  } else if (list[idx][subIdx].amt_wdrl == "0") {
                    list[idx][subIdx].type_in_out = "01";
                    list[idx][subIdx].amt = Common.formatNumber(
                      list[idx][subIdx].amt_dep,
                      false,
                      true
                    );
                  }
                  list[idx][subIdx].contents = _this.getTransText(
                    list[idx][subIdx]
                  );
                }
              }
              if ((list || "") == "" || list[0].length == 0) {
                _this.listTrans = [];
              } else {
                _this.listTrans = list;
              }
              _this.isShowTrans = true;
            });
        });
    },
    // 소비 지출 삭제처리
    deleteConsume: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("seq_consume", this.consumeVO.seq_consume);
      formData.append("no_approval", this.consumeVO.no_approval);
      formData.append("yn_pay_installment", this.consumeVO.yn_pay_installment);
      this.$http
        .post("/m/consume/deleteConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
    // 배너 데이터 조회
    getBannerData: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("type_in_out", this.curTab);
      formData.append("cd_fc", this.consumeVO.cd_fc);
      formData.append("contents", this.consumeVO.contents);

      this.$http
        .post("/m/consume/getBannerData.json", formData)
        .then(function(response) {
          var data = response.data.bannerData;
          _this.bannerData = data;
          _this.seen = true;
        });
    },
    // seq_tran을 통해 소비지출 데이터 조회
    chkTrans: function() {
      var seq_tran = this.$route.query.seq_tran;
      var _this = this;
      this.$http
        .get("/m/consume/getConsumeInfoWithSeqTran.json", {
          params: {
            seq_tran: seq_tran
          }
        })
        .then(function(response) {
          var consumeVO = response.data.consumeVO;
          if (consumeVO == null) {
            _this.getPersonTransDetail();
          } else {
            _this.isNew = false;
            _this.isAuto = true;
            _this.allocateConsume(consumeVO);
          }
        });
    },
    // chkTrans에서 조회된 데이터가 없을 때 신규 생성을 위해 입출금내역 조회
    getPersonTransDetail: function() {
      var _this = this;
      this.$http
        .get("/m/consume/getPersonTransDetail.json", {
          params: {
            seq_tran: _this.$route.query.seq_tran
          }
        })
        .then(function(response) {
          var transVO = response.data.transVO;
          _this.makeConsumeVOByTran(transVO);
          _this.seen = true;
        });
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 화면 데이터 초기화
    setDefault: function() {
      this.consumeVO = {
        dt_trd: new Date(),
        means_consume: null
      };
      this.curClass = "";
      this.curType = "";
      this.listRegSeqTran = [];
      this.listTrans = {};
      if (this.curTab == "01") {
        this.listPersonIncomeClassInfo();
      } else {
        this.listPersonConsumeClassInfo();
      }
    },
    // 입출금내역 중 표기 될 텍스트 선택
    getTransText: function(vo) {
      var regexp = /^[0-9]*$/;
      if (regexp.test(vo.doc1) || (vo.doc1 || "") == "") {
        if (regexp.test(vo.doc2) || (vo.doc2 || "") == "") {
          return vo.nm_an;
        } else {
          return vo.doc2;
        }
      } else {
        return vo.doc1;
      }
    },
    // 이력 상세 이동
    goAnalyze: function() {
      this.$router.push({
        path: "/consume/analyze",
        query: {
          type_in_out: this.curTab,
          contents: this.consumeVO.contents,
          cd_fc: this.consumeVO.cd_fc
        }
      });
    },
    // 소비지출 데이터를 화면에 표출되는 형식에 맞게 할당
    allocateConsume: function(consumeVO) {
      consumeVO.dt_trd = new Date(Common.formatDateDot(consumeVO.dt_trd));
      this.meansConsumeOption = [];
      this.meansConsumeOption.push({
        text: consumeVO.nm_card,
        value: consumeVO.no_card
      });
      consumeVO.means_consume = {
        text: consumeVO.nm_card,
        value: consumeVO.no_card
      };
      this.consumeVO = consumeVO;

      if (this.curTab == "02") {
        this.curClass = consumeVO.cd_class;
        this.curType = consumeVO.cd_type;
        this.orgClass = consumeVO.cd_class;
        this.orgType = consumeVO.cd_type;
      } else {
        this.curClass = consumeVO.cd_class;
        this.orgClass = consumeVO.cd_class;
      }
      this.nmBanner = consumeVO.contents;
      this.getBannerData();
    },
    // 입출금 내역을 소비지출 데이터에 맞게 변경
    makeConsumeVOByTran: function(transVO) {
      this.meansConsumeOption = [
        {
          text: transVO.nm_an,
          value: transVO.an,
          means_consume: "04"
        }
      ];

      this.consumeVO.means_consume = {
        text: transVO.nm_an,
        value: transVO.an,
        means_consume: "04"
      };
      this.consumeVO.cd_fc = transVO.cd_fc;
      this.consumeVO.nm_fc = transVO.nm_trd;
      this.consumeVO.no_card = transVO.an;
      this.consumeVO.nm_card = transVO.nm_an;
      this.consumeVO.type_in_out = this.curTab;
      this.consumeVO.amt_in_out = Common.formatNumber(
        parseInt(transVO.amt_dep) + parseInt(transVO.amt_wdrl) + ""
      );
      this.consumeVO.contents = this.getTransText(transVO);
      this.consumeVO.dt_trd = new Date(Common.formatDateDot(transVO.dt_trd));
      this.consumeVO.tm_trd = transVO.tm_trd;
      this.consumeVO.seq_tran = transVO.seq_tran;

      this.isNew = true;
      this.isTran = true;
      this.isPersonRegist = false;
      this.isShowTrans = false;
    }
    // ---------------------//기타---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.item.disabled {
  background: #ddd;
}
</style>
