<template>
  <div id="spinner" v-if="isVisible">
      <circle-spin />
  </div>
</template>

<script>
import Vue from "vue";
import VueSpinners from "vue-spinners";

Vue.use(VueSpinners);

export default {
  name: "Spinner",
  props: {
    isVisible: Boolean
  }
};
</script>

<style scoped>
#spinner {
  width: 100%;
  height: 100%;
  top: 0;
  position: fixed;
  background-color: grey;
  opacity: 0.5;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
/* p {
  color: grey;
  font-size: 10em;
  margin: 0;
  padding: 0;
} */
</style>
