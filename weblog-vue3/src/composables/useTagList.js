import { ref } from 'vue'
//引入menuStore
import { useMenuStore } from '@/stores/menu'
import { useRoute, onBeforeRouteUpdate, useRouter } from 'vue-router'
import { setTabList, getTabList } from '@/composables/cookie.js'
export function useTabList() {
    const menuStore = useMenuStore()
    const route = useRoute()
    const router = useRouter()
    //当前被选中的tab
    const activeTab = ref(route.path)
    //导航栏tab数组
    const tabList = ref([
        {
            title: '仪表盘',
            path: '/admin/index',
        },
        // {
        //     title: '文章管理',
        //     path: '/admin/article/list', 
        // },
        // {
        //     title: '分类管理',
        //     path: '/admin/category/list', 
        // },
        // {
        //     title: '标签管理',
        //     path: '/admin/tag/list', 
        // },
        // {
        //     title: '博客设置',
        //     path: '/admin/blog/setting', 
        // },
    ])
    //添加 tab标签页
    const  addTab = (tab) =>{
        //检查标签是否存在
        console.log(tabList.value.findIndex(item => item.path == tab.path))
        let isTabNotExisted = tabList.value.findIndex(item => item.path == tab.path) == -1
        //如果不存在
        if(isTabNotExisted){
            tabList.value.push(tab)
        }
        // 存储 tabList 到 cookie 中
        setTabList(tabList.value)
    }

    onBeforeRouteUpdate((to, from) =>{
        //设置被激活的tab 标签
        activeTab.value = to.path
        //打印控制台日志
        console.log({
            title: to.meta.title,
            path: to.path
        })
        addTab({
            title: to.meta.title,
            path: to.path
        })
    })
    //删除tab标签
    const removeTab = (targetPath) => {
        let tabs = tabList.value
        // 当前被选中的 tab 标签
        let actTab = activeTab.value
        console.log("tabs--"+tabs+",actTab=="+actTab+",targetPath=="+targetPath)
        // 如果要删除的是当前被选中的标签页，则需要判断其被删除后，需要激活哪个 tab 标签页
        if (actTab === targetPath) {
            // 循环 tabList
            tabs.forEach((tab, index) => {
                // 获取被选中的 tab 元素
                if (tab.path === targetPath) {
                    // 获取被选中的 tab 元素
                    const nextTab = tabs[index + 1] || tabs[index - 1]
                    if (nextTab) {
                        actTab = nextTab.path                 
                    }
                }
            })
        }
        // 需要被激活的标签页
        activeTab.value = actTab
        // 过滤掉被删除的标签页, 重新设置回去
        tabList.value = tabs.filter((tab) => tab.path !== targetPath)
        // 存储到 cookie 中
        setTabList(tabList.value)
        
        // 切换标签页
        tabChange(activeTab.value)
    }
    //标签切换事件
    const tabChange =(path) =>{
        console.log("切换到" + path)
        //设置被激活的tab
        activeTab.value = path
        router.push(path)
    }
    function initTabList(){
        //从cookie中获取缓存起来的导航栏标签数据
        let tabs = getTabList()
        if(tabs){
            tabList.value = tabs
        }
    }
    // 处理关闭标签菜单事件
    const handlerCloseTabs = (command) =>{
        console.log(command)
        //首页路由
        let indexPath = '/admin/index'
        //处理关闭其他
        if(command == 'closeOthers'){
            //仅过滤出首页和当前页
            tabList.value = tabList.value.filter((tab) => tab.path == indexPath || tab.path == activeTab.value )
        }else{//处理关闭全部
            //切换到首页
            activeTab.value = indexPath
            tabList.value = tabList.value.filter((tab) => tab.path == indexPath )
            tabChange(activeTab.value)
        }
        setTabList(tabList)

    }

    //初始化标签栏
    initTabList()

    return {
        menuStore,
        activeTab,
        tabList,
        tabChange,
        removeTab,
        handlerCloseTabs
    }
}