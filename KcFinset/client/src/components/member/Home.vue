<template>
  <main class="MemberMain">
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
  .MemberMain {background-color: #283593;}
</style>
