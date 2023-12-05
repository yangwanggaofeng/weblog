import { ElMessage } from "element-plus";
import 'element-plus/es/components/message/style/css'
import nProgress from "nprogress";
//消息提示
export function showMessage(message = '提示内容', type = 'success', customeClass = ''){
    return ElMessage({
        type: type,
        message,
        customeClass,
    })
}

export function showPageLoading(){
    nProgress.start()
}
export function hidePageLoading(){
    nProgress.done()
}