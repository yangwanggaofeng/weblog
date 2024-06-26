import axios from "@/axios";

//获取首页分类数据
export function getCategoryPageList(){
    return axios.post("/category/list")
}
