<template>
  <div>
    <!-- Header -->
    <div class="pop-top">
      <p class="title">신용정보 올리기란 ?</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
    </div>
    <!-- Content -->
    <div class="container pop-wrap" id="content">

      <p class="mt15">신용카드를 사용하지 않거나 평소 금융거래가 많지 않은 분들을 대상으로 본인이 정상적으로 신용경제활동을 하고 있음을 입증할 수 있게 함으로써, 보다 나은 평가등급을 받을 수 있도록 도와드리는 제도입니다.</p>
      <div>
        <h3>평가방법은 ?</h3>
        <p class="mt15">제출하시는 납부 실적은 최대 6개월 또는 12개월간 개인신용평가에 긍정적으로만 활용이 됩니다.</p>
        <p class="mt15">제출하시는 납부 내역중 지연납부한 월이 있을경우 지연납부에 해당하는 월 이후 성실납부한 월로부터 납부실적으로 인정합니다.</p>
      </div>
      <div>
        <h3>제외 대상은</h3>
        <p class="mt15">10일이상, 10만원 이상의 연체가 진행중이거나</p>
        <p class="mt15">채무불이행정보(한국신용정보원,신용정보사)</p>
        <p class="mt15">미해제건을 보유한 회원의 경우 제한이 됩니다.</p>
      </div>
    </div>
    <!-- //Content -->
  </div>
</template>

<script>
export default {
  name: "RaiseInfo"
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
