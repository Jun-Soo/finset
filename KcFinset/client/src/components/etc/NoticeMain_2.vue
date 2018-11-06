<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a href="#">공지</a>
        <a href="#" class="on">이벤트</a>
      </div>
    </div>

    <div class="board event">
      <ul class="list">
        <li>
          <a @click="$router.push('')">
            <div>
              <p class="subject">이벤트 제목</p>
              <p class="date">2018-04-11</p>
              <p class="state ing">진행중</p>
            </div>
            <div>
              <img src="../../assets/images/common/board_thumb.png" width="80" alt="" />
            </div>
          </a>
        </li>
        <li>
          <a @click="$router.push('')">
            <div>
              <p class="subject">이벤트 제목</p>
              <p class="date">2018-04-11</p>
              <p class="state done">종료</p>
            </div>
            <div>
              <img src="../../assets/images/common/board_thumb.png" width="80" alt="" />
            </div>
          </a>
        </li>
        <li>
          <a @click="$router.push('')">
            <div>
              <p class="subject">이벤트 제목</p>
              <p class="date">2018-04-11</p>
              <p class="state pre">예정</p>
            </div>
            <div>
              <img src="../../assets/images/common/board_thumb.png" width="80" alt="" />
            </div>
          </a>
        </li>
      </ul>
    </div>

  </section>
</template>

<script>
export default {
  name: "EtcNoticeMain_2",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "공지/이벤트";
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
