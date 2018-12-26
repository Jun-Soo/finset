<template>
  <div style="width=100%; height=100%">
    <input @click="open" type="button" :disabled="disabled" :value="selected" class="btn-cate btn-multiselect" v-bind:class="{'on' : isShow}">
    <aside :id="id" :class="{'on' : isShow}" class="search-wrap">
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
      <div v-if="!multiple" class="select-cate one">
        <div class="cate-wrap" :style="cateSize(this)">
          <ul>
            <li v-for="option in options" :key="option.value" :class="{'on':option.value==selected1}" @click="click(option)">
              {{option.text}}
            </li>
          </ul>
        </div>
      </div>
      <div class="action btn1">
        <a @click="clickConfirm" class="solid" v-if="multiple">확인</a>
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
    if (this.height) {
      if (this.height == "auto") {
        this.style = this.style + "height: auto;";
      } else {
        this.style = this.style + "height: " + this.height + "px;";
      }
    }
    if (this.placeholder) {
      this.selected = this.placeholder;
    }
    this.chkSelectValue();
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.btn-cate.btn-multiselect {
  padding-right: 20px;
  background: url("../../../assets/images/consume/btn_cate.png") no-repeat 95%
    center/10px;
  color: #454545;
  text-align: left;
  width: 100%;
  padding-left: 10px;
}

aside.search-wrap .select-cate .cate-wrap:last-of-type ul li {
  text-align: left;
}

aside.search-wrap .select-cate.one .cate-wrap {
  display: block;
}
</style>
