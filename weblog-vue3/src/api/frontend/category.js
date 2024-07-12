import axios from "@/axios";

//获取首页分类数据
export function getCategoryPageList(){
    return axios.post("/category/list")
}

//获取分类下的文章分页数据数据
export function getCategoryArticlePageList(data){
    return axios.post("/category/article/list",data)
}
