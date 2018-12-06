<template>
  <div v-if="seen">
    <section>
      <div class="box-list noMG">
        <ul class="consume-cate-list">
          <draggable v-model="consumeCategory" @start="drag=true" :options="draggableOptions" @update="changeClass">
            <li v-for="(eachClass, index) in consumeCategory" :key="index" class="liClass" @click="toggleSlide">
              <div class="wrap each-class">
                <!-- <p class="title" @click="slideUpAll">{{eachClass[1]}}</p> -->
                <p class="title handle" @mousedown="slideUpAll">{{eachClass[1]}}</p>
                <!-- <p class="title" @touchstart="slideUpAll">{{eachClass[1]}}</p> -->
                <p class="links">
                  <button class="delete" @click="deleteClass(eachClass[0], eachClass[2])"></button>
                  <button class="modify" @click="clickModify('class', eachClass[1], eachClass[0])"></button>
                </p>
              </div>
              <ul>
                <draggable v-model="eachClass[3]" :options="draggableOptions" @update="changeType(index)">
                  <!-- <li v-for="eachType in eachClass[2]" :key="eachType.nm_type"> -->
                  <li v-for="eachType in eachClass[3]" :key="eachType.nm_type">
                    <!-- <div class="wrap" :slot="eachType.cd_type == '99'?'footer':false"> -->
                    <div class="wrap">
                      <p class="title handle">{{eachType.nm_type}}</p>
                      <p class="links">
                        <button class="delete" @click="deleteType(eachClass[0], eachType.cd_type, eachType.sort_type)"></button>
                        <button class="modify" @click="clickModify('type', eachType.nm_type, eachClass[0], eachType.cd_type)"></button>
                      </p>
                    </div>
                  </li>
                </draggable>
                <li class="nosub footer">
                  <div class="wrap each-class">
                    <p class="title etc">기타</p>
                  </div>
                </li>
                <button class="add-cate" @click="clickAdd('type', eachClass[0])">항목추가</button>
              </ul>
            </li>
          </draggable>
          <li class="nosub footer">
            <div class="wrap each-class">
              <p class="title etc">기타</p>
            </div>
          </li>
          <button class="add-cate" @click="clickAdd('class')">항목추가</button>
        </ul>
      </div>
    </section>

    <aside class="search-wrap" :class="{'on': isShowAdd}">
      <div class="top" @click="closeAdd">
        <button v-text="headTitle"></button>
      </div>
      <div class="wrap">
        <input type="text" class="cate" v-model="nmCate" v-validate="'required'" data-vv-name="이름">
        <p class="warn" v-if="errors.has('이름')">{{errors.first('이름')}}</p>
      </div>
      <div class="action btn1">
        <a @click="confirmCate" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import Constant from "@/assets/js/constant.js";
import "@/assets/js/jquery.easing.1.3.js";

