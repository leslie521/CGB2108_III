import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import ElementUI from '../components/ElementUI.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/user/user.vue'
import ItemCat from '../components/items/ItemCat.vue'
import Item from '../components/items/Item.vue'
import AddItem from '../components/items/addItem.vue'

//使用路由机制
Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  {path: '/elementUI', component: ElementUI},
  {path: '/home', component: Home , redirect:'/welcome' , children: [
    {path: '/welcome', component: Welcome },
    {path: '/user', component: User },
    {path: '/itemCat', component: ItemCat },
    {path: '/item', component: Item},
    {path: '/item/addItem', component: AddItem }
    ]}

]

//路由导航守卫!!!!!!!

const router = new VueRouter({
  routes
})
//设置路由导航守卫
//关于参数说明:
  //1. to 将要访问的路径
  //2. from 从哪个路径跳转来的
  //3. next 是一个函数   next() 表示放行  next("/login") 强制跳转
router.beforeEach((to,from,next) => {
  //1.当用户访问登录页面,则直接放行 如果不需要执行后续操作 执行return
  if(to.path === '/login') return next()
  //2.当用户访问其他页面 需要校验是否有token
  let token = window.sessionStorage.getItem('token')
  //如果数据为null  则访问登录页面
  if(token === null || token === ''){
    return next('/login')
  }
  //如果数据不为null 则放行
  next()
})

export default router
