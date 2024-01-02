<template>
    <!--sytle和:style的区别-->
    <div class="fixed top-[64px] h-[44px] px-2 right-0 z-50 flex items-center bg-white " :style="{left: menuStore.menuWidth}">
        <!-- <div style="margin-bottom: 20px">
            <el-button size="small" @click="addTab(editableTabsValue)">
                add tab
            </el-button>
        </div> -->
        <el-tabs v-model="activeTab" type="card" class="demo-tabs"  @tab-remove="removeTab" @tab-change="tabChange"
            style="min-width: 10px;">
            <el-tab-pane v-for="item in tabList" :key="item.path" :label="item.title" :name="item.path" :closable="item.path != '/admin/index'" >
            </el-tab-pane>  
        </el-tabs>
        <!--右侧下拉菜单-->
        <span class="ml-auto flex items-center justify-center h-[32px] w-[32px]">
            <el-dropdown @command="handlerCloseTabs">
                <span class="el-dropdown-link">
                    <el-icon>
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="closeOthers" >关闭其他</el-dropdown-item>
                        <el-dropdown-item command="closeAll" >关闭全部</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </span>
    </div>
    <div class="h-[44px]">

    </div>
</template>
<script setup>
import { useTabList } from '@/composables/useTagList.js'
const { menuStore, activeTab, tabList, tabChange, removeTab, handlerCloseTabs } = useTabList()
/*import { ref } from 'vue'
//引入menuStore
import { useMenuStore } from '@/stores/menu'
import { useRoute, onBeforeRouteUpdate, useRouter } from 'vue-router'
import { setTabList, getTabList } from '@/composables/cookie.js'

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
//添加tab标签
// const addTab = (targetPath) => {
//     const newTabName = `${++tabIndex}`
//     editableTabs.value.push({
//         title: 'New Tab',
//         name: newTabName,
//         content: 'New Tab content',
//     })
//     editableTabsValue.value = newTabName
// }
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
const handlerCloseTabs = (command) =>{
    console.log(command)
    //首页路由
    let indexPath = '/admin/index'
    //处理关闭其他
    if(command == 'closeOthers'){
        console.log(activeTab.path)
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
initTabList()*/
</script>
<style>
/* .demo-tabs>.el-tabs__content {
    padding: 32px;
    color: #6b778c;
    font-size: 32px;
    font-weight: 600;
} */
.el-tabs__item {
    font-size: 12px;
    border: 1px solid #d8dce5 !important;
    border-radius: 3px !important;
}

.el-tabs--card>.el-tabs__header .el-tabs__item {
    margin-left: 0.1rem !important;
    margin-right: 0.1rem !important;
}

.el-tabs__item.is-active {
    background-color: var(--el-color-primary) !important;
    color: #fff;
}

.el-tabs__item.is-active::before {
    content: "";
    background-color: #fff;
    display: inline-block;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    position: relative;
    margin-right: 4px;
}

.el-tabs {
    height: 32px;
}

.el-tabs__header {
    margin-bottom: 0;
}

.el-tabs--card>.el-tabs__header .el-tabs__nav {
    border: 0;
}

.el-tabs--card>.el-tabs__header .el-tabs__item {
    height: 32px;
    line-height: 32px;
    border: 0;
    background: #fff;
}

.el-tabs--card>.el-tabs__header {
    border: 0;
}

.el-tabs__nav-prev,
.el-tabs__nav-next {
    line-height: 35px;
}

.is-disabled {
    cursor: not-allowed;
    color: #d1d5db;
}</style>
  