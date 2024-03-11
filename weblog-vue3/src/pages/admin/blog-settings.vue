<template>
    <div>
        <!--卡片组件， shadow="never" 指定card卡片没有阴影-->
        <el-card shadow="never">
            <el-form ref="formRef" :model="form" label-width="160px" :rules="rules">
                <el-form-item label="博客名称" prop="name">
                    <el-input v-model="form.name" clearable />
                </el-form-item>
                <el-form-item label="作者名称" prop="author">
                    <el-input v-model="form.author" clearable />
                </el-form-item>
                <el-form-item label="博客Logo" prop="logo">
                    <el-upload class="avatar-uploader" action="#" :show-file-list="false" :on-change="handleLogoChange"
                        :auto-upload="false">
                        <img v-if="form.logo" :src="form.logo" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="作者头像" prop="avatar">
                    <el-upload class="avatar-uploader" action="#" :show-file-list="false"
                        :on-change="handleAvatarChange" :auto-upload="false">
                        <img v-if="form.avatar" :src="form.avatar" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="介绍语" prop="introduction">
                    <el-input v-model="form.introduction" type="textarea" />
                </el-form-item>

                <el-form-item label="开启 GihHub 访问">
                    <el-switch v-model="isGithubChecked" inline-prompt :active-icon="check" :inactive-icon="close"
                        @change="githubSwitchChange" />
                </el-form-item>
                <el-form-item label="GitHub 主页访问地址" v-if="isGithubChecked">
                    <el-input v-model="form.githubHomepage" clearable placeholder="请输入 GitHub 主页访问的 URL" />
                </el-form-item>

                <el-form-item label="开启 Gitee 访问">
                    <el-switch v-model="isGiteeChecked" inline-prompt :active-icon="check" :inactive-icon="close"
                        @change="giteeSwitchChange" />
                </el-form-item>
                <el-form-item label="Gitee 主页访问地址" v-if="isGiteeChecked">
                    <el-input v-model="form.giteeHomepage" clearable placeholder="请输入 Gitee 主页访问的 URL" />
                </el-form-item>
                <el-form-item label="开启 Zhihu 访问">
                    <el-switch v-model="isZhihuChecked" inline-prompt :active-icon="check" :inactive-icon="close"
                        @change="zhihuSwitchChange" />
                </el-form-item>
                <el-form-item label="Zhihu 主页访问地址" v-if="isZhihuChecked">
                    <el-input v-model="form.zhihuHomepage" clearable placeholder="请输入 Zhihu 主页访问的 URL" />
                </el-form-item>

                <el-form-item label="开启 csdn 访问">
                    <el-switch v-model="isCsdnChecked" inline-prompt :active-icon="check" :inactive-icon="close"
                        @change="csdnSwitchChange" />
                </el-form-item>
                <el-form-item label="csdn 主页访问地址" v-if="isCsdnChecked">
                    <el-input v-model="form.csdnHomepage" clearable placeholder="请输入 csdn 主页访问的 URL" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" :loading="btnLoading" @click="onSubmit">保存</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
// import { check, close } from '@element-plus/icons-vue';
import { Check, Close } from '@element-plus/icons-vue';
import { getBlogSettingsDetail, updateBlogSettings } from '@/api/admin/blogsettings'
import { uploadFile } from '@/api/admin/file'
import { showMessage } from '@/composables/utils'
// 是否显示保存按钮的 loading 状态，默认为 false
const btnLoading = ref(false)
// 表单引用
const formRef = ref(null)

