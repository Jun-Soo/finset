<template>
  <section v-if="seen">
    <div class="cs-top">
      <div class="cs-search">
        <input type="search" placeholder="자주 묻는 질문 검색">
      </div>
    </div>

    <div class="faq-list">
      <p class="title">납부내역</p>
      <div class="list">
        <p><a href="#">신용정보 조회를 하면 신용등급이 떨어지지 않나요</a></p>
        <p>본인의 신용정보를 조회하는 것은 신용등급에 영향을 주지 않습니다.</p>
      </div>
      <div class="list">
        <p><a href="#">신용정보 조회를 하면 신용등급이 떨어지지 않나요</a></p>
        <p>본인의 신용정보를 조회하는 것은 신용등급에 영향을 주지 않습니다.</p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "EtcFaqDetail",
  data() {
    return {
      id_board: "",
      boardForm:"",
      nm_board:"",
      pagedList:"",
      seq:"",
      seen:false,
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "자주 묻는 질문";
  },
  created() {
    this.id_board = this.$route.query.id_board;
    this.$http.get('/m/customercenter/getCustomerFAQDetail.json?id_board='+this.id_board)
    .then(response=>{
      // debugger;
      this.boardForm = response.data.boardForm
      this.nm_board = response.data.nm_board
      this.pagedList = response.data.pagedList.source
      this.seq = response.data.seq
      this.seen=true;
    })
  },
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
