<template>
  <!--html代码-->
  <!-- <h1>Hello vue3!</h1> -->

  <el-config-provider :locale="locale">
    <router-view></router-view>
  </el-config-provider>
</template>

<script setup>
//js 代码, setup 标识通常和组合式 API 搭配使用, 用于告诉 Vue 需要在编译时进行一些处理，让我们可以更简洁地使用组合式 API 
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
const locale = zhCn
</script>

<style>
/*css 代码， scoped 表示节点内 css 样式只针对此组件有效，不影响其他组件 */
/* 自定义顶部加载 Loading 颜色 */
#nprogress .bar {
  background: #409eff !important;
}
</style>