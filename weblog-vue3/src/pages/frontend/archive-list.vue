<template>
    <Header></Header>
    <!--主内容区域-->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!--  grid 表格布局，分为 4 列，元素间隔为 gap-4 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4  md:col-span-3 mb-3">
                <div v-for="(archiveArr, index) in archiveArticles" :key="index"
                    class="p-5 mb-4 border border-gray-100 rounded-lg bg-gray-50 dark:bg-gray-800 dark:border-gray-700">
                    <time class="text-lg font-semibold text-gray-900 dark:text-white">{{ archiveArr.month }}</time>
                    <ol class="mt-3 divide-y divider-gray-200 dark:divide-gray-700">
                        <li v-for="(article ,index2) in archiveArr.articleList">
                            <a href="#" class="items-center block p-3 sm:flex hover:bg-gray-100 dark:hover:bg-gray-700">
                                <img class="w-24 h-12 mb-3 mr-3 rounded-lg sm:mb-0"
                                    :src="article.cover"
                                    alt="Jese Leos image" />
                                <div class="text-gray-600 dark:text-gray-400">
                                    <h2 class="text-base font-normal text-gray-900">
                                        {{ article.title }}
                                    </h2>
                                    <span
                                        class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                        <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400 dark:text-white"
                                            aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                            viewBox="0 0 20 20">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2"
                                                d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                        </svg>
                                        {{article.createDate}}
                                    </span>
                                </div>
                            </a>
                        </li>
                        <!-- <li>
                            <a href="#" class="items-center block p-3 sm:flex hover:bg-gray-100 dark:hover:bg-gray-700">
                                <img class="w-24 h-12 mb-3 mr-3 rounded-lg sm:mb-0"
                                    src="https://img.quanxiaoha.com/quanxiaoha/193dd1504ebb4f138085acb23619e0dd.jpg"
                                    alt="Jese Leos image" />
                                <div class="text-gray-600 dark:text-gray-400">
                                    <h2 class="text-base font-normal text-gray-900">
                                        从零手撸前后端分离博客（已更新16w+字）
                                    </h2>
                                    <span
                                        class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                        <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400 dark:text-white"
                                            aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                            viewBox="0 0 20 20">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2"
                                                d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                        </svg>
                                        2024-07-01
                                    </span>
                                </div>
                            </a>
                        </li> -->
                    </ol>
                </div>
                <!-- <div
                    class="p-5 mb-4 border border-gray-100 rounded-lg bg-gray-50 dark:bg-gray-800 dark:border-gray-700">
                    <time class="text-lg font-semibold text-gray-900 dark:text-white">2024-06</time>
                    <ol class="mt-3 divide-y divider-gray-200 dark:divide-gray-700">
                        <li>
                            <a href="#" class="items-center block p-3 sm:flex hover:bg-gray-100 dark:hover:bg-gray-700">
                                <img class="w-24 h-12 mb-3 mr-3 rounded-lg sm:mb-0"
                                    src="https://img.quanxiaoha.com/quanxiaoha/193dd1504ebb4f138085acb23619e0dd.jpg"
                                    alt="Jese Leos image" />
                                <div class="text-gray-600 dark:text-gray-400">
                                    <h2 class="text-base font-normal text-gray-900">
                                        从零手撸前后端分离博客（已更新16w+字）
                                    </h2>
                                    <span
                                        class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                        <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400 dark:text-white"
                                            aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                            viewBox="0 0 20 20">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2"
                                                d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                        </svg>
                                        2024-06-30
                                    </span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="items-center block p-3 sm:flex hover:bg-gray-100 dark:hover:bg-gray-700">
                                <img class="w-24 h-12 mb-3 mr-3 rounded-lg sm:mb-0"
                                    src="https://img.quanxiaoha.com/quanxiaoha/193dd1504ebb4f138085acb23619e0dd.jpg"
                                    alt="Jese Leos image" />
                                <div class="text-gray-600 dark:text-gray-400">
                                    <h2 class="text-base font-normal text-gray-900">
                                        从零手撸前后端分离博客（已更新16w+字）
                                    </h2>
                                    <span
                                        class="inline-flex items-center text-xs font-normal text-gray-500 dark:text-gray-400">
                                        <svg class="inline w-2.5 h-2.5 mr-2 text-gray-400 dark:text-white"
                                            aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                            viewBox="0 0 20 20">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                stroke-width="2"
                                                d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                        </svg>
                                        2024-06-01
                                    </span>
                                </div>
                            </a>
                        </li>
                    </ol>
                </div> -->
                <nav aria-label="Page navigation example" class="mt-10 flex justify-center ">
                    <ul class="flex items-center -space-x-px h-10 text-base">
                        <li>
                            <a @click="getArticles(current -1 )"
                                class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current > 1 ? '' : 'cursor-not-allowed']"
                                >
                                <span class="sr-only">上一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <!--页码-->
                        <li v-for="pageNo, index in pages" :key="index">
                            <a @click="getArchiveArticle(pageNo)"
                                class="flex items-center justify-center px-4 h-10 leading-tight bg-white border border-gray-300 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[pageNo == current ? 'text-blue-600 bg-blue-50 hover:bg-blue-100 hover:text-blue-700' : 'text-gray-500  hover:bg-gray-100 hover:text-gray-700']">{{
                                index + 1 }}</a>

                        </li>
                        <li>
                            <a @click="getArchiveArticle(current +1 )"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current < pages ? '' :'cursor-not-allowed']">
                                <span class="sr-only">下一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

            <!-- 右边侧边栏，占用一列 -->
            <div class="col-span-4 md:col-span-1">
                <!-- 博主信息模块 -->
                <UserInfoCard></UserInfoCard>

                <!-- 分类模块 -->
                <CategoryListCard></CategoryListCard>

                <!-- 标签模块 -->
                <TagListCard></TagListCard>
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
import TagListCard from '@/layouts/frontend/components/TagListCard.vue';
import {getArchivePageList} from '@/api/frontend/archive.js'
//文章集合
const archiveArticles = ref([])
//当前页码
const current = ref(1)
//每页显示条数
const size = ref(3)
//总文章个数
const total = ref(0)
//总页数
const pages = ref(0)
//获取指定页的文章数据
function getArchiveArticle(currentNo) {
    //上下页是否能够点击判断，当跳转上一页小于1时；则不允许跳转；当要跳转下一页且页码大于总页数时，则不允许跳转
    if(currentNo <1 || (pages.value >0 && currentNo > pages.value)) {
        console.log("非法页码")
        return
    }
    console.log("size.value===" + size.value)
    //调用分页接口渲染数据
    getArchivePageList({ current: currentNo, size: size.value }).then((res) => {
        if (res.success) {
            archiveArticles.value = res.data
            current.value = res.current
            total.value = res.total
            size.value = res.size
            pages.value = res.pages
        }
    })
}
getArchiveArticle(current.value)
</script>