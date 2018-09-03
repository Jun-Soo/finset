import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import FinsetMain from '@/components/main/FinsetMain'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'FinsetMain',
      component: FinsetMain
    },
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
