<template>
  <div>
    <section>
      <div class="container pb90">
        <ul class="consume-detail">
          <div>
            <li>
              <p class="key">금액1</p>
              <p>
                <money :theme="'auto'" v-model="money.first" :change="test" />
                <em>원</em>
              </p>
            </li>
          </div>
          <div>
            <li>
              <p class="key">금액2</p>
              <p>
                <money v-model="money.second" />
                <em>원</em>
              </p>
            </li>
          </div>
          <div>
            <li>
              <p class="key">금액3</p>
              <p>
                <money v-model="money.third" />
                <em>원</em>
              </p>
            </li>
          </div>
        </ul>
      </div>
    </section>
  </div>
</template>


<script>
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import datepicker from "vuejs-datepicker";
import { ko } from "vuejs-datepicker/dist/locale";

export default {
  name: "SampleDatepicker",
  data() {
    return {
      Common: Common,
      ko: ko,
      money: {
        first: "11",
        second: null,
        third: null
      }
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "데이트피커 예시";
  },
  created() {},
  beforeMount() {},
  mounted() {
    Common.datepickerInit("div-date", this);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    openDatepicker: function(index) {
      if (index == 1) {
        this.$refs.datepicker1.showCalendar();
      } else if (index == 2) {
        this.$refs.datepicker2.showCalendar();
      } else if (index == 3) {
        this.$refs.datepicker3.showCalendar();
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
