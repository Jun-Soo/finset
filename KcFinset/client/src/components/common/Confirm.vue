<template>
  <div id="modal-confirm" class="modal-confirm" style="display: block;">
    <div class="debt-modal-body">
      <p class="text" v-text="modalText"></p>
    </div>
    <div class="modal-btns">
      <a v-if="!isSingle" @click="cancel" class="stroke">취소</a>
      <a @click="confirm" class="solid">확인</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommonConfirm",
  props: {
    modalText: {
      type: String,
      required: false,
      default: ""
    },
    cancel: {
      type: Function,
      required: false,
      default: function() {}
    },
    confirm: {
      type: Function,
      required: false,
      default: function() {}
    },
    isSingle: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    var modal = document.getElementById("modal-confirm");

    if (modal) {
      var marginWidth = modal.offsetWidth / 2;
      modal.style.marginLeft = "-" + marginWidth + "px";
      var marginHeight = modal.offsetHeight / 2;
      modal.style.marginTop = "-" + marginHeight + "px";
    }
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    marginLeft: function(param1, param2, param3) {
      console.log(param1);
      console.log(param2);
      console.log(param3);
      return "width:200px";
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
