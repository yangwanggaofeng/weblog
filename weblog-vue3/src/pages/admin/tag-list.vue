<template>
    <div>
        标签管理页
        <!--表头分页查询条件，shadow="never" 指定card 卡片组件没有阴影-->
        <el-card shadow="never" class="mb-5">
            <!--flex布局 ，内容垂直居中-->
            <div class="flex items-center ">
                <el-text>标签名称</el-text>
                <div class="ml-3 w-52 mr-5 ">
                    <el-input v-model="searchTagName" placeholder="请输入（模糊查询）" clearable />
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
                <el-button type="primary" @click="addTagBtnClick">
                    <el-icon class="mr-1">
                        <Plus />
                    </el-icon>
                    新增
                </el-button>
            </div>
            <el-table :data="tableData" stripe style="width: 100%" v-loading="tableLoading">
                <el-table-column prop="name" label="标签名称" width="180">
                    <template #default="scope">
                        <el-tag class="ml-2" type="success">{{ scope.row.name }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" />
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteTagSubmit(scope.row)">
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
                    :small="false" :disabled="disabled" :background="true" layout="total, sizes, prev, pager, next, jumper"
                    :total="total" @size-change="handSizeChange" @current-change="getTableData" />
            </div>
        </el-card>
        <FormDialog ref="formDialogRef" title="添加文章标签" destroryOnClose @submit="onSubmit">
            <!-- <el-form ref="formRef" :rules="rules" :model="form">
                <el-form-item label="标签名称" prop="name" label-width="120px">

                    <el-input size="large" v-model="form.name" placeholder="请输入标签名称" clearable maxlength="20"
                        show-word-limit />
                </el-form-item>

            </el-form> -->
            <el-form ref="formRef" :rules="rules" :model="form">
                <el-form-item prop="tags"  >
                    <!-- <div class="flex gap-2"> -->
                        <el-tag v-for="tag in dynamicTags" :key="tag" closable :disable-transitions="false"
                            @close="handleClose(tag)" class="mx-1">
                            {{ tag }}
                        </el-tag>
                        <span class="w-20">
                        <el-input v-if="inputVisible" ref="InputRef" v-model="inputValue" class="ml-1 w-20" size="small"
                            @keyup.enter="handleInputConfirm" @blur="handleInputConfirm" />
                        <el-button v-else class="button-new-tag" size="small" @click="showInput">
                            + 新增标签
                        </el-button>
                    </span>
                    <!-- </div> -->
                </el-form-item>
            </el-form>
        </FormDialog>
    </div>
</template>
<script setup>
// 引入所需图标
import FormDialog from '@/components/FormDialog.vue';
import { Search, RefreshRight, Delete } from '@element-plus/icons-vue'
import { ref, reactive, nextTick } from 'vue';
import moment from 'moment'
import { getTagPageList, addTag, deleteTag } from '@/api/admin/tag'
import { showMessage, showModel } from '@/composables/utils'
import { ElInput } from 'element-plus'
// 分页查询的标签名称
const searchTagName = ref('')
// 日期
const pickDate = ref('')
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
    getTagPageList({
        "current": current.value,
        "endTime": endDate.value,
        "size": size.value,
        "strarTime": startDate.value,
        "name": searchTagName.value

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
    searchTagName.value = ''
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
}
// const dialogVisible = ref(false)
const formDialogRef = ref(null)
// 表单引用
const formRef = ref(null)

// 修改用户密码表单对象
const form = reactive({
    tags: [],
})

// 规则校验
// const rules = {
//     name: [
//         {
//             required: true,
//             message: '标签名称不能为空',
//             trigger: 'blur'
//         }, {
//             min: 1,
//             max: 20,
//             message: '标签名称字数要求大于 1 个字符，小于 20 个字符',
//             trigger: 'blur'
//         },
//     ]
// }
const onSubmit = () => {
    formDialogRef.value.showBtnLoading()
    //先验证表单字段
    formRef.value.validate((valid) => {
        form.tags = dynamicTags.value
        console.log(form.tags)

        // if (!valid) {
        //     console.log('表单验证不通过')
        //     return false
        // }
        //调用添加标签接口
        addTag(form).then((res) => {
            console.log(res)
            //判断是否成功
            if (res.success == true) {
                showMessage("添加成功")
                //将表单中的标签置空
                form.tags = ''
                //动态标签数组置空
                dynamicTags.value = []
                //隐藏对话框
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
const deleteTagSubmit = (row) => {
    console.log(row)
    showModel('是否确认要删除该标签吗？').then(() => {
        deleteTag(row.id).then((res) => {
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
//新增标签按钮点击事件
const addTagBtnClick = () => {
    formDialogRef.value.open()
}
//标签输入框值
const inputValue = ref('')
//已输入标签数组
const dynamicTags = ref([])
//控制标签输入框是否显示
const inputVisible = ref(false)
// const InputRef = ref<InstanceType<typeof ElInput>>()
// 原生JavaScript没有类型
//标签输入框的引用
const InputRef = ref('')

const handleClose = (tag) => {
    dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)
}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value.input.focus()
  })
}

const handleInputConfirm = () => {
    if (inputValue.value) {
        dynamicTags.value.push(inputValue.value)
    }
    inputVisible.value = false
    inputValue.value = ''
}
</script>