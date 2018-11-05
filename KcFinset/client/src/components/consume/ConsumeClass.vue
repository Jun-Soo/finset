<template>
  <div>
    <section>
      <div class="box-list noMG">
        <ul class="consume-cate-list">
          <draggable v-model="consumeCategory" @start="drag=true" @end="drag=false">
            <li v-for="(eachClass, index) in consumeCategory" :key="index">
              <div class="wrap each-class">
                <p class="title">{{eachClass[1]}}</p>
                <p class="links">
                  <button class="delete"></button>
                  <button class="modify"></button>
                </p>
              </div>
              <ul>
                <draggable>
                  <li v-for="eachType in eachClass[2]" :key="eachType.nm_class">
                    <div class="wrap">
                      <p class="title">{{eachType.nm_type}}</p>
                      <p class="links">
                        <button class="delete"></button>
                        <button class="modify"></button>
                      </p>
                    </div>
                  </li>
                </draggable>
                <button class="add-cate" @click="clickAdd('type')">항목추가</button>
              </ul>
            </li>
          </draggable>

          <!-- <li v-for="eachClass in consumeCategory" :key="eachClass.cd_class">
            <div class="wrap">
              <p class="title">{{eachClass.nm_class}}</p>
              <p class="links">
                <button class="delete"></button>
                <button class="modify"></button>
              </p>
            </div>
            <ul v-if="eachClass != undefined">
              <li v-for="eachType in eachClass['listCdType']" :key="eachType.nm_class">
                <div class="wrap">
                  <p class="title">{{eachType.nm_type}}</p>
                  <p class="links">
                    <button class="delete"></button>
                    <button class="modify"></button>
                  </p>
                </div>
              </li>
              <button class="add-cate">항목추가</button>
            </ul>
          </li> -->
          <button class="add-cate" @click="clickAdd('type')">항목추가</button>
        </ul>
      </div>
    </section>

    <aside class="search-wrap" :class="{'on': isShowAdd}">
      <div class="top" @click="closeAdd">
        <button>{{popupTitle}}</button>
      </div>
      <div class="wrap">
        <input type="text" class="cate">
      </div>
      <div class="action btn1">
        <a href="#" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import "@/assets/js/jquery.easing.1.3.js";

export default {
  name: "ConsumeConsumeClass",
  data() {
    return {
      consumeCategory: [],
      isShowAdd: false,
      popupTitle: "카테고리 추가"
    };
  },
  components: {
    draggable
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "지출 카테고리 설정";
  },
  created() {
    this.listPersonConsumeClassInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listPersonConsumeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonConsumeClassInfo.json")
        .then(function(response) {
          var list = response.data.listPersonConsumeClassInfo;
          // var listCdClass = new Object();
          var listCdClass = new Array();
          var i = 0;
          for (var cd_class in list) {
            var nm_class = "";
            var listCdType = new Array();
            for (var idx in list[cd_class]) {
              if (idx == 0) {
                nm_class = list[cd_class][idx].nm_class;
              }
              listCdType.push({
                cd_type: list[cd_class][idx].cd_type,
                nm_type: list[cd_class][idx].nm_type,
                name: list[cd_class][idx].nm_type,
                order: idx
              });
            }
            listCdClass[i++] = [cd_class, nm_class, listCdType];
          }
          _this.consumeCategory = listCdClass;
        });
    },
    clickAdd: function(key) {
      this.isShowAdd = true;
      if (key == "class") {
        this.popupTitle = "카테고리 추가";
      } else if (key == "type") {
        this.popupTitle = "세부항목 추가";
      }
    },
    closeAdd: function() {
      this.isShowAdd = false;
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
