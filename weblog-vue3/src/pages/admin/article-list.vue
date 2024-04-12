<template>
    <div>
        文章管理页
        <!--表头分页查询条件，shadow="never" 指定card 卡片组件没有阴影-->
        <el-card shadow="never" class="mb-5">
            <!--flex布局 ，内容垂直居中-->
            <div class="flex items-center ">
                <el-text>文章标题</el-text>
                <div class="ml-3 w-52 mr-5 ">
                    <el-input v-model="searchArticleTitle" placeholder="请输入（模糊查询）" clearable />
                </div>
                <el-text>创建日期</el-text>
                <!--日期选择组件（区间选择）-->
                <div class="ml-3 w-30 mr-5 ">
                    <el-date-picker v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
                        end-placeholder="结束时间" size="default" @change="datepickerChange" :shortcuts="shortcuts">
                    </el-date-picker>
                </div>
                <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
                <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
            </div>
        </el-card>

        <el-card>
            <div class="mb-5">
                <!-- <el-button type="primary" @click="dialogVisible = true"> -->
                <el-button type="primary" @click="isArticlePublishEditorShow = true">
                    <el-icon class="mr-1">
                        <EditPen />
                    </el-icon>
                    写文章
                </el-button>

            </div>
            <el-table :data="tableData" :border="true" stripe style="width: 100%" v-loading="tableLoading">
                <el-table-column prop="title" label="标题" width="380" />
                <!-- <el-table-column prop="cover" label="封面" width="180" /> -->
                <el-table-column prop="cover" label="封面" width="180">
                    <template #default="scope">
                        <el-image style="width: 100px;" :src="scope.row.cover" />
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" />
                <el-table-column label="操作" >
                    <template #default="scope">
                        <el-button size="small" @click="updateArticleSubmit(scope.row)">
                            <el-icon class="mr-1">
                                <Edit />
                            </el-icon>
                            编辑
                        </el-button>
                        <el-button type="danger" size="small" @click="deleteArticleSubmit(scope.row)">
                            <el-icon class="mr-1">
                                <Delete />
                            </el-icon>
                            删除
                        </el-button>

                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <div class="mt-10 flex justify-center">
                <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[1, 10, 20, 50]"
                    :small="false" :disabled="disabled" :background="true"
                    layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handSizeChange"
                    @current-change="getTableData" />
            </div>
        </el-card>
        <el-dialog v-model="isArticlePublishEditorShow" :show-close="false" :fullscreen="true">
            <template #header="{ close, titleId, titleClass }">
                <!-- 固钉组件，固钉到顶部 -->
                <el-affix :offset="0" style="width: 100%;">
                    <!-- 指定 flex 布局， 高度为 10， 背景色为白色 -->
                    <div class="flex h-10 bg-white">
                        <h4 class="font-bold">写文章</h4>
                        <!--靠右对齐-->
                        <div class="ml-auto flex">
                            <el-button @click="isArticlePublishEditorShow = false">取消</el-button>
                            <el-button type="primary" @click="publishArticleSubmit">
                                <el-icon class="mr-1">
                                    <Promotion />
                                </el-icon>
                                发布
                            </el-button>
                        </div>
                    </div>
                </el-affix>
            </template>
            <!-- label-position="top" 用于指定 label 元素在上面  -->
            <el-form :model="form" ref="publishArticleFormRef" label-position="top" size="large" :rules="rules">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="form.title" autocomplete="off" size="large" maxlength="40" show-word-limit
                        clearable />
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <!-- Markdown 编辑器 -->
                    <MdEditor v-model="form.content" @onUploadImg="onUploadImg" editorId="publishArticleEditor" />
                </el-form-item>
                <el-form-item label="封面" prop="cover">
                    <div class="w-10">
                        <el-upload class="avatar-uploader" action="#" :show-file-list="false"
                            :on-change="handleCoverChange" :auto-upload="false">
                            <img v-if="form.cover" :src="form.cover" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon">
                                <Plus />
                            </el-icon>
                        </el-upload>
                    </div>
                </el-form-item>
                <el-form-item label="摘要" prop="summary">
                    <el-input v-model="form.summary" type="textarea" placeholder="请输入文章摘要" :row="3" />
                </el-form-item>
                <el-form-item label="分类" prop="categoryId">
                    <el-select v-model="form.categoryId" clearable reserve-keyword placeholder="---请选择---" size="large">
                        <el-option v-for="item in categoryies" :key="item.value" :label="item.label"
                            :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标签" prop="tags">
                    <!--
                                filterable:为el-select添加filterable属性即可启用搜索功能。为el-select添加filterable属性即可启用搜索功能。
                                 默认情况下，Select 会找出所有 label 属性包含输入值的选项。 如果希望使用其他的搜索逻辑，可以通过
                                 传入一个 filter-method 来实现。 filter-method 为一个 Function，它会在输入值发生变化时调用，参数为当前输入值。
                                 multiple： 属性即可启用多选
                                 从服务器搜索数据，输入关键字进行查找。为了启用远程搜索，需要将filterable和remote设置为true
                                 reserve-keyword: 当 multiple 和 filterable被设置为 true 时，是否在选中一个选项后保留当前的搜索关键词
                                 allow-create： 是否允许用户创建新条目， 只有当 filterable 设置为 true 时才会生效。
                                 default-first-option：是否在输入框按下回车时，选择第一个匹配项。 需配合 filterable 或 remote 使用

                            -->
                    <el-select v-model="form.tags" multiple filterable remote reserve-keyword placeholder="请输入文章标签"
                        remote-show-suffix allow-create default-first-option :remote-method="remoteMethod"
                        :loading="tagSelectLoading" size="large">
                        <el-option v-for="item in tags" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
            </el-form>

        </el-dialog>
        <el-dialog v-model="isArticleUpdateEditorShow" :show-close="false" :fullscreen="true" :close-on-press-escape="false" >
            <template #header="{ close, titleId, titleClass }">
                <!-- 固钉组件，固钉到顶部 -->
                <el-affix :offset="0" style="width: 100%;">
                    <!-- 指定 flex 布局， 高度为 10， 背景色为白色 -->
                    <div class="flex h-10 bg-white">
                        <h4 class="font-bold">编辑文章</h4>
                        <!--靠右对齐-->
                        <div class="ml-auto flex">
                            <el-button @click="isArticleUpdateEditorShow = false">取消</el-button>
                            <el-button type="primary" @click="saveArticleSubmit">
                                <el-icon class="mr-1">
                                    <Promotion />
                                </el-icon>
                                保存
                            </el-button>
                        </div>
                    </div>
                </el-affix>
            </template>
            <!-- label-position="top" 用于指定 label 元素在上面  -->
            <el-form :model="updateArticleForm" ref="updateArticleFormRef" label-position="top" size="large" :rules="rules">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="updateArticleForm.title" autocomplete="off" size="large" maxlength="40" show-word-limit
                        clearable />
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <!-- Markdown 编辑器 -->
                    <MdEditor v-model="updateArticleForm.content" @onUploadImg="onUploadImg" editorId="publishArticleEditor" />
                </el-form-item>
                <el-form-item label="封面" prop="cover">
                    <div class="w-10">
                        <el-upload class="avatar-uploader" action="#" :show-file-list="false"
                            :on-change="handleUpdateCoverChange " :auto-upload="false">
                            <img v-if="updateArticleForm.cover" :src="updateArticleForm.cover" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon">
                                <Plus />
                            </el-icon>
                        </el-upload>
                    </div>
                </el-form-item>
                <el-form-item label="摘要" prop="summary">
                    <el-input v-model="updateArticleForm.summary" type="textarea" placeholder="请输入文章摘要" :row="3" />
                </el-form-item>
                <el-form-item label="分类" prop="categoryId">
                    <el-select v-model="updateArticleForm.categoryId" clearable reserve-keyword placeholder="---请选择---" size="large">
                        <el-option v-for="item in categoryies" :key="item.value" :label="item.label"
                            :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标签" prop="tags">
                    <el-select v-model="updateArticleForm.tags" multiple filterable remote reserve-keyword placeholder="请输入文章标签"
                        remote-show-suffix allow-create default-first-option :remote-method="remoteMethod"
                        :loading="tagSelectLoading" size="large">
                        <el-option v-for="item in tags" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
            </el-form>

        </el-dialog>
    </div>
