<template>
  <div ref="progressBar" id="progressBar" class="progressBar progress" @click="click">
    <div ref="background" id="background">
      <h4>{{text}}</h4>
    </div>
    <div ref="container" id="container">
      <div ref="foreground" id="foreground">
        <h4>{{text}}</h4>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Progress",
  props: {
    max: {
      default: 0.8,
      type: Number
    },
    text: {
      default: "설정된 예산이 없습니다",
      type: String
    },
    click: {
      default: function() {},
      type: Function
    }
  },
  data() {
    return {
      animationTime: "1s"
    };
  },
  components: {},
  watch: {
    max: function(param) {
      let clientWidth = this.$refs.progressBar.clientWidth;
      this.$refs.container.style.width = clientWidth * this.max + "px";
    }
  },
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    // background/foreground
    // width가 고정이여서 윈도우창 사이즈가 바뀌어도, 같이 바뀌지는 않습니다.
    let clientWidth = this.$refs.progressBar.clientWidth;
    let clientHeight = this.$refs.progressBar.clientHeight;
    this.$refs.background.style.width = clientWidth + "px";
    this.$refs.background.style.height = clientHeight + "px";
    this.$refs.foreground.style.width = clientWidth + "px";
    this.$refs.foreground.style.height = clientHeight + "px";

    this.$refs.container.style.width = clientWidth * this.max + "px";
    this.$refs.container.style.transitionDuration = this.animationTime;
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    start() {
      this.$refs.container.style.width = this.max + "%";
      this.$refs.container.style.transitionDuration =
        this.animationTime / 1000 + "s";

      let _this = this;
      var start = 0;
      var interval = setInterval(function() {
        if (start >= _this.max) clearInterval(interval);
        _this.current = start++;
      }, _this.animationTime / _this.max);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
.progressBar {
  /* --progressBarWidth: 500px; */
  --progressBarHeight: 40px;
  --fontSize: 13px;
  --font-weight: 700;
  --borderRadius: 50px;
  /* width: var(--progressBarWidth); */
  height: var(--progressBarHeight);
  position: relative;
}

.progressBar #background,
.progressBar #container #foreground {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  /* font-weight: bold; */
  font-weight: var(--font-weight);
  border-radius: var(--borderRadius);
}

.progressBar #background {
  /* progressBar 기본 색상 */
  /* background: blue; */
  background: #fff;
  border: 1px solid #62c1d0;
}
.progressBar #background h4 {
  /* progressBar 기본 텍스트 색상 */
  /* color: red; */
  color: #62c1d0;
  font-weight: var(--font-weight);
  font-size: var(--fontSize);
}
.progressBar #container #foreground {
  /* progressBar 데이터 색상 */
  /* background: red; */
  background: #62c1d0;
  border: 1px solid #62c1d0;
}
.progressBar #container #foreground h4 {
  /* progressBar 데이터 텍스트 색상 */
  /* color: blue; */
  color: #fff;
  font-size: var(--fontSize);
}

.progressBar #container {
  position: absolute;
  top: 0;
  left: 0;
  width: 0%;
  overflow: hidden;
  transition-timing-function: ease;
}
</style>
