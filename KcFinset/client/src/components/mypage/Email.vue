<template>
  <section>
    <div class="cert-wrap">
      <p class="title">이메일</p>
      <input id="emailInput" type="text" v-model="email">
      <p class="warn" v-if="errMsg">{{errMsg}}</p>
    </div>

    <div class="btn-wrap float">
      <a @click="modifyEmail()" class="solid box blue">다음</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "MypageEmail",
  data() {
    return {
      email:'',
      errMsg:''
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "이메일 변경";
  },
  created() {
  },
  beforeMount() {},
  mounted() {
    $('#emailInput').attr("value", localStorage.getItem('email'));
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {
  },
  destroyed() {},
  methods: {
    modifyEmail : function(){
      let _this = this;
      debugger;
      var frm = new FormData();
      frm.append("no_person", _this.no_person);
      frm.append("email", _this.email)
      _this.$http.post("/m/person/modifyPersonEmail.json", frm)
      .then(response=>{
        var result = response.data;
          console.log(result);
          if (result.result == "00") {
            this.$toast.center("이메일을 수정하였습니다");
          } else {
            this.$toast.center(result.message);
            return false;
          }
      }).catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },

    validate : function(){
      let _this= this;
      let regEx = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
      if(_this.email.length<6 || !regEx.test(_this.email)) {
        _this.errMsg = '메일형식이 맞지 않습니다. 다시 입력해주세요';
        $('#emailInput').focus();
      }else{
        _this.errMsg ='';
      }
    }

  }
};
</script>