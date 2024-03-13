package com.study.weblog.admin.controller;

import com.study.weblog.admin.model.vo.aiticle.PublishArticleReqVO;
import com.study.weblog.admin.service.AdminArticleService;
import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
}
