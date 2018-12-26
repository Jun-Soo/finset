<template>
  <div>
    <section>
      <div class="container pb90">
        <ul class="consume-detail">
          <div>
            <li>
              <p class="key">날짜1</p>
              <p>
                <datepicker v-model="date.first" ref="datepicker1" :language="ko" :format="Common.formatDateDot" class="div-date"></datepicker>
                <button class="cal" @click="openDatepicker(1)"></button>
              </p>
            </li>
          </div>
          <div>
            <li>
              <p class="key">날짜2</p>
              <p>
                <datepicker v-model="date.second" ref="datepicker2" :language="ko" :format="Common.formatDateDot" class="div-date"></datepicker>
                <button class="cal" @click="openDatepicker(2)"></button>
              </p>
            </li>
          </div>
          <div>
            <li>
              <p class="key">날짜3</p>
              <p>
                <datepicker v-model="date.third" ref="datepicker3" :language="ko" :format="Common.formatDateDot" class="div-date"></datepicker>
                <button class="cal" @click="openDatepicker(3)"></button>
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
      date: {
        first: new Date(),
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