//表单对象
const form = reactive({
    name: '',
    author: '',
    logo: '',
    introduction: '',
    avatar: '',
    githubHomepage: '',
    giteeHomepage: '',
    csdnHomepage: '',
    zhihuHomepage: '',
})
const rules = {
    name: [{ required: true, message: '请输入博客名称', trigger: 'blur' }],
    author: [{ required: true, message: '请输入作者名称', trigger: 'blur' }],
    logo: [{ required: true, message: '请上传博客logo', trigger: 'blur' }],
    avatar: [{ required: true, message: '请上传博客头像', trigger: 'blur' }],
    introduction: [{ required: true, message: '请输入介绍语', trigger: 'blur' }],
}
// 是否开启 GitHub
const isGithubChecked = ref(false)
// 是否开启 Gitee
const isGiteeChecked = ref(false)
// 是否开启 zhihu
const isZhihuChecked = ref(false)
// 是否开启 csdn
const isCsdnChecked = ref(false)

// 监听 Github Switch 改变事件
const githubSwitchChange = (checked) => {
    if (checked == false) {
        form.githubHomepage = ''
    }
}
// 监听 Gitee Switch 改变事件
const giteeSwitchChange = (checked) => {
    if (checked == false) {
        form.giteeHomepage = ''
    }
}
// 监听 Github Switch 改变事件
const zhihuSwitchChange = (checked) => {
    if (checked == false) {
        form.zhihuHomepage = ''
    }
}
// 监听 Github Switch 改变事件
const csdnSwitchChange = (checked) => {
    if (checked == false) {
        form.csdnHomepage = ''
    }
}

// 初始化博客设置数据，并渲染到页面上
function initBlogSettings() {
    getBlogSettingsDetail().then((e) => {
        if (e.success == true) {
            //设置表单数据
            form.name = e.data.name
            form.author = e.data.author
            form.logo = e.data.logo
            form.avatar = e.data.avatar
            form.introduction = e.data.introduction
            if (e.data.githubHomepage) {
                isGithubChecked = true
                form.githubHomepage = e.data.githubHomepage
            }
            if (e.data.giteeHomepage) {
                isGiteeChecked = true
                form.giteeHomepage = e.data.giteeHomepage
            }
            if (e.data.zhihuHomepage) {
                isZhihuChecked = true
                form.zhihuHomepage = e.data.zhihuHomepage
            }
            if (e.data.csdnHomepage) {
                isCsdnChecked = true
                form.csdnHomepage = e.data.csdnHomepage
            }
        }
    })
}
initBlogSettings()

const handleLogoChange = (file) => {
    //表单对象
    // 表单对象
    let formData = new FormData()
    // 添加 file 字段，并将文件传入 
    formData.append('file', file.raw)
    // console.log(formData.file.name)
    uploadFile(formData).then((e) => {
        //响应失败提示错误信息
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'error')
            return
        }
        //成功则设置log 链接，并提示成功
        form.logo = e.data.url
        console.log(form.logo)
        showMessage("上传成功")
    })

}
const handleAvatarChange = (file) => {
    //表单对象
    // 表单对象
    let formData = new FormData()
    // 添加 file 字段，并将文件传入 
    formData.append('file', file.raw)
    // console.log(formData.file.name)
    uploadFile(formData).then((e) => {
        //响应失败提示错误信息
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'error')
            return
        }
        //成功则设置log 链接，并提示成功
        form.avatar = e.data.url
        console.log(form.logo)
        showMessage("上传成功")
    })

}
const onSubmit = () => {
    formRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        }else{
           btnLoading.value = true
           updateBlogSettings(form).then((res) =>{
                if(res.success == false){
                    //获取服务器端返回的错误信息
                    let message = res.message
                    //提示错误信息
                    showMessage(message, 'error')
                    return
                }
                //重新渲染页面信息
                initBlogSettings()
                showMessage('保存成功')

           }).finally(() => btnLoading.value = false ) //隐藏保存按钮 loading
        }
    })

}
</script>
<style scoped>
.avatar-uploader .avatar {
    width: 100px;
    height: 100px;
    display: block;
}
</style>

<style>
/* 解决 textarea :focus 状态下，边框消失的问题 */
.el-textarea__inner:focus {
    outline: 0 !important;
    box-shadow: 0 0 0 1px var(--el-input-focus-border-color) inset !important;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    text-align: center;
}
</style>
