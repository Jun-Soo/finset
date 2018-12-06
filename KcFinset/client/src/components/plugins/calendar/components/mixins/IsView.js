// import moment from 'moment'

export default {
  props: {
    activeDate: {
      type: Object
    },
    events: {
      type: Array
    }
  },
  methods: {
  },
  watch: {
    activeDate () {
      this.buildCalendar()
    },
    events () {
      this.buildCalendar()
    }
  }
}
