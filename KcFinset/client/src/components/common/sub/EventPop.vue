<template>
  <div id="modal-con" class="modal-con" style="display:block;">
    <div class="top">
      {{modalDate}}
      <button class="modal-close" @click="hideDetail"></button>
    </div>
    <div class="cal-con" style="max-height:80vh;overflow-y:auto">
      <div class="cal-top">
        <div>
          <em class="income">수입</em>
          <p>{{incomeTotal}} 원</p>
        </div>
        <div>
          <em class="debt">지출</em>
          <p>{{consumeTotal}} 원</p>
        </div>
        <div>
          <em class="loan">대출</em>
          <p>{{debtTotal}} 원</p>
        </div>
      </div>
      <div class="body">
        <div v-for="(incomeVO, index) in incomeList" :key="'income-'+index" class="list">
          <div>
            <em :class="settingList[shareList.findIndex(person => person.no_person === incomeVO.no_person)].color">
              {{formatSharePerson(shareList.filter(person => person.no_person === incomeVO.no_person)[0])}}
            </em>
            <em class="income">수입</em>
            <em class="text">{{incomeVO.contents}}</em>
          </div>
          <div class="won">{{Common.formatNumber(incomeVO.amt_in_out)}} 원</div>
        </div>

        <div v-for="(consumeVO, index) in consumeList" :key="'consume-'+index" class="list">
          <div>
            <em :class="settingList[shareList.findIndex(person => person.no_person === consumeVO.no_person)].color">
              {{formatSharePerson(shareList.filter(person => person.no_person === consumeVO.no_person)[0])}}
            </em>
            <em class="debt">지출</em>
            <em class="text">{{consumeVO.contents}}</em>
          </div>
          <div class="won">{{Common.formatNumber(consumeVO.amt_in_out)}} 원</div>
        </div>

        <div v-for="(debtVO, index) in debtList" :key="'debt-'+index" class="list">
          <div>
            <em :class="settingList[shareList.findIndex(person => person.no_person === debtVO.no_person)].color">
              {{formatSharePerson(shareList.filter(person => person.no_person === debtVO.no_person)[0])}}
            </em>
            <em class="loan">대출</em>
            <em class="text" v-text="(debtVO.nm_fc||'')==''?debtVO.creditor:debtVO.nm_fc"></em>
          </div>
          <div class="won">{{Common.formatNumber(debtVO.amt_repay)}} 원</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "CommonEventPop",
  props: {
    modalDate: {
      type: String,
      default: ""
    },
    incomeList: {
      type: Array,
      default: {}
    },
    consumeList: {
      type: Array,
      default: {}
    },
    debtList: {
      type: Array,
      default: {}
    },
    incomeTotal: {
      type: String,
      default: {}
    },
    consumeTotal: {
      type: String,
      default: {}
    },
    debtTotal: {
      type: String,
      default: {}
    },
    settingList: {
      type: Array,
      default: {}
    },
    shareList: {
      type: Array,
      default: []
    }
  },
  data() {
    return {
      Common: Common
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    var modal = document.getElementById("modal-con");

    if (modal) {
      var marginWidth = modal.offsetWidth / 2;
      modal.style.marginLeft = "-" + marginWidth + "px";
      var marginHeight = modal.offsetHeight / 2;
      modal.style.marginTop = "-" + marginHeight + "px";
    }

    var body = document.getElementsByTagName("body")[0];
    var newClassName = body.className + " not-scroll";
    body.className = newClassName;
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {
    var body = document.getElementsByTagName("body")[0];
    var newClassName = body.className
      .replace("not-scroll", "")
      .replace(/(^\s*)|(\s*$)/gi, "");
    body.className = newClassName;
  },
  methods: {
    // 이름 포맷 변경
    formatSharePerson: function(person) {
      if ((person || "") == "") {
        return "";
      } else if (
        (person.no_person || "") == "" ||
        (person.nm_person || "") == ""
      ) {
        return "";
      }

      var myNoPerson = this.$store.state.user.noPerson;
      if (person.no_person == myNoPerson) {
        return "나";
      } else {
        if (person.nm_person.length > 3) {
          return person.nm_person.substring(0, 3);
        } else {
          return person.nm_person;
        }
      }
    },
    hideDetail: function() {
      this.$emit("hideDetail");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
