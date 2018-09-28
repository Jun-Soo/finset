<template>
  <div id="app">
    <router-view/>
    <Spinner
      v-bind:is-visible="spinnerIsVisible"
    />
  </div>
</template>

<script>
import Spinner from "./components/common/Spinner.vue";
export default {
  name: "App",
  components: {
    Spinner
  },
  computed: {
    spinnerIsVisible: function() {return this.$store.state.isLoading}
  }
};
</script>
