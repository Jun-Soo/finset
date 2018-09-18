<template>
  <main class="memberMain">
    <FinsetHeader></FinsetHeader>
    <router-view/>
  </main>
</template>

<script>
import FinsetHeader   from   './../common/FinsetHeader'

export default {
  name: 'memberHome',
  components: {
    FinsetHeader: FinsetHeader
  }
}
</script>

<style>
  .memberMain {background-color: #283593; height: 100%;}
</style>
