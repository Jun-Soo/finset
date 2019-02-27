<template>
  <div>
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev" @click="prev"></button>
        <p>{{calendarYear}}.{{calendarMonth}}</p>
        <button class="next" @click="next"></button>
        <button class="year" @click="goYear">년</button>
        <button class="today" @click="goToToday">TODAY</button>
      </div>
    </div>

    <!-- // 금보원
    <div class="check-flex">
      <div class="income">
        <button :class="{ 'on':isActiveIncome }" @click="clickIncome">수입</button>
        <em>{{Common.formatNumber(sumData.sumIncome)}}</em>
      </div>
      <div class="debt">
        <button :class="{ 'on':isActiveConsume }" @click="clickConsume">지출</button>
        <em>{{Common.formatNumber(sumData.sumConsume)}}</em>
      </div>
      <div class="loan">
        <button :class="{ 'on':isActiveDebt }" @click="clickDebt">대출</button>
        <em>{{Common.formatNumber(sumData.sumDebt)}}</em>
      </div>
    </div>
    
    <div class="container-wrap noMG">
      <div class="list-wrap" v-if="shareList.length != 1">
        <div class="filter-wrap">
          <div v-for="(person, index) in shareList" :key="person.no_person" class="filter" :class="settingList[index].color">
            <input type="checkbox" :checked="person.isShow" :id="settingList[index].id"><label @click="clickShare(index)">{{formatSharePerson(person)}}</label>
          </div>
        </div>
      </div>
    </div>
    -->
    <component :is="activeView" :class="'v-cal-content--' + activeView" v-bind="activeViewProps"></component>
    <footer class="v-cal-footer"></footer>
  </div>
</template>

<script>
import Common from "@/assets/js/common.js";

import Event from "../model/Event";

import config from "../utils/config";
import { defaultLabels, defaultViews } from "../utils/config";

import { EventBus } from "./EventBus";

import moment from "moment";
import Month from "./views/Month";

