<template>
<div id="wrapper">
    <header id="header">
        <div class="input-group">
            <div class="input-group-btn">
                <button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
            </div>
            <div id="g-menu" class="g-menu">
                <button type="button" id="btn_alarm" class="ico-alarm"></button>
                <button type="button" class="btn btn-gmenu" v-on:click="createMemo()">저장</button>
            </div>
            <h1>메모</h1>
        </div>
    </header>
    <div id="content_memo">
        <div class="form-group">
            <form name="frmCreateMemo" id="frmCreateMemo" method="POST">
                <div class="list-info">
                    <textarea class="form-control" name="memo_text" id="memo_text" v-model="memo_text"></textarea>
                </div>
                <div class="div_hidden">
                    <input type="hidden" name="no_person" id="no_person" value="${memoVO.no_person }"/>
                    <input type="hidden" name="no_manage_info" id="no_manage_info" value="${memoVO.no_manage_info }"/>
                </div>
                <div id="alarmModal" class="modal">
                    <div class="alert-content">
                        <div class="alert-body">
                            <div id="body-header" class="alertText">날짜와 시간을 선택해주세요<br/><br/></div>
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="outer_alarm_date">날짜</label>
                                    <input type="date" class="form-control" id="outer_alarm_date" autocomplete="off"/>
                                    <input type="hidden" name="alarm_date" id="alarm_date" autocomplete="off"/>
                                </div>
                                <div class="form-group">
                                    <label for="outer_alarm_time">시간</label>
                                    <input type="time" class="form-control" id="outer_alarm_time" autocomplete="off" />
                                    <input type="hidden" name="alarm_time" id="alarm_time" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="alert-footer">
                            <button type="button" id="btn_cancel" class="btn btn-lg btn-default" data-dismiss="modal">취소</button> 
                            <button type="button" id="btn_confirm" class="btn btn-lg btn-default">확인</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</template>

<script>
import router from "@/comm/router.js";

export default {
  name: "memoCreate",
  data() {
    return {
      memo_text: ""
    };
  },
  component: {},
  // computed () {
  // },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    createMemo() {
      var thisObj = this;
      this.$http
        .get("/api/memo/createMemo.json", {
          params: {
            memo_text: thisObj.memo_text,
            no_manage_info: thisObj.$route.params.no_manage_info
          }
        })
        .then(function(response) {
          if (response.data.code == "00") {
            router.push("/memo/main");
          } else {
            thisObj.$toast.center("저장에 실패하였습니다.");
          }
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
</style>
