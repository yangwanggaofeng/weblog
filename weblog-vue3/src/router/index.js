// import Login from '../pages/frontend/login.vue'
import Login from '@/pages/admin/login.vue'   //使用别名引用
// import Index from '../pages/frontend/index.vue'
import Index from '@/pages/frontend/index.vue'
import AdminIndex from '@/pages/admin/index.vue'
import Admin from '@/layouts/admin/admin.vue'

import AdminArticleList from '@/pages/admin/article-list.vue'
import AdminCategoryList from '@/pages/admin/category-list.vue'
import AdminTagList from '@/pages/admin/tag-list.vue'
import AdminBlogSetting from '@/pages/admin/blog-settings.vue'
import ArchiveList from '@/pages/frontend/archive-list.vue'
import CategoryList from '@/pages/frontend/category-list.vue'
import CategoryArticleList from '@/pages/frontend/category-article-list.vue'
import TagList from '@/pages/frontend/tag-list.vue'
import TagArticleList from '@/pages/frontend/tag-article-list.vue'
import { createRouter, createWebHashHistory } from 'vue-router'

// 统一在这里声明所有路由
const routes = [
    {
        path: '/', // 路由地址
        component: Index, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 首页' // 页面标题
        }
    },
    {
        path: '/login', // 路由地址
        component: Login, // 对应组件
        meta: { // meta 信息
            title: '登录页' // 页面标题
        }
    },
    {
        path: '/admin',//后台首页
        component: Admin,// 对应 admin.vue 布局文件
        children: [
            {
                path: '/admin/index', // 路由地址
                component: AdminIndex, // 对应组件
                meta: {
                    title: '仪表盘' // 页面标题
                }
            },
            {
                path: '/admin/article/list',
                component: AdminArticleList,
                meta: {
                    title: '文章管理'
                }
            },
            {
                path: '/admin/category/list',
                component: AdminCategoryList,
                meta: {
                    title: '分类管理'
                }
            },
            {
                path: '/admin/tag/list',
                component: AdminTagList,
                meta: {
                    title: '标签管理'
                }
            },
            {
                path: '/admin/blog/settings',
                component: AdminBlogSetting,
                meta: {
                    title: '博客设置'
                }
            }
        ]
    }, {
        path: '/archive/list', // 路由地址
        component: ArchiveList, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 归档首页' // 页面标题
        }
    },
    {
        path: '/category/list', // 路由地址
        component: CategoryList, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 分类首页' // 页面标题
        }
    },
    {
        path: '/category/article/list', // 路由地址
        component: CategoryArticleList, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 分类文章页' // 页面标题
        }
    },
    {
        path: '/tag/list', // 路由地址
        component: TagList, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 标签首页' // 页面标题
        }
    },{
        path: '/tag/article/list', // 路由地址
        component: TagArticleList, // 对应组件
        meta: { // meta 信息
            title: 'Weblog 标签文章页' // 页面标题
        }
    },

]

// 创建路由
const router = createRouter({
    // 指定路由的历史管理方式，hash 模式指的是 URL 的路径是通过 hash 符号（#）进行标识
    history: createWebHashHistory(),
    // routes: routes 的缩写
    routes,
})

// ES6 模块导出语句，它用于将 router 对象导出，以便其他文件可以导入和使用这个对象
export default router