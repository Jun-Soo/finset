<template>
  <div v-if="seen">
    <section>
      <div class="box-list noMG">
        <ul class="consume-cate-list">
          <draggable v-model="incomeCategory" :options="draggableOptions" @start="drag=true" @update="changeClass">
            <li v-for="(eachClass, index) in incomeCategory" :key="index" class="nosub">
              <div class="wrap each-class">
                <p class="title handle">{{eachClass.nm_class}}</p>
                <p class="links">
                  <button class="delete" @click="deleteCate(eachClass.cd_class, eachClass.sort_class)"></button>
                  <button class="modify" @click="clickModify(eachClass.cd_class, eachClass.nm_class)"></button>
                </p>
              </div>
            </li>
          </draggable>
          <li class="nosub footer">
            <div class="wrap each-class">
              <p class="title etc">기타</p>
            </div>
          </li>
          <button class="add-cate" @click="clickAdd">항목추가</button>
        </ul>
      </div>
    </section>

    <aside class="search-wrap" :class="{'on': isShowAdd}">
      <div class="top" @click="closeAdd">
        <button v-text="isModify?'수입 카테고리 수정':'수입 카테고리 추가'"></button>
      </div>
      <div class="wrap">
        <input type="text" class="cate" v-model="nmCate" v-validate="'required'" data-vv-name="카테고리명">
        <p class="warn" v-if="errors.has('카테고리명')">{{errors.first('카테고리명')}}</p>
      </div>
      <div class="action btn1">
        <a @click="addCate" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import Constant from "@/assets/js/constant.js";
import ko from "vee-validate/dist/locale/ko.js";
import "@/assets/js/jquery.easing.1.3.js";

export default {
  name: "ConsumeIncomeClass",
  data() {
    return {
      seen: false, // 화면 표출 여부
      incomeCategory: [], // 수입 카테고리 리스트
      isShowAdd: false, // 하단 팝업창 표출 여부
      isModify: false, // 수정 여부(아니면 추가)
      // draggable에서 사용하는 옵션
      draggableOptions: {
        handle: ".handle", // 드래그 시킬 태그의 클래스
        touchStartThreshold: 200 // 드래그 시작까지 누르고 있어야 하는 시간
      },
      nmCate: "" // 하단 팝업창에 들어갈 문구
    };
  },
  components: {
    draggable
  },
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "수입 카테고리 설정";
  },
  created() {
    this.listPersonIncomeClassInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    // ---------------------화면 컨트롤---------------------
    // 항목추가 클릭
    clickAdd: function() {
      this.isModify = false;
      this.nmCate = "";
      this.isShowAdd = true;
    },
    // 항목추가 팝업창 닫기
    closeAdd: function() {
      this.errors.clear();
      this.isShowAdd = false;
    },
    // 수정 버튼 클릭
    clickModify: function(cd_class, nm_class) {
      this.isModify = true;
      this.curClass = cd_class;
      this.nmCate = nm_class;
      this.isShowAdd = true;
    },
    // ---------------------//화면 컨트롤---------------------
    // ---------------------데이터 이동---------------------
    // 수입 분류 리스트 조회
    listPersonIncomeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonIncomeClassInfo.json")
        .then(function(response) {
          var list = response.data.listPersonIncomeClassInfo;
          list.pop();
          for (var idx in list) {
            list[idx].order = idx;
            list[idx].name = list[idx].nm_class;
          }
          _this.incomeCategory = list;
          _this.seen = true;
        });
    },
    // 수입 분류 추가 혹은 수정
    addCate: function() {
      var _this = this;
      this.$validator.validateAll().then(res => {
        if (res) {
          var formData = new FormData();
          if (this.isModify) {
            formData.append("type_in_out", "01");
            formData.append("cd_class", this.curClass);
            formData.append("nm_class", this.nmCate);
            this.$http
              .post("/m/consume/modifyPersonConsumeClassNmClass.json", formData)
              .then(function(response) {
                _this.closeAdd();
                _this.listPersonIncomeClassInfo();
              });
          } else {
            formData.append("nm_class", this.nmCate);
            this.$http
              .post("/m/consume/createPersonConsumeClassIncome.json", formData)
              .then(function(response) {
                _this.closeAdd();
                _this.listPersonIncomeClassInfo();
              });
          }
        }
      });
    },
    // 수입 분류 삭제
    deleteCate: function(cd_class, sort_class) {
      this.$dialogs
        .confirm("정말로 삭제하시겠습니까?", Constant.options)
        .then(res => {
          if (res.ok) {
            var _this = this;
            var formData = new FormData();
            formData.append("type_in_out", "01");
            formData.append("cd_class", cd_class);
            formData.append("sort_class", sort_class);
            this.$http
              .post("/m/consume/deletePersonConsumeClass.json", formData)
              .then(function(response) {
                _this.listPersonIncomeClassInfo();
              });
          }
        });
    },
    // 수입 분류 정렬 순서 변경
    changeClass: function() {
      var _this = this;
      var incomeClasses = this.incomeCategory;
      var formData = new FormData();
      for (var idx in incomeClasses) {
        formData.append(
          "list[" + idx + "].cd_class",
          incomeClasses[idx].cd_class
        );
        formData.append("list[" + idx + "].sort_class", parseInt(idx) + 1);
        formData.append("list[" + idx + "].type_in_out", "01");
      }
      idx++;
      this.$http
        .post("/m/consume/modifyPersonSortClass.json", formData)
        .then(function(response) {
          _this.listPersonIncomeClassInfo();
        });
    }
    // ---------------------//데이터 이동---------------------
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.consume-cate-list li .wrap .title.etc {
  background: none;
}
</style>
