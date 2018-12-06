<template>
  <main>
    <FinsetHeader></FinsetHeader>
    <router-view/>
  </main>
</template>

<script>
import FinsetHeader   from   './../common/FinsetHeader'

export default {
  name: 'mypageHome',
  components: {
    FinsetHeader: FinsetHeader
  }
}
</script>

<style>
</style>
