<template>
  <main>
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