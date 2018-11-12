<template>
  <div>
    <div class="pop-top">
      <p class="title">금융정보 제공동의서</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
      <!-- v-on:click="this.$parent.@click('inner')" -->
    </div>
    <!-- Content -->
    <div class="container pop-wrap" v-html="text">

    </div>
  </div>
</template>
	
<script>
export default {
  name: "Terms1",
  props: {
    text: {
      // 금융정보 제공동의서
      type: String,
      default: ""
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>