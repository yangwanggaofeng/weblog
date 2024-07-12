package com.study.weblog.web.service;

import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;

public interface ArchiveService {
    /**
     * 获取文章归档分页列表
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchiveArticlePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}
