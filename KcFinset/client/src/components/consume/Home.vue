<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <router-view/>
  </main>
</template>

<script>
import  FinsetHeader  from   './../common/FinsetHeader'
import  FinsetBottom  from   './../common/FinsetBottom'
import './../../assets/js/common.js'

export default {
  name: 'debtMain',
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom
  }
}
</script>

<style>
</style>
