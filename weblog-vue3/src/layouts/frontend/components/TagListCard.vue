<template>
    <!-- 标签 -->
    <div class="w-full p-5 mt-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
        <!-- 标签标题 -->
        <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h2>
        <!-- 标签列表 -->
        <span v-for="(tag, index) in tages" :key="index"
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">{{tag.name}}</span>
        <!-- <span
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
        <span
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
        <span
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
        <span
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
        <span
            class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span> -->
    </div>
</template>
<script setup>
import {ref} from 'vue'
import { getTagPageList } from '@/api/frontend/tag.js'
//所有标签
const tages = ref([])
getTagPageList().then((res) =>{
    if(res.success){
        tages.value = res.data
    }
})

</script>