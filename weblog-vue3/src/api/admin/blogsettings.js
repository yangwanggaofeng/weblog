import axios from "@/axios";

//获取博客详情
export function getBlogSettingsDetail(){
    return axios.post("/admin/blog/detail")
}
//更新博客信息
export function updateBlogSettings(data){
    return axios.post("/admin/blog/settings/update", data)
}
