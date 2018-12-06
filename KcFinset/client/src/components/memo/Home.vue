<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <router-view/>
  </main>
</template>

<script>
import  FinsetHeader  from   './../common/FinsetHeader'
import './../../assets/js/common.js'

export default {
  name: 'debtMain',
  components: {
    FinsetHeader: FinsetHeader
  }
}
</script>

<style>
</style>
