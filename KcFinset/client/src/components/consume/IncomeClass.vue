<template>
  <div>
    <section>
      <div class="box-list noMG">
        <ul class="consume-cate-list">
          <draggable v-model="incomeCategory" @start="drag=true" @update="changeClass">
            <li v-for="(eachClass, index) in incomeCategory" :key="index">
              <div class="wrap each-class">
                <p class="title">{{eachClass.nm_class}}</p>
                <p class="links">
                  <button class="delete" @click="deleteCate(eachClass.cd_class)"></button>
                  <button class="modify" @click="modifyCate(eachClass.cd_class, eachClass.nm_class)"></button>
                </p>
              </div>
            </li>
          </draggable>
          <button class="add-cate" @click="clickAdd">항목추가</button>
        </ul>
      </div>
    </section>

    <aside class="search-wrap" :class="{'on': isShowAdd}">
      <div class="top" @click="closeAdd">
        <button v-text="isModify?'수입 카테고리 수정':'수입 카테고리 추가'"></button>
      </div>
      <div class="wrap">
        <input type="text" class="cate" v-model="nmCate">
      </div>
      <div class="action btn1">
        <a @click="addCate" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import "@/assets/js/jquery.easing.1.3.js";

export default {
  name: "ConsumeIncomeClass",
  data() {
    return {
      incomeCategory: [],
      isShowAdd: false,
      isModify: false,
      nmCate: ""
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
          for (var idx in list) {
            list[idx].order = idx;
            list[idx].name = list[idx].nm_class;
          }
          _this.incomeCategory = list;
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
    deleteCate: function(cd_class) {
      var _this = this;
      var formData = new FormData();
      formData.append("cd_class", cd_class);
      this.$http
        .post("/m/consume/deletePersonConsumeClass.json", formData)
        .then(function(response) {
          _this.listPersonIncomeClassInfo();
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
      }
      this.$http
        .post("/m/consume/modifyPersonSortClass.json", formData)
        .then(function(response) {});
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
