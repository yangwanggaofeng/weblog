package com.study.weblog.web.service;

import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;

/**
 * @ClassName TagService
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
public interface TagService {

    /**
     * 获取标签信息
     * @return
     */
    Response findTagList();

    /**
     * 获取标签下的文章分页雷彪
     * @param findTagArticlePageListReqVO
     * @return
     */
    PageResponse findTagArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
