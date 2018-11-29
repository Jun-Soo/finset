<template>
  <section>
    <div class="pop-top">
      <p class="title">직장명 검색</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
    </div>
    <div class="cs-top">
      <div class="cs-search">
        <input type="search" v-model="nm_comp" @change="changeInput">
      </div>
    </div>

    <div class="container pb90">
      <p>직장명 또는 사업자 번호 입력 후 검색해 주세요<br>사업자 번호는 “-” 없이 입력해 주세요</p>

      <div class="company-list">
        <div class="item" :class="{'on':job.isSelect}" v-for="(job,index) in jobList" :key="index">
          <a @click="clickJob(index)">
            <p class="title">{{job.korentrnm}}</p>
            <p class="text">사업자번호 : {{job.business}}<br>
              대표자명 : {{job.korreprnm}}<br>
              사업장 주소 : {{job.nolt_koraddr}}
            </p>
          </a>
        </div>
      </div>
      <p class="mt20" style="text-align: center">직장명이 없을 경우 직접 입력이 가능합니다. (직접입력)</p>

      <div class="btn-wrap">
        <a href="#" class="blue stroke" @click="clickInsJob()">직장명 직접 입력</a>
      </div>
    </div>

    <div class="btn-wrap float">
      <a class="box blue solid" @click="clickConfirm()">확인</a>
    </div>

  </section>
</template>

<script>
import Common from "./../../assets/js/common.js";
import ko from "vee-validate/dist/locale/ko.js";
export default {
  name: "",
  data() {
    return {
      page: 1,
      index: null,
      jobList: [],
      nm_comp: ""
    };
  },
  props: {
    no_bunch: {
      //순번
      type: String,
      default: ""
    }
  },
  components: {},
  computed: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    changeInput: function(option) {
      this.page = 1;
      Common.pagination(this.searchJob, "modal");
    },
    searchJob: function(callback) {
      var _this = this;
      if (this.nm_comp == "") {
        this.$toast.center("직장명을 입력해주세요.");
        return false;
      }
      this.$store.state.isLoading = true;
      var formData = new FormData();
      formData.append("page", this.page);
      formData.append("txt_detail", this.nm_comp);
      this.$http
        .post("/m/loan/listJobResult.json", formData, {
          headers: {
            async: false,
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          }
        })
        .then(response => {
          var list = response.data.pagedList.source;
          if (list === null || list.length === 0) {
            this.$store.state.isLoading = false;
            callback();
            return;
          }
          for (var i = 0; i < list.length; i++) {
            list[i].isSelect = false;
          }

          if (_this.page == 1) {
            _this.jobList = list;
          } else {
            for (var key in list) {
              _this.jobList.push(list[key]);
            }
          }
          _this.page++;
          this.$store.state.isLoading = false;
        })
        .catch(e => {
          this.$toast.center(ko.messages.error);
        });
    },
    clickJob: function(index) {
      for (var i = 0; i < this.jobList.length; i++) {
        if (i == index) {
          this.jobList[i].isSelect = !this.jobList[i].isSelect;
        } else {
          this.jobList[i].isSelect = false;
        }
      }
    },
    clickInsJob: function() {
      this.$parent.$parent.openPop("CreditInsJobNm");
      this.$emit("popclose");
    },
    clickConfirm: function(job) {
      var _this = this;
      for (var i = 0; i < this.jobList.length; i++) {
        if (this.jobList[i].isSelect) {
          this.index = i;
          var formData = new FormData();
          formData.append("no_bunch", this.no_bunch);
          formData.append("kiscode", this.jobList[i].kiscode);
          formData.append("bizno", this.jobList[i].business);
          this.$http
            .post("/m/loan/procKisCompanyOutline.json", formData, {
              headers: {
                async: false,
                "Content-Type":
                  "application/x-www-form-urlencoded; charset=UTF-8"
              }
            })
            .then(response => {
              var result = response.data;
              if (result.result == "00") {
                _this.$parent.$parent.korentrnm = this.jobList[
                  this.index
                ].korentrnm;
                // _this.$parent.$parent.bizno = this.jobList[this.index].business;
                // _this.$parent.$parent.crpno = this.jobList[this.index].crpno;
                // _this.$parent.$parent.kiscode = this.jobList[this.index].kiscode
                // _this.$parent.$parent.ltgmktdivcd = this.jobList[
                //   this.index
                // ].ltgmktdivcd;
                // _this.$parent.$parent.eprmdydivcd = this.jobList[
                //   this.index
                // ].eprmdydivcd;
                // _this.$parent.$parent.etl_ipc_yn = this.jobList[
                //   this.index
                // ].etl_ipc_yn;
                _this.$emit("popclose");
              } else {
                _this.$toast.center("다시 시도해주세요.");
              }
            })
            .catch(e => {
              _this.$toast.center(ko.messages.error);
            });
        }
      }
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
