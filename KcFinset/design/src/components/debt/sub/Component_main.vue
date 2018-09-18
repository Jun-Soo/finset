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
                            <dd>{{oneDebt.amt_repay}}</dd>
                        </dl>
                        <dl>
                            <dt>대출잔액</dt>
                            <dd>{{oneDebt.amt_remain}}</dd>
                        </dl>
                        <dl>
                            <dt>???</dt>
                            <dd>{{oneDebt.amt_contract}}</dd>
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

export default {
  name: 'Component_main',
  data () {
    return {
        debtListData : []
    }
  },
  component: {
  },
  computed: {
  },
  beforeCreate() {
  },
  created() {
  },
  beforeMount() {
  },
  mounted() {
  },
  beforeUpdate() {
  },
  updated() {
  },
  beforeDestroy() {
  },
  destroyed() {
  },
  methods: {
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>

</style>