export default {
  name: "VueScheduler",
  components: { Month },
  props: {
    events: {
      type: Array,
      default: () => []
    },
    labels: {
      type: Object,
      default: () => config.labels,
      validator(value) {
        for (const labelKey in defaultLabels) {
          if (!value.hasOwnProperty(labelKey)) {
            console.error("Missing prop label: " + labelKey);
            return false;
          }
        }
        return true;
      }
    },
    initialDate: {
      type: [Date, Object],
      default: () => config.initialDate
    },
    initialView: {
      type: String,
      default: () => config.initialView
    },
    eventDisplay: {
      type: [String, Function],
      default: () => config.eventDisplay
    },
    shareList: {
      type: [Array, Object],
      dafault: {}
    },
    clickShare: {
      type: Function,
      default: function() {}
    },
    sumData: {
      type: Object,
      default: {}
    },
    type: {
      type: String,
      default: ""
    },
    settingList: {
      type: Array,
      default: []
    }
  },
  data() {
    return {
      today: moment(),
      activeView: "",
      activeDate: null,
      isActiveIncome: true,
      isActiveConsume: true,
      isActiveDebt: true,
      Common: Common
    };
  },
  mounted() {
    moment.locale("en");
    //  Initial setup
    this.activeView = this.initialView;
    this.activeDate = moment(this.initialDate).locale("en");

    //화면 이동 로직
    var _this = this;
    let origMousePoint;
    $(document).on("mousedown", ".v-cal-content", function(event) {
      origMousePoint = event.clientX;
    });
    $(document).on("mouseup", ".v-cal-content", function(event) {
      if (origMousePoint - event.clientX > 150) {
        _this.next();
      } else if (origMousePoint - event.clientX < -150) {
        _this.prev();
      }
    });
    $(document).on("touchstart", ".v-cal-content", function(event) {
      origMousePoint = event.touches[0].clientX;
    });
    $(document).on("touchend", ".v-cal-content", function(event) {
      if (origMousePoint - event.changedTouches[0].clientX > 150) {
        _this.next();
      } else if (origMousePoint - event.changedTouches[0].clientX < -150) {
        _this.prev();
      }
    });
    ////화면 이동 로직
    if (this.type == "debt") {
      this.isActiveConsume = false;
      this.isActiveIncome = false;
    } else if (this.type == "consume") {
      this.isActiveDebt = false;
    }
    //  Bind events
    this.bindEvents();
  },
  beforeDestroy() {
    EventBus.$off("day-clicked");
    EventBus.$off("time-clicked");
    EventBus.$off("event-clicked");
  },
  methods: {
    bindEvents() {
      var _this = this;
      EventBus.$on("event-clicked", event => {
        this.$emit("event-clicked", {
          date: event.date.toDate(),
          isActiveIncome: _this.isActiveIncome,
          isActiveConsume: _this.isActiveConsume,
          isActiveDebt: _this.isActiveDebt
        });
      });
    },
    goToToday() {
      this.activeDate = moment(this.initialDate).locale("en");
      this.$emit("curYM", this.activeDate.format("YYYYMM"));
    },
    prev(e) {
      this.activeDate = moment(
        this.activeDate.subtract(1, this.activeView + "s")
      );
      this.$emit("curYM", this.activeDate.format("YYYYMM"));
    },
    next(e) {
      this.activeDate = moment(this.activeDate.add(1, this.activeView + "s"));
      this.$emit("curYM", this.activeDate.format("YYYYMM"));
    },
    clickIncome(e) {
      this.clickBtn(e);
      this.isActiveIncome = !this.isActiveIncome;
    },
    clickConsume(e) {
      this.clickBtn(e);
      this.isActiveConsume = !this.isActiveConsume;
    },
    clickDebt(e) {
      this.clickBtn(e);
      this.isActiveDebt = !this.isActiveDebt;
    },
    clickBtn(e) {
      var isActive = e.toElement.classList.contains("active");
      if (isActive) {
        e.toElement.classList.remove("active");
      } else {
        e.toElement.classList.add("active");
      }
    },
    changeType() {
      console.log("cahnge");
    },
    goYear: function() {
      this.$router.push("/common/yearCal");
    },
    // 이름 포맷 변경
    formatSharePerson: function(person) {
      if ((person || "") == "") {
        return "";
      } else if (
        (person.no_person || "") == "" ||
        (person.nm_person || "") == ""
      ) {
        return "";
      }

      var myNoPerson = this.$store.state.user.noPerson;
      if (person.no_person == myNoPerson) {
        return "나";
      } else {
        if (person.nm_person.length > 3) {
          return person.nm_person.substring(0, 3);
        } else {
          return person.nm_person;
        }
      }
    }
  },
  filters: {},
  watch: {
    initialDate() {
      this.activeDate = moment(this.initialDate);
    },
    initialView() {
      this.activeView = this.initialView;
    },
    activeDate() {
      this.$emit(this.activeView + "-changed", this.activeDate.toDate());
    },
    activeView() {
      this.$emit("view-changed", this.activeView);
    }
  },
  computed: {
    newEvents() {
      return this.events.map(e => {
        return new Event(e).bindGetter("displayText", this.eventDisplay);
      });
    },
    activeViewProps() {
      let props = {
        activeDate: this.activeDate,
        minDate: this.minDate,
        maxDate: this.maxDate,
        events: this.newEvents.filter(event => {
          return event.date.isSame(this.activeDate, this.activeView);
        })
      };
      return props;
    },
    calendarYear() {
      if (this.activeDate === null) return "";

      if (this.activeView === "month") {
        return this.activeDate.format("YYYY");
      }
    },
    calendarMonth() {
      if (this.activeDate === null) return "";

      if (this.activeView === "month") {
        return this.activeDate.format("MM");
      }
    },
    getPrevMoment() {
      return moment(moment(this.initialDate).subtract(1, "month" + "s"));
    },
    getNextMoment() {
      return moment(moment(this.initialDate).add(1, "month" + "s"));
    }
  }
};
</script>

<style scoped>
</style>
