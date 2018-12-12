<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="container mt30">
        <div class="checks">
          <!-- <div v-bind:class="checked"> -->
          <!--전체약관동의-->
          <input type="checkbox" id="check-all" v-model="chkAll" v-on:click="allChecked()"><label for="check-all">전체 약관 동의</label>

          <div class="box-agree">
            <p><input type="checkbox" name="checkbox1" id="checkbox1" v-model="chkBox1"><label for="checkbox1">[필수] 서비스 이용동의</label></p>
            <ul>
              <li><a v-on:click="openPop('1')">서비스 이용약관</a></li>
              <li><a v-on:click="openPop('2')">개인정보 처리방침</a></li>
              <li><a v-on:click="openPop('3')">KCB 올크레딧 이용약관</a></li>
              <li><a v-on:click="openPop('4')">개인정보 수집·이용 동의</a></li>
              <li><a v-on:click="openPop('5')">개인정보 제3자 제공 동의</a></li>
              <li><a v-on:click="openPop('6')">{{nm_code}}</a></li>
            </ul>
          </div>

          <div class="box-agree">
            <p><input type="checkbox" name="checkbox2" id="checkbox2" v-model="chkBox2"><label for="checkbox2">[필수] 통신사/본인확인 서비스 이용 동의</label></p>
            <ul>
              <li><a v-on:click="openPop('7')">본인확인서비스 이용약관</a>
              </li>
              <li><a v-on:click="openPop('8')">개인정보 수집 · 이용/취급위탁 동의</a>
              </li>
              <li><a v-on:click="openPop('9')">고유식별정보처리 동의</a>
              </li>
              <li><a v-on:click="openPop('10')">통신사 본인확인 이용약관 동의</a>
              </li>
            </ul>
          </div>

          <div class="box-agree">
            <p><input type="checkbox" name="checkbox3" id="checkbox3" v-model="chkBox3"><label for="checkbox3">[선택] 마케팅 정보 수신 동의</label></p>
            <ul>
              <li><a v-on:click="openPop('11')">마케팅 정보 수신 동의</a></li>
            </ul>
          </div>
        </div>
      </div>
      <!-- next button -->
      <div v-if="checked" class="btn-wrap">
        <a role="button" id="confirmButton" class="btn-next" v-on:click="confirmedTerms()">다음</a>
      </div>
    </section>
    <vue-modal transitionName="zoom-in" name="my-modal1" v-on:popclose="closePop('1')">
      <Terms1 slot="body" v-on:popclose="closePop('1')"></Terms1>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal2" v-on:popclose="closePop('2')">
      <Terms2 slot="body" v-on:popclose="closePop('2')"></Terms2>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal3" v-on:popclose="closePop('3')">
      <Terms3 slot="body" v-on:popclose="closePop('3')"></Terms3>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal4" v-on:popclose="closePop('4')">
      <Terms4 slot="body" v-on:popclose="closePop('4')"></Terms4>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal5" v-on:popclose="closePop('5')">
      <Terms5 slot="body" v-on:popclose="closePop('5')"></Terms5>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal6" v-on:popclose="closePop('6')">
      <Terms6 slot="body" v-on:popclose="closePop('6')"></Terms6>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal7" v-on:popclose="closePop('7')">
      <Terms7 slot="body" v-on:popclose="closePop('7')"></Terms7>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal8" v-on:popclose="closePop('8')">
      <Terms8 slot="body" v-on:popclose="closePop('8')"></Terms8>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal9" v-on:popclose="closePop('9')">
      <Terms9 slot="body" v-on:popclose="closePop('9')"></Terms9>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal10" v-on:popclose="closePop('10')">
      <Terms10 slot="body" v-on:popclose="closePop('10')"></Terms10>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal11" v-on:popclose="closePop('11')">
      <Terms11 slot="body" v-on:popclose="closePop('11')"></Terms11>
    </vue-modal>
  </div>

</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import Terms1 from "./Terms1.vue";
import Terms2 from "./Terms2.vue";
import Terms3 from "./Terms3.vue";
import Terms4 from "./Terms4.vue";
import Terms5 from "./Terms5.vue";
import Terms6 from "./Terms6.vue";
import Terms7 from "./Terms7.vue";
import Terms8 from "./Terms8.vue";
import Terms9 from "./Terms9.vue";
import Terms10 from "./Terms10.vue";
import Terms11 from "./Terms11.vue";

export default {
  name: "certStep1",
  data() {
    return {
      errMsg: "",
      checked: "",
      term_db: "",
      nm_code: "",
      chkAll: false,
      chkBox1: false,
      chkBox2: false,
      chkBox3: false
    };
  },
  components: {
    Terms1: Terms1,
    Terms2: Terms2,
    Terms3: Terms3,
    Terms4: Terms4,
    Terms5: Terms5,
    Terms6: Terms6,
    Terms7: Terms7,
    Terms8: Terms8,
    Terms9: Terms9,
    Terms10: Terms10,
    Terms11: Terms11
  },
  beforeCreate() {},
  created() {
    let _this = this;
    let frm = new FormData();

    _this.$store.state.title = "약관동의";
    frm.append("code_value", "1.0");
    frm.append("code_group", "OPENAPI_TERMS");
    _this.$http.post("/m/login/getAgreeTerm.json", frm).then(response => {
      //정상, 에러 처리 필요
      if(response.data.result =="1"){
        _this.term_db = response.data.etc;
        _this.nm_code = response.data.nm_code;
      }else { //openAPI 못불러옴

      }
    });
  },
  beforeMount() {},
  mounted() {
    var _this = this;
    $(":checkbox").change(function() {
      if (_this.chkBox1 && _this.chkBox2 && _this.chkBox3) {
        _this.chkAll = true;
        _this.checked = "check";
      } else {
        _this.chkAll = false;
        _this.checked = "";
      }

      if (_this.chkBox1 && _this.chkBox2) {
        _this.checked = "check";
      }

      if (_this.chkBox3) {
        _this.$store.state.user.isEventPush = true;
      }
    });
  },
  beforeUpdate() {},
  updated() {
    if (this.chkAll) window.scrollTo(0, window.innerHeight);
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {
    allChecked: function() {
      var _this = this;
      if (_this.chkAll) {
        _this.chkAll = false;
      } else {
        _this.chkAll = true;
      }

      if (_this.chkAll) {
        _this.checked = "check";
        _this.chkBox1 = true;
        _this.chkBox2 = true;
        _this.chkBox3 = true;
      } else {
        _this.checked = "";
        _this.chkBox1 = false;
        _this.chkBox2 = false;
        _this.chkBox3 = false;
      }
    },
    closePop: function(gubun) {
      var _this = this;
      _this.$modals.hide("my-modal" + gubun);
    },
    confirmedTerms: function() {
      var _this = this;
      if (_this.chkBox1 && _this.chkBox2) {
        _this.$router.push("/member/certStep2");
      }
    },
    openPop: function(gubun) {
      var _this = this;
      _this.$modals.show("my-modal" + gubun);
    }
  }
};
</script>