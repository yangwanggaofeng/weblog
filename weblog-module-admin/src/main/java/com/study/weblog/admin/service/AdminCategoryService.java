package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.category.AddCategoryVo;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;

public interface AdminCategoryService {

    /**
     * 添加分类
     * @param addCategoryVo
     * @return
     */
    Response addCategory(AddCategoryVo addCategoryVo);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);
}
