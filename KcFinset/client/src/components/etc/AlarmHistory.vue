<template>
  <section v-if="seen">
    <div class="nodata" v-if="alarmList.length == 0">알림 내역이 없습니다.</div>
    <div class="alarm-history" v-else v-for="(item, idx) in alarmList" :key="item.rnum">
      <div v-if="idx==0 || (idx!=0&&item.dt_frt!=alarmList[idx-1].dt_frt)">
        <p class="date">{{formatDateDot(item.dt_frt)}}</p>
        <div>
          <div class="alarm-list" v-for="_item in alarmList" :key="_item.rnum" v-if="_item.dt_frt==item.dt_frt">
            <div class="item">
              <a v-if="_item.link_addr!=null||_item.link_addr!=undefined" @click="moveDetailPage(_item.link_addr)">
                <p v-if="_item.push_divcd=='02'" class="ico alarm-credit">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='03'" class="ico alarm-debt">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='04'" class="ico alarm-goods">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='05'" class="ico alarm-event">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='06'" class="ico alarm-share">{{_item.title}}</p>
                <p v-else class="ico alarm">{{_item.title}}</p>
                <p class="text">{{_item.body}}</p>
              </a>
              <a v-else>
                <p v-if="_item.push_divcd=='02'" class="ico alarm-credit">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='03'" class="ico alarm-debt">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='04'" class="ico alarm-goods">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='05'" class="ico alarm-event">{{_item.title}}</p>
                <p v-else-if="_item.push_divcd=='06'" class="ico alarm-share">{{_item.title}}</p>
                <p v-else class="ico alarm">{{_item.title}}</p>
                <p class="text">{{_item.body}}</p>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";

export default {
  name: "EtcAlarmHistory",
  data() {
    return {
      push_divcd: "",
      page: 1,
      totalPage: 1,
      rnum: "",
      alarmList: new Array(),
      pushEachForm: {},
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "알림내역";
  },
  created() {},
  beforeMount() {},
  mounted() {
    let _this = this;
    if (_this.totalPage >= _this.page) {
      Common.pagination(this.getListNotification);
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    getListNotification: function() {
      let _this = this;
      let url = "/m/customercenter/listNotification.json";
      let frm = new FormData();
      frm.append("page", _this.page);

      // frm.append("push_divcd", "01");
      _this.$http.post(url, frm).then(response => {
        _this.page = response.data.pushEachForm.page;
        _this.pushEachForm = response.data.pushEachForm;
        _this.totalPage = response.data.pushEachForm.pageCount;
        if (_this.page == 1) {
          console.log();
          _this.alarmList = response.data.pagedList.source;
        } else {
          var pList = response.data.pagedList.source;
          for (var key in pList) {
            _this.alarmList.push(pList[key]);
          }
        }
        console.log(_this.pushEachForm);
        console.log(_this.alarmList);
        _this.page++;
        this.seen = true;
      });
    },
    moveDetailPage: function(url) {
      let _this = this;
      _this.$router.push(url);
    },
    formatDateDot: function(date) {
      return Common.formatDateDot(date);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
