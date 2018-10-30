<template>
    <section>
        <div class="cert-check-wrap">
            <p class="title">가입형태</p>
            <select>
                <option>근로소득자용</option>
            </select>
            <p class="title">대상기간</p>
            <select>
                <option>2017년</option>
            </select>
        </div>

        <div class="cert-check-wrap">
            <p class="text">본인 확인을 위해<br>주민 등록 번호 뒷자리를 입력하여 주세요</p>
            <p class="title">이름</p>
            <input type="text">
            <p class="title">주민등록번호</p>
            <div class="grid">
                <div class="number"><input type="number"></div>
                <div class="dash">-</div>
                <div class="number last"><input type="password"></div>
            </div>
        </div>
        
        <div class="btn-wrap">
            <a href="#" class="solid blue box">확인</a>
        </div>
    </section>
</template>

<script>
export default {
  name: "CreditRaiseInsPersonInfoNts",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "본인 확인";
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
