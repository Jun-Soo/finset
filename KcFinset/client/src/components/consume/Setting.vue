<template>
    <section>
        <div class="cert-check-wrap">
            <p class="title">회원정보</p>
            <select>
                <option>매월1일</option>
            </select>
        </div>
        
        <dl class="setting-wrap">
            <dt>지출</dt>
            <dd>
                <p class="key">지출예산설정</p>
                <p class="value"><a href="#">설정</a></p>
            </dd>
            <dd>
                <p class="key">카테고리설정</p>
                <p class="value"><a href="#">설정</a></p>
            </dd>
            <dd>
                <p class="key">할부개월수 분할</p>
                <p class="value"><button class="btn-onoff"></button></p>
            </dd>
            <dt>수입</dt>
            <dd>
                <p class="key">카테고리설정</p>
                <p class="value"><a href="#">설정</a></p>
            </dd>
        </dl>
    </section>
</template>

<script>
export default {
  name: "ConsumeSetting",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "설정";
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
