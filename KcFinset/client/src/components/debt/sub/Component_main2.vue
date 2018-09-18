<template>
<div id="wrapper">
    test용입니다
</div>
</template>

<script>
import router from '@/comm/router.js'

export default {
  name: 'Component_main',
  data () {
    return {
        debtListData : []
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
      this.$http.get('/api/debt/listDebtPg.json', {
        params: {}
      }).then(function (response) {
        var list = response.data.debtListData;
        for(var i=0; i<list.length; i++) {
            list[i].href = "/debt/detail?no_manage_info="+list[i].no_manage_info;
            list[i].style = "background-image:url('/api/fincorp/getFinCorpIcon.crz?cd_fc="+list[i].cd_fc+"')";
        }
        thisObj.debtListData = response.data.debtListData;
      })
    },
    navigator (no_manage_info) {
        router.push({name:"debtDetail", params: {no_manage_info:no_manage_info}});
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
