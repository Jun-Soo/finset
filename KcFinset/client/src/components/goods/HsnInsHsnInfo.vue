<template>
  <section>
    <div class="container">
      <p>주택정보를 입력해 주세요</p>
      <ul class="debt-modify">
        <li>
          <p class="key">종류</p>
          <p>
            <multiselect v-model="selectObj.building_type" ref="sel_building_type" label="text" :show-labels="false" :options="options_building_type" placeholder="종류 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionFirst" v-validate="'required'" data-vv-name='종류'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('종류')">{{errors.first('종류')}}</p>
        <li>
          <p class="key">시/도</p>
          <p>
            <multiselect v-model="selectObj.region1" ref="sel_region1" label="text" :show-labels="false" :options="options_sel_region1" placeholder="시/도 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionSecond" v-validate="'required'" data-vv-name='시/도'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('시/도')">{{errors.first('시/도')}}</p>
        <li>
          <p class="key">시/군/구</p>
          <p>
            <multiselect v-model="selectObj.region2" ref="sel_region2" label="text" :show-labels="false" :options="options_sel_region2" placeholder="시/군/구 선택" :searchable="false" :allow-empty="false" @select="listAddrRegionThird" v-validate="'required'" data-vv-name='시/군/구'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('시/군/구')">{{errors.first('시/군/구')}}</p>
        <li>
          <p class="key">읍/면/동</p>
          <p>
            <multiselect v-model="selectObj.region3" ref="sel_region3" label="text" :show-labels="false" :options="options_sel_region3" placeholder="읍/면/동 선택" :searchable="false" :allow-empty="false" @select="listSrchApartment" v-validate="'required'" data-vv-name='읍/면/동'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('읍/면/동')">{{errors.first('읍/면/동')}}</p>
        <li>
          <p class="key">아파트명</p>
          <p>
            <multiselect v-model="selectObj.apartment" ref="sel_apartment" label="text" :show-labels="false" :options="options_sel_apartment" placeholder="아파트명 선택" :searchable="false" :allow-empty="false" @select="scrapKbMarketPrice" v-validate="'required'" data-vv-name='아파트명'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('아파트명')">{{errors.first('아파트명')}}</p>
        <li>
          <p class="key">공급면적/전용면적</p>
          <p>
            <multiselect v-model="selectObj.pricePyeong" ref="sel_pricePyeong" label="text" :show-labels="false" :options="options_sel_pricePyeong" placeholder="면적 선택" :searchable="false" :allow-empty="false" @select="selectPricePyeong" v-validate="'required'" data-vv-name='면적'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('면적')">{{errors.first('면적')}}</p>
        <li>
          <p class="key">층수</p>
          <p>
            <multiselect v-model="selectObj.floor" ref="sel_floor" label="text" :show-labels="false" :options="options_sel_floor" placeholder="층수 선택" :searchable="false" :allow-empty="false" v-validate="'required'" data-vv-name='층수'>
            </multiselect>
          </p>
        </li>
        <p class="warn" v-if="errors.has('층수')">{{errors.first('층수')}}</p>
      </ul>
      <div class="btn-wrap float">
        <a @click="clickNext()" class="solid blue box">다음</a>
      </div>
    </div>
  </section>
</template>

<script>
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      cd_fc: this.$route.params.cd_fc,
      cd_goods: this.$route.params.cd_goods,
      no_bunch: this.$route.params.no_bunch,
      kcb_di: this.$route.params.kcb_di,
      ssn_person: this.$route.params.ssn_person,
      options_building_type: [
        { text: "아파트", value: "1" },
        { text: "오피스텔", value: "2" }
      ],
      options_sel_region1: [],
      options_sel_region2: [],
      options_sel_region3: [],
      options_sel_apartment: [],
      options_sel_pricePyeong: [],
      options_sel_floor: [],
      listAddrRegion1: new Object(),
      listAddrRegion2: new Object(),
      listAddrRegion3: new Object(),
      listSrchApartmentInfo: new Object(),
      scrapKbMarketPriceList: new Object(),
      kbMarketPricePyeongList: new Object(),
      kbMarketPricePriceList: new Object(),
      selectObj: new Object(),
      returnObj: {
        address: "",
        price: ""
      }
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "주택 정보";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //종류에 따른 시/도 데이터 조회
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
    //시/도 에 따른 시/군/구 데이터 조회
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
    //시/군/구 에 따른 읍/면/동 데이터 조회
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
    //상위 데이터들에 따른 아파트명 조회
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
    //상위 데이터들과 아파트명에 따른 공급면적/ 전용면적 조회
    scrapKbMarketPrice: function(param) {
      var building_type = this.selectObj.building_type.value;
      var region1_code = this.selectObj.region1.value;
      var region2_code = this.selectObj.region2.value;
      var region3_code = this.selectObj.region3.value;
      var apartment = param.value;
      var _this = this;

      this.$store.state.isLoading = true;

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
              value: list[idx].floor_plan_area_supply
            });
            _this.kbMarketPricePriceList[idx] =
              response.data.kbMarketPricePriceList[idx];
          }
          _this.kbMarketPricePyeongList = list;
          _this.$store.state.isLoading = false;

          _this.$refs.sel_pricePyeong.$el.focus();
          _this.returnObj.address = param.text;
          //층수
          if (response.data.kbMarketPriceComplexList) {
            var floor_highest = response.data.kbMarketPriceComplexList[0].floor_highest.slice(
              0,
              -1
            );
            for (var i = 1; i <= Number(floor_highest); i++) {
              _this.options_sel_floor.push({
                text: i + "층",
                value: i
              });
            }
          }
        });
    },
    //면적 선택시 사용될 함수
    selectPricePyeong: function(param) {
      //실제 가격
      // this.returnObj.price = this.kbMarketPricePriceList[
      //   param.value
      // ].sale_general_average;
      this.$refs.sel_floor.$el.focus();
    },
    clickNext: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          console.log(this.returnObj);
          _this.updateTxFc();
        }
      });
    },
    updateTxFc: function() {
      var _this = this;
      var formData = new FormData();
      formData.append("no_bunch", this.no_bunch);
      formData.append("cd_house_type", this.selectObj.building_type.value);
      formData.append("region_1", this.selectObj.region1.value);
      formData.append("region_2", this.selectObj.region2.value);
      formData.append("region_3", this.selectObj.region3.value);
      formData.append("security_addr", this.selectObj.apartment.value);
      formData.append(
        "security_exclusive_area",
        this.selectObj.pricePyeong.value
      );
      formData.append("security_floor", this.selectObj.floor.value);
      this.$http
        .post("/m/loanhomemortgage/modifyLoanREHomeInfo.json", formData)
        .then(function(response) {
          var result = response.data;
          if (result.result == "00") {
            console.log("success");
            _this.$router.push({
              name: "GoodsHsnInsIncome",
              params: {
                cd_fc: _this.cd_fc,
                cd_goods: _this.cd_goods,
                no_bunch: _this.no_bunch,
                kcb_di: _this.kcb_di,
                ssn_person: _this.ssn_person
              }
            });
          }
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
