<template>
  <div>
    <div class="pop-top">
      <p class="title">금리인하요구권이란?</p>
      <a
        class="btn-close"
        v-on:click="$emit('popclose')"
      ></a>
      <!-- v-on:click="this.$parent.@click('inner')" -->
    </div>
    <!-- Content -->
    <div class="container pop-wrap">
      <div>
        <h2>채무자가 약정당시와 비교하여 신용상태가 현저하게 개선된 경우 은행의 심사를 통하여 대출금리의 일부를 인하해주는 제도입니다</h2>
      </div>
      <div>
        <h3>* 금리 인하 범위에 해당되더라도 다음의 경우에는 불가합니다.</h3>
        <ul class="list-style01">
          <li>현재 연체중인 경우</li>
          <li>신용회복 진행중인 경우</li>
          <li>관리 불가 채권</li>
          <li>법적 절차 진해 중(법 비용 발생)인 경우</li>
          <li>파산면책, 신용회복, 개인 회생등 지원 및 결정된 경우</li>
          <li>기존 중도 상환 방지 등에 따른 금리가 인하된 경우</li>
          <li>당행 거래내역이 현저하게 불량한 경우</li>
        </ul>
      </div>
      <div>
        <h3>* ‘금리 인하 요구권’ 조건에 맞는다면</h3>
        <ul class="list-style01">
          <li>금리인하신청서</li>
          <li>신용상태가 개선되었다는 서류(재직증명서 등)를 금융회사에 제출하시면 됩니다.</li>
        </ul>
      </div>
      <div>
        <h3>* 제출 후 5~10영업일 후면 결과를 통보 받습니다.</h3>
        <h3 style="margin-top:3px;">* 단, 아래의 대출등은 제외 됩니다.</h3>
        <ul class="list-style01">
          <li>햇살론등 정책자금대출</li>
          <li>보험사의 보험 계약 대출 등</li>
        </ul>
      </div>
    </div>
  </div>
</template>
	
<script>
export default {
  name: "debtReqIntrCutInfo"
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>