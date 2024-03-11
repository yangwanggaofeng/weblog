package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.tag.AddTagReqVO;
import com.study.weblog.admin.model.vo.tag.DeleteTagVO;
import com.study.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.study.weblog.admin.model.vo.tag.SearchTagVO;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;

public interface AdminTagService {
    /**
     * 添加标签
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页数据查询
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagVO
     * @return
     */

    Response deleteTag(DeleteTagVO deleteTagVO);

    /**
     * 获取文章标签的Select 列表数据
     * @return
     */
    Response findTagSelectList();

    /**
     * 根据标签关键词模糊查询
     * @param searchTagVO
     * @return
     */
    Response searchTag(SearchTagVO searchTagVO);
}
