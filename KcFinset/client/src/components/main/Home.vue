<template>
  <main>
    <router-view/>
  </main>
</template>

<script>
export default {
  created () {
    // this.$toast.center('토스트 테스트중');
    this.getUserPage()
  },
  methods: {

    getUserPage: function() {

      var _this = this;
      var data = {hp:''};

      this.$http.get('/api/base/frameBase.json', {
        params: data
      }).then(function (response) {
        console.log(JSON.stringify(response.data));
        _this.$router.push({name: response.data.rtnPath})
      })
    }
  }
}
</script>

<style>
</style>
