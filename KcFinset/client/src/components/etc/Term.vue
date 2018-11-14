<template>
  <section>
    <div class="cs-links">
      <p><a @click="openPop('1')">서비스 이용약관</a></p>
      <p><a @click="openPop('2')">개인정보 처리방침</a></p>
    </div>
    <vue-modal transitionName="zoom-in" name="my-modal1" v-on:popclose="closePop('1')">
      <serviceTerm slot="body" v-on:popclose="closePop('1')"></serviceTerm>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal2" v-on:popclose="closePop('2')">
      <privacyTerm slot="body" v-on:popclose="closePop('2')"></privacyTerm>
    </vue-modal>
  </section>
</template>

<script>
import serviceTerm from "../member/Terms1";
import privacyTerm from "../member/Terms2";

export default {
  name: "EtcTerm",
  data() {
    return {};
  },
  components: {
    serviceTerm:serviceTerm,
    privacyTerm:privacyTerm
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이용 정책 및 약관";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    closePop: function(gubun) {
      var _this = this;
      _this.$modals.hide("my-modal" + gubun);
    },
    openPop: function(gubun) {
      var _this = this;
      _this.$modals.show("my-modal" + gubun);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
