<template>
  <div v-if='this.$store.state.isLoggedIn'>
    <header>
      <gnb />
    </header>
    <div v-if="this.$store.state.header.type == 'main'" id='header' class="top main">
      <ul>
        <li id="header_main" v-bind:class="{on: this.$store.state.header.active=='main'}"><a @click="$router.push('/main')">MY</a></li>
        <li id="header_credit" v-bind:class="{on: this.$store.state.header.active=='credit'}"><a @click="$router.push('/credit/main')">신용</a></li>
        <li id="header_debt" v-bind:class="{on: this.$store.state.header.active=='debt'}"><a @click="$router.push('/debt/main')">부채</a></li>
        <li id="header_consume" v-bind:class="{on: this.$store.state.header.active=='consume'}"><a @click="$router.push('/consume/main')">지출</a></li>
        <li id="header_assets" v-bind:class="{on: this.$store.state.header.active=='assets'}"><a @click="$router.push('/assets/main')">자산</a></li>
      </ul>

    </div>
    <div v-else-if="this.$store.state.header.type == 'sub'" id='header' class="sub-top">
      <a @click="$router.go(-1)" class="btn-back"></a>
      <p class="title">{{this.$store.state.title}}</p>
      <a v-if="isSetting" @click="$router.go('')" class="setting"></a>
    </div>
  </div>
  <div v-else>
    <div id='header' class="sub-top">
      <p class="title">{{this.$store.state.title}}</p>
      <a v-if="isSetting" @click="$router.go('')" class="setting"></a>
    </div>
  </div>
</template>

<script>
import gnb from "./Gnb";
export default {
  name: "FinsetHeader",
  data() {
    return {
      isSetting: false
    };
  },
  components: {
    gnb
  },
  // computed () {
  // },
  beforeCreate() {},
  created() {
    if (
      location.href.indexOf("alarmHistory") > -1 ||
      location.href.indexOf("mypage/state") > -1
    ) {
      this.isSetting = true;
      console.log(this.isSetting);
    }
  },
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
