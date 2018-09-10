<template>
    <!-- Header -->
    <header id='header'>
      <div class='input-group'>
        <div class='input-group-btn blind'>
          <button type='button' class='ui-nav nav-back'>뒤로가기</button>
        </div>
        <h1>{{this.$store.state.title}}</h1>
        <!-- <div class='g-menu'>
          <button type='button' class='ico ico-notilist'>알림내역</button>
          <button type='button' class='btn btn-gmenu'>정보공유</button>
        </div> -->
      </div>
    </header>
</template>

<script>
export default {
  name: 'FinsetHeader',
  data () {
    return {
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created () {
  },
  beforeMount() {
  },
  mounted () {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy () {
  },
  destroyed() {
  },
  methods: {
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">

</style>
