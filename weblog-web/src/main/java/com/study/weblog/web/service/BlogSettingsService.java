package com.study.weblog.web.service;

import com.study.weblog.common.utils.Response;

/**
 * @ClassName BlogSettingsService
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/24
 * @Version 1.0
 **/
public interface BlogSettingsService {
    /**
     * 获取博客设置信息
     * @return
     */
    Response findBlogSettingsDeatail();
}
