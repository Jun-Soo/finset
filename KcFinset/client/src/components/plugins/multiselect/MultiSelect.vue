<template>
  <div style="width=100%; height=100%">
    <input :id="id" @click="open" type="button" :value="selected" class="btn-cate btn-search">
    <aside :class="{'on' : isShow}" class="search-wrap" v-show="isShow">
      <div class="top" @click="close">
        <button>{{title}}</button>
        <!-- <a v-if="setIsShow" class="btn-setting" @click="clickSetting"></a> -->
      </div>
      <div v-if="multiple" class="select-cate">
        <div class="cate-wrap">
          <ul>
            <li v-for="option in options" :key="option.value" :class="{'on':option.value==selected1}" @click="multiclick(option, 'm')">
              {{option.text}}
            </li>
          </ul>
        </div>
        <div class="cate-wrap" v-if="options!={}">
          <ul v-if="options[selected1] != undefined">
            <li v-for="(option, index) in options[selected1]['list']" :key="index" :class="{'on':option.value==selected2}" @click="multiclick(option, 'd')">
              {{option.text}}
            </li>
          </ul>
        </div>
      </div>
      <div v-if="multiple" class="action btn1">
        <a @click="clickConfirm" class="solid">확인</a>
      </div>
      <div v-if="!multiple" class="select-cate one">
        <div class="cate-wrap">
          <ul>
            <li v-for="option in options" :key="option.value" :class="{'on':option.value==selected1}" @click="click(option)">
              {{option.text}}
            </li>
          </ul>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
import multiselectMixin from "./multiselectMixin";

export default {
  name: "MultiSelect",
  mixins: [multiselectMixin],
  components: {},
  computed: {},
  watch: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    console.log(this.options);
    if (this.height) {
      if (this.height == "auto") {
        this.style = this.style + "height: auto;";
      } else {
        this.style = this.style + "height: " + this.height + "px;";
      }
    }
  },
  beforeUpdate() {},
  updated() {
    console.log(this.isShow);
  },
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
