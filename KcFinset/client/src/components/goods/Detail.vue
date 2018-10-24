<template>
	<section>
		<div class="goods-top">
			<p class="symbol"><img src="../../assets/images/common/bu_samsung.png" alt=""/>삼성생명보험</p>
			<p class="title">국민은행 대출 상품명</p>
			<p class="text">넉넉한 한도화 낮은 금리, 소득증빙이 가능한 직장인이라면 최대 1억원까지 대출한도 모바일에서 바로 확인 가능</p>
			<p class="opt"><span>직장인</span><span>낮은금리</span></p>
		</div>
		<div class="tab mt50">
			<div class="wrap">
				<a href="#" class="on">금리</a>
				<a href="#">상세</a>
			</div>
		</div>
		<div class="goods-detail">
			<div class="container">
				<div>
					<p class="key">대출금리</p>
					<p class="value">기준금리 어쩌고 저쩌고</p>
				</div>
				<div>
					<p class="key">우대금리</p>
					<p class="value">최대 0.6% <br>당행 계좌로 매월 급여이체 : 0.2%</p>
				</div>
				<div class="btn-wrap">
					<a href="#" class="solid blue">조회하기</a>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
export default {
  name: "GoodsDetail",
  data() {
    return {};
  },
  components: {},
  // computed () {
  // },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "추천상품";
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
<style scoped>
</style>
