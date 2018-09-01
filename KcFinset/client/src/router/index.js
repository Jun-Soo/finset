import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import FinsetWorld from '@/components/main/FinsetWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'FinsetWorld',
      component: FinsetWorld
    },
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
