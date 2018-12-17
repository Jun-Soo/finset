<template>
  <section>
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>자산을 직접 입력하여</em><br>관리하세요</p>
        <div class="list">
          <div>
            <ul class="flex">
              <li class="key">분류</li>
              <li class="value">
                <multiselect v-model="cd_assets_class" ref="cd_assets_class" placeholder="분류선택" track-by="text" label="text" :options="assetsClassOptions" :searchable="false" :allow-empty="false" @select="selectAssetsClass" v-validate="'required'" data-vv-name='분류'>
                </multiselect>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('분류')">{{errors.first('분류')}}</p>
          </div>
          <div v-if="showKey=='30'||showKey=='50'||showKey=='60'">
            <ul class="flex">
              <li class="key">{{title_detail_class}}</li>
              <li class="value">
                <multiselect v-model="cd_detail_class" ref="cd_detail_class" :placeholder="title_detail_class+'선택'" track-by="text" label="text" :options="options_detail_class" :searchable="false" :allow-empty="false" v-validate="'required'" :data-vv-name='title_detail_class'>
                </multiselect>
              </li>
            </ul>
            <p class="warn" v-if="errors.has(title_detail_class)">{{errors.first(title_detail_class)}}</p>
          </div>

          <!--부동산-->
          <div v-if="showKey=='30'">
            <ul class="flex">
              <li class="key">주소</li>
              <li class="value">
                <input type="text" v-model="view_addr" readonly="readonly">
                <input type="hidden" v-model="real_estate_addr" v-validate="'required'" data-vv-name='주소'>
                <button class="search" @click="scAddress()"></button>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('주소')">{{errors.first('주소')}}</p>
          </div>

          <!--자동차-->
          <div v-if="showKey=='40'">
            <ul class="flex">
              <li class="key">모델명</li>
              <li class="value">
                <input type="text" v-model="nm_model" v-validate="'required|max:15'" data-vv-name='모델명'>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('모델명')">{{errors.first('모델명')}}</p>
          </div>

          <!--귀금속-->
          <div v-if="showKey=='50'">
            <ul class="flex">
              <li class="key">보유량</li>
              <li class="value">
                <input type="number" v-model="amount_jewelry" v-validate="'required|numeric|max:12'" data-vv-name='보유량'>g
              </li>
            </ul>
            <p class="warn" v-if="errors.has('보유량')">{{errors.first('보유량')}}</p>
          </div>

          <!--외화-->
          <div v-if="showKey=='60'">
            <ul class="flex">
              <li class="key">보유금액</li>
              <li class="value">
                <input type="number" v-model="amt_balance" v-validate="'required|numeric|max:12'" data-vv-name='보유금액'>원
              </li>
            </ul>
            <p class="warn" v-if="errors.has('보유금액')">{{errors.first('보유금액')}}</p>
          </div>

          <!--기타-->
          <div v-if="showKey=='90'">
            <ul class="flex">
              <li class="key">종류</li>
              <li class="value">
                <input type="text" v-model="etc_assets" v-validate="'required|max:15'" data-vv-name='종류'>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('종류')">{{errors.first('종류')}}</p>
          </div>

          <!--공통-->
          <template v-if="showKey!=''">
            <div>
              <ul class="flex">
                <!--가격/환산금액-->
                <li class="key">{{title_amt_evaluation}}</li>
                <li class="value">
                  <input type="number" v-model="amt_evaluation" v-validate="'required|numeric|max:12'" :data-vv-name='title_amt_evaluation'>원
                </li>
              </ul>
              <p class="warn" v-if="errors.has(title_amt_evaluation)">{{errors.first(title_amt_evaluation)}}</p>
            </div>
            <div>
              <ul class="block">
                <li class="key">메모</li>
                <li class="value">
                  <input type="text" v-model="memo" v-validate="'max:15'" data-vv-name='메모'>
                </li>
              </ul>
              <p class="warn" v-if="errors.has('메모')">{{errors.first('메모')}}</p>
            </div>
          </template>
        </div>

      </div>
      <div v-if="isShowBtn" @click="createAssets()" class="btn-wrap float">
        <a class="solid blue box">등록하기</a>
      </div>
    </div>

    <vue-modal transitionName="zoom-in" name="postcode-modal">
      <div slot="body">
        <DaumPostcode :on-complete="selectPostcode" />
      </div>
    </vue-modal>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";

import DaumPostcode from "vuejs-daum-postcode";

