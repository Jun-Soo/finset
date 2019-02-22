<template>
  <section v-if="seen">
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>자산을 직접 입력하여</em><br>관리하세요</p>
        <div class="list">
          <div>
            <ul class="flex">
              <li class="key">분류</li>
              <li class="value">
                <multiselect :id="'cd_assets_class'" v-model="cd_assets_class" ref="cd_assets_class" key="cd_assets_class" placeholder="분류선택" :title="'분류'" :options="assetsClassOptions" :onClose="selectAssetsClass" :disabled="!isNew" v-validate="'multi'" data-vv-name='분류'>
                </multiselect>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('분류')" v-html="'분류 항목은 필수 정보입니다.'"></p>
          </div>

          <!--부동산-->
          <template v-if="showKey=='30'">
            <div>
              <ul class="flex">
                <li class="key">보유</li>
                <li class="value">
                  <multiselect :id="'cd_class_prop'" v-model="cd_class_prop" ref="cd_class_prop" key="cd_class_prop" :title="'보유'" :options="option_class_prop" v-validate="'multi'" data-vv-name='보유'>
                  </multiselect>
                </li>
              </ul>
              <p class="warn" v-if="errors.has('보유')" v-html="'보유 항목은 필수 정보입니다.'"></p>
            </div>
            <div>
              <ul class="flex">
                <li class="key">주소</li>
                <li class="value">
                  <input type="text" v-model="view_addr" readonly="readonly">
                  <input type="hidden" v-model="real_estate_addr" key="real_estate_addr" v-validate="'required'" data-vv-name='주소'>
                  <button class="search" @click="scAddress()"></button>
                </li>
              </ul>
              <p class="warn" v-if="errors.has('주소')">{{errors.first('주소')}}</p>
            </div>
          </template>

          <!--자동차-->
          <div v-if="showKey=='40'">
            <ul class="flex">
              <li class="key">모델명</li>
              <li class="value">
                <input type="text" v-model="nm_model" key="nm_model" v-validate="'required|max:15'" data-vv-name='모델명'>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('모델명')">{{errors.first('모델명')}}</p>
          </div>

          <!--귀금속-->
          <template v-if="showKey=='50'">
            <div>
              <ul class="flex">
                <li class="key">종류</li>
                <li class="value">
                  <multiselect :id="'cd_class_nbmt'" v-model="cd_class_nbmt" ref="cd_class_nbmt" key="cd_class_nbmt" :title="'종류'" :options="option_class_nbmt" v-validate="'multi'" data-vv-name='종류'>
                  </multiselect>
                </li>
              </ul>
              <p class="warn" v-if="errors.has('종류')" v-html="'종류 항목은 필수 정보입니다.'"></p>
            </div>
            <div>
              <ul class="flex">
                <li class="key">보유량</li>
                <li class="value">
                  <input type="number" v-model="amount_jewelry" key="amount_jewelry" v-validate="'required|numeric|max:12'" data-vv-name='보유량'>g
                </li>
              </ul>
              <p class="warn" v-if="errors.has('보유량')">{{errors.first('보유량')}}</p>
            </div>
          </template>

          <!--외화-->
          <template v-if="showKey=='60'">
            <div>
              <ul class="flex">
                <li class="key">통화</li>
                <li class="value">
                  <multiselect :id="'cd_class_frcr'" v-model="cd_class_frcr" ref="cd_class_frcr" key="cd_class_frcr" :title="'종류'" :options="option_class_frcr" v-validate="'multi'" data-vv-name='통화'>
                  </multiselect>
                </li>
              </ul>
              <p class="warn" v-if="errors.has('통화')" v-html="'통화 항목은 필수 정보입니다.'"></p>
            </div>
            <div>
              <ul class="flex">
                <li class="key">보유금액</li>
                <li class="value">
                  <money v-model="amt_balance" key="amt_balance" v-validate="'required|numeric|max:12'" data-vv-name='보유금액' />원
                </li>
              </ul>
              <p class="warn" v-if="errors.has('보유금액')">{{errors.first('보유금액')}}</p>
            </div>
          </template>

          <!--기타-->
          <div v-if="showKey=='90'">
            <ul class="flex">
              <li class="key">종류</li>
              <li class="value">
                <input type="text" v-model="etc_assets" key="etc_assets" v-validate="'required|max:15'" data-vv-name='종류'>
              </li>
            </ul>
            <p class="warn" v-if="errors.has('종류')">{{errors.first('종류')}}</p>
          </div>

          <!--공통-->
          <template v-if="showKey!=''">
            <!--가격/환산금액-->
            <div v-if="showKey=='60'">
              <ul class="flex">
                <li class="key">환산금액</li>
                <li class="value">
                  <money v-model="amt_evaluation_frcr" key="amt_evaluation_frcr" v-validate="'required|numeric|max:12'" data-vv-name='환산금액' />원
                </li>
              </ul>
              <p class="warn" v-if="errors.has('환산금액')">{{errors.first('환산금액')}}</p>
            </div>
            <div v-else>
              <ul class="flex">
                <li class="key">가격</li>
                <li class="value">
                  <money v-model="amt_evaluation" key="amt_evaluation" v-validate="'required|numeric|max:12'" data-vv-name='가격' />원
                </li>
              </ul>
              <p class="warn" v-if="errors.has('가격')">{{errors.first('가격')}}</p>
            </div>
            <ul class="block">
              <li class="key">메모</li>
              <li class="value">
                <input type="text" v-model="memo" key="memo" v-validate="'max:15'" data-vv-name='메모'>
                <p class="warn" v-if="errors.has('메모')">{{errors.first('메모')}}</p>
              </li>
            </ul>
          </template>
        </div>

      </div>
      <div v-if="isShowBtn&&isNew" @click="createAssets()" class="btn-wrap float">
        <a class="solid blue box">등록하기</a>
      </div>
      <div v-else-if="isShowBtn&&!isNew" class="btn-wrap col2">
        <a @click="delAssets()">삭제</a>
        <a @click="modAssets()" class="btn-solid">저장</a>
      </div>
    </div>

    <vue-modal transitionName="zoom-in" name="postcode-modal">
      <div slot="body">
        <div class="v-modal__heading">
          <div class="v-modal__title">주소검색</div>
          <span @click="closePostcodeMd()" class="v-modal__close-btn">&times;</span>
        </div>
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
      seen: true,
      showKey: "",
      isShowModal: false,
      isShowBtn: false,
      isNew: true,
      sort: "",
      assetsClassOptions: [
        { text: "부동산", value: "30" },
        { text: "자동차", value: "40" },
        { text: "귀금속", value: "50" },
        { text: "외화", value: "60" },
        { text: "기타", value: "90" }
      ],
      cd_assets_class: "",
      cd_detail_class: "",
      option_class_prop: "", //부동산 - 보유(option)
      cd_class_prop: "", //부동산 - 보유
      view_addr: "", //부동산 - 주소(view용)
      real_estate_addr: "", //부동산 - 주소
      nm_model: "", //자동차 - 모델명
      option_class_nbmt: "", //귀금속 - 종류(option)
      cd_class_nbmt: "", //귀금속 - 종류
      amount_jewelry: "", //귀금속 - 보유량
      option_class_frcr: "", //외화 - 통화(option)
      cd_class_frcr: "", //외화 - 통화
      amt_balance: "", //외화 - 보유금액
      amt_evaluation_frcr: "", //외화 - 환산금액
      etc_assets: "", //기타 - 종류
      amt_evaluation: "", //공통 - 가격
      memo: "" //공통 - 메모
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
  created() {
    //수정, 삭제 - cd_assets_class값 셋팅
    if (
      "" != this.$route.query.cd_assets_class &&
      this.$route.query.cd_assets_class != null
    ) {
      for (var i = 0; i < this.assetsClassOptions.length; i++) {
        if (
          this.assetsClassOptions[i].value == this.$route.query.cd_assets_class
        ) {
          this.cd_assets_class = this.assetsClassOptions[i];
          this.selectAssetsClass(this.cd_assets_class);
        }
      }
    }
    this.sort = this.$route.query.sort; //수정, 삭제 - sort(seq)값 셋팅
    console.log(this.sort);
    if ((this.sort || "") != "") {
      this.isNew = false;
      this.seen = false;
    }
    this.option_class_prop = Common.makeOptions("cd_assets_prop", "");
    this.option_class_prop.unshift({ text: "선택", value: "" });
    this.option_class_nbmt = Common.makeOptions("cd_assets_nbmt", "");
    this.option_class_nbmt.unshift({ text: "선택", value: "" });
    this.option_class_frcr = Common.makeOptions("cd_assets_frcr", "");
    this.option_class_frcr.unshift({ text: "선택", value: "" });
    this.cd_class_prop = this.option_class_prop[0];
    this.cd_class_nbmt = this.option_class_nbmt[0];
    this.cd_class_frcr = this.option_class_frcr[0];

    if ((this.sort || "") != "") {
      this.getAssetsInfo();
    }
  },
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

      if (_this.showKey == param.value) return false;

      _this.errors.clear();
      _this.initForm();

      _this.showKey = param.value;

      _this.isShowBtn = true;
    },
    initForm: function() {
      var _this = this;
      _this.cd_class_prop = _this.option_class_prop[0];
      _this.cd_class_nbmt = _this.option_class_nbmt[0];
      _this.cd_class_frcr = _this.option_class_frcr[0];
      _this.view_addr = "";
      _this.real_estate_addr = "";
      _this.nm_model = "";
      _this.amount_jewelry = "";
      _this.amt_balance = "";
      _this.amt_evaluation_frcr = "";
      _this.etc_assets = "";
      _this.amt_evaluation = "";
      _this.memo = "";
    },
    //주소검색
    scAddress: function() {
      this.isShowModal = true;
    },
    //주소팝업close
    closePostcodeMd: function() {
      this.isShowModal = false;
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
                if ("30" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_prop.value);
                } else if ("50" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_nbmt.value);
                } else if ("60" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_frcr.value);
                }
                formData.append("real_estate_addr", _this.real_estate_addr);
                formData.append("nm_model", _this.nm_model);
                formData.append("amount_jewelry", _this.amount_jewelry);
                formData.append("amt_balance", _this.amt_balance);
                formData.append("etc_assets", _this.etc_assets);
                if ("60" == cd_assets_class) {
                  formData.append("amt_evaluation", _this.amt_evaluation_frcr);
                } else {
                  formData.append("amt_evaluation", _this.amt_evaluation);
                }
                formData.append("memo", _this.memo);

                this.$http
                  .post("/m/assets/createAssetsInfo.json", formData)
                  .then(response => {
                    this.$toast.center(response.data.message);
                    if ("00" == response.data.result) {
                      _this.$router.push("/assets/etcMain");
                    }
                  })
                  .catch(e => {
                    this.$toast.center(ko.messages.error);
                  });
              }
            });
        }
      });
    },
    getAssetsInfo: function() {
      var _this = this;
      this.$http
        .get("/m/assets/getAssetsEtcInfo.json", {
          params: {
            cd_assets_class: _this.cd_assets_class.value,
            sort: _this.sort
          }
        })
        .then(response => {
          var assetsInfo = response.data.assetsInfo;
          var cd_assets_class = assetsInfo.cd_assets_class;
          if ("30" == cd_assets_class) {
            //부동산
            for (var i = 0; i < _this.option_class_prop.length; i++) {
              if (
                _this.option_class_prop[i].value == assetsInfo.cd_detail_class
              ) {
                _this.cd_class_prop = _this.option_class_prop[i];
              }
            }
            _this.view_addr = assetsInfo.real_estate_addr; //주소
            _this.real_estate_addr = assetsInfo.real_estate_addr; //주소
          } else if ("40" == cd_assets_class) {
            //자동차
            _this.nm_model = assetsInfo.nm_model; //모델명
          } else if ("50" == cd_assets_class) {
            //귀금속
            for (var i = 0; i < _this.option_class_nbmt.length; i++) {
              if (
                _this.option_class_nbmt[i].value == assetsInfo.cd_detail_class
              ) {
                _this.cd_class_nbmt = _this.option_class_nbmt[i];
              }
            }
            _this.amount_jewelry = assetsInfo.amount_jewelry; //보유량
          } else if ("60" == cd_assets_class) {
            //외화
            for (var i = 0; i < _this.option_class_frcr.length; i++) {
              if (
                _this.option_class_frcr[i].value == assetsInfo.cd_detail_class
              ) {
                _this.cd_class_frcr = _this.option_class_frcr[i];
              }
            }
            _this.amt_balance = assetsInfo.amt_balance; //보유금액
          } else if ("90" == cd_assets_class) {
            //기타
            _this.etc_assets = assetsInfo.etc_assets; //종류
          }
          if ("60" == cd_assets_class) {
            //외화 - 환산금액
            _this.amt_evaluation_frcr = assetsInfo.amt_evaluation;
          } else {
            //공통 - 금액
            _this.amt_evaluation = assetsInfo.amt_evaluation;
          }
          //공통 - 메모
          _this.memo = assetsInfo.memo;
          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    modAssets: function() {
      var _this = this;

      this.$validator.validateAll().then(res => {
        if (res) {
          Constant.options.title = "FINSET";
          this.$dialogs
            .confirm("자산정보를 수정하시겠습니까?", Constant.options)
            .then(res => {
              if (res.ok) {
                var cd_assets_class = _this.cd_assets_class.value;
                var formData = new FormData();
                formData.append("sort", _this.sort);
                formData.append("cd_assets_class", cd_assets_class);
                if ("30" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_prop.value);
                } else if ("50" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_nbmt.value);
                } else if ("60" == cd_assets_class) {
                  formData.append("cd_detail_class", _this.cd_class_frcr.value);
                }
                formData.append("real_estate_addr", _this.real_estate_addr);
                formData.append("nm_model", _this.nm_model);
                formData.append("amount_jewelry", _this.amount_jewelry);
                formData.append("amt_balance", _this.amt_balance);
                formData.append("etc_assets", _this.etc_assets);
                if ("60" == cd_assets_class) {
                  formData.append("amt_evaluation", _this.amt_evaluation_frcr);
                } else {
                  formData.append("amt_evaluation", _this.amt_evaluation);
                }
                formData.append("memo", _this.memo);

                this.$http
                  .post("/m/assets/updateAssetsInfo.json", formData)
                  .then(response => {
                    this.$toast.center(response.data.message);
                    if ("00" == response.data.result) {
                      _this.$router.push("/assets/etcMain");
                    }
                  })
                  .catch(e => {
                    this.$toast.center(ko.messages.error);
                  });
              }
            });
        }
      });
    },
    delAssets: function() {
      var _this = this;

      Constant.options.title = "FINSET";
      this.$dialogs
        .confirm("자산정보를 삭제하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            var formData = new FormData();
            formData.append("sort", _this.sort);
            formData.append("cd_assets_class", _this.cd_assets_class.value);

            this.$http
              .post("/m/assets/deleteAssetsInfo.json", formData)
              .then(response => {
                this.$toast.center(response.data.message);
                if ("00" == response.data.result) {
                  _this.$router.push("/assets/etcMain");
                }
              })
              .catch(e => {
                this.$toast.center(ko.messages.error);
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
