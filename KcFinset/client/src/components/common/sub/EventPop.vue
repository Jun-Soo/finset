<template>
  <div id="modal-con" class="modal-con" style="display:block;">
    <div class="top">
      {{modalDate}}
      <button class="modal-close"></button>
    </div>
    <div class="cal-con">
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
              {{shareList.filter(person => person.no_person === incomeVO.no_person)[0].nm_person}}
            </em>
            <em class="income">수입</em>
            <em class="text">{{incomeVO.contents}}</em>
          </div>
          <div class="won">{{Common.formatNumber(incomeVO.amt_in_out)}} 원</div>
        </div>

        <div v-for="(consumeVO, index) in consumeList" :key="'consume-'+index" class="list">
          <div>
            <em :class="settingList[shareList.findIndex(person => person.no_person === consumeVO.no_person)].color">
              {{shareList.filter(person => person.no_person === consumeVO.no_person)[0].nm_person}}
            </em>
            <em class="debt">지출</em>
            <em class="text">{{consumeVO.contents}}</em>
          </div>
          <div class="won">{{Common.formatNumber(consumeVO.amt_in_out)}} 원</div>
        </div>

        <div v-for="(debtVO, index) in debtList" :key="'debt-'+index" class="list">
          <div>
            <em :class="settingList[shareList.findIndex(person => person.no_person === debtVO.no_person)].color">
              {{shareList.filter(person => person.no_person === debtVO.no_person)[0].nm_person}}
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
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    formatModalDate(date) {
      var mm = date.getMonth() + 1;
      var dd = date.getDate();

      var weekdays = new Array(7);
      weekdays[0] = "월요일";
      weekdays[1] = "화요일";
      weekdays[2] = "수요일";
      weekdays[3] = "목요일";
      weekdays[4] = "금요일";
      weekdays[5] = "토요일";
      weekdays[6] = "일요일";

      var weekday = weekdays[date.getDay()];
      return mm + "." + dd + ". " + weekday;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