</template>
<script setup>
// 引入所需图标
import FormDialog from '@/components/FormDialog.vue';
import { Search, RefreshRight, Delete } from '@element-plus/icons-vue'
import { ref, reactive } from 'vue';
import moment from 'moment'
import { getArticlePageList, deleteArticle, publishArticle,getArticleDetail, updateArticle } from '@/api/admin/article'
import { showMessage, showModel } from '@/composables/utils'
import { MdEditor } from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { uploadFile } from '@/api/admin/file'
import { getCategorySelectList } from '@/api/admin/category'
import { searchTags, getTagSelectList } from '@/api/admin/tag'
// 分页查询的分类名称
const searchArticleTitle = ref('')
// 日期
const pickDate = ref('')
// 表格数据
const tableData = ref([])
// 当前页码，给了一个默认值 1
const current = ref(1)
// 总数据量，给了个默认值 0
const total = ref(0)
// 每页显示的数据量，给了个默认值 10
const size = ref(10)
const tableLoading = ref(false)
//获取分页数据
function getTableData() {
    // 显示表格 loading
    tableLoading.value = true
    //调用后台分页接口，并传入参数
    getArticlePageList({
        "current": current.value,
        "endTime": endDate.value,
        "size": size.value,
        "strarTime": startDate.value,
        "title": searchArticleTitle.value

    }).then((res) => {
        console.log(res.data)
        console.log(res.success)
        if (res.success == true) {
            tableData.value = res.data
            current.value = res.current
            size.value = res.size
            total.value = res.total
        }
    }).finally(() => tableLoading.value = false) //隐藏表格
}
//查询条件：开始时间和结束时间
const startDate = reactive({})
const endDate = reactive({})
//监听日志组件改变事件，并将开始结束时间设置到变量中
const datepickerChange = (e) => {
    startDate.value = moment(e[0]).format('YYYY-MM-DD')
    endDate.value = moment(e[1]).format('YYYY-MM-DD')
    console.log('开始时间： ' + startDate.value + ",结束时间：" + endDate.value)
}
const oneDay = 3600 * 1000 * 24
const shortcuts = [
    {
        text: '最近一周',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            return [start, end]
        }
    }, {
        text: '最近一个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - oneDay * 30)
            return [start, end]
        }
    }, {
        text: '最近三个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - oneDay * 90)
            return [start, end]
        }
    }
]
getTableData()
const handSizeChange = (chooseSize) => {
    size.value = chooseSize
    getTableData()
}
const reset = () => {
    searchArticleTitle.value = ''
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
}
// const dialogVisible = ref(false)
const formDialogRef = ref(null)
// const add = () => {
//     console.log("add")
//     dialogVisible.value = true
// }
// 表单引用
const publishArticleFormRef = ref(null)

