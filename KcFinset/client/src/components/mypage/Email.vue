<template>
  <section v-if="seen">
    <div class="cert-wrap">
      <p class="title">이메일</p>
      <input id="emailInput" type="text" v-model="email" placeholder="이메일을 입력해주세요" v-validate="'required|max:11'" data-vv-name='이메일'>
      <p class="warn" v-if="errMsg">{{errMsg}}</p>
    </div>

    <div class="btn-wrap float">
      <a @click="modifyEmail()" class="solid box blue">저장</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageEmail",
  data() {
    return {
      email: "",
      errMsg: "",
      seen: false
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이메일 변경";
  },
  created() {},
  beforeMount() {},
  mounted() {
    var _this = this;
    var tEmail = localStorage.getItem("email");
    if (tEmail != null && tEmail != "") {
      _this.email = tEmail;
    } else {
      _this.email = "";
    }
    _this.seen = true;
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    modifyEmail: function() {
      let _this = this;
      var frm = new FormData();
      frm.append("email", _this.email);
      _this.$http
        .post("/m/person/modifyPersonEmail.json", frm)
        .then(response => {
          var result = response.data;
          console.log(result);
          if (result.result == "00") {
            this.$toast.center("변경 내용이 저장되었습니다.");
            this.$router.go(-1);
          } else {
            this.$toast.center(result.message);
            return false;
          }
        });
    },

    validate: function() {
      let _this = this;
      let regEx =
        "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
      if (_this.email.length < 6 || !regEx.test(_this.email)) {
        _this.errMsg = "메일형식이 맞지 않습니다. 다시 입력해주세요";
        $("#emailInput").focus();
      } else {
        _this.errMsg = "";
      }
    }
  }
};
</script>