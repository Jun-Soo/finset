<template>
    <section>
        <div class="container">
            <textarea></textarea>
        </div>
        
        <div class="btn-wrap float">
            <a href="#" class="blue box solid">등록</a>
        </div>
        
    </section>
</template>

<script>
export default {
  name: "",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "메모등록";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