export default {
  name: "ConsumeConsumeClass",
  data() {
    return {
      seen: true,
      consumeCategory: [],
      isShowAdd: false,
      isClass: true,
      isModify: false,
      draggableOptions: {
        handle: ".handle",
        touchStartThreshold: 200
      },
      nmCate: "",
      curCdClass: "",
      curCdType: ""
    };
  },
  components: {
    draggable
  },
  computed: {
    headTitle: function() {
      if (this.isClass && this.isModify) {
        return "카테고리 수정";
      } else if (!this.isClass && this.isModify) {
        return "세부항목 수정";
      } else if (this.isClass && !this.isModify) {
        return "카테고리 추가";
      } else if (!this.isClass && !this.isModify) {
        return "세부항목 추가";
      }
    }
  },
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
          list.pop();
          var listCdClass = new Array();
          var i = 0;
          for (var listIdx in list) {
            var nm_class = "";
            var cd_class = "";
            var sort_class = "";
            var listCdType = new Array();
            for (var idx in list[listIdx]) {
              if (idx == 0) {
                nm_class = list[listIdx][idx].nm_class;
                cd_class = list[listIdx][idx].cd_class;
                sort_class = list[listIdx][idx].sort_class;
              }
              if (list[listIdx][idx].cd_type != "999") {
                listCdType.push({
                  cd_type: list[listIdx][idx].cd_type,
                  nm_type: list[listIdx][idx].nm_type,
                  sort_type: list[listIdx][idx].sort_type,
                  name: list[listIdx][idx].nm_type,
                  order: idx
                });
              }
            }
            listCdClass[i++] = [cd_class, nm_class, sort_class, listCdType];
          }
          _this.consumeCategory = listCdClass;
          _this.seen = true;
        });
    },
    clickAdd: function(key, cd_class) {
      this.isModify = false;
      this.nmCate = "";
      if (key == "class") {
        this.isClass = true;
      } else if (key == "type") {
        this.isClass = false;
        this.curCdClass = cd_class;
      }
      this.isShowAdd = true;
    },
    clickModify: function(key, name, cd_class, cd_type) {
      this.isModify = true;
      this.nmCate = name;
      this.curCdClass = cd_class;
      if (key == "class") {
        this.isClass = true;
      } else if (key == "type") {
        this.isClass = false;
        this.curCdType = cd_type;
      }
      this.isShowAdd = true;
    },
    closeAdd: function() {
      this.errors.clear();
      this.isShowAdd = false;
    },
    slideUpAll: function() {
      var liClasses = $(".liClass");

      for (var eachClass of liClasses) {
        if ($(eachClass).hasClass("on")) {
          $(eachClass)
            .find("ul")
            .slideUp(0, "easeInOutExpo");
          $(eachClass).removeClass("on");
        }
      }
    },
    changeClass: function() {
      var _this = this;
      var consumeClass = this.consumeCategory;
      var formData = new FormData();
      for (var idx in consumeClass) {
        formData.append("list[" + idx + "].cd_class", consumeClass[idx][0]);
        formData.append("list[" + idx + "].sort_class", parseInt(idx) + 1);
        formData.append("list[" + idx + "].type_in_out", "02");
      }
      this.$http
        .post("/m/consume/modifyPersonSortClass.json", formData)
        .then(function(response) {});
    },
    changeType: function(index) {
      var _this = this;
      var cd_class = this.consumeCategory[index][0];
      var consumeType = this.consumeCategory[index][3];
      var formData = new FormData();
      for (var idx in consumeType) {
        formData.append("list[" + idx + "].cd_class", cd_class);
        formData.append("list[" + idx + "].cd_type", consumeType[idx].cd_type);
        formData.append("list[" + idx + "].sort_type", parseInt(idx) + 1);
      }
      this.$http
        .post("/m/consume/modifyPersonSortType.json", formData)
        .then(function(response) {});
    },
    toggleSlide: function(param) {
      if (param.srcElement.localName == "div") {
        if (
          $(param.target)
            .closest("li")
            .hasClass("on")
        ) {
          $(param.target)
            .closest("li")
            .find("ul")
            .slideUp(500, "easeInOutExpo");
          $(param.target)
            .closest("li")
            .removeClass("on");
        } else {
          $(param.target)
            .closest("li")
            .find("ul")
            .slideDown(500, "easeInOutExpo");
          $(param.target)
            .closest("li")
            .addClass("on");
        }
      }
    },
    deleteClass: function(cd_class, sort_class) {
      this.$dialogs
        .confirm("정말로 삭제하시겠습니까?", Constant.options)
        .then(res => {
          // console.log(res); // {ok: true|false|undefined}
          if (res.ok) {
            var _this = this;
            var formData = new FormData();
            formData.append("type_in_out", "02");
            formData.append("cd_class", cd_class);
            formData.append("sort_class", sort_class);
            this.$http
              .post("/m/consume/deletePersonConsumeClass.json", formData)
              .then(function(response) {
                _this.slideUpAll();
                _this.listPersonConsumeClassInfo();
              });
          } else {
            // this.$dialogs.alert("취소를 선택했습니다.", Constant.options);
          }
        });
    },
    deleteType: function(cd_class, cd_type, sort_type) {
      this.$dialogs
        .confirm("정말로 삭제하시겠습니까?", Constant.options)
        .then(res => {
          // console.log(res); // {ok: true|false|undefined}
          if (res.ok) {
            var _this = this;
            var formData = new FormData();
            formData.append("type_in_out", "02");
            formData.append("cd_class", cd_class);
            formData.append("cd_type", cd_type);
            formData.append("sort_type", sort_type);
            this.$http
              .post("/m/consume/deletePersonConsumeClassType.json", formData)
              .then(function(response) {
                _this.listPersonConsumeClassInfo();
              });
          } else {
            // this.$dialogs.alert("취소를 선택했습니다.", Constant.options);
          }
        });
    },
    confirmCate: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          formData.append("type_in_out", "02");
          if (!this.isModify) {
            if (this.isClass) {
              formData.append("nm_class", _this.nmCate);
              this.$http
                .post("/m/consume/createPersonConsumeClassClass.json", formData)
                .then(function(response) {
                  _this.closeAdd();
                  _this.listPersonConsumeClassInfo();
                });
            } else {
              formData.append("cd_class", _this.curCdClass);
              formData.append("nm_type", _this.nmCate);
              this.$http
                .post("/m/consume/createPersonConsumeClassType.json", formData)
                .then(function(response) {
                  _this.closeAdd();
                  _this.listPersonConsumeClassInfo();
                });
            }
          } else {
            if (this.isClass) {
              formData.append("cd_class", this.curCdClass);
              formData.append("nm_class", this.nmCate);
              this.$http
                .post(
                  "/m/consume/modifyPersonConsumeClassNmClass.json",
                  formData
                )
                .then(function(response) {
                  _this.closeAdd();
                  _this.listPersonConsumeClassInfo();
                });
            } else {
              formData.append("cd_class", this.curCdClass);
              formData.append("cd_type", this.curCdType);
              formData.append("nm_type", this.nmCate);
              this.$http
                .post(
                  "/m/consume/modifyPersonConsumeClassNmType.json",
                  formData
                )
                .then(function(response) {
                  _this.closeAdd();
                  _this.listPersonConsumeClassInfo();
                });
            }
          }
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.consume-cate-list li .wrap .title.etc {
  background: none;
}
</style>
