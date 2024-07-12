package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.study.weblog.web.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/category")
@Api(tags = "前台分类")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/list")
    @ApiOperation(value = "前台获取分类列表")
    @ApiOperationLog(description = "前台获取分类列表")
    public Response findCategoryPageList(){
        return categoryService.FindCategoryList();
    }

    @PostMapping("/article/list")
    @ApiOperation(value = "前台获取分类下文章分页数据")
    @ApiOperationLog(description = "前台获取分类下文章分页数据")
    public PageResponse findCategoryArticlePageList(@RequestBody FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO){
        return categoryService.fingCategoryArticlePageList(findCategoryArticlePageListReqVO);
    }
}