// 修改用户密码表单对象
// 表单对象
const form = reactive({
    id: null,
    title: '',
    content: '请输入内容',
    cover: '',
    categoryId: null,
    tags: [],
    summary: ""
})

// 规则校验
// 表单校验规则
const rules = {
    title: [
        { required: true, message: '请输入文章标题', trigger: 'blur' },
        { min: 1, max: 40, message: '文章标题要求大于1个字符，小于40个字符', trigger: 'blur' },
    ],
    content: [{ required: true }],
    cover: [{ required: true }],
    categoryId: [{ required: true, message: '请选择文章分类', trigger: 'blur' }],
    tags: [{ required: true, message: '请选择文章标签', trigger: 'blur' }],
}
const onSubmit = () => {
    formDialogRef.value.showBtnLoading()
    //先验证表单字段
    formRef.value.validate((valid) => {
        console.log(form.name)

        if (!valid) {
            console.log('表单验证不通过')
            return false
        }
        //调用修改密码接口
        addCategory(form).then((res) => {
            console.log(res)
            //判断是否成功
            if (res.success == true) {
                showMessage("添加成功")
                form.name = ''
                // dialogVisible.value = false
                formDialogRef.value.close()
                //重新请求分页接口渲染数据
                getTableData()
            } else {
                //获取服务端返回的错误信息
                let message = res.message
                //提示信息
                showMessage(message, "error")
            }
        }).finally(() => formDialogRef.value.closeBtnLoading())
    })

}
const deleteArticleSubmit = (row) => {
    console.log(row)
    showModel('是否确认要删除该文章吗吗？').then(() => {
        deleteArticle(row.id).then((res) => {
            if (res.success == true) {
                showMessage("删除成功")
                //重新请求分页接口渲染数据
                getTableData()
            } else {
                //获取服务端返回的错误信息
                let message = res.message
                //提示信息
                showMessage(message, "error")
            }
        })
    }).catch(() => {
        showMessage("取消删除")
    })

}
//新增分类按钮点击事件
const addArticleBtnClick = () => {
    formDialogRef.value.open()
}


