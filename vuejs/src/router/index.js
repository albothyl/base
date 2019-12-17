import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import BoardList from '@/components/BoardList'
import WriteBoard from '@/components/WriteBoard'
import Detail from '@/components/Detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/boards',
      name: 'BoardList',
      component: BoardList
    },
    {
      path: '/insertBoard',
      name: 'WriteBoard',
      component: WriteBoard
    },
    {
      path: '/detail/:seq',
      name: 'Detail',
      component: Detail
    }
  ]
})