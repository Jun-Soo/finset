<template>
  <div v-if='this.$store.state.isLoggedIn'>
    <header v-if="this.$store.state.header.type != 'noHeader'">
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
      <a @click="clickBack" class="btn-back"></a>
      <p class="title">{{this.$store.state.title}}</p>
      <a v-if="isSetting" @click="$router.push('/mypage/regAlarm')" class="setting"></a>
    </div>
    <div v-if="this.$store.state.header.type == 'noHeader'" id='header' class="sub-top">
      <p class="title">{{this.$store.state.title}}</p>
      <a v-if="isSetting" @click="$router.push('/mypage/regAlarm')" class="setting"></a>
    </div>
  </div>
  <div v-else>
    <div id='header' class="sub-top">
      <p class="title">{{this.$store.state.title}}</p>
      <a v-if="isSetting" @click="$router.push('/mypage/regAlarm')" class="setting"></a>
    </div>
  </div>
</template>

<script>
import Constant from "./../../assets/js/constant.js";
import gnb from "./Gnb";
export default {
  name: "FinsetHeader",
  data() {
    return {
      isSetting: false,
      isBackKey: false
    };
  },
  components: {
    gnb
  },
  // computed () {
  // },
  beforeCreate() {},
  created() {
    window.androidBackFn = this.androidBackFn;

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
  updated() {
    if (Constant.userAgent == "Android") {
      let ynEnd = "N";
      if (this.$store.state.header.type !== "sub") ynEnd = "Y";
      window.Android.setEndApp(ynEnd);
    }
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    androidBackFn: function() {
      console.log("androidBackFn called");
      //모달 해제
      if (
        this.$modals != undefined &&
        this.$modals.shownModals != undefined &&
        this.$modals.shownModals.length > 0
      ) {
        var shownModals = this.$modals.shownModals;
        for (var idx in shownModals) {
          this.$modals.hide(shownModals[idx]);
        }
        this.isBackKey = true;
      }
      //alert, confirm 창 해제
      if (
        this.$dialogs != undefined &&
        this.$dialogs.items != undefined &&
        this.$dialogs.items.length > 0
      ) {
        var items = this.$dialogs.items;
        for (var idx in items) {
          this.$dialogs.remove(items[idx]);
        }
        this.isBackKey = true;
      }
      if (this.isBackKey) {
        this.isBackKey = false;
        return;
      }

      this.clickBack();
    },
    clickBack: function() {
      // if (
      //   this.$store.state.header.fromPath != "" &&
      //   this.$store.state.header.backPath == ""
      // ) {
      //   var from = this.$store.state.header.fromPath;
      //   this.$store.state.header.fromPath = "";
      //   this.$router.push(from);
      // } else
      if (this.$store.state.header.backPath == "") {
        this.$router.go(-1);
      } else {
        var back = this.$store.state.header.backPath;
        this.$store.state.header.backPath = "";
        this.$router.push(back);
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