//是否显示文章发布对话框
const isArticlePublishEditorShow = ref(false)
//封面按钮
const handleCoverChange = (file) => {
    //表单对象
    // 表单对象
    let formData = new FormData()
    // 添加 file 字段，并将文件传入 
    formData.append('file', file.raw)
    uploadFile(formData).then((e) => {
        //响应失败提示错误信息
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'error')
            return
        }
        //成功则设置log 链接，并提示成功
        form.cover = e.data.url
        showMessage("上传成功")
    })

}
const onUploadImg = async (files, callback) => {
    const res = await Promise.all(
        files.map((file) => {
            return new Promise((rev, rej) => {
                console.log('===>编辑器开始上传文件...')
                let formdata = new FormData();
                formdata.append('file', file);
                uploadFile(formdata).then((res) => {
                    console.log(res)
                    console.log("访问路径" + res.data.url)
                    // 调用 callback 函数，回显上传图片
                    callback([res.data.url]);
                })
            });
        })
    );
}
//文章分类
const categoryies = ref([])
getCategorySelectList().then((res) => {
    console.log("获取分类数据")
    categoryies.value = res.data
})
// 标签 select Loading 状态，默认不显示
const tagSelectLoading = ref(false)
// 文章标签
const tags = ref([])
// 渲染标签数据
getTagSelectList().then(res => {
    tags.value = res.data
})
const remoteMethod = (query) => {
    console.log("远程搜索" + tags.value)
    //如果用户查询的关键次不为空
    if (query) {
        //显示loading
        tagSelectLoading.value = true
        // 调用标签模糊查询接口
        searchTags(query).then((res) => {
            if (res.success) {
                console.log(res.data)
                tags.value = res.data
            }
        }).finally(() => tagSelectLoading.value = false)
    }
}
//发布文章
const publishArticleSubmit = () => {
    console.log("markdown 内容" + form.content)
    publishArticleFormRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        } else {
            publishArticle(form).then((res) => {
                if (res.success) {
                    showMessage("发布成功")
                    isArticlePublishEditorShow.value = false
                    //form表单清空
                    form.title = ''
                    form.content = ''
                    form.cover = ''
                    form.summary = ''
                    form.categoryId = null
                    form.tags = []
                    // 重新请求分页接口，渲染列表数据
                    getTableData()
                } else {
                    let message = res.message
                    //提示错误信息
                    showMessage(error, message)
                    return
                }
            })
        }

    })
}
// 编辑文章表单引用
const updateArticleFormRef = ref(null)
//是否显示文章发布对话框
const isArticleUpdateEditorShow = ref(false)
// 修改文章表单对象
const updateArticleForm = reactive({
        id: null,
        title: '',
        content: '请输入内容',
        cover: '',
        categoryId: null,
        tags: [],
        summary: ""
 })
 //封面按钮
const handleUpdateCoverChange = (file) => {
    //表单对象
    // 表单对象
    let formData = new FormData()
    // 添加 file 字段，并将文件传入 
    formData.append('file', file.raw)
    uploadFile(formData).then((e) => {
        //响应失败提示错误信息
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'error')
            return
        }
        //成功则设置log 链接，并提示成功
        updateArticleForm.cover = e.data.url
        showMessage("上传成功")
    })

}
// 编辑文章按钮点击事件
const updateArticleSubmit = (row) => {
    console.log(row)
     // 显示编辑文章对话框
    isArticleUpdateEditorShow.value = true
    let articleId = row.id

    getArticleDetail(articleId).then((res) => {
        if (res.success == true) {
            // 设置表单数据
            updateArticleForm.id = res.data.id
            updateArticleForm.title = res.data.title
            updateArticleForm.cover = res.data.cover
            updateArticleForm.content = res.data.content
            updateArticleForm.categoryId = res.data.categoryId
            updateArticleForm.tags = res.data.tagIds
            updateArticleForm.summary = res.data.summary
        } 
    })
}
const saveArticleSubmit = () => {
    console.log("markdown 内容" + form.content)
    updateArticleFormRef.value.validate((valid) => {
        if (!valid) {
            console.log('表单验证不通过')
            return false
        } else {
            updateArticle(updateArticleForm).then((res) => {
                if (res.success) {
                    showMessage("保存成功")
                    isArticleUpdateEditorShow.value = false
                    // 重新请求分页接口，渲染列表数据
                    getTableData()
                } else {
                    let message = res.message
                    //提示错误信息
                    showMessage(error, message)
                    return
                }
            })
        }

    })
}

</script>
<style scoped>
/* 封面图片样式 */
.avatar-uploader .avatar {
    width: 200px;
    height: 100px;
    display: block;
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 200px;
    height: 100px;
    text-align: center;
}

.el-dialog__body {
    padding-top: 0px;
}

/* 指定 select 下拉框宽度 */
.el-select--large {
    width: 600px;
}
</style>
<style>
.md-editor-footer {
    height: auto;
}
</style>