<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a :class="{'on':tabKey == 'search'}" @click="clickTab('search')">검색</a>
        <a :class="{'on':tabKey == 'direct'}" @click="clickTab('direct')">직접입력</a>
      </div>
    </div>

    <div class="container">
      <ul v-if="tabKey == 'search'" class="debt-modify">
        <li>
          <p class="key">종류</p>
          <p>
            <multiselect v-model="selectObj.building_type" ref="sel_building_type" label="text" :show-labels="false" :options="options_building_type" placeholder="종류 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionFirst">
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">시/도</p>
          <p>
            <multiselect v-model="selectObj.region1" ref="sel_region1" label="text" :show-labels="false" :options="options_sel_region1" placeholder="시/도 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionSecond">
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">시/군/구</p>
          <p>
            <multiselect v-model="selectObj.region2" ref="sel_region2" label="text" :show-labels="false" :options="options_sel_region2" placeholder="시/군/구 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionThird">
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">읍/면/동</p>
          <p>
            <multiselect v-model="selectObj.region3" ref="sel_region3" label="text" :show-labels="false" :options="options_sel_region3" placeholder="읍/면/동 선택" :searchable="false" :allow-empty="false" @select="listSrchApartment">
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">아파트명</p>
          <p>
            <multiselect v-model="selectObj.apartment" ref="sel_apartment" label="text" :show-labels="false" :options="options_sel_apartment" placeholder="아파트명 선택" :searchable="false" :allow-empty="false" @select="scrapKbMarketPrice">
            </multiselect>
          </p>
        </li>
        <li>
          <p class="key">공급면적/전용면적</p>
          <p>
            <multiselect v-model="selectObj.pricePyeong" ref="sel_pricePyeong" label="text" :show-labels="false" :options="options_sel_pricePyeong" placeholder="면적 선택" :searchable="false" :allow-empty="false" @select="selectPricePyeong">
            </multiselect>
          </p>
        </li>
      </ul>
      <ul v-if="tabKey == 'direct'" class="debt-modify">
        <li>
          <p class="key">종류</p>
          <p>
            <input type="text" v-model="selectObj.direct" />
          </p>
        </li>
      </ul>
      <div v-if="isFinished" class="btn-wrap float">
        <a class="solid blue box">확인</a>
      </div>
    </div>

  </section>
</template>

<script>
import Common from "@/assets/js/common.js";

export default {
  name: "DebtCalcSearch",
  data() {
    return {
      tabKey: "search",
      options_building_type: [
        { text: "아파트", value: "1" },
        { text: "오피스텔", value: "2" }
      ],
      options_sel_region1: [],
      options_sel_region2: [],
      options_sel_region3: [],
      options_sel_apartment: [],
      options_sel_pricePyeong: [],
      listAddrRegion1: new Object(),
      listAddrRegion2: new Object(),
      listAddrRegion3: new Object(),
      listSrchApartmentInfo: new Object(),
      scrapKbMarketPriceList: new Object(),
      kbMarketPricePyeongList: new Object(),
      kbMarketPricePriceList: new Object(),
      selectObj: new Object(),
      isFinished: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "주택 입력";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    clickTab: function(key) {
      if (key == this.tabKey) {
        return;
      } else {
        this.tabKey = key;
      }
    },
    listAddrRegionFirst: function(param) {
      var building_type = param.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion1.json", {
          params: { building_type: building_type }
        })
        .then(function(response) {
          var list = response.data.listAddrRegion1;
          _this.options_sel_region1 = [];
          for (var idx in list) {
            _this.options_sel_region1.push({
              text: list[idx].nm_code,
              value: list[idx].code_value
            });
          }
          _this.listAddrRegion1 = list;
          _this.$refs.sel_region1.$el.focus();
        });
    },
    listAddrRegionSecond: function(param) {
      var region1_code = param.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion2.json", {
          params: { region1_code: region1_code }
        })
        .then(function(response) {
          var list = response.data.listAddrRegion2;
          _this.options_sel_region2 = [];
          for (var idx in list) {
            _this.options_sel_region2.push({
              text: list[idx].nm_code,
              value: list[idx].code_value
            });
          }
          _this.listAddrRegion2 = list;
          _this.$refs.sel_region2.$el.focus();
        });
    },
    listAddrRegionThird: function(param) {
      var region2_code = param.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion3.json", {
          params: { region2_code: region2_code }
        })
        .then(function(response) {
          var list = response.data.listAddrRegion3;
          _this.options_sel_region3 = [];
          for (var idx in list) {
            _this.options_sel_region3.push({
              text: list[idx].nm_code,
              value: list[idx].code_value
            });
          }
          _this.listAddrRegion3 = list;
          _this.$refs.sel_region3.$el.focus();
        });
    },
    listSrchApartment: function(param) {
      var building_type = this.selectObj.building_type.value;
      var region1_code = this.selectObj.region1.value;
      var region2_code = this.selectObj.region2.value;
      var region3_code = param.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listSrchApartmentInfo.json", {
          params: {
            building_type: building_type,
            region1_code: region1_code,
            region2_code: region2_code,
            region3_code: region3_code
          }
        })
        .then(function(response) {
          var list = response.data.listSrchApartmentInfo;
          _this.options_sel_apartment = [];
          for (var idx in list) {
            _this.options_sel_apartment.push({
              text: list[idx].apartment_name,
              value: list[idx].apartment
            });
          }
          _this.listSrchApartmentInfo = list;
          _this.$refs.sel_apartment.$el.focus();
        });
    },
    scrapKbMarketPrice: function(param) {
      var building_type = this.selectObj.building_type.value;
      var region1_code = this.selectObj.region1.value;
      var region2_code = this.selectObj.region2.value;
      var region3_code = this.selectObj.region3.value;
      var apartment = param.value;
      var _this = this;

      this.spinnerIsVisible = true;

      this.$http
        .get("/m/kbrealestate/scrapKbMarketPriceList.json", {
          params: {
            building_type: building_type,
            region1_code: region1_code,
            region2_code: region2_code,
            region3_code: region3_code,
            apartment: apartment
          }
        })
        .then(function(response) {
          var list = response.data.kbMarketPricePyeongList;
          for (var idx in list) {
            _this.options_sel_pricePyeong.push({
              text:
                list[idx].floor_plan_area_supply +
                "(" +
                list[idx].floor_plan_area_dedicated +
                ")",
              value: idx
            });
            _this.kbMarketPricePriceList[idx] =
              response.data.kbMarketPricePriceList[idx];
          }
          _this.kbMarketPricePyeongList = list;
          _this.$refs.sel_pricePyeong.$el.focus();
          _this.spinnerIsVisible = false;
        });
    },
    selectPricePyeong: function(param) {
      console.log(
        //실제 사용할 가격
        this.kbMarketPricePriceList[param.value].sale_general_average
      );
      this.isFinished = true;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
