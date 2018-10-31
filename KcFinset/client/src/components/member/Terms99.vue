<template>
  <div>
    <div class="pop-top">
      <p class="title">금리</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
      <!-- v-on:click="this.$parent.@click('inner')" -->
    </div>
    <!-- Content -->
    <div class="container pop-wrap">
      <div>
        <p class="mt15">채무자가 약정당시와 비교하여 신용상태가 현저하게 개선된 경우 은행의 심사를 통하여 대출금리의 일부를 인하해주는 제도입니다
          * 금리 인하 범위에 해당되더라도 다음의 경우에는 불가합니다.
          * ‘금리 인하 요구권’ 조건에 맞는다면
          1. 금리인하신청서
          2. 신용상태가 개선되었다는 서류 ( 재직증명서 등)를
          금융회사에 제축하시면 됩니다.

          * 제출 후 5~10영업일 후면 결과를 통보 받습니다.
          * 단, 아래의 대출등은 제외 됩니다.
          1. 햇살론등 정책자금대출
          2. 보험사의 보험 계약 대출 등
        </p>
      </div>
    </div>
  </div>
</template>
	
<script>
export default {
  name: "Terms1"
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>