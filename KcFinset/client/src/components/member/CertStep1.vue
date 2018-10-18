<template>
  <div id="wrapper" class="bg_white">
    <!-- Content -->
    <section id="content">
      <div class="container">
        <div class="check-all">
          <div class="checkbox" v-bind:class="checked"><label><input type="checkbox" id="checkall" v-model="chkAll" v-on:click="allChecked()"> 전체 약관 동의</label></div>
        </div>
      </div>
      <!-- panel -->
      <div class="panel-group agree-panel" id="termsList">
        <div class="panel panel-default">
          <div class="panel-heading">
            <div class="checkbox">
              <label>
  							<input type="checkbox" name="checkbox1" id="checkbox1" v-model="chkBox1" />
  							<a href="" class="collapsed" role="button" data-toggle="collapse"><em>[필수]</em> 서비스 이용동의</a>
  						</label>
            </div>
          </div>
          <div class="panel-collapse" id="panel1">
            <ul class="list-group">
              <li>
                <a class="list-group-item" v-on:click="open('1')"> <!--"popTerms('hp1')">-->
                  <label>서비스 이용약관</label>
                  <button name="show-popup" type="button" class="btn-terms" v-on:click="open">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal1">
                    <div slot="header">
                      <h3>서비스 이용약관</h3>
                    </div>
                    <Terms1></Terms1>
                  </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('2')">
                  <label>개인정보 처리방침</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal2">
                    <div slot="header">
                      <h3>개인정보 처리방침</h3>
                    </div>
                    <Terms2></Terms2>
                    </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('3')">
                  <label>KCB 올크레딧 이용약관</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal3">
                    <div slot="header">
                      <h3>KCB 올크레딧 이용약관</h3>
                    </div>
                    <Terms3></Terms3>
                  </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('4');">
                  <label>개인정보 수집 · 이용 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal4">
                    <div slot="header">
                      <h3>개인정보 수집 · 이용 동의</h3>
                    </div>
                    <Terms4></Terms4>
                    </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('5');">
                  <label>개인정보 제3자 제공 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal5">
                    <div slot="header">
                      <h3>개인정보 제3자 제공 동의</h3>
                    </div>
                    <Terms5></Terms5>
                  </vue-modal>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <div class="checkbox">
              <label>
  							<input type="checkbox" name="checkbox2" id="checkbox2" v-model="chkBox2" />
  							<a href="" class="collapsed" role="button" data-toggle="collapse"><em>[필수]</em> 휴대전화 본인인증 동의</a>
  						</label>
            </div>
          </div>
          <div class="panel-collapse" id="panel2">
            <ul class="list-group">
              <li>
                <a class="list-group-item" v-on:click="open('6');">
                  <label>본인확인서비스 이용약관</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal6">
                    <div slot="header">
                      <h3>본인확인서비스 이용약관</h3>
                    </div>
                    <Terms6></Terms6>
                  </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('7');">
                  <label>개인정보 수집 · 이용/취급위탁 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal7">
                    <div slot="header">
                      <h3>개인정보 수집 · 이용/취급위탁 동의</h3>
                    </div>
                    <Terms7></Terms7>
                  </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('8');">
                  <label>고유식별정보처리 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal8">
                    <div slot="header">
                      <h3>고유식별정보처리 동의</h3>
                    </div>
                    <Terms8></Terms8>
                  </vue-modal>
                </a>
              </li>
              <li>
                <a class="list-group-item" v-on:click="open('9');">
                  <label>통신사 본인확인 이용약관 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal9">
                    <div slot="header">
                      <h3>통신사 본인확인 이용약관 동의</h3>
                    </div>
                    <Terms9></Terms9>
                  </vue-modal>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <div class="checkbox">
              <label>
  							<input type="checkbox" name="checkbox3" id="checkbox3" v-model="chkBox3" />
  							<a href="" class="collapsed" role="button" data-toggle="collapse"><em>[선택]</em> 마케팅 정보 수신 동의</a>
  						</label>
            </div>
          </div>
          <div class="panel-collapse" id="panel3">
            <ul class="list-group">
              <li>
                <a class="list-group-item" v-on:click="open('10');">
                  <label>마케팅 정보 수신 동의</label>
                  <button type="button" class="btn-terms">약관보기</button>
                  <vue-modal transitionName="zoom-in" name="my-modal10">
                    <div slot="header">
                      <h3>마케팅 정보 수신 동의</h3>
                    </div>
                    <Terms10></Terms10>
                  </vue-modal>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- //panel -->
      <div class="btn-fixed-bottom" id="next_div">
        <a role="button" id="confirmButton" class="btn btn-lg btn-primary btn-block" v-on:click="confirmedTerms()">다음</a>
      </div>
    </section>
    <!-- //Content -->
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

export default {
  name: "certStep1",
  data() {
    return {
      errMsg: "",
      checked: "",
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
    Terms10: Terms10
  },
  beforeCreate() {},
  created() {
    if (Constant.userAgent == "Android") {
      window.Android.setEndApp("Y");
    }
    this.$store.state.title = "약관동의 (1/7)";
  },
  beforeMount() {},
  mounted() {
    var _this = this;

    $(":checkbox").change(function() {
      Common.affixBottom("hide");
      //약관 전체동의 체크
      if (_this.chkBox1 && _this.chkBox2 && _this.chkBox3) {
        _this.chkAll = true;
        _this.checked = "check";
      } else {
        _this.chkAll = false;
        _this.checked = "";
      }

      if (_this.chkBox1 && _this.chkBox2) {
        Common.affixBottom("show");
      }

      //마케팅 동의수신 체크시 parameter값 셋팅
      if (_this.chkBox3) {
        _this.$store.state.user.isEventPush = true;
      }
    });
  },
  beforeUpdate() {},
  updated() {},
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

    confirmedTerms: function() {
      var _this = this;
      if (_this.chkBox1 && _this.chkBox2) {
        _this.$router.push("/member/certStep2");
      }
    },

    open: function(gubun) {
      var _this = this;
      _this.$modals.show("my-modal" + gubun);
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
