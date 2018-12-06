<template>
  <div>
    <div class="pop-top">
      <p class="title">금융정보 제공동의서</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
      <!-- v-on:click="this.$parent.@click('inner')" -->
    </div>
    <!-- Content -->
    <div class="container pop-wrap">
      <div v-for="article in articles" v-bind:key='article.key'>
        <p class="mt15">{{article.text}}</p>
      </div>
    </div>
  </div>
</template>
	
<script>
export default {
  name: "Terms1",
  props: {
    text: {
      // 금융정보 제공동의서
      type: String,
      default: ""
    }
  },
  data() {
    return {
      articles: new Array()
    };
  },
  created() {
    var _this = this;
    _this.transfer(this.text);
  },
  methods: {
    transfer(str) {
      let _this = this;
      let tmplist = str.split("\n");
      debugger;
      for (var i = 0; i < tmplist.length; i++) {
        if (tmplist[i] == "\n") continue;
        var tJson = {};
        tJson["text"] = tmplist[i];
        tJson["key"] = i;
        _this.articles.push(tJson);
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>