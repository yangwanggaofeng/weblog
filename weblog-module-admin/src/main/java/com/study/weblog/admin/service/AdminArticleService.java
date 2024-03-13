package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.aiticle.PublishArticleReqVO;
import com.study.weblog.common.utils.Response;

/**
 * @ClassName AdminArticleService
 * @Description 文章管理
 * @Author zhang
 * @Date 2024/3/11
 * @Version 1.0
 **/
public interface AdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);
}
