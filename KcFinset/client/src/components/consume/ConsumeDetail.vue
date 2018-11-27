<template>
  <div>
    <section>
      <div class="tab">
        <div class="wrap">
          <a @click="clickTab('02')" :class="{'on':curTab === '02'}">지출</a>
          <a @click="clickTab('01')" :class="{'on':curTab === '01'}">수입</a>
        </div>
      </div>
      <div class="container pb90">
        <ul class="consume-detail">
          <li>
            <p class="key" v-text="curTab=='01'?'입금':'결제수단'" style="width:12%"></p>
            <p style="width:88%">
              <multiselect v-validate="'required'" data-vv-name='수단' :disabled="!isNew" v-model="consumeVO.means_consume" ref="selMeansConsume" label="text" :show-labels="false" :options="meansConsumeOption" placeholder="결제수단" :searchable="false" :allow-empty="false" @select="selectMeans">
              </multiselect>
            </p>
          </li>
          <li>
            <p class="key">금액</p>
            <p>
              <input type="text" v-model="consumeVO.amt_in_out" :readonly="chkReadonly" v-validate="'required'" data-vv-name="금액"><em>원</em>
              <!-- <p class="warn" v-if="errors.has('이메일')">{{errors.first('금액')}}</p> -->
            </p>
          </li>
          <li>
            <p class="key">카테고리</p>
            <p>
              <!-- <button class="btn-cate btn-search" @click="showCategory" :disabled="!isMine" v-text="categoryText" v-validate="'required'" data-vv-name="카테고리"></button> -->
              <button class="btn-cate btn-search" @click="showCategory" :disabled="!isMine" v-text="categoryText"></button>
            </p>
          </li>
          <li>
            <p class="key" v-text="curTab=='01'?'출처':'결제처'"></p>
            <p><input type="text" v-model="consumeVO.contents" :readonly="chkReadonly" v-validate="'required'" data-vv-name="출처"></p>
          </li>
          <li>
            <p class="key">날짜</p>
            <!-- <p v-if="isNew&&!isAuto"> -->
            <p>
              <datepicker v-model="consumeVO.dt_trd" :language="ko" :format="formatDateDot" class="div-date" :disabled="chkReadonly"></datepicker>
            </p>
            <!-- <p v-if="!isNew||isAuto" readonly>
              <input type="text" :value="formatDateDot(consumeVO.dt_trd)" readonly="readonly">
            </p> -->
          </li>
          <li class="memo">
            <p class="key">메모</p>
            <p><input type="text" v-model="consumeVO.memo" :readonly="!isMine"></p>
          </li>
        </ul>

        <div v-if="isNew&&isMine" class="btn-wrap float">
          <a @click="clickSave" class="solid blue box">저장</a>
        </div>
        <div v-if="!isNew&&isMine" class="btn-wrap col2">
          <a @click="clickDelete">삭제</a>
          <a @click="clickSave" class="btn-solid">저장</a>
        </div>
      </div>

    </section>

    <aside class="search-wrap" :class="{'on':isShowCategory}">
      <div class="top" @click="closeCategory">
        <button>카테고리</button>
        <a class="btn-setting"></a>
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
    <vue-modal transitionName="fade" name="transModal">
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
            <p class="date">{{formatDateDot(listTrans[index][0].dt_trd,"mmdd")}}</p>
            <div v-for="(vo, subIndex) in subList" :key="subIndex" @click="selectTrans(index, subIndex)" class="item">
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
      curTab: "02",
      consumeCategory: {},
      orgClass: "",
      orgType: "",
      curClass: "",
      curType: "",
      consumeVO: {
        dt_trd: new Date()
      },
      bannerData: "",
      isNew: true,
      isMine: true,
      isAuto: false,
      isShowBanner: false,
      isShowCategory: false,
      isShowTrans: false,
      listTrans: {},
      ko: ko,
      dt_trans: "",
      meansConsumeOption: []
    };
  },
  components: {
    datepicker
  },
  computed: {
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
    chkReadonly: function() {
      if (!this.isMine) {
        return true;
      } else if (this.isNew && !this.isAuto) {
        return false;
      } else if (!this.isNew && !this.isAuto) {
        return false;
      } else {
        return true;
      }
    }
  },
  watch: {
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
    this.consumeVO.dt_trd = new Date();
    this.ko.ymd = true;
    this.isNew = (this.$route.query.seq_consume || "") == "";
    if (this.isNew) {
      this.isAuto = false;
      this.curTab = "02";
      this.isMine = true;
    } else {
      this.curTab = this.$route.query.type_in_out;
      this.isMine =
        this.$route.query.isMine == "true" || this.$route.query.isMine == true;
      this.isAuto =
        this.$route.query.isAuto == "true" || this.$route.query.isAuto == true;
    }
    this.setDefault();
    Common.datepickerInit("div-date");
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {
    Common.datepickerInit("div-date");
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //데이터 포멧 부분
    formatNumber: function(number) {
      return Common.formatNumber(number);
    },
    formatDateDot: function(date, pattern) {
      return Common.formatDateDot(date, pattern);
    },
    formatNmCard: function(nm_card) {
      if ((nm_card || "") == "") {
        return "-";
      } else {
        return nm_card.substr(0, 25);
      }
    },
    //화면 컨트롤 부분
    clickTab: function(key) {
      if (!this.isNew || this.isAuto || this.curTab == key) {
        return;
      }
      this.curTab = key;
      this.setDefault();
    },
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
    selectTrans: function(index, subIndex) {
      var transVO = this.listTrans[index][subIndex];

      // this.consumeVO.means_consume = "04";
      this.consumeVO.cd_fc = transVO.cd_fc;
      this.consumeVO.nm_fc = transVO.nm_trd;
      this.consumeVO.no_card = transVO.an;
      this.consumeVO.nm_card = transVO.nm_an;
      this.consumeVO.type_in_out = this.curTab;
      this.consumeVO.amt_in_out = Common.formatNumber(
        parseInt(transVO.amt_dep) + parseInt(transVO.amt_wdrl) + ""
      );
      var regexp = /^[0-9]*$/;
      this.consumeVO.contents = this.getTransText(transVO);
      this.consumeVO.dt_trd = new Date(Common.formatDateDot(transVO.dt_trd));
      this.consumeVO.tm_trd = transVO.tm_trd;

      this.isAuto = true;
      this.isShowTrans = false;
    },
    clickDelete: function() {
      var _this = this;
      this.$dialogs
        .confirm("정말로 삭제하시겠습니까?", Constant.options)
        .then(res => {
          // console.log(res); // {ok: true|false|undefined}
          if (res.ok) {
            _this.deleteConsume();
          } else {
            // this.$dialogs.alert("취소를 선택했습니다.", Constant.options);
          }
        });
    },
    // closeModal: function() {
    //   this.$modals.hide("confirmModal");
    // },
    selectMeans: function(meansOption) {
      this.setDefault();
      if (meansOption.means_consume != "02") {
        this.listPersonTransDetail(meansOption.value);
      } else {
        this.isShowTrans = false;
        this.isAuto = false;
      }
    },
    showCategory: function() {
      this.isShowCategory = true;
    },
    closeTransModal: function() {
      this.consumeVO.means_consume = "04";
      this.isShowTrans = false;
    },
    //데이터 이동부분
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
          var vo = response.data.consumeVO;
          vo.dt_trd = new Date(Common.formatDateDot(vo.dt_trd));
          vo.nm_card = _this.formatNmCard(vo.nm_card);
          vo.amt_in_out = _this.chkReadonly
            ? _this.formatNumber(vo.amt_in_out)
            : vo.amt_in_out;
          _this.meansConsumeOption = [];
          _this.meansConsumeOption.push({
            text: vo.nm_card,
            value: vo.no_card
          });
          vo.means_consume = { text: vo.nm_card, value: vo.no_card };
          _this.consumeVO = vo;

          if (_this.curTab == "02") {
            _this.curClass = vo.cd_class;
            _this.curType = vo.cd_type;
            _this.orgClass = vo.cd_class;
            _this.orgType = vo.cd_type;
          } else {
            _this.curClass = vo.cd_class;
            _this.orgClass = vo.cd_class;
          }

          _this.getBannerData();
        });
    },
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
          if (!_this.isNew) {
            _this.getConsumeInfo();
          }
        });
    },
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
          if (!_this.isNew) {
            _this.getConsumeInfo();
          }
        });
    },
    createConsume: function() {
      this.consumeVO.type_in_out = this.curTab;

      var _this = this;
      var formData = new FormData();

      formData.append("type_in_out", this.consumeVO.type_in_out);
      var means_consume = this.consumeVO.means_consume.means_consume;

      formData.append("means_consume", means_consume);
      formData.append(
        "cd_fc",
        means_consume == "02" ? null : this.consumeVO.cd_fc
      );
      formData.append(
        "nm_card",
        means_consume == "02" ? "현금" : this.consumeVO.nm_card
      );
      formData.append(
        "no_card",
        means_consume == "02" ? null : this.consumeVO.no_card
      );
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
      if (this.isAuto) {
        formData.append("yn_auto", "Y");
      } else {
        formData.append("yn_auto", "N");
      }
      formData.append("yn_budget_except", "N");

      this.$http
        .post("/m/consume/createConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
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
      formData.append("yn_auto", this.isAuto == true ? "Y" : "N");

      this.$http
        .post("/m/consume/modifyConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
    listPersonTransDetail: function(no_card) {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonTransDetail.json", {
          params: {
            no_card: no_card,
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
    },
    deleteConsume: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("seq_consume", this.consumeVO.seq_consume);
      this.$http
        .post("/m/consume/deleteConsumeInfo.json", formData)
        .then(function(response) {
          // _this.closeModal();
          _this.$router.go(-1);
        });
    },
    getBannerData: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("type_in_out", _this.curTab);
      formData.append("nm_card", _this.consumeVO.nm_card);
      formData.append("contents", _this.consumeVO.contents);

      this.$http
        .post("/m/consume/getBannerData.json", formData)
        .then(function(response) {
          var data = response.data.bannerData;
          if (data == 0) {
            _this.isShowBanner = false;
          } else {
            _this.isShowBanner = true;
          }
          _this.bannerData = data;
        });
    },
    listMeansConsume: function() {
      var _this = this;

      this.$http
        .get("/m/consume/listMeansConsume.json")
        .then(function(response) {
          var list = response.data.listMeansConsume;
          _this.meansConsumeOption = [];
          for (var idx in list) {
            _this.meansConsumeOption.push({
              means_consume: list[idx].means_consume,
              text:
                list[idx].means_consume == "02" ? "현금" : list[idx].nm_card,
              value: list[idx].no_card,
              cd_fc: list[idx].cd_fc
            });
          }
        });
    },
    //기타 편의를 위해 함수로 구현한 부분
    setDefault: function() {
      this.consumeVO = {
        dt_trd: new Date(),
        means_consume: "default"
      };
      this.curClass = "";
      this.curType = "";
      this.dt_trans = "";
      if (this.curTab == "01") {
        this.listPersonIncomeClassInfo();
      } else {
        this.listPersonConsumeClassInfo();
      }
      if (this.isNew) {
        this.listMeansConsume();
      }
    },
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
