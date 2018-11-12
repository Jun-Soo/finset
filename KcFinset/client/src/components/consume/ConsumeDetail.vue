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
            <p readonly v-else><input type="text" :value="formatNmCard(consumeVO.nm_card)" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">금액</p>
            <p><input type="text" :value="formatNumber(consumeVO.amt_in_out)" :readonly="!isNew"><em>원</em></p>
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
              <select>
                <option>신한카드</option>
              </select>
            </p>
            <p readonly v-else><input type="text" :value="formatNmCard(consumeVO.nm_card)" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">금액</p>
            <p><input type="text" :value="formatNumber(consumeVO.amt_in_out)" :readonly="!isNew"><em>원</em></p>
          </li>
          <li>
            <p class="key">카테고리</p>
            <p><button class="btn-cate btn-search" @click="showCategory" v-text="consumeVO.nm_class+'-'+consumeVO.nm_type"></button></p>
          </li>
          <li>
            <p class="key">결제처</p>
            <p><input type="text" :value="consumeVO.contents" :readonly="!isNew"></p>
          </li>
          <li>
            <p class="key">날짜</p>
            <p v-if="($route.query.seq_consume||'')==''">
              <!-- <select>
                <option>데이트피커 넣어야대</option>
              </select> -->
              <datepicker></datepicker>
            </p>
            <p v-else readonly>
              <input type="text" :value="formatDateDot(consumeVO.dt_trd)" :readonly="!isNew">
            </p>
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

    </section>

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
      consumeVO: [],
      isNew: true,
      isShowCategory: false
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
          _this.consumeVO = response.data.consumeVO;
          _this.consumeVO.dt_trd = new Date(
            Common.formatDate(_this.consumeVO.dt_trd)
          );
          _this.curTab = _this.consumeVO.type_in_out;
          if (_this.curTab == "02") {
            _this.curClass = _this.consumeVO.cd_class;
            _this.curType = _this.consumeVO.cd_type;
            _this.orgClass = _this.consumeVO.cd_class;
            _this.orgType = _this.consumeVO.cd_type;
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
    showCategory: function() {
      this.isShowCategory = true;
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
    },
    clickConfirm: function() {
      this.consumeVO.cd_class;
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

      this.isShowCategory = false;
    },
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
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
