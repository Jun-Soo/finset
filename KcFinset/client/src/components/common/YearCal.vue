<template>
  <section>
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev"></button>
        <p>2018.07</p>
        <button class="next"></button>
        <button class="setting"></button>
        <button class="today">TODAY</button>
      </div>
    </div>

    <div class="check-flex">
      <div class="income">
        <button class="on">수입</button>
        <em>1,234,565,000</em>
      </div>
      <div class="debt">
        <button class="on">지출</button>
        <em>1,234,565,000</em>
      </div>
      <div class="loan">
        <button>대출</button>
        <em>1,234,565,000</em>
      </div>
    </div>

    <div class="calendar">
      <div class="year">
        <div>
          <p class="mon">1월</p>
          <em class="income">465,000</em>
          <em class="debt">32,000</em>
          <em class="loan">100,000,000</em>
          <p class="sum">+1,500,000</p>
        </div>
        <div>
          <p class="mon">2월</p>
          <em class="income">465,000</em>
          <em class="debt">32,000</em>
          <em class="loan">100,000,000</em>
          <p class="sum">+1,500,000</p>
        </div>
        <div>
          <p class="mon">3월</p>
          <em class="income">465,000</em>
          <em class="debt">32,000</em>
          <em class="loan">100,000,000</em>
          <p class="sum">+1,500,000</p>
        </div>
        <div>
          <p class="mon">4월</p>
        </div>
        <div>
          <p class="mon">5월</p>
        </div>
        <div>
          <p class="mon">6월</p>
        </div>
        <div>
          <p class="mon">7월</p>
        </div>
        <div>
          <p class="mon">8월</p>
        </div>
        <div>
          <p class="mon">9월</p>
        </div>
        <div>
          <p class="mon">10월</p>
        </div>
        <div>
          <p class="mon">11월</p>
        </div>
        <div>
          <p class="mon">12월</p>
        </div>
      </div>
    </div>
    <button class="btn-spend-add"></button>

  </section>
</template>

<script>
export default {
  name: "CommonYearCal",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "습관달력";
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
