<template>
    <div>
        分类管理页
        <!--表头分页查询条件，shadow="never" 指定card 卡片组件没有阴影-->
        <el-card shadow="never" class="mb-5">
            <!--flex布局 ，内容垂直居中-->
            <div class="flex items-center ">
                <el-text>分类名称</el-text>
                <div class="ml-3 w-52 mr-5 ">
                    <el-input v-model="searchCategoryName" placeholder="请输入（模糊查询）" clearable />
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
                <el-button type="primary" @click="addCategoryBtnClick">
                    <el-icon class="mr-1">
                        <Plus />
                    </el-icon>
                    新增
                </el-button>
            </div>
            <el-table :data="tableData"  stripe  style="width: 100%" v-loading="tableLoading">
                <el-table-column prop="name" label="分类名称" width="180" />
                <el-table-column prop="createTime" label="创建时间" width="180" />
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="danger" size="small" @click="deleteCategorySubmit(scope.row)">
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
        <!--添加分类-->
        <!-- <el-dialog v-model="dialogVisible" title="新增分类" width="40%" :draggable="true" :close-on-click-modal="false"
            :close-on-press-escape="false">
            <el-form ref="formRef" :rules="rules" :model="form">
                <el-form-item label="分类名称" prop="name" label-width="120px">
                  
                    <el-input size="large" v-model="form.name" placeholder="请输入分类名称" clearable maxlength="20"
                        show-word-limit />
                </el-form-item>

            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit">
                        提交
                    </el-button>
                </span>
            </template>
        </el-dialog> -->
        <FormDialog ref="formDialogRef" title="添加文章分类" destroryOnClose @submit="onSubmit">
            <el-form ref="formRef" :rules="rules" :model="form">
                <el-form-item label="分类名称" prop="name" label-width="120px">

                    <el-input size="large" v-model="form.name" placeholder="请输入分类名称" clearable maxlength="20"
                        show-word-limit />
                </el-form-item>

            </el-form>
        </FormDialog>
    </div>
</template>
<script setup>
// 引入所需图标
import FormDialog from '@/components/FormDialog.vue';
import { Search, RefreshRight, Delete } from '@element-plus/icons-vue'
import { ref, reactive } from 'vue';
import moment from 'moment'
import { getCategoryPageList, addCategory, deleteCategory } from '@/api/admin/category'
import { showMessage, showModel } from '@/composables/utils'

// 分页查询的分类名称
const searchCategoryName = ref('')
// 日期
const pickDate = ref('')
// const tableData = [
//     {
//         name: 'java',
//         createTime: '2016-05-03 12:00:00',
//     },
//     {
//         name: 'golang',
//         createTime: '2016-05-03 12:00:00',
//     }
// ]
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
    getCategoryPageList({
        "current": current.value,
        "endTime": endDate.value,
        "size": size.value,
        "strarTime": startDate.value,
        "name": searchCategoryName.value

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
    searchCategoryName.value = ''
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
const formRef = ref(null)

// 修改用户密码表单对象
const form = reactive({
    name: '',
})

// 规则校验
const rules = {
    name: [
        {
            required: true,
            message: '分类名称不能为空',
            trigger: 'blur'
        }, {
            min: 1,
            max: 20,
            message: '分类名称字数要求大于 1 个字符，小于 20 个字符',
            trigger: 'blur'
        },
    ]
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
const deleteCategorySubmit = (row) => {
    console.log(row)
    showModel('是否确认要删除该分类吗？').then(() => {
        deleteCategory(row.id).then((res) => {
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
const addCategoryBtnClick = () => {
    formDialogRef.value.open()
}

</script>