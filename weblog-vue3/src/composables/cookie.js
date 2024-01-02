import {useCookies } from "@vueuse/integrations/useCookies"

const cookie = useCookies()

// ============================== Token 令牌 ==============================
//存储在cookie中的Token的key
const TOKEN_KEY = "Authorization"
//获取Token值
export function getToken(){
    return cookie.get(TOKEN_KEY)
}

//设置Token到cookie中
export function setToken(token){
    console.log("设置token" + token)
    return cookie.set(TOKEN_KEY, token)
}

//删除Token
export function removeToken(){
    return cookie.remove(TOKEN_KEY)
}

// ============================== 标签页 ==============================
//存储在cookie中的key
const TAB_LIST_KEY = 'tabList'

//获取TabList
export function getTabList(){
    return cookie.get(TAB_LIST_KEY)
}

//存储tabList 到cookie
export function setTabList(tabList){
    return cookie.set(TAB_LIST_KEY, tabList)
}
