package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.category.AddCategoryVO;
import com.study.weblog.admin.model.vo.category.DeleteCategoryVO;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;

public interface AdminCategoryService {

    /**
     * 添加分类
     * @param addCategoryVo
     * @return
     */
    Response addCategory(AddCategoryVO addCategoryVo);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     * @param deleteCategoryVO
     * @return
     */
    Response deleteCategory(DeleteCategoryVO deleteCategoryVO);

    /**
     * 获取分类文章的Select 列表数据
     * @return
     */
    Response findCategorySelectList();
}
