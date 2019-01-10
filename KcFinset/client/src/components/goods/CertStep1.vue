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
            <p><input type="checkbox" name="checkbox1" id="checkbox1" v-model="chkBox1"><label for="checkbox1">[필수] 개인(신용) 정보 활용 동의</label></p>
            <ul>
              <li><a v-on:click="openPop('1')">개인(신용)정보 수집·이용 동의</a>
              </li>
              <li><a v-on:click="openPop('1')">고유식별정보 수집·이용 동의</a>
              </li>
              <li><a v-on:click="openPop('3')">개인(신용)정보 제공·조회 동의</a>
              </li>
              <li><a v-on:click="openPop('3')">고유식별정보 제공·조회 동의</a>
              </li>
            </ul>
          </div>

          <div class="box-agree">
            <p><input type="checkbox" name="checkbox2" id="checkbox2" v-model="chkBox2"><label for="checkbox2">[필수] [사잇돌2대출조회] SGI 서울 보증 계약 체결, 이행 등을 위한 동의</label></p>
            <ul>
              <li><a v-on:click="openPop('5')">본인확인서비스 이용약관</a>
              </li>
              <li><a v-on:click="openPop('5')">개인정보 수집 · 이용/취급위탁 동의</a>
              </li>
              <li><a v-on:click="openPop('5')">고유식별정보처리 동의</a>
              </li>
              <li><a v-on:click="openPop('5')">통신사 본인확인 이용약관 동의</a>
              </li>
            </ul>
          </div>

          <div class="box-agree">
            <p><input type="checkbox" name="checkbox3" id="checkbox3" v-model="chkBox3"><label for="checkbox3">[필수] 통신사/본인확인 서비스 이용 동의</label></p>
            <ul>
              <li><a v-on:click="openPop('7')">본인확인서비스 이용약관</a>
              </li>
              <li><a v-on:click="openPop('8')">개인정보 수집·이용/취급위탁 동의</a>
              </li>
              <li><a v-on:click="openPop('9')">고유식별정보처리 동의</a>
              </li>
              <li><a v-on:click="openPop('10')">통신사 본인확인 이용약관 동의</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- next button -->
      <div v-if="chkAll" class="btn-wrap">
        <a role="button" id="confirmButton" class="btn-next" v-on:click="confirmedTerms()">다음</a>
      </div>
    </section>
    <vue-modal transitionName="zoom-in" name="my-modal1" v-on:popclose="closePop('1')">
      <Terms1 slot="body" v-on:popclose="closePop('1')"></Terms1>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal3" v-on:popclose="closePop('3')">
      <Terms3 slot="body" v-on:popclose="closePop('3')"></Terms3>
    </vue-modal>
    <vue-modal transitionName="zoom-in" name="my-modal5" v-on:popclose="closePop('5')">
      <Terms5 slot="body" v-on:popclose="closePop('5')"></Terms5>
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
  </div>

</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";
import Terms1 from "./sub/Terms1.vue";
import Terms3 from "./sub/Terms3.vue";
import Terms5 from "./sub/Terms5.vue";
import Terms7 from "./sub/Terms7.vue";
import Terms8 from "./sub/Terms8.vue";
import Terms9 from "./sub/Terms9.vue";
import Terms10 from "./sub/Terms10.vue";

export default {
  name: "certStep1",
  data() {
    return {
      chkAll: false,
      chkBox1: false,
      chkBox2: false,
      chkBox3: false
    };
  },
  components: {
    Terms1: Terms1,
    Terms3: Terms3,
    Terms5: Terms5,
    Terms7: Terms7,
    Terms8: Terms8,
    Terms9: Terms9,
    Terms10: Terms10
  },
  beforeCreate() {},
  created() {
    _this.$store.state.title = "약관동의";
    _this.$store.state.header.type = "sub";
  },
  beforeMount() {},
  mounted() {
    var _this = this;
    $(":checkbox").change(function() {
      if (_this.chkBox1 && _this.chkBox2 && _this.chkBox3) {
        _this.chkAll = true;
      } else {
        _this.chkAll = false;
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
      this.chkAll = !this.chkAll;
      if (_this.chkAll) {
        _this.chkBox1 = true;
        _this.chkBox2 = true;
        _this.chkBox3 = true;
      } else {
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
      if (_this.chkBox1 && _this.chkBox2 && _this.chkBox3) {
        // 상품조회 약관 승인 DB 저장
        var formData = new FormData();
        formData.append("no_person", this.$store.state.user.noPerson);
        this.$http
          .post("/m/person/createPersonAgreeHistGoods.json", formData)
          .then(function(response) {
            var result = response.data;
            if (result.result > 0) {
              _this.$router.push({
                name: "GoodsCertStep2",
                params: {
                  type: _this.$route.params.type,
                  cd_fc: _this.$route.params.cd_fc,
                  cd_goods: _this.$route.params.cd_goods
                }
              });
            } else {
              _this.$toast.center("약관내역 DB 저장 시 오류가 발생했습니다.");
            }
          });
      }
    },
    openPop: function(gubun) {
      var _this = this;
      _this.$modals.show("my-modal" + gubun);
    }
  }
};
</script>