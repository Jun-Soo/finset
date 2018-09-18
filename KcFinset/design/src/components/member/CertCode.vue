<template>
  <div id="wrapper">
    <!-- Content -->
    <section id="content">
      <div class="container security-code">
        <div class="security-code-wrap security-passw">
          <p>
            {{ certMessage }}
          </p>
          <div class="code-group clearfix">
            <div class="code form-shake">
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass1">코드번호</label>
                <input type="password" class="form-control code-no active" name="pass_number" id="pass_number1" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass2">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number2" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass3">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number3" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark" v-bind:class="classPass4">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number4" maxlength="1" value="" placeholder="" />
              </div>
              <!-- <div class="mark-group">
                <label for="" class="code-mark">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number5" maxlength="1" value="" placeholder="" />
              </div>
              <div class="mark-group">
                <label for="" class="code-mark">코드번호</label>
                <input type="password" class="form-control code-no" name="pass_number" id="pass_number6" maxlength="1" value="" placeholder="" />
              </div> -->
            </div>
          </div>
          <ul class="keypad">
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('1')">1</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('2')">2</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('3')">3</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('4')">4</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('5')">5</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('6')">6</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('7')">7</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('8')">8</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" v-on:click="btnClick('9')">9</button></li>
            <li class="btn-none"><button type="button" class="btn btn-lg btn-block">&nbsp;</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
            <li><button type="button" class="btn btn-lg btn-block btn-backspace" v-on:click="backClick()">←</button></li>
          </ul>
        </div>
      </div>
    </section>
  	<!-- //Content -->
  </div>
</template>

<script>
import Common from "./../../assets/js/common.js";
import Constant from "./../../assets/js/constant.js";

import ko from "vee-validate/dist/locale/ko.js";

export default {
  name: "certCode",
  data() {
    return {
      errMsg: "",
      certMessage: "",
      noPerson: this.$store.state.user.noPerson,
      j_password: "",
      tempPwd: "",
      chkPwd: false,
      chkFingerPrint: "",
      //class
      classPass1: "",
      classPass2: "",
      classPass3: "",
      classPass4: ""
    };
  },
  component: {
  },
  computed: {
  },
  beforeCreate() {
  },
  created() {
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
  .memberMain {background-color: #283593; height: 100%;}
</style>
