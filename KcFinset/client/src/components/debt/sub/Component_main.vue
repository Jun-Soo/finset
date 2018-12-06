<template>
<div id="wrapper">
    <div class="list-block" v-for="oneDebt in debtListData" :key="oneDebt.index">
            <div class="container-fluid">
                <!-- <a href="/debt/detail?no_manage_info={{oneDebt.no_manage_info}}"> -->
                <a v-on:click="navigator(oneDebt.no_manage_info)">
                    <div class="list-heading">
                        <li class="bank-title">
                            <!-- <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>{{oneDebt.nm_fc}} -->
                            <span class="thumb-logo" :style=oneDebt.style></span>{{oneDebt.nm_fc}}
                        </li>
                        <label class="label-type">{{oneDebt.debt_type}}</label>
                    </div>
                    <div class="list-info">
                        <dl>
                            <dt>상환금액(당월)</dt>
                            <dd>{{Common.formatNumber(oneDebt.amt_repay)}}</dd>
                        </dl>
                        <dl>
                            <dt>대출잔액</dt>
                            <dd>{{oneDebt.amt_remain}}</dd>
                        </dl>
                        <dl>
                            <dt>???</dt>
                            <dd>{{Common.formatNumber(oneDebt.amt_contract)}}</dd>
                        </dl>
                        <dl>
                            <dt>???</dt>
                            <dd>{{oneDebt.rate_repay}}%</dd>
                        </dl>
                    </div>
                </a>
            </div>
    </div>
</div>
</template>

<script>
import router from '@/comm/router.js'
import Common from "@/assets/js/common.js";

export default {
  name: 'Component_main',
  data () {
    return {
        debtListData : [],
        Common : Common
    }
  },
  component: {
  },
  // computed () {
  // },
  beforeCreate() {
  },
  created () {
    this.listDebtPg();
  },
  beforeMount() {
  },
  mounted () {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy () {
  },
  destroyed() {
  },
  methods: {
    formatAmt (amt, isMinus) {
        var regex = /^[0-9]/g;
        if(!regex.test(amt)) {
            return amt;
        }
        if(isMinus) {
            return "-"+Number(amt).toLocaleString("en").split(".")[0];
        } else {
            return Number(amt).toLocaleString("en").split(".")[0];
        }
    },
    formatDate (date, isFull) {
        var yyyy = date.substring(0,4);
        var mm = date.substring(4,6);
        var dd = date.substring(6,8);
        if(isFull) {
            return yyyy+"-"+mm+"-"+dd;
        } else {
            return mm+"-"+dd;
        }
    },
    goCalendar () {
        location.href = "/debt/calendar"
    },
    listDebtPg () {
      var thisObj = this;
      this.$http.get('/m/debt/listDebtPg.json', {
        params: {}
      }).then(function (response) {
        var list = response.data.debtListData;
        for(var i=0; i<list.length; i++) {
            list[i].href = "/debt/detail?no_manage_info="+list[i].no_manage_info;
            list[i].style = "background-image:url('/m/fincorp/getFinCorpIcon.crz?cd_fc="+list[i].cd_fc+"')";
        }
        thisObj.debtListData = response.data.debtListData;
      })
    },
    navigator (no_manage_info) {
        this.$store.commit("SET_NO_MANAGE_INFO",no_manage_info);
        router.push("/debt/detail");
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
