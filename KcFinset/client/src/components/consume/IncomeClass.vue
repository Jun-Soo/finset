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
                  <button class="modify" @click="modifyCate(eachClass.cd_class, eachClass.nm_class)"></button>
                </p>
              </div>
            </li>
          </draggable>
          <li class="nosub footer">
            <div class="wrap each-class">
              <p class="title etc">기타</p>
            </div>
          </li>
          <button v-if="!isMax" class="add-cate" @click="clickAdd">항목추가</button>
        </ul>
      </div>
    </section>

    <aside class="search-wrap" :class="{'on': isShowAdd}">
      <div class="top" @click="closeAdd">
        <button v-text="isModify?'수입 카테고리 수정':'수입 카테고리 추가'"></button>
      </div>
      <div class="wrap">
        <input type="text" class="cate" v-model="nmCate">
        <p class="warn">{{errors.first('금액')}}</p>
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
import "@/assets/js/jquery.easing.1.3.js";

export default {
  name: "ConsumeIncomeClass",
  data() {
    return {
      seen: false,
      incomeCategory: [],
      isShowAdd: false,
      isModify: false,
      draggableOptions: {
        handle: ".handle",
        touchStartThreshold: 200
      },
      nmCate: "",
      isMax: false
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
    clickAdd: function() {
      this.isModify = false;
      this.nmCate = "";
      this.isShowAdd = true;
    },
    closeAdd: function() {
      this.isShowAdd = false;
    },
    addCate: function() {
      var _this = this;
      var formData = new FormData();
      if (this.isModify) {
        formData.append("type_in_out", "01");
        formData.append("cd_class", this.curClass);
        formData.append("nm_class", this.nmCate);
        var _this = this;
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
    },
    modifyCate: function(cd_class, nm_class) {
      this.isModify = true;
      this.curClass = cd_class;
      this.nmCate = nm_class;
      this.isShowAdd = true;
    },
    deleteCate: function(cd_class, sort_class) {
      this.$dialogs
        .confirm("정말로 삭제하시겠습니까?", Constant.options)
        .then(res => {
          // console.log(res); // {ok: true|false|undefined}
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
          } else {
            // this.$dialogs.alert("취소를 선택했습니다.", Constant.options);
          }
        });
    },
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
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
.consume-cate-list li .wrap .title.etc {
  background: none;
}
</style>
