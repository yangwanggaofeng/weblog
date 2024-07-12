<template>
    <Header></Header>
    <!--主内容区域-->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!--  grid 表格布局，分为 4 列，元素间隔为 gap-4 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4  md:col-span-3 mb-3">
                <!-- 标签模块 -->
                <!-- 标签 -->
                <div v-if="tages.length"
                    class="w-full p-5 mt-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                    <!-- 标签标题 -->
                    <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h2>
                    <!-- 标签列表 -->
                    <span v-for="(tag, index) in tages" :key="index" @click="goTagArticleListPage(tag.id, tag.name)"
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-3 py-1 rounded-full hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">{{ tag.name }}</span>
                </div>
            </div>
            <!-- 右边侧边栏，占用一列 -->
            <div class="col-span-4 md:col-span-1">
                <!-- 博主信息模块 -->
                <UserInfoCard></UserInfoCard>
                <!-- 分类模块 -->
                <CategoryListCard></CategoryListCard>

            </div>
        </div>
    </main>
    <Footer></Footer>
</template>



<script setup>
import { ref } from 'vue'
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue';
import UserInfoCard from '@/layouts/frontend/components/userInfoCard.vue';
import CategoryListCard from '@/layouts/frontend/components/CategoryListCard.vue';
import { getTagPageList } from '@/api/frontend/tag.js'
import { useRouter } from 'vue-router';
//引入路由
const router = useRouter()

//跳转到分类文章列表页
const goTagArticleListPage = (tagId,tagName) =>{
    console.log("分页跳转---------》"+tagId)
    router.push({path: '/tag/article/list',query:{tagId,tagName}})
}
//所有标签
const tages = ref([])
getTagPageList().then((res) =>{
    if(res.success){
        tages.value = res.data
    }
})

</script>