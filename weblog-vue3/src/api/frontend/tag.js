import axios from "@/axios";

//获取首页分类数据
export function getTagPageList(){
    return axios.post("/tag/list")
}
