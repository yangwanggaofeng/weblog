import router from '@/router/index'
import { getToken } from '@/composables/cookie'
import { showMessage } from '@/composables/utils'
import { showPageLoading } from '@/composables/utils'
import { hidePageLoading } from '@/composables/utils'
import { useBlogSettingsStore } from '@/stores/blogsettings'



//全局路由守卫
router.beforeEach((to, from, next) => {
    // to and from are both route objects. must call `next`.
    //展示页面加载loading
    console.log("全局路由守卫")
    showPageLoading()
    let token = getToken()
    if(!token && to.path.startsWith("/admin")){
        showMessage('请先登录', 'error')
        next({path: '/login' })
    }else if(token && to.path == "/login"){
        // 若用户已经登录，且重复访问登录页
        showMessage('请勿重复登录', 'warning')
        // 跳转后台首页
        next({path: '/admin/index'})
    // }else{
    //         next()
    // }
    }else if (!to.path.startsWith("/admin")){
        //如果访问的非 /admin的前缀路由
        //引入博客设置 store
        let blogSettingStore = useBlogSettingsStore()
        //获取博客设置信息并保存到全局中
        blogSettingStore.getBlogSettings()
        next()
    }else{
        next()
    }
})
router.afterEach((to, from) => {
    // to and from are both route objects.
    console.log("全局路由后置守卫")
    let title = (to.meta.title? to.meta.title : '') + ' - weblog'
    document.title = title
     // 隐藏页面加载 Loading
    hidePageLoading()
})