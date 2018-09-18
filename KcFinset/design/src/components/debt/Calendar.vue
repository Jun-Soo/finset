<template>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
				</div>
				<h1>부채달력</h1>
				<div class="g-menu" id="src_menu">
					<button type="button" id="today" class="btn btn-gmenu">오늘</button>
					<button type="button" class="ico ico-notilist" onclick="goNoti();">알림내역</button>
				</div>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div id="calendar"></div>
			<div class="container-fluid">
				<div class="list-group calendar-list" id="listDebtByMm"></div>
			</div>
		</section>
		<!-- //Content -->
	</div>
</template>

<script>
export default {
  name: 'debtCalendar',
  data () {
    return {
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
    $("#calendar").datepicker();
  },
  created () {
  },
  beforeMount() {
  },
  mounted () {
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
<style scoped>

</style>
