<template>
  <div v-if="seen">
    <section>
      <div class="box-list noMG">
        <ul class="consume-cate-list">
          <draggable v-model="consumeCategory" @start="drag=true" :options="draggableOptions" @update="modifyClassSort">
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
                <draggable v-model="eachClass[3]" :options="draggableOptions" @update="modifyTypeSort(index)">
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
      seen: false, // 화면 표시 여부
      consumeCategory: [], // 지출 분류, 항목
      isShowAdd: false, // 추가, 수정 팝업 표시 여부
      isClass: true, // 팝업 화면 표출 시 분류 여부(아닐 시 항목)
      isModify: false, // 팝업 화면 표출 시 수정 여부(아닐 시 삭제)
      // draggable에 필요한 옵션
      draggableOptions: {
        handle: ".handle", // 드래그 컨트롤 할 태그의 클래스
        touchStartThreshold: 200 // 드래그가 시작될 때 까지 터치가 유저되어야 하는 시간
      },
      nmCate: "", // 팝업 input 태그에 들어가는 문구
      curCdClass: "", // 현재 컨트롤 되고 있는 분류의 코드
      curCdType: "" // 현개 컨트롤 되고 있는 항목의 코드
    };
  },
  components: {
    draggable
  },
  computed: {
    // 팝업창 상단의 텍스트
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
    // ---------------------화면 이벤트---------------------
    // 항목추가 버튼 클릭
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
    // 수정 버튼 클릭
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
    // 팝업창 종료 버튼 클릭
    closeAdd: function() {
      this.errors.clear();
      this.isShowAdd = false;
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 소비지출 카테고리 조회
    listPersonConsumeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonConsumeClassInfo.json")
        .then(function(response) {
          var list = response.data.listPersonConsumeClassInfo;
          // 기타의 경우 별도로 움직여야 해서 리스트에서 마지막 요소를 제거
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
    // 분류 정렬 순서 변경
    modifyClassSort: function() {
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
    // 항목 정렬 순서 변경
    modifyTypeSort: function(index) {
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
    // 분류 삭제 처리
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
    // 항목 삭제 처리
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
    // 분류, 항목 삭제 혹은 수정 처리
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
    },
    // ---------------------//데이터 이동---------------------
    // ---------------------기타---------------------
    // 전체 아코디언 접기
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
    // 아코디언 접고 펼치기
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
    }
    // ---------------------//기타---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.consume-cate-list li .wrap .title.etc {
  background: none;
}
</style>
