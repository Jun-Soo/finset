<template>
  <input type="text" inputmode="numeric" pattern="[0-9]*" v-model="formatedValue" :placeholder="placeholder" :class="theme" :readonly="readonly" :disabled="disabled" />
</template>

<script>
export default {
  name: "Money",
  props: {
    // 표현되는 숫자 값
    value: {
      type: String
    },
    // placeholder 문구
    placeholder: {
      type: String,
      required: false
    },
    // 클래스 명
    theme: {
      type: String,
      required: false,
      default: ""
    },
    // readonly 설정 여부
    readonly: {
      type: Boolean,
      required: false,
      default: false
    },
    // disabled 설정 여부
    disabled: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  watch: {},
  data() {
    return {
      orgVal: ""
    };
  },
  components: {},
  computed: {
    formatedValue: {
      get: function() {
        if ((this.value || "") != "") {
          var numVal = parseInt(this.orgVal);
          if (!isNaN(numVal)) {
            return numVal.toLocaleString();
          } else {
            return "";
          }
        } else {
          return "";
        }
      },
      set: function(val) {
        this.orgVal = val.replace(/,/g, "");
        this.$emit("input", this.orgVal);
      }
    }
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
