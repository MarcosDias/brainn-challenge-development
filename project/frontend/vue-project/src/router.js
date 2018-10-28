import Vue from 'vue'
import Router from 'vue-router'
import Base from '@/pages/Base'
import FindUser from '@/pages/FindUser'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Base',
      component: Base,
      children: [{
        path: '',
        component: FindUser
      }]
    }
  ]
})
