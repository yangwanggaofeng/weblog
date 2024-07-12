package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.study.weblog.web.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TagController
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tag")
@Api(tags = "标签")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/list")
    @ApiOperation(value = "前台获取标签列表")
    @ApiOperationLog(description = "前台获取标签列表")
    public Response findTagList() {
        return tagService.findTagList();
    }
    @PostMapping("/article/list")
    @ApiOperation(value = "前台获取标签下文章列表")
    @ApiOperationLog(description = "前台获取标签下文章列表")
    public PageResponse findTagArticlePageList(@RequestBody FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        return tagService.findTagArticlePageList(findTagArticlePageListReqVO);
    }
}
