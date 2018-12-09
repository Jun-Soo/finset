<template>
  <section v-if="seen">
    <div class="container">
      <multiselect track-by="text" v-model="orderType" label="text" :preselect-first="true" :options="options" :searchable="false" :allow-empty="false">
      </multiselect>
      <div v-if="orderType.value=='date'">
        <div class="nobox-list" v-for="(each, idx) in detailList" :key="idx">
          <p class="date" v-if="idx==0 || (idx!=0&&each.dt_trd!=detailList[idx-1].dt_trd)">{{formatDateDot(each.dt_trd)}}</p>
          <div class="item">
            <div class="flex">
              <p><em :class="each.color">{{each.nm_person}}</em>{{each.contents}}</p>
              <p><em class="number">{{numberWithCommas(each.amt_in_out)}}</em>원</p>
            </div>
            <div class="flex">
              <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />{{each.nm_class}}</p>
              <p class="key">{{each.nm_fc}}</p>
            </div>
          </div>
        </div>
      </div>
      <div v-else-if="orderType.value=='amt'">
        <div class="nobox-list" v-for="_item in detailList2" :key="_item.seq">
          <!-- <p class="date">{{formatDateDot(each.dt_trd)}}</p> -->
          <div class="item">
            <div class="flex">
              <p><em :class="_item.color">{{_item.nm_person}}</em>{{_item.contents}}</p>
              <p><em class="number">{{numberWithCommas(_item.amt_in_out)}}</em>원</p>
            </div>
            <div class="flex">
              <p class="key"><img src="../../assets/images/common/bu_list_drug.png" width="15px" class="mr5" alt="" />{{_item.nm_class}}</p>
              <p class="key">{{_item.nm_fc}}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "ConsumeIncomeStats",
  data() {
    return {
      shareList: [],
      options: [
        { text: "날짜순", value: "date" },
        { text: "금액순", value: "amt" }
      ],
      settingList: [
        { color: "circle red", id: "chk1", no_person: "", nm_person: "" },
        { color: "circle orange", id: "chk2", no_person: "", nm_person: "" },
        { color: "circle green", id: "chk3", no_person: "", nm_person: "" },
        { color: "circle blue", id: "chk4", no_person: "", nm_person: "" },
        { color: "circle purple", id: "chk5", no_person: "", nm_person: "" }
      ],
      orderType: { text: "날짜순", value: "date" },
      consumeForm: new FormData(),
      detailList: [],
      detailList2: [],
      initYN: true,
      seen: false
    };
  },
  components: {},
  watch: {
    orderType: function() {
      if (!this.initYN) {
        console.log(this.orderType.value);
        this.seen = false;
        this.getSettlementDetail();
      }
    }
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "지출상세";
  },
  created() {
    // console.log(this.$route.query);
    this.dataSet(this.$route.query);
    this.listConsumeShareInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {
    this.initYN = false;
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listConsumeShareInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listConsumeSharePersonInfo.json", { params: {} })
        .then(function(response) {
          var list = response.data.listConsumeSharePersonInfo;

          _this.shareList = list;
          _this.dataPeriod = "yr";
          _this.setPersonColor();
        });
    },
    setPersonColor: function() {
      for (var k in this.shareList) {
        this.settingList[k].no_person = this.shareList[k].no_person;
        this.settingList[k].nm_person = this.shareList[k].nm_person.substring(
          1
        );
      }
      this.getSettlementDetail();
    },
    getSettlementDetail: function() {
      let _this = this;
      let url = "/m/consume/getSettlementDetail.json";
      _this.consumeForm.set("orderType", _this.orderType.value);
      // _this.detailList = [];
      this.$http.post(url, _this.consumeForm).then(function(response) {
        let tList = response.data.rangeList;
        let sList = _this.settingList;
        for (var i in tList) {
          for (var h in sList) {
            if (tList[i].no_person == sList[h].no_person) {
              tList[i].color = sList[h].color;
              tList[i].nm_person = sList[h].nm_person;
              tList[i]["seq"] = i;
            }
          }
        }
        if (_this.orderType.value == "date") {
          _this.detailList = tList;
        } else {
          _this.detailList2 = tList;
        }
        _this.seen = true;
      });
    },
    dataSet: function(jsonObj) {
      let cFrm = this.consumeForm;

      cFrm.append("dt_from", jsonObj.dt_trd);
      cFrm.append("chartType", jsonObj.chartType);
      cFrm.append("listType", jsonObj.listType);
      cFrm.append("orderType", this.orderType.value);
      cFrm.append("type_in_out", jsonObj.type_in_out);
      cFrm.append("no_person_list", this.splitPersonList(jsonObj.personList));
      if (jsonObj.listType == "category") {
        cFrm.append("contents", jsonObj.nm_class);
      } else if (jsonObj.listType == "store") {
        cFrm.append("contents", jsonObj.contents);
      } else {
        cFrm.append("no_card", localStorage.getItem("no_card"));
        cFrm.append("contents", localStorage.getItem("nm_card"));
        localStorage.removeItem("no_card", idx.no_card);
        localStorage.removeItem("nm_card", idx.nm_card);
      }
    },
    splitPersonList: function(str) {
      return str.split(",");
    },
    numberWithCommas: function(x) {
      return x.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
