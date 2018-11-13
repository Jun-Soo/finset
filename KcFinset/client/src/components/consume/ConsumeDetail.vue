<template>
  <div>
    <section>
      <div class="tab">
        <div class="wrap">
          <a @click="clickTab" :class="{'on':curTab === '02'}">지출</a>
          <a @click="clickTab" :class="{'on':curTab === '01'}">수입</a>
        </div>
      </div>

      <div class="container pb90" v-if="curTab === '01'">
        <ul class="consume-detail">
          <li>
            <p class="key">입금</p>
            <p v-if="isNew">
              <select>
                <option>입출금계좌</option>
                <option>계좌조회</option>
                <option>카드</option>
                <option>현금</option>
              </select>
            </p>
            <p readonly v-else><input type="text" v-model="consumeVO.nm_card" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">금액</p>
            <p><input type="text" v-model="consumeVO.amt_in_out" :readonly="!isNew"><em>원</em></p>
          </li>
          <li>
            <p class="key">카테고리</p>
            <p><button class="btn-cate btn-search" @click="showCategory">{{consumeVO.nm_class}}</button></p>
          </li>
          <li>
            <p class="key">출처</p>
            <p><input type="text" :value="consumeVO.contents" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">날짜</p>
            <p v-if="isNew">
              <select>
                <option>데이트피커 넣어야대</option>
              </select>
            </p>
            <p v-else readonly><input type="text" :value="formatDateDot(consumeVO.dt_trd)" :readonly="!isNew"></p>
          </li>
          <li class="memo">
            <p class="key">메모</p>
            <p><input type="text"></p>
          </li>
        </ul>

        <div class="consume-comment">
          <a href="#">이번달에 <em>#번 5300원</em> 소비하였네요</a>
        </div>

        <div class="btn-wrap col2">
          <a href="#">삭제</a>
          <a href="#" class="btn-solid">저장</a>
        </div>
      </div>

      <div class="container pb90" v-if="curTab === '02'">
        <ul class="consume-detail">
          <li>
            <p class="key">결제수단</p>
            <p v-if="isNew">
              <select id="sel-means_consume" @change="selectMeans" v-model="consumeVO.means_consume">
                <option value="default" disabled="disabled">선택</option>
                <option value="04">입출금계좌</option>
                <option value="00">계좌조회</option>
                <option value="01">카드</option>
                <option value="02">현금</option>
              </select>
            </p>
            <p readonly v-else><input type="text" v-model="consumeVO.nm_card" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">금액</p>
            <p><input type="text" v-model="consumeVO.amt_in_out" :readonly="!isNew&&!isMine&&!isTransfer"><em>원</em></p>
          </li>
          <li>
            <p class="key">카테고리</p>
            <p>
              <button class="btn-cate btn-search" @click="showCategory" :disabled="!isMine" v-text="consumeVO.nm_class==null?'카테고리를 선택하세요':consumeVO.nm_class+'-'+consumeVO.nm_type"></button>
            </p>
          </li>
          <li>
            <p class="key">결제처</p>
            <p><input type="text" v-model="consumeVO.contents" :readonly="!isNew&&!isMine&&!isTransfer"></p>
          </li>
          <li>
            <p class="key">날짜</p>
            <p v-if="isNew">
              <datepicker v-model="consumeVO.dt_trd" :language="ko" :format="formatDateDot" class="div-date"></datepicker>
            </p>
            <p v-else readonly>
              <input type="text" :value="formatDateDot(consumeVO.dt_trd)" readonly="readonly">
            </p>
          </li>
          <li class="memo">
            <p class="key">메모</p>
            <p><input type="text" v-model="consumeVO.memo" :readonly="!isMine"></p>
          </li>
        </ul>

        <div v-if="!isNew" class="consume-comment">
          <a href="#">이번달에 <em>#번 5300원</em> 소비하였네요</a>
        </div>

        <div v-if="isNew" class="btn-wrap float">
          <a @click="clickSave" class="solid blue box">저장</a>
        </div>
        <div v-else class="btn-wrap col2">
          <a @click="clickDelete">삭제</a>
          <a @click="clickSave" class="btn-solid">저장</a>
        </div>
      </div>

    </section>

    <select v-if="isShowTrans" @change="transChange">
      <option v-for="(vo, index) in listTrans" :key="index" :value="index">{{vo.doc1}} - {{vo.an}} - {{vo.dt_trd}}</option>
    </select>

    <aside class="search-wrap" :class="{'on':isShowCategory}">
      <div class="top" @click="closeCategory">
        <button>카테고리</button>
        <a class="btn-setting"></a>
      </div>
      <div class="select-cate">
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
      <div class="action btn1">
        <a @click="clickConfirm" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

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
        dt_trd: new Date(),
        means_consume: "default"
      },
      isNew: true,
      isMine: true,
      isTransfer: false,
      isShowCategory: false,
      isShowTrans: false,
      listTrans: {},
      ko: ko
    };
  },
  components: {
    datepicker
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보수정";
  },
  created() {
    this.consumeVO.dt_trd = new Date();
    this.ko.ymd = true;
    this.isNew = (this.$route.query.seq_consume || "") == "";
    this.listPersonConsumeClassInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //데이터 포멧 부분
    formatNumber: function(number) {
      return Common.formatNumber(number);
    },
    formatDateDot: function(date) {
      return Common.formatDateDot(date);
    },
    formatNmCard: function(nm_card) {
      if ((nm_card || "") == "") {
        return "-";
      } else {
        return nm_card.substr(0, 25);
      }
    },
    //화면 컨트롤 부분
    clickTab: function() {
      if (!this.isNew) {
        return;
      }
      switch (this.curTab) {
        case "01":
          this.curTab = "02";
          break;
        case "02":
          this.curTab = "01";
          break;
      }
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
        this.$set(
          this.consumeVO,
          "nm_type",
          this.consumeCategory[this.orgClass].listCdType.filter(
            eachType => eachType.cd_type == this.orgType
          )[0].nm_type
        );
      } else {
      }
    },
    clickConfirm: function() {
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
      this.orgType = this.curClass;
      this.isShowCategory = false;
    },
    clickSave: function() {
      if (this.isNew) {
        this.createConsume();
      } else {
        this.modifyConsume();
      }
    },
    transChange: function(e) {
      if (this.curTab == "02") {
        var transVO = this.listTrans[e.target.value];

        this.consumeVO.means_consume = "04";
        this.consumeVO.cd_fc = transVO.cd_fc;
        this.consumeVO.nm_fc = transVO.nm_trd;
        this.consumeVO.cd_card = transVO.an;
        this.consumeVO.nm_card = transVO.nm_an;
        this.consumeVO.type_in_out = this.curTab;
        this.consumeVO.amt_in_out = Common.formatNumber(
          parseInt(transVO.amt_dep) + parseInt(transVO.amt_wdrl) + ""
        );
        var regexp = /^[0-9]*$/;
        this.consumeVO.contents = transVO.doc1;
        if (regexp.test(transVO.doc1) && (transVO.doc2 || "") != "") {
          this.consumeVO.contents = transVO.doc2;
        }
        this.consumeVO.dt_trd = new Date(Common.formatDateDot(transVO.dt_trd));
        this.consumeVO.tm_trd = transVO.tm_trd;

        this.isTransfer = true;

        var means_consume = document.getElementById("sel-means_consume");
        means_consume.innerHTML =
          "<option value='04'>(" +
          transVO.nm_fc +
          ")" +
          transVO.an +
          "</option>";
      } else {
      }
    },
    clickDelete: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("seq_consume", this.consumeVO.seq_consume);
      this.$http
        .post("/m/consume/deleteConsumeInfo.json", formData)
        .then(function(response) {
          _this.$router.go(-1);
        });
    },
    selectMeans: function(e) {
      if (e.target.value == "00") {
        this.listPersonTransDetail();
      }
    },
    showCategory: function() {
      this.isShowCategory = true;
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
          vo.amt_in_out = _this.formatNumber(vo.amt_in_out);

          _this.consumeVO = vo;

          _this.curTab = vo.type_in_out;
          if (_this.curTab == "02") {
            _this.curClass = vo.cd_class;
            _this.curType = vo.cd_type;
            _this.orgClass = vo.cd_class;
            _this.orgType = vo.cd_type;
          } else {
            _this.curClass = vo.cd_class;
            _this.orgClass = vo.cd_class;
          }
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
    createConsume: function() {
      this.consumeVO.type_in_out = this.curTab;

      var _this = this;
      var formData = new FormData();

      formData.append("type_in_out", this.consumeVO.type_in_out);
      formData.append("means_consume", this.consumeVO.means_consume);
      var target = document.getElementById("sel-means_consume");
      formData.append(
        "nm_card",
        this.consumeVO.nm_card == undefined
          ? target.options[target.selectedIndex].text
          : this.consumeVO.nm_card
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
      formData.append("cd_type", this.consumeVO.cd_type);
      formData.append("contents", this.consumeVO.contents);
      formData.append("memo", this.consumeVO.memo);
      formData.append("amt_in_out", this.consumeVO.amt_in_out);
      formData.append("mon_installment", "0");
      formData.append("mon_remaining", "0");
      formData.append("yn_pay_installmnet", "N");
      formData.append("yn_cancel", "N");
      formData.append("yn_delete", "N");
      formData.append("yn_auto", "N");
      formData.append("yn_budget_except", "N");

      this.$http
        .post("/m/consume/createConsumeInfo.json", formData)
        .then(function(response) {
          alert("ㅎㅎ");
        });
    },
    modifyConsume: function() {
      var _this = this;

      var formData = new FormData();
      formData.append("seq_consume", this.consumeVO.seq_consume);
      formData.append("cd_class", this.consumeVO.cd_class);
      formData.append("cd_type", this.consumeVO.cd_type);
      formData.append("memo", this.consumeVO.memo);

      this.$http
        .post("/m/consume/modifyConsumeInfo.json", formData)
        .then(function(response) {});
    },

    listPersonTransDetail: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonTransDetail.json")
        .then(function(response) {
          console.log(response.data.listPersonTransDetail);
          _this.isShowTrans = true;
          _this.listTrans = response.data.listPersonTransDetail;
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.vdp-datepicker__calendar {
  position: fixed;
  font-size: 13px;
  line-height: 40px;
}
.vdp-datepicker__calendar header {
  position: static;
}
.div-date {
  text-align: right;
}
</style>
