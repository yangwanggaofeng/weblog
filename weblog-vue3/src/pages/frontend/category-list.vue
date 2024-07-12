<template>
    <Header></Header>
    <!--主内容区域-->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!--  grid 表格布局，分为 4 列，元素间隔为 gap-4 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4  md:col-span-3 mb-3">
                <!-- 分类列表 -->
                 <!-- 分类模块 -->
                 <CategoryListCard></CategoryListCard>
            </div>
            <!-- 右边侧边栏，占用一列 -->
            <div class="col-span-4 md:col-span-1">
                <!-- 博主信息模块 -->
                <UserInfoCard></UserInfoCard>
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

</script>