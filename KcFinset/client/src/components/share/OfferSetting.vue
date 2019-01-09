<template>
  <section v-if="seen">
    <div class="con-top share-top">
      <!--상태에 따라 메세지 달라짐-->
      <template v-if="cd_share=='01'">
        <p v-if="shareInfo.share_status=='01'">{{shareInfo.req_nm_person}}님<em>이 정보제공을 요청한 항목입니다.</em></p>
        <p v-else>{{shareInfo.req_nm_person}}님<em>에게 제공중인 항목입니다.</em></p>
      </template>
      <template v-else-if="cd_share=='02'">
        <p v-if="shareInfo.share_status=='01'">{{shareInfo.req_nm_person}}님<em>이 공유 요청한 항목입니다.</em></p>
        <p v-else>{{shareInfo.req_nm_person}}님<em>과 공유중인 항목입니다.</em></p>
      </template>
      <div class="request">
        <ul>
          <li>
            <em class="key">요청일</em>
            <em>{{formatDateDot(shareInfo.dt_frt)}}</em>
          </li>
          <li v-if="shareInfo.share_status=='02'">
            <em class="key">허용일</em>
            <em>{{formatDateDot(shareInfo.dt_stt_offer)}}</em>
          </li>
          <li>
            <em class="key">상태</em>
            <em class="state">{{shareInfo.share_status=='01'? '대기중' : '공유중'}}</em>
          </li>
        </ul>
      </div>
    </div>

    <div v-if="cd_share=='01'" class="box-list noMG list02">
      <p class="header">정보 제공 항목</p>
      <div v-if="reqInfo.yn_credit_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">신용등급(연체정보 포함)</p>
          <p><button @click="changeItem('credit');" class="btn-onoff" :class="{on: yn_credit_info=='Y'}"></button></p>
        </div>
      </div>
      <div v-if="reqInfo.yn_debt_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">대출개설 및 잔고 현황</p>
          <p><button @click="changeItem('debt');" class="btn-onoff" :class="{on: yn_debt_info=='Y'}"></button></p>
        </div>
      </div>
    </div>
    <div v-else-if="cd_share=='02'" class="box-list noMG list02 pb90">
      <p class="header">통합관리용 정보 항목</p>
      <div v-if="reqInfo.yn_asset_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">자산정보 (계좌번호)</p>
          <p><button @click="changeItem('asset');" class="btn-onoff" :class="{on: yn_asset_info=='Y'}" :disabled="assetList.length==0"></button></p>
        </div>
        <div v-if="assetList.length==0">계좌 내역이 없습니다</div>
        <div v-else class="checks">
          <div class="small">
            <p v-for="assetInfo in assetList" :key="assetInfo.index">
              <input type="checkbox" v-model="assetKeys" :value="assetInfo.rnum" :id="'asset'+assetInfo.rnum" @change="changeCheckItem('asset');">
              <label v-if="assetInfo.cd_assets_class == '00'" :for="'asset'+assetInfo.rnum">{{assetInfo.nm_account}} ({{assetInfo.no_account}})</label>
              <label v-else-if="assetInfo.cd_assets_class == '10'" :for="'asset'+assetInfo.rnum">{{assetInfo.nm_account}}</label>
            </p>
          </div>
        </div>
      </div>
      <div v-if="reqInfo.yn_consume_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">소비 정보(카드내역 및 사용정보)</p>
          <p><button @click="changeItem('consume');" class="btn-onoff" :class="{on: yn_consume_info=='Y'}" :disabled="consumeList.length==0"></button></p>
        </div>
        <div v-if="consumeList.length==0">계좌 내역이 없습니다</div>
        <div v-else class="checks">
          <div class="small">
            <p v-for="consumeInfo in consumeList" :key="consumeInfo.index">
              <input type="checkbox" v-model="consumeKeys" :value="consumeInfo.rnum" :id="'consume'+consumeInfo.rnum" @change="changeCheckItem('consume');">
              <template v-if="consumeInfo.code_value=='01' || consumeInfo.code_value=='04'">
                <label :for="'consume'+consumeInfo.rnum">{{consumeInfo.nm_card}}</label>
              </template>
              <template v-else-if="consumeInfo.code_value=='02' || consumeInfo.code_value=='03'">
                <label :for="'consume'+consumeInfo.rnum">{{consumeInfo.nm_code}}</label>
              </template>
            </p>
          </div>
        </div>
      </div>
      <div v-if="reqInfo.yn_debt_info=='Y'" class="item">
        <div class="flex">
          <p class="corp big">부채정보</p>
          <p><button @click="changeItem('debt');" class="btn-onoff" :class="{on: yn_debt_info=='Y'}" :disabled="debtList.length==0"></button></p>
        </div>
        <div v-if="debtList.length==0">계좌 내역이 없습니다</div>
        <div v-else class="checks">
          <div class="small">
            <p v-for="debtInfo in debtList" :key="debtInfo.index">
              <input type="checkbox" v-model="debtKeys" :value="debtInfo.rnum" :id="'debt'+debtInfo.rnum" @change="changeCheckItem('debt');">
              <label :for="'debt'+debtInfo.rnum">{{debtInfo.nm_fc}} {{debtInfo.debt_type}} (잔액 : {{formatNumber(debtInfo.amt_remain)}})</label>
            </p>
          </div>
        </div>
      </div>
    </div>

    <div v-if="shareInfo.share_status=='02'" class="btn-wrap">
      <a @click="settingStatus('04');" class="stroke blue">공유 취소</a>
    </div>

    <!-- 공유상태에 따라 버튼 달라짐 -->
    <div v-if="shareInfo.share_status=='01'" class="btn-wrap col2">
      <a @click="settingStatus('03');" class="btn-solid">거절</a>
      <a @click="settingItems('02');" class="btn-stroke">허용</a>
    </div>
    <div v-else class="btn-wrap float">
      <a @click="settingItems('07');" class="solid blue box">저장</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "ShareOfferSetting",
  data() {
    return {
      errMsg: "",
      seen: false,
      cd_share: "01", //신용정보(01) / 금융정보(02) 구분
      share_status: "", //공유상태
      seq_share: "", //seq
      reqInfo: "", //공유요청정보
      shareInfo: "", //공유정보
      yn_credit_info: "", //신용YN
      yn_debt_info: "", //대출YN
      yn_asset_info: "", //자산YN
      yn_consume_info: "", //소비YN
      assetList: [], //자산정보list
      consumeList: [], //소비정보list
      debtList: [], //부채정보list
      assetKeys: [], //자산insert keys
      consumeKeys: [], //소비insert keys
      debtKeys: [], //부채insert keys
      addAssetList: [], //자산정보 addList
      addConsumeList: [], //소비정보 addList
      addDebtList: [], //부채정보 addList
      changeCnt: 0 //변경건수
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {
    var cd_share;
    if ("" != Constant.params.cd_share && Constant.params.cd_share != null) {
      cd_share = Constant.params.cd_share;
    } else {
      cd_share = this.$route.query.cd_share;
    }
    this.$store.state.header.type = "sub";
    if ("01" == cd_share) {
      this.$store.state.title = "신용정보 제공항목";
    } else if ("02" == cd_share) {
      this.$store.state.title = "금융정보 공유항목";
    }
    this.cd_share = cd_share;
    this.$store.state.header.backPath =
      "/share/main?cd_share=" + this.cd_share + "&type_list=offer";

    if ("" != Constant.params.seq_share && Constant.params.seq_share != null) {
      this.seq_share = Constant.params.seq_share;
    } else {
      this.seq_share = this.$route.query.seq_share;
    }
    this.getSettingInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //설정정보
    getSettingInfo: function() {
      var _this = this;
      this.$http
        .get("/m/customercenter/getShareInfoSetting.json", {
          params: { seq_share: _this.seq_share }
        })
        .then(response => {
          _this.reqInfo = response.data.reqInfo;
          _this.shareInfo = response.data.shareInfo;

          //항목값 셋팅
          if ("01" == _this.cd_share) {
            _this.yn_credit_info = _this.shareInfo.yn_credit_info;
            _this.yn_debt_info = _this.shareInfo.yn_debt_info;
          } else {
            _this.yn_asset_info = _this.shareInfo.yn_asset_info;
            _this.yn_consume_info = _this.shareInfo.yn_consume_info;
            _this.yn_debt_info = _this.shareInfo.yn_debt_info;
          }

          //금융정보 - 계좌list 셋팅(요청한것만)
          if ("02" == _this.cd_share) {
            if ("Y" == _this.reqInfo.yn_asset_info) {
              _this.assetList = response.data.assetList;
              if (_this.assetList.length == 0) _this.yn_asset_info = "";
            }
            if ("Y" == _this.reqInfo.yn_consume_info) {
              _this.consumeList = response.data.consumeList;
              if (_this.consumeList.length == 0) _this.yn_consume_info = "";
            }
            if ("Y" == _this.reqInfo.yn_debt_info) {
              _this.debtList = response.data.debtList;
              if (_this.debtList.length == 0) _this.yn_debt_info = "";
            }
          }

          //자산
          if ("Y" == _this.yn_asset_info) {
            for (var idx in _this.assetList) {
              if (
                "01" == _this.shareInfo.share_status ||
                ("02" == _this.shareInfo.share_status &&
                  "Y" == _this.assetList[idx].yn_share)
              ) {
                _this.assetKeys.push(_this.assetList[idx].rnum);
              }
            }
          }

          //소비
          if ("Y" == _this.yn_consume_info) {
            for (var idx in _this.consumeList) {
              if (
                "01" == _this.shareInfo.share_status ||
                ("02" == _this.shareInfo.share_status &&
                  "Y" == _this.consumeList[idx].yn_share)
              ) {
                _this.consumeKeys.push(_this.consumeList[idx].rnum);
              }
            }
          }

          //부채
          if ("Y" == _this.yn_debt_info) {
            for (var idx in _this.debtList) {
              if (
                "01" == _this.shareInfo.share_status ||
                ("02" == _this.shareInfo.share_status &&
                  "Y" == _this.debtList[idx].yn_share)
              ) {
                _this.debtKeys.push(_this.debtList[idx].rnum);
              }
            }
          }

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    formatDateDot: function(data) {
      return Common.formatDateDot(data);
    },
    formatNumber: function(data) {
      return Common.formatNumber(data);
    },
    //항목변경
    changeItem: function(nm_item) {
      var _this = this;

      if ("01" == _this.cd_share) {
        //신용정보
        if ("credit" == nm_item) {
          //신용
          if ("" == _this.yn_credit_info) {
            _this.yn_credit_info = "Y";
          } else {
            _this.yn_credit_info = "";
          }
        } else if ("debt" == nm_item) {
          if ("" == _this.yn_debt_info) {
            _this.yn_debt_info = "Y";
          } else {
            _this.yn_debt_info = "";
          }
        }
      } else {
        if ("asset" == nm_item) {
          //자산
          if ("" == _this.yn_asset_info) {
            _this.yn_asset_info = "Y";

            for (var idx in _this.assetList) {
              //모두 체크
              _this.assetKeys.push(_this.assetList[idx].rnum);
            }
          } else {
            _this.yn_asset_info = "";

            _this.assetKeys = []; //모두 체크해제
          }
        } else if ("consume" == nm_item) {
          //소비
          if ("" == _this.yn_consume_info) {
            _this.yn_consume_info = "Y";

            for (var idx in _this.consumeList) {
              _this.consumeKeys.push(_this.consumeList[idx].rnum);
            }
          } else {
            _this.yn_consume_info = "";

            _this.consumeKeys = [];
          }
        } else if ("debt" == nm_item) {
          //부채
          if ("" == _this.yn_debt_info) {
            _this.yn_debt_info = "Y";

            for (var idx in _this.debtList) {
              _this.debtKeys.push(_this.debtList[idx].rnum);
            }
          } else {
            _this.yn_debt_info = "";

            _this.debtKeys = [];
          }
        }
      }
    },
    //체크박스 변경
    changeCheckItem: function(nm_item) {
      var _this = this;
      if ("asset" == nm_item) {
        //자산
        if (_this.assetKeys.length != 0) {
          //하나라도 선택되면 Y
          _this.yn_asset_info = "Y";
        } else {
          //선택된게 없으면 N
          _this.yn_asset_info = "N";
        }
      } else if ("consume" == nm_item) {
        //소비
        if (_this.consumeKeys.length != 0) {
          //하나라도 선택되면 Y
          _this.yn_consume_info = "Y";
        } else {
          //선택된게 없으면 N
          _this.yn_consume_info = "N";
        }
      } else if ("debt" == nm_item) {
        //부채
        if (_this.debtKeys.length != 0) {
          //하나라도 선택되면 Y
          _this.yn_debt_info = "Y";
        } else {
          //선택된게 없으면 N
          _this.yn_debt_info = "N";
        }
      }
    },
    //공유상태변경(거절, 공유취소)
    settingStatus: function(share_status) {
      var _this = this;

      Constant.options.title = "FINSET";
      var dgContents = "";
      //거절
      if ("03" == share_status) {
        dgContents = "공유 요청을 거절하시겠습니까?";
        //공유취소
      } else if ("04" == share_status) {
        dgContents = "공유를 취소하시겠습니까?";
      }
      this.$dialogs.confirm(dgContnest, Constant.options).then(res => {
        if (res.ok) {
          console.log("seq_share" + _this.seq_share);

          var formData = new FormData();
          formData.append("seq_share", _this.seq_share);
          _this.share_status = share_status;
          formData.append("share_status", share_status);

          this.$http
            .post(
              "/m/customercenter/updatePersonShareInfoSetStatus.json",
              formData
            )
            .then(function(response) {
              _this.$toast.center(response.data.message);
              if ("00" == response.data.cdResult) {
                _this.sendPush(); //문자발송
              }
            })
            .catch(e => {
              _this.$toast.center(ko.messages.error);
            });
        }
      });
    },
    //공유항목변경(허용, 저장)
    settingItems: function(share_status) {
      var _this = this;

      //validation 체크
      if (!_this.validItems()) return false;

      Constant.options.title = "FINSET";
      var dgContents = "";
      //허용
      if ("02" == share_status) {
        dgContents = "공유 요청을 허용하시겠습니까?";

        //저장(항목변경)
      } else if ("07" == share_status) {
        dgContents = "공유항목을 변경하시겠습니까?";
      }
      this.$dialogs.confirm(dgContents, Constant.options).then(res => {
        if (res.ok) {
          if ("01" == _this.cd_share) {
            if (_this.yn_credit_info != _this.shareInfo.yn_credit_info)
              _this.changeCnt++;
            if (_this.yn_debt_info != _this.shareInfo.yn_debt_info)
              _this.changeCnt++;
          } else {
            //금융정보일 경우 계좌정보 셋팅
            _this.setAccounts();
          }

          if ("07" == share_status && _this.changeCnt == 0) {
            _this.$toast.center("변경사항이 없습니다.");
            return false;
          }

          console.log("cd_share" + _this.cd_share);
          console.log("seq_share" + _this.seq_share);
          console.log("share_status" + share_status);
          console.log("yn_credit_info" + _this.yn_credit_info);
          console.log("yn_debt_info" + _this.yn_debt_info);
          console.log("yn_asset_info" + _this.yn_asset_info);
          console.log("yn_consume_info" + _this.yn_consume_info);
          console.log("addAssetList_length" + _this.addAssetList.length);
          console.log("addConsumeList_length" + _this.addConsumeList.length);
          console.log("addDebtList_length" + _this.addDebtList.length);

          var formData = new FormData();
          formData.append("cd_share", _this.cd_share);
          formData.append("seq_share", _this.seq_share);
          _this.share_status = share_status;
          formData.append("share_status", share_status);
          if ("01" == _this.cd_share) {
            //신용
            formData.append("yn_credit_info", _this.yn_credit_info);
            formData.append("yn_debt_info", _this.yn_debt_info);
          } else {
            //금융
            formData.append("yn_asset_info", _this.yn_asset_info);
            formData.append("yn_consume_info", _this.yn_consume_info);
            formData.append("yn_debt_info", _this.yn_debt_info);
            formData.append("addAssetList", JSON.stringify(_this.addAssetList));
            formData.append(
              "addConsumeList",
              JSON.stringify(_this.addConsumeList)
            );
            formData.append("addDebtList", JSON.stringify(_this.addDebtList));
          }

          this.$http
            .post(
              "/m/customercenter/updatePersonShareInfoSetItems.json",
              formData
            )
            .then(function(response) {
              _this.$toast.center(response.data.message);
              if ("00" == response.data.cdResult) {
                _this.sendPush(); //문자발송
              }
            })
            .catch(e => {
              _this.$toast.center(ko.messages.error);
            });
        }
      });
    },
    //항목 선택여부 체크
    validItems: function() {
      var _this = this;

      if ("01" == _this.cd_share) {
        if (_this.yn_credit_info != "Y" && _this.yn_debt_info != "Y") {
          _this.$toast.center("항목을 선택해 주세요.");
          return false;
        }
      } else {
        if (
          _this.yn_asset_info != "Y" &&
          _this.yn_consume_info != "Y" &&
          _this.yn_debt_info != "Y"
        ) {
          _this.$toast.center("항목을 선택해 주세요.");
          return false;
        }
      }
      return true;
    },
    //계좌정보 셋팅
    setAccounts: function() {
      var _this = this;
      var isEqRnum = false;
      var idx = 0;

      _this.addAssetList = [];
      _this.addConsumeList = [];
      _this.addDebtList = [];
      _this.changeCnt = 0;

      //자산
      if ("Y" == _this.yn_asset_info) {
        for (var i = 0; i < _this.assetList.length; i++) {
          for (var j = 0; j < _this.assetKeys.length; j++) {
            if (_this.assetList[i].rnum == _this.assetKeys[j]) {
              isEqRnum = true;
              if ("Y" != _this.assetList[i].yn_share) {
                _this.changeCnt++;
              }
              _this.addAssetList[idx] = _this.assetList[i];
              idx++;
            }
          }

          if (!isEqRnum && "Y" == _this.assetList[i].yn_share) {
            _this.changeCnt++;
          }
          isEqRnum = false;
        }

        idx = 0;
      }
      //소비
      if ("Y" == _this.yn_consume_info) {
        for (var i = 0; i < _this.consumeList.length; i++) {
          for (var j = 0; j < _this.consumeKeys.length; j++) {
            if (_this.consumeList[i].rnum == _this.consumeKeys[j]) {
              isEqRnum = true;
              if ("Y" != _this.consumeList[i].yn_share) {
                _this.changeCnt++;
              }
              _this.addConsumeList[idx] = _this.consumeList[i];
              idx++;
            }
          }

          if (!isEqRnum && "Y" == _this.consumeList[i].yn_share) {
            _this.changeCnt++;
          }
          isEqRnum = false;
        }

        idx = 0;
      }
      //부채
      if ("Y" == _this.yn_debt_info) {
        for (var i = 0; i < _this.debtList.length; i++) {
          for (var j = 0; j < _this.debtKeys.length; j++) {
            if (_this.debtList[i].rnum == _this.debtKeys[j]) {
              isEqRnum = true;
              if ("Y" != _this.debtList[i].yn_share) {
                _this.changeCnt++;
              }
              _this.addDebtList[idx] = _this.debtList[i];
              idx++;
            }
          }

          if (!isEqRnum && "Y" == _this.debtList[i].yn_share) {
            _this.changeCnt++;
          }
          isEqRnum = false;
        }
      }

      idx = 0;
    },
    //push발송
    sendPush: function() {
      var _this = this;

      console.log("share_status" + _this.share_status);
      console.log("seq_share" + _this.seq_share);

      var formData = new FormData();
      formData.append("share_status", _this.share_status);
      formData.append("seq_share", _this.seq_share);
      this.$http
        .post("/m/customercenter/sendPersonShareInfoPush.json", formData)
        .then(function(response) {
          if ("00" == response.data.cdResult) {
            _this.$toast.center("푸시발송에 성공했습니다.");
            _this.goShareInfoMain();
          } else {
            _this.$toast.center("푸시발송에 실패했습니다.");
            return false;
          }
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    },
    //공유관리 메인으로 이동
    goShareInfoMain: function() {
      var _this = this;
      this.$router.push({
        name: "shareMain",
        query: { cd_share: _this.cd_share, type_list: "offer" }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
