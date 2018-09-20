<template>
<div id="wrapper">
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="chkHistory();">뒤로가기</button>
			</div>
			<h1>메모</h1>
			<form id="frmMemo" method="post">
				<input type="hidden" name="seq_memo_info" id="seq_memo_info"/>
				<input type="hidden" name="no_manage_info" id="no_manage_info" value="${no_manage_info }"/>
			</form>
		</div>
	</header>
	<div id="content">
		<div id="ico-plus-div">
			<button id="ico-plus" v-on:click="createMemo()"></button>
		</div>
		<div class="list-block" v-for="memoVO in list" :key="memoVO.index" v-on:click="updateMemo()">
				<div class="container-fluid container_short_padding">
					<div class="list-info memo_list_limited">
						<div class="memo_div">
							{{memoVO.memo_text }}
						</div>
						<div class="memo_date">
							{{memoVO.dt_lst }}
						</div>
					</div>
					<div class="div_hidden">
						<!-- <input type="hidden" id="seq_memo_info_each" value="${memo.seq_memo_info }"/> -->
						<input type="hidden" id="seq_memo_info_each" :value=memoVO.seq_memo_info />
					</div>
				</div>
		</div>
	</div>
</div>
</template>

<script>
import router from "@/comm/router.js";
import Common from "@/assets/js/common.js";

export default {
  name: "memoMain",
  data() {
    return {
      list: [],
      page: 1
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    var thisObj = this;
    Common.pagination(this.listMemo);
  },
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    listMemo(callback) {
      var thisObj = this;
      this.$http
        .get("/m/memo/listMemo.json", {
          params: { no_manage_info: "", page: thisObj.page }
        })
        .then(function(response) {
          var listMemo = response.data.listMemo;
          if (thisObj.page == 1) {
            thisObj.list = listMemo;
          } else {
            for (var a in listMemo) {
              thisObj.list.push(listMemo[a]);
            }
          }
          if (listMemo.length > 0) {
            callback();
          }
        });
      thisObj.page++;
    },
    updateMemo() {
      console.log("구현해야됨");
      router.go(-1);
    },
    createMemo() {
      router.push("/memo/create");
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
