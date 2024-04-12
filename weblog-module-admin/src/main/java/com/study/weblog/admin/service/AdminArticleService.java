package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.aiticle.*;
import com.study.weblog.common.utils.PageResponse;
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

    /**
     * 删除文章
     * @param deleteArticleReqVO
     * @return
     */
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);

    /**
     * 分页查询文章信息
     * @param findArticlePageListReqVO
     * @return
     */
    PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);

    /**
     * 查询文章详情
     * @param findArticleDetailReq
     * @return
     */
    Response findArticleDetail(FindArticleDetailReq findArticleDetailReq);

    /**
     * 更新文章内容
     * @param updateArticleReqVO
     * @return
     */
    Response updateArticle(UpdateArticleReqVO updateArticleReqVO);
}
