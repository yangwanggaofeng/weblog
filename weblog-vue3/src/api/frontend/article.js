import axios from "@/axios";

//获取首页文章列表
export function getArticlePageList(data){
    return axios.post("/article/list", data)
}

