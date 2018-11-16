<template>
  <section>
    <div class="cs-top">
      <div class="cs-search">
        <input type="search" id="txtDetail" v-on:keyup.enter="goSearch" v-model="txt_detail" placeholder="자주 묻는 질문 검색">
      </div>
    </div>

    <div class="cs-links">
      <p><a @click="$router.push('/etc/faqDetail?id_board=credit')">신용관리</a></p>
      <p><a @click="$router.push('/etc/faqDetail?id_board=debt')">부채관리</a></p>
      <p><a @click="$router.push('/etc/faqDetail?id_board=goods')">상품조회</a></p>
      <p><a @click="$router.push('/etc/faqDetail?id_board=security')">보안</a></p>
      <p><a @click="$router.push('/etc/faqDetail?id_board=etc')">기타</a></p>
    </div>
  </section>
</template>

<script>
export default {
  name: "EtcFaqList",
  data() {
    return {
      txt_detail: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "자주 묻는 질문";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    goSearch: function() {
      let _this = this;
      _this.$router.push("/etc/faqDetail?txt_detail=" + _this.txt_detail);
    }
  }
};
</script>