export default {
  name: "assetsDirInput",
  data() {
    return {
      assetsClassOptions: [
        { text: "부동산", value: "30" },
        { text: "자동차", value: "40" },
        { text: "귀금속", value: "50" },
        { text: "외화", value: "60" },
        { text: "기타", value: "90" }
      ],
      cd_assets_class: "",
      showKey: "",
      title_detail_class: "",
      options_detail_class: [],
      cd_detail_class: "",
      view_addr: "", //부동산 - 주소(view용)
      real_estate_addr: "", //부동산 - 주소
      nm_model: "", //자동차 - 모델명
      amount_jewelry: "", //귀금속 - 보유량
      amt_balance: "", //외화 - 보유금액
      etc_assets: "", //기타 - 종류
      title_amt_evaluation: "",
      amt_evaluation: "", //공통 - 가격, 외화 - 환산금액
      memo: "", //공통 - 메모
      isShowModal: false,
      isShowBtn: false
    };
  },
  components: {
    DaumPostcode
  },
  computed: {},
  watch: {
    isShowModal: function(param) {
      if (param) {
        this.$modals.show("postcode-modal");
      } else {
        this.$modals.hide("postcode-modal");
      }
    }
  },
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "직접 입력";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //분류선택시
    selectAssetsClass: function(param) {
      var _this = this;

      _this.cd_detail_class = "";
      _this.errors.clear();
      _this.initForm();

      //selectbox Setting
      if ("30" == param.value) {
        _this.title_detail_class = "보유";
        _this.options_detail_class = Common.makeOptions("cd_assets_prop", "");
      } else if ("50" == param.value) {
        _this.title_detail_class = "종류";
        _this.options_detail_class = Common.makeOptions("cd_assets_nbmt", "");
      } else if ("60" == param.value) {
        _this.title_detail_class = "통화";
        _this.options_detail_class = Common.makeOptions("cd_assets_frcr", "");
      } else {
        _this.title_detail_class = "";
        _this.options_detail_class = [];
      }

      if ("60" == param.value) {
        _this.title_amt_evaluation = "환산금액";
      } else {
        _this.title_amt_evaluation = "가격";
      }

      _this.showKey = param.value;
      _this.isShowBtn = true;
    },
    initForm: function() {
      var _this = this;
      _this.title_detail_class = "";
      _this.cd_detail_class = "";
      _this.view_addr = "";
      _this.real_estate_addr = "";
      _this.nm_model = "";
      _this.amount_jewelry = "";
      _this.amt_balance = "";
      _this.etc_assets = "";
      _this.title_amt_evaluation = "";
      _this.amt_evaluation = "";
      _this.memo = "";
    },
    //주소검색
    scAddress: function() {
      this.isShowModal = true;
    },
    //다음 주소를 선택했을 때
    selectPostcode: function(param) {
      var _this = this;
      console.log(param.address);
      _this.view_addr = param.address;
      _this.real_estate_addr = param.address;
      this.isShowModal = false;
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    createAssets: function() {
      var _this = this;

      this.$validator.validateAll().then(res => {
        if (res) {
          Constant.options.title = "FINSET";
          this.$dialogs
            .confirm("자산정보를 등록하시겠습니까?", Constant.options)
            .then(res => {
              if (res.ok) {
                var cd_assets_class = _this.cd_assets_class.value;
                var formData = new FormData();
                formData.append("cd_assets_class", cd_assets_class);
                if (
                  "30" == cd_assets_class ||
                  "50" == cd_assets_class ||
                  "60" == cd_assets_class
                ) {
                  formData.append(
                    "cd_detail_class",
                    _this.cd_detail_class.value
                  );
                }
                formData.append("real_estate_addr", _this.real_estate_addr);
                formData.append("nm_model", _this.nm_model);
                formData.append("amount_jewelry", _this.amount_jewelry);
                formData.append("amt_balance", _this.amt_balance);
                formData.append("etc_assets", _this.etc_assets);
                formData.append("amt_evaluation", _this.amt_evaluation);
                formData.append("memo", _this.memo);

                this.$http
                  .post("/m/assets/createAssetsInfo.json", formData)
                  .then(response => {
                    this.$toast.center(response.data.message);
                    if ("00" == response.data.result) {
                      _this.$router.push("/assets/main");
                    }
                  })
                  .catch(e => {
                    this.$toast.center(ko.messages.error);
                  });
              }
            });
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
