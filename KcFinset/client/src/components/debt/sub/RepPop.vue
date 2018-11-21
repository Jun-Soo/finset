<template>
  <div id="modal-wrap">
    <div id="modal-con" class="modal-con" style="display:block;">
      <div class="top">
        {{curRepay.req_yyyymm}}
        <button @click="close" class="modal-close"></button>
      </div>
      <div class="debt-modal-body">
        <p class="number">{{Common.formatNumber(curRepay.amt_repay)}} <em> 원</em></p>
        <p class="text"><em>원금 </em> {{Common.formatNumber(curRepay.amt_repay_p)}} 원</p>
        <p class="text"><em>이자 </em> {{Common.formatNumber(curRepay.amt_repay_i)}} 원</p>
      </div>
      <a @click="close" class="btn-confirm">확인</a>
    </div>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "DebtRepPop",
  props: {
    close: {
      type: Function,
      required: false,
      default: function() {}
    },
    curRepay: {
      type: Object,
      required: true
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
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
