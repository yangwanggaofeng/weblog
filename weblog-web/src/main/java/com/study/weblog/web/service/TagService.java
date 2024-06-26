package com.study.weblog.web.service;

import com.study.weblog.common.utils.Response;

/**
 * @ClassName TagService
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
public interface TagService {

    /**
     * 获取便签信息
     * @return
     */
    Response findTagList();
}
