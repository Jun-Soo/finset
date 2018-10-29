<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <router-view/>
    <FinsetBottom v-if="isBottom"></FinsetBottom>
  </main>
</template>

<script>
import  FinsetHeader  from   './../common/FinsetHeader'
import  FinsetBottom  from   './../common/FinsetBottom'
import './../../assets/js/common.js'

export default {
  name: 'debtMain',
  data() {
    return {
      isBottom: false
    };
  },
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom
  }
}
</script>

<style>
</style>
