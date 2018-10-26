<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <router-view/>
    <FinsetBottom></FinsetBottom>
  </main>
</template>

<script>
import FinsetHeader from "./../common/FinsetHeader";
import FinsetBottom from "./../common/FinsetBottom";
import "./../../assets/js/common.js";

export default {
  name: "creditHome",
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom
  }
};
</script>

<style>
</style>
