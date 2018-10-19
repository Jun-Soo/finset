<template v-if='this.$store.state.isLoggedIn'>
<!-- <body class="main"> -->
	<!--   HEADER 로드   -->
	<!-- <header></header> -->
	<!--   CONTENTS   -->
    <!-- <section> -->

    <div v-if="this.$store.state.header.type == 'main'" id='header' class="top main">
        <ul>
            <li id="header_main" v-bind:class="{on: this.$store.state.header.active=='main'}"><a href="/main">MY</a></li>
            <li id="header_credit" v-bind:class="{on: this.$store.state.header.active=='credit'}"><a href="/credit/main">신용</a></li>
            <li id="header_debt" v-bind:class="{on: this.$store.state.header.active=='debt'}"><a href="/debt/main">부채</a></li>
            <li id="header_consume" v-bind:class="{on: this.$store.state.header.active=='consume'}"><a href="/consume/main">지출</a></li>
            <li id="header_assets" v-bind:class="{on: this.$store.state.header.active=='assets'}"><a href="/assets/main">자산</a></li>
        </ul>
    </div>
    <div v-else id='header' class="sub-top">
        <a href="#" class="btn-back"></a>
        <p class="title">{{this.$store.state.title}}</p>
    </div>
</template>

<script>
export default {
  name: 'FinsetHeader',
  data() {
    return {
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created() {
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy () {
  },
  destroyed() {
  },
  methods: {
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
