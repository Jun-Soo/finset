<template>
  <div>
		<!-- Header -->
		<div class="pop-top">
			<p class="title">개인정보 제3자 제공 동의</p>
			<a class="btn-close" ></a> 
			<!-- v-on:click="this.$parent.@click('inner')" -->
		</div> 
		<!-- Content -->
		<div id="content" class="container pop-wrap">
			<p class="date">update 2018.02.01</p>
			<p class="mt15">본인은 「신용정보의 이용 및 보호에 관한 법률」 제32조 제1항, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 제24조의 2, 「개인정보 보호법」 제17조 등의 관련 법규에 따라 귀사가 아래와 같은 내용으로 본인의 개인정보를 제공하는 것에 동의합니다.</p>	
			<div>
				<h2>제 1 조 (개인정보를 제공받는 자)</h2>	
				<p class="mt15">코리아크레딧뷰로 주식회사</p>
			</div>
			<div>
				<h2>제 2 조 (개인정보를 제공받는 자의 이용목적)</h2>
				<p class="mt15">개인 식별 및 본인 확인, 귀사에 대한 회원 별 신용조사보고서 제공</p>
			</div>
			<div>
				<h2>제 3 조 (제공 대상 개인정보)</h2>
				<p class="mt15">이름, 생년월일, 성별, 중복가입확인정보(DI)</p>
			</div>
			<div>
				<h2>제 4 조 (보유 및 이용기간)</h2>
				<p class="mt15">개인(신용)정보 제공 동의 철회시 또는 제공된 목적이 달성된 시점까지(단, 관계법령에 보존기간 등이 별도로 설정되어 있는 경우에는 그에 따름)</p>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  name: "Terms5",
  data() {
    return {};
  },

  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
