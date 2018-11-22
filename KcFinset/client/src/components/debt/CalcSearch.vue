<template>
  <section>
    <div class="tab">
      <div class="wrap">
        <a href="#" class="on">검색</a>
        <a href="#">직접입력</a>
      </div>
    </div>

    <div class="container">
      <ul class="debt-modify">
        <li>
          <p class="key">종류</p>
          <p>
            <select id="sel_building_type" @change="listAddrRegionFirst">
              <option selected="selected" disabled="disabled">종류선택</option>
              <option value="1">아파트</option>
              <option value="2">오피스텔</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">시/도</p>
          <p>
            <select id="sel_region1" @change="listAddrRegionSecond">
              <option selected="selected" disabled="disabled">시/도 선택</option>
              <option v-for="(firstVO, index) in listAddrRegion1" :key="index" :value="firstVO.code_value">{{firstVO.nm_code}}</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">시/군/구</p>
          <p>
            <select id="sel_region2" @change="listAddrRegionThird">
              <option selected="selected" disabled="disabled">시/군/구 선택</option>
              <option v-for="(secondVO, index) in listAddrRegion2" :key="index" :value="secondVO.code_value">{{secondVO.nm_code}}</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">읍/면/동</p>
          <p>
            <select id="sel_region3" @change="listSrchApartment">
              <option selected="selected" disabled="disabled">읍/면/동 선택</option>
              <option v-for="(thirdVO, index) in listAddrRegion3" :key="index" :value="thirdVO.code_value">{{thirdVO.nm_code}}</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">아파트명</p>
          <p>
            <select @change="scrapKbMarketPrice">
              <option selected="selected" disabled="disabled">아파트명 선택</option>
              <option v-for="(fourthVO, index) in listSrchApartmentInfo" :key="index" :value="fourthVO.apartment">{{fourthVO.apartment_name}}</option>
            </select>
          </p>
        </li>
        <li>
          <p class="key">공급면적/전용면적</p>
          <p>
            <select>
              <option>면적 선택</option>
              <option v-for="(fifthVO, index) in kbMarketPricePyeongList" :key="index" :value="fifthVO.floor_plan_area_supply + '/' + fifthVO.floor_plan_area_dedicated">
                {{fifthVO.floor_plan_area_supply +"("+fifthVO.floor_plan_area_dedicated+")"}}
              </option>
            </select>
          </p>
        </li>
      </ul>
      <div class="btn-wrap float">
        <a href="#" class="solid blue box">확인</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtCalcSearch",
  data() {
    return {
      listAddrRegion1: new Object(),
      listAddrRegion2: new Object(),
      listAddrRegion3: new Object(),
      listSrchApartmentInfo: new Object(),
      scrapKbMarketPriceList: new Object(),
      kbMarketPricePyeongList: new Object(),
      kbMarketPricePriceList: new Object()
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
    listAddrRegionFirst: function(param) {
      var building_type = param.target.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion1.json", {
          params: { building_type: building_type }
        })
        .then(function(response) {
          _this.listAddrRegion1 = response.data.listAddrRegion1;
        });
    },
    listAddrRegionSecond: function(param) {
      var region1_code = param.target.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion2.json", {
          params: { region1_code: region1_code }
        })
        .then(function(response) {
          _this.listAddrRegion2 = response.data.listAddrRegion2;
        });
    },
    listAddrRegionThird: function(param) {
      var region2_code = param.target.value;
      var _this = this;
      this.$http
        .get("/m/kbrealestate/listAddrRegion3.json", {
          params: { region2_code: region2_code }
        })
        .then(function(response) {
          _this.listAddrRegion3 = response.data.listAddrRegion3;
        });
    },
    listSrchApartment: function(param) {
      var building_type = document.getElementById("sel_building_type").value;
      var region1_code = document.getElementById("sel_region1").value;
      var region2_code = document.getElementById("sel_region2").value;
      var region3_code = param.target.value;
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
          _this.listSrchApartmentInfo = response.data.listSrchApartmentInfo;
        });
    },
    scrapKbMarketPrice: function(param) {
      var building_type = document.getElementById("sel_building_type").value;
      var region1_code = document.getElementById("sel_region1").value;
      var region2_code = document.getElementById("sel_region2").value;
      var region3_code = document.getElementById("sel_region3").value;
      var apartment = param.target.value;
      var _this = this;

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
          _this.kbMarketPricePyeongList = response.data.kbMarketPricePyeongList;
          console.log(response.data.kbMarketPricePyeongList);
          console.log(response.data.kbMarketPricePriceList);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
