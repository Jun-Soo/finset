<template>
  <main class="scarpMain">
    <FinsetHeader></FinsetHeader>
    <router-view/>
  </main>
</template>

<script>
import FinsetHeader   from   './../common/FinsetHeader'

export default {
  name: 'scrapHome',
  components: {
    FinsetHeader: FinsetHeader
  }
}
</script>

<style>
  .scrapMain {background-color: #283593; }
</style>
