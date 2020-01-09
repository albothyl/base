import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import BoardList from '@/components/BoardList'
import WriteBoard from '@/components/WriteBoard'
import Detail from '@/components/Detail'

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      meta: {
        loginRequired: false
      }
    },
    {
      path: '/boards',
      name: 'BoardList',
      component: BoardList,
      meta: {
        loginRequired: true
      }
    },
    {
      path: '/insertBoard',
      name: 'WriteBoard',
      component: WriteBoard,
      meta: {
        loginRequired: true
      }
    },
    {
      path: '/detail/:seq',
      name: 'Detail',
      component: Detail,
      meta: {
        loginRequired: true
      }
    }
  ]
})