<template>
  <section v-if="seen">
    <div class="alarm-setting">
      <ul v-if="!isiOS">
        <li>
          <p><em>방해금지모드</em>소리/진동 없이 알림 받기</p>
          <p><button v-bind:class="personVo.cd_push=='0'?btnOn:btnOff" name="notAlarm" @click="allChkPush"></button></p>
        </li>
      </ul>
      <ul id="pushes" name="pushes">
        <li v-if="cdPush.yn_use=='Y'" v-for="cdPush in cdPushes" :key="cdPush.code_value">
          <p><em>{{cdPush.nm_code}}</em>{{cdPush.etc}}</p>
          <p><button v-bind:id='cdPush.code_value' v-bind:name='"each_push"+cdPush.code_value' v-for="pushSetting in pushSettings" :key="pushSetting.item_push" v-if="pushSetting.item_push===cdPush.code_value" :class="pushSetting.yn_push==='Y' ? btnOn:btnOff" @click="eachChkPush"></button></p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

export default {
  name: "MypageRegAlarm",
  data() {
    return {
      personVo: new Array(),
      cdPushes: new Array(), //push list들 from comm_cd
      pushSettings: new Array(), // pushlist by person_no from push setting info,
      cList: new Array(),
      btnOn: "btn-onoff on",
      btnOff: "btn-onoff",
      //TO DO all푸시 - 소리진동타입update , each푸시 푸시항목명, yn체크, yn_push값 update
      type_push: "", //all / each
      cd_push: "", //all-소리/진동타입
      item_push: "", //each-푸시항목명
      stat_push: "", //each푸시 yn 체크
      yn_push: "", //all푸시 yn체크,
      seen: false,
      isiOS: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "알림설정";
  },
  created() {
    let _this = this;
    _this.$http
      .get("/m/customercenter/getCustomerNotificationSetting.json")
      .then(response => {
        _this.cdPushes = response.data.listCdPush;
        _this.pushSettings = response.data.listPushSetting;
        _this.personVo = response.data.personVO;
        for (var i = 0; i < _this.cdPushes.length; i++) {
          _this.cList.push(_this.cdPushes[i].code_value);
        }
        _this.seen = true;
      });
    if (Constant.userAgent === "iOS") {
      _this.isiOS = true;
    }
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    eachChkPush: function(obj) {
      //each 이면 modifyCdPush, insertCdPushHist
      let _this = this;
      let cnt = 0;
      _this.type_push = "each";
      //data 값 변경
      for (var i = 0; i < _this.pushSettings.length; i++) {
        if (_this.pushSettings[i].item_push == obj.target.id) {
          if (obj.target.className == _this.btnOff) {
            //킬때
            _this.stat_push = "Y";
            _this.pushSettings[i].yn_push = "Y";
          } else {
            //끌때
            _this.stat_push = "N";
            _this.pushSettings[i].yn_push = "N";
          }
        }
        if (_this.pushSettings[i].yn_push == "Y") {
          if (_this.cList.includes(_this.pushSettings[i].item_push)) {
            cnt++;
          }
        }
      }

      // cnt를 통해 each push가 하나라도 체크되어있는지 확인
      if (cnt == 0) {
        _this.yn_push = "N"; //모든 푸쉬 수신 안함
      } else {
        _this.yn_push = "Y";
      } //personVO에 넣는 작업

      let form = new FormData();
      form.append("stat_push", _this.stat_push);
      form.append("item_push", obj.target.id);
      form.append("type_push", _this.type_push);
      form.append("cd_push", obj.target.id);
      form.append("yn_push", _this.yn_push);
      // form.append("id_frt", _this.$store.state.user.noPerson);

      _this.$http
        .post("/m/person/modifyPushNoti.json", form, {
          header: {
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {});
    },
    //방해금지모드 설정 (only Android / iOS는 기능X)
    allChkPush: function(obj) {
      let _this = this;
      _this.type_push = "all";

      if (obj.target.className == _this.btnOff) {
        //킬때       //소리+진동X 푸시수신
        if (Constant.userAgent == "Android") {
          window.Android.settingPushType("0");
        }
        _this.cd_push = "0";
      } else {
        //끌때        //소리+진동O 푸시수신
        if (Constant.userAgent == "Android") {
          window.Android.settingPushType("1");
        }
        _this.cd_push = "1";
      }

      let form = new FormData();
      // form.append("stat_push", _this.stat_push);
      form.append("item_push", "");
      form.append("type_push", _this.type_push);
      form.append("cd_push", _this.cd_push);
      // form.append("yn_push", _this.yn_push);
      // form.append("id_frt", _this.$store.state.user.noPerson);

      _this.$http
        .post("/m/person/modifyPushNoti.json", form, {
          header: {
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {
          _this.personVo = response.data.personVO;
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
