<template>
  <section>
    <div class="alarm-history mt30">
      <p class="date">2018.9.15</p>
      <div class="alarm-list">
        <div class="item">
          <a @click="$router.go('')">
            <p class="ico alarm">단기연체정보변동</p>
            <p class="text">단기연체정보 변동</p>
          </a>
        </div>
        <div class="item">
          <a @click="$router.go('')">
            <p class="ico alarm">단기연체정보변동</p>
            <p class="text">단기연체정보 변동</p>
          </a>
        </div>
      </div>
    </div>
    <div class="alarm-history">
      <p class="date">2018.9.15</p>
      <div class="alarm-list">
        <div class="item">
          <a @click="$router.go('')">
            <p class="ico alarm">단기연체정보변동</p>
            <p class="text">단기연체정보 변동</p>
          </a>
        </div>
        <div class="item">
          <a @click="$router.go('')">
            <p class="ico alarm">단기연체정보변동</p>
            <p class="text">단기연체정보 변동</p>
          </a>
        </div>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "EtcAlarmHistory",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "알림내역";
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
