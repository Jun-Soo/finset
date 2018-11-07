<template>
  <section>
    <div class="alarm-setting">
      <ul>
        <li>
          <p><em>방해금지모드</em>소리/진동 없이 알림 받기</p>
          <p><button class="btn-onoff on" @click="allChkPush"></button></p>
        </li>
        <li v-for="cdPush in cdPushes" :key="cdPush.code_value">
          <p><em>{{cdPush.nm_code}}</em>{{cdPush.etc}}</p>
          <p><button v-bind:id="cdPush.code_value" name="each_push" v-for="pushSetting in pushSettings" :key="pushSetting.item_push" v-if="pushSetting.item_push===cdPush.code_value" :class="pushSetting.yn_push==='Y' ? 'btn-onoff on':'btn-onoff'" @click="eachChkPush"></button></p>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageRegAlarm",
  data() {
    return {
      cdPushes: new Array(),
      pushSettings: new Array(),
      //TO DO all푸시 - 소리진동타입update , each푸시 푸시항목명, yn체크, yn_push값 update
      type_push: "", //all / each
      cd_push: "", //all-소리/진동타입
      item_push: "", //each-푸시항목명
      stat_push: "", //each푸시 yn 체크
      yn_push: "", //all푸시 yn체크,
      all_push: "",
      each_push: ""
      // pushId : 'push'+1
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
      .get("/m/customercenter/frameCustomerNotificationSetting.crz")
      .then(response => {
        _this.cdPushes = response.data.listCdPush;
        _this.pushSettings = response.data.listPushSetting;
      });
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    eachChkPush: function(obj) {
      let _this = this;
      _this.type_push = "each";
      debugger;
      if (obj.target.className == "btn-onoff") {
        // 킬때
        _this.stat_push = "Y";
      } else {
        _this.stat_push = "N";
      }

      var cnt = 0;
      //푸쉬하나라도 체크되어있는지 확인
      // $.each($("input[name='each_push']"), function(index, item) {
      //   if ($(this).is(":checked")) {
      //     cnt++;
      //   }
      // });
      if (cnt == 0) {
        $("#yn_push").val("N"); //모든 푸쉬 수신 안함
      } else {
        $("#yn_push").val("Y"); //푸쉬 수신
      }
      let form = new FormData();
      form.append();
      _this.$http
        .post("/m/person/modifyPushNoti.json", form, {
          header: {
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {});
    },
    //방해금지모드 설정
    allChkPush: function(obj) {
      let _this = this;
      _this.type_push = "all";

      if ($(obj).is(":checked")) {
        //소리+진동X 푸시수신
        if (userAgent == "Android") {
          window.Android.settingPushType("0");
        }
        $("#cd_push").val("0");

      } else {
        //소리+진동O 푸시수신
        if (userAgent == "Android") {
          window.Android.settingPushType("1");
        }

        $("#cd_push").val("1");

      }

      $("#item_push").val("");

      var data = frmPushStep.ajaxSubmit();
      $.ajax({
        url: "<c:url value='/m/person/modifyPushNoti.json'/>",
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        type: "POST",
        async: false,
        success: function(result) {},
        error: function(e) {
          errMsg(e);
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
