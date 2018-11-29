<template>
  <div>
    <div class="pop-top">
      <p class="title">신용상담이란?</p>
      <a
        class="btn-close"
        v-on:click="$emit('popclose')"
      ></a>
      <!-- v-on:click="this.$parent.@click('inner')" -->
    </div>
    <!-- Content -->
    <div class="container pop-wrap">
      <div>
        <h2>FINSET에서 제공하는 신용상담은 서민금융연구원과 협업을 하여 아래의 내용으로 상담이 진행이 됩니다.</h2>
      </div>
      <div>
        <h3>대상</h3>
        <ul class="list-style01">
          <li>채무독촉 등의 어려움을 겪고 있는 분</li>
          <li>채무를 갚기 위해서 대출을 받으면서 돌려막기를 하고 계신분</li>
          <li>채무가 계속 늘고 있는 분</li>
          <li>채무를 효율적으로 상환할 수 있는 방법을 찾고 있는분</li>
          <li>채무를 발생하는 과소비, 미흡한 신용관리 등의 원인들을 개선하고 싶으신 분</li>
          <li>채무를 줄이면서 저축하고 싶으신분</li>
        </ul>
      </div>
      <div>
        <h3>주요 상담 내용</h3>
        <ul class="list-style01">
          <li>여러 대안을 복합적으로 검토하여 단기,중기,장기의 해결책을 제안</li>
          <li>부채내역, 자산상태, 소득 및 지출 등의 재무상황을 정확히 진단하여 대안을 제시</li>
          <li>대안을 시행하면서 발생할 수 있는 돌발상황에 잘 대처할 수 있도록 수시로 조언 및 점검</li>
          <li>채무가 다시 발생하지 않도록 개인별로 적합한 재무관리 방법 제안</li>
        </ul>
      </div>
    </div>
  </div>
</template>
	
<script>
export default {
  name: "creditCounselInfo"
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>