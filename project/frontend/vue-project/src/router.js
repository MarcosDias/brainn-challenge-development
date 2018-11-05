import Vue from 'vue'
import Router from 'vue-router'
import Base from '@/pages/Base'
import FindUser from '@/pages/FindUser'
import GridUser from '@/pages/GridUser'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Base,
      children: [{
        path: '',
        component: FindUser
      }, {
        path: ':username',
        component: GridUser
      }]
    }
  ]
})
