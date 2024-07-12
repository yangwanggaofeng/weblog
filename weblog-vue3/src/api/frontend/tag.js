import axios from "@/axios";

//获取首页分类数据
export function getTagPageList(){
    return axios.post("/tag/list")
}

//获取首页分类数据
//获取分类下的文章分页数据数据
export function getTagArticlePageList(data){
    return axios.post("/tag/article/list",data)
}

