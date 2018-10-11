<template>
<div>
  <div id="progress-bg">
    <vue-progress-bar id="progress"></vue-progress-bar>
  </div>
  <button type="button" style="align:" v-on:click="clickStart"> Start </button>
</div>
</template>

<script>
import Vue from "vue";
import VueProgressBar from "vue-progressbar";

const options = {
  color: "#FC6E6D",
  failedColor: "#874b4b",
  thickness: "15px",
  transition: {
    speed: "0.2s",
    opacity: "0.6s",
    termination: 300
  },
  position: "inherit",
  autoRevert: false,
  location: "top",
  autoFinish: false,
  inverse: false
};
Vue.use(VueProgressBar, options);

export default {
  name: "test",
  data() {
    return {
      percentage: 0
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickStart() {
      var random = Math.floor(Math.random() * 100);
      this.$Progress.set(random);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
#progress {
  border-radius: 7px;
}
#progress-bg {
  border-radius: 7px;
  background-color: #D2D3D7;
}
</style>
