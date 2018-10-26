<template>
  <div>
	<!-- Header -->
		<div class="pop-top">
			<p class="title">마케팅 정보 수신 동의</p>
			<a class="btn-close" ></a> 
			<!-- v-on:click="this.$parent.@click('inner')" -->
		</div> 
	<!-- Content -->
		<div class="container pop-wrap" id="content">
			<p class="date">update 2018.02.01</p>
			<p class="mt15">주식회사 코스콤(이하 “회사”)은 회원에게 더 나은 서비스 제공을 목적으로 아래와 같이 개인정보를 수집, 이용하고 있습니다. 회원정보를 이용하는 경우 관련법률에 따라 사전에 회원에게 동의를 얻고 있으며 동의 이후에라도 회원의 의사에 따라 동의를 철회 할 수 있습니다.</p>
			<div>
				<h3>제 1조 (개인정보 수집 항목)</h3>
				<p class="mt15">이름, 휴대전화번호</p>
			</div>
			<div>
				<h3>제 2조 (개인정보 수집 및 이용 목적)</h3>
				<p class="mt15">핀셋의 상품ㆍ서비스에 대한 광고ㆍ홍보ㆍ프로모션 제공</p>
			</div>
			<div>
				<h3>제 3조 (개인정보 보유 및 이용기간)</h3>
				<p class="mt15">광고ㆍ마케팅 목적의 개인정보 수집ㆍ이용에 대한 동의시부터 서비스 이용계약 종료시 또는 회원 요청에 따라 마케팅 정보 수신 동의 철회 요청시까지</p>
				<p class="mt15">※ 마케팅 정보 수신 동의를 거부하는 경우에도 핀셋 서비스의 이용에는 영향이 없으며 핀셋 앱 또는 고객센터를 통해 언제든지 철회할 수 있습니다.</p>
			</div>
		</div>	
	<!-- //Content -->
	</div>
</template>

<script>
export default {
  name: "Terms10",
  data() {
    return {};
  },

  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
