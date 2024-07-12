package com.study.weblog.web.service;

import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;

public interface CategoryService {
    /**
     * 获取分类列表
     * @return
     */
    Response FindCategoryList();

    /**
     * 获取分类下的分页数据
     */
    PageResponse fingCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}
