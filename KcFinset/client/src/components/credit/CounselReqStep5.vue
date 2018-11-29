<template>
  <section>

    <div class="loading success">
      {{this.$store.state.user.nmPerson}}님의 신용상담을<br>신청하였습니다.
    </div>

    <div class="btn-wrap float">
      <a @click="$router.push('/credit/counselMain')" class="solid box blue">홈으로</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "creditCounselReqStep5",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "noHeader";
    this.$store.state.title = "";
  },
  created() {},
  beforeMount() {},
  mounted() {},
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
