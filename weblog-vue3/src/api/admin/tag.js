import axios from "@/axios";
//获取标签分页数据
export function getTagPageList(data){
    return axios.post("/admin/tag/list", data)
}
//添加标签
export function addTag(data){
    return axios.post("/admin/tag/add", data)
}
//删除标签
export function deleteTag(id){
    return axios.post("/admin/tag/delete", {id})
}
//获取标签select
export function getTagSelectList(){
    return axios.post("/admin/tag/select/list")
}
//搜索标签
export function searchTags(key){
    return axios.post("/admin//tag/search", {key})
}