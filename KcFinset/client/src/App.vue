<template>
  <div id="app">
    <FinsetHeader></FinsetHeader>
    <router-view/>
    <FinsetBottom></FinsetBottom>
  </div>
</template>

<script>
import  FinsetHeader  from   './components/common/FinsetHeader'
import  FinsetBottom  from   './components/common/FinsetBottom'
export default {
  name: 'App',
  components: {
    FinsetHeader: FinsetHeader,
    FinsetBottom: FinsetBottom
  }
}
</script>

<style>
</style>
