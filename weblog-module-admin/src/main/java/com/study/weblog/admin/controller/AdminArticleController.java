package com.study.weblog.admin.controller;

import com.study.weblog.admin.model.vo.aiticle.*;
import com.study.weblog.admin.service.AdminArticleService;
import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminArticleController
 * @Description TODO
 * @Author zhang
 * @Date 2024/3/11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {
    @Autowired
    private AdminArticleService adminArticleService;

    @PostMapping("/publish/article")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO){
        return adminArticleService.publishArticle(publishArticleReqVO);
    }

    @PostMapping("/delete/article")
    @ApiOperation("删除文章")
    @ApiOperationLog(description = "删除文章")
    public Response deleteAritcle(@RequestBody @Validated DeleteArticleReqVO deleteArticleReqVO){
        return adminArticleService.deleteArticle(deleteArticleReqVO);
    }

    @PostMapping("/article/list")
    @ApiOperation("文章信息分页获取")
    @ApiOperationLog(description = "文章信息分页获取")
    public PageResponse findArticlePageList(@RequestBody @Validated FindArticlePageListReqVO findArticlePageListReqVO){
        return adminArticleService.findArticlePageList(findArticlePageListReqVO);
    }
    @PostMapping("/article/detail")
    @ApiOperation("查询文章详情")
    @ApiOperationLog(description = "查询文章详情")
    public Response getArticleDetail(@RequestBody @Validated FindArticleDetailReq findArticleDetailReq){
        return adminArticleService.findArticleDetail(findArticleDetailReq);
    }
    @PostMapping("/article/update")
    @ApiOperation("更新文章信息")
    @ApiOperationLog(description = "更新文章信息")
    public Response updateArticle(@RequestBody @Validated UpdateArticleReqVO updateArticleReqVO){
        return adminArticleService.updateArticle(updateArticleReqVO);
    }
}
