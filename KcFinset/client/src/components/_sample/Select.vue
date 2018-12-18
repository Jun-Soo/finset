<template>
  <div class="container pb90">
    <ul class="consume-detail">
      <div>
        <li>
          <p class="key">결제수단</p>
          <p>
            <multiselect :id="'payType'" :title="'결제수단'" v-model="payValue" :options="meansConsumeOption" :onClose="nextOpen" />
          </p>
        </li>
      </div>
      <div>
        <li>
          <p class="key">카테고리</p>
          <p>
            <multiselect :id="'category'" ref="category" :title="'카테고리'" v-model="categoryValue" :multiple="true" :options="consumeCategory" />
          </p>
        </li>
      </div>
    </ul>
  </div>

</template>

<script>
import Common from "@/assets/js/common.js";
import Constant from "@/assets/js/constant.js";
import multiselect from "@/components/plugins/multiselect/MultiSelect.vue";

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
  components: {
    multiselect
  },
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
    selectClose: function(option) {
      console.log(option);
    },
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
    nextOpen: function() {
      this.$refs.category.open();
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
