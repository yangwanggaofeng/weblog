import axios from "@/axios";
//获取分类分页数据
export function getArticlePageList(data){
    return axios.post("/admin/article/list", data)
}
//删除文章
export function deleteArticle( id){
    return axios.post("/admin/delete/article", {id})


}
//发布文章
export function publishArticle( data){
    return axios.post("/admin/publish/article",data)

}
//发布文章
export function getArticleDetail(id){
    return axios.post("/admin/article/detail",{id})

}
//更新文章
export function updateArticle(data){
    return axios.post("/admin/article/update", data)
}
