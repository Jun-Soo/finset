<template>
  <div>
    <section>
      <div class="container">
        <div class="bar-top noMG">
          <div class="top">
            <p class="symbol"><img src="../../assets/images/common/bu_city.png" alt="" />삼성생명보험</p>
            <div class="btn-menu-wrap">
              <button class="btn-menu-pop"></button>
              <div class="menu">
                <a href="#">수정</a>
                <a href="#">삭제</a>
                <a href="#">메모</a>
                <a href="#">전화걸기</a>
              </div>
            </div>
          </div>
          <p class="key">상환금액(당월)</p>
          <div class="wrap">
            <div class="left">650,000<em>원</em></div>
            <div class="right">
              <p class="key">잔액</p>
              <p class="value">130,000,000 원</p>
            </div>
          </div>
          <div class="bar">
            <p style="width: 25%"></p>
          </div>
          <div class="bar-text-wrap">
            <p class="bar-text">원금상환비율 <em>44%</em></p>
            <p class="bar-card">금리<em>2.75%</em><button class="btn-info"></button></p>
          </div>
        </div>
      </div>

      <div class="tab">
        <div class="wrap">
          <a href="#">계약정보</a>
          <a href="#" class="on">상환정보</a>
        </div>
      </div>

      <div class="block-list2 noMG">
        <div class="index">
          <p class="normal">정상</p>
          <p class="overdue">연체</p>
          <p class="etc">기타</p>
          <p></p>
        </div>

        <ul class="list">
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="overdue" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="etc" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
          <li class="normal" data-modal="modal01">18.02</li>
        </ul>
      </div>

    </section>

    <div id="modal-wrap">
      <div class="modal-con" data-modal-con="modal01">
        <div class="top">
          2018.12
          <button class="modal-close"></button>
        </div>
        <div class="debt-modal-body">
          <p class="number">697,000<em>원</em></p>
          <p class="text"><em>원금</em> 393,000 원</p>
          <p class="text"><em>이자</em> 304,000 원</p>
        </div>
        <a href="#" class="btn-confirm">확인</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DebtDetail_2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "상세정보";
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
<style lang="scss">
</style>
