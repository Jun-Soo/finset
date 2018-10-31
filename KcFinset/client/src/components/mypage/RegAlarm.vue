<template>
  <section>
    <div class="alarm-setting">
      <ul>
        <li>
          <p><em>방해금지모드</em>소리/진동 없이 알림 받기</p>
          <p><button class="btn-onoff on"></button></p>
        </li>
        <li>
          <p><em>일반알림</em>공지사항,이벤트 등 알림</p>
          <p><button class="btn-onoff on"></button></p>
        </li>
        <li>
          <p><em>신용관리알림</em>신용정보 변동, 조회 등 알림</p>
          <p><button class="btn-onoff on"></button></p>
        </li>
        <li>
          <p><em>부채관리 알림</em>대출 상환 정보 등 알림</p>
          <p><button class="btn-onoff on"></button></p>
        </li>
        <li>
          <p><em>상품신청 알림</em>신청상품 진행정보 등 알림</p>
          <p><button class="btn-onoff on"></button></p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageRegAlarm",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "알림설정";
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
