import axios from "@/axios";

//获取归档首页文章列表
export function getArchivePageList(data){
    // console.log(data)
    return axios.post("/archive/list", data)
}

