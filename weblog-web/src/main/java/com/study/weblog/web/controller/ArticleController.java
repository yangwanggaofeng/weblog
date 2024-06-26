package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;
import com.study.weblog.web.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "前台文章")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/list")
    @ApiOperation(value = "获取首页文章分页数据")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Response findArticlePageList(@RequestBody FindIndexArticlePageListReqVO findIndexArticlePageListReqVO){
        return articleService.findArticlePageList(findIndexArticlePageListReqVO);
    }


    @PostMapping("/test")
    @ApiOperation(value = "测试单实例")
    @ApiOperationLog(description = "测试单实例")
    public Response testSingle(){
        return articleService.testsingel();
    }
}
