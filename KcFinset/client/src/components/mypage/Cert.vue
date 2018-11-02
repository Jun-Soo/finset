<template>
  <section>
        
        <div class="alarm-setting">
            <ul>
                <li>
                    <a href="#" class="block">
                        <p><em>인증서 가져오기</em>PC에 있는 인증서를 휴대폰으로 복사</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="block">
                        <p><em>비밀번호 변경</em>휴대폰 보인 확인 필요</p>
                    </a>
                </li>
                <li>
                    <p><em>지문인증 설정</em>비밀번호없이 지문으로 인증</p>
                    <p><button class="btn-onoff on"></button></p>
                </li>
            </ul>
        </div>
        
    </section>
</template>

<script>
export default {
  name: "MypageCert",
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
