<template>
  <!-- <input type="text" inputmode="numeric" pattern="[0-9]*" v-model="formatedValue" :id="id" :placeholder="placeholder" :class="theme" :readonly="readonly" :disabled="disabled" @change="change" /> -->
  <input type="text" autocomplete="off" inputmode="numeric" pattern="[0-9]*" v-model="formatedVal" :id="id" :placeholder="placeholder" :class="theme" :name="name" :readonly="readonly" :disabled="disabled" @focus="deformatValue" @blur="formatValue" />
</template>

<script>
export default {
  name: "Money",
  props: {
    // input 태그의 id
    id: {
      type: String,
      required: false
    },
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
      required: false
    },
    // input 태그의 name
    name: {
      type: String,
      required: false
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
    },
    // change 이벤트
    change: {
      type: Function,
      required: false,
      default: function() {}
    }
  },
  watch: {
    value: function(val) {
      this.formatValue(val);
      if (this.change) {
        this.change();
      }
    }
  },
  data() {
    return {
      orgVal: "",
      formatedVal: ""
    };
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    this.formatValue(this.value);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    formatValue: function(val) {
      if ((val || "") == "") {
        val = "";
      }
      this.orgVal =
        typeof val == "string" || typeof val == "number"
          ? val.replace(/[^0-9]/g, "")
          : this.formatedVal.replace(/[^0-9]/g, "");
      var numVal = parseInt(this.orgVal);
      if (isNaN(numVal)) {
        this.formatedVal = "";
      } else {
        this.formatedVal = numVal.toLocaleString();
      }
      this.$emit("input", this.orgVal);
    },
    deformatValue: function() {
      if (!this.readonly && !this.disabled) {
        this.formatedVal = this.orgVal;
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
