<template>
  <section>
    <div class="pop-top">
      <p class="title">직장명 검색</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
    </div>
    <div class="container">
      <p>직장명을 직접 입력해 주세요<br>사업자 번호는 “-” 없이 입력해 주세요</p>
      <ul class="debt-modify">
        <li>
          <p class="key">직장명</p>
          <p><input type="text" placeholder="직장명 입력" v-model="nm_comp" @keyup="chkValidate"></p>
        </li>
        <li>
          <p class="key">사업자번호</p>
          <p><input type="text" placeholder="사업자 번호 입력" v-model="no_biz_comp" @keyup="chkValidate"></p>
        </li>
      </ul>
      <div class="btn-wrap float" v-if="showConfirm">
        <a class="solid blue box" @click="clickConfirm">확인</a>
      </div>
    </div>
  </section>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      showConfirm: false,
      nm_comp: "",
      no_biz_comp: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    chkValidate: function() {
      if (this.nm_comp == "" || this.no_biz_comp == "") {
        this.showConfirm = false;
      } else {
        this.showConfirm = true;
      }
    },
    clickConfirm: function() {
      if (this.nm_comp == "") {
        this.$toast.center("직장명을 입력해주세요.");
        return false;
      } else if (this.no_biz_comp == "") {
        this.$toast.center("사업자번호를 입력해주세요.");
        return false;
      }
      var isValid = this.checkBizID(this.no_biz_comp);
      if (isValid == false) {
        this.$toast.center("사업자번호가 잘못되었습니다.");
        return false;
      }

      this.$parent.$parent.korentrnm = this.nm_comp;
      this.$parent.$parent.bizno = this.no_biz_comp;
      this.$emit("popclose");
    },
    checkBizID: function(bizID) {
      //사업자등록번호 체크
      // bizID는 숫자만 10자리로 해서 문자열로 넘긴다.
      var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
      var tmpBizID,
        i,
        chkSum = 0,
        c2,
        remander;
      bizID = bizID.replace(/-/gi, "");

      for (i = 0; i <= 7; i++) chkSum += checkID[i] * bizID.charAt(i);
      c2 = "0" + checkID[8] * bizID.charAt(8);
      c2 = c2.substring(c2.length - 2, c2.length);
      chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
      remander = (10 - (chkSum % 10)) % 10;

      if (Math.floor(bizID.charAt(9)) == remander) return true; // OK!
      return false;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
