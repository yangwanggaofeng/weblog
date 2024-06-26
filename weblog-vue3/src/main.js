//import './assets/main.css'  // 相对路径，引入 main.css 样式文件
import '@/assets/main.css';    //使用别名@引入
import 'animate.css';

import { createApp } from 'vue'  // 引入 createApp 方法
import App from '@/App.vue'     // 引入 App.vue 组件

import router from '@/router'   //导入路由 ，使用别名@导入路由，其中别名@在vite.config.js中已定义

// 导入 Element Plus 图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//引入nprogress.css
import 'nprogress/nprogress.css'
import '@/permission'
// import { createPinia } from 'pinia'
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// const pinia = createPinia()
// pinia.use(piniaPluginPersistedstate)
// 引入全局状态管理 Pinia
import pinia from '@/stores'

const app = createApp(App)
app.use(router)     //应用路由
app.use(pinia)
//解决浏览器调试没有Pinia问题
// const app = createApp(App).use(pinia).use(router).mount('#app')

// 引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')    // 创建应用，并将 App 根组件挂载到 <div id="#app"></div> 中
