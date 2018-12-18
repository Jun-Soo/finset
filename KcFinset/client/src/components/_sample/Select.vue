<template>
  <div class="container pb90">
    <ul class="consume-detail">
      <div>
        <li>
          <p class="key">결제수단</p>
          <p>
            <multiselect :id="'payType'" :title="'결제수단'" v-model="payValue" :options="meansConsumeOption" :onClose="nextOpen" v-validate="'required'" data-vv-name="결제수단" />
          </p>
        </li>
        <p class="warn" v-if="errors.has('결제수단')" v-text="'결제수단 항목은 필수 정보입니다'"></p>
      </div>
      <div>
        <li>
          <p class="key">카테고리</p>
          <p>
            <multiselect :id="'category'" ref="category" :title="'카테고리'" v-model="categoryValue" :multiple="true" :options="consumeCategory" :onClose="selectClose" v-validate="'required'" data-vv-name="카테고리" />
          </p>
        </li>
        <p class="warn" v-if="errors.has('카테고리')" v-text="'카테고리 항목은 필수 정보입니다'"></p>
      </div>
    </ul>
  </div>

</template>

<script>
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";

export default {
  name: "",
  data() {
    return {
      payValue: "",
      categoryValue: "",
      meansConsumeOption: {},
      consumeCategory: {},
      consumeVO: {
        dt_trd: new Date()
      }
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "FINSET";
  },
  created() {
    this.listMeansConsume();
    this.listPersonConsumeClassInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listMeansConsume: function() {
      var _this = this;

      this.$http
        .get("/m/consume/listMeansConsume.json")
        .then(function(response) {
          var list = response.data.listMeansConsume;
          _this.meansConsumeOption = [];
          for (var idx in list) {
            _this.meansConsumeOption.push({
              means_consume: list[idx].means_consume,
              text:
                list[idx].means_consume == "02" ? "현금" : list[idx].nm_card,
              value: list[idx].no_card,
              cd_fc: list[idx].cd_fc
            });
          }
        });
    },
    listPersonConsumeClassInfo: function() {
      var _this = this;
      this.$http
        .get("/m/consume/listPersonConsumeClassInfo.json", {
          params: {
            no_person: "P000000293"
          }
        })
        .then(function(response) {
          var list = response.data.listPersonConsumeClassInfo;
          var listCdClass = new Object();
          for (var eachClass of list) {
            var value = "";
            var text = "";
            var listCdType = new Array();
            for (var idx in eachClass) {
              if (idx == 0) {
                value = eachClass[idx].cd_class;
                text = eachClass[idx].nm_class;
              }
              listCdType.push({
                value: eachClass[idx].cd_type,
                text: eachClass[idx].nm_type
              });
            }
            listCdClass[value] = {
              value: value,
              text: text,
              list: listCdType
            };
          }
          _this.consumeCategory = listCdClass;
        });
    },
    nextOpen: function(option) {
      this.$refs.category.open();
    },
    selectClose: function(option) {
      console.log(option);
      this.$validator.validateAll().then(res => {
        if (res) {
          console.log(res);
        } else {
          this.$toast.center("입력값을 확인해주세요.");
        }
      });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
