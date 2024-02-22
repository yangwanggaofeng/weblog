package com.study.weblog.admin.controller;

import com.study.weblog.admin.model.vo.category.AddCategoryVO;
import com.study.weblog.admin.model.vo.category.DeleteCategoryVO;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.study.weblog.admin.service.AdminCategoryService;
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
 * @ClassName AdminCategoryController
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService adminCategoryService;

    @PostMapping("/category/add")
    @ApiOperation("添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryVO addCategoryVo){
        return adminCategoryService.addCategory(addCategoryVo);
    }

    @PostMapping("/category/list")
    @ApiOperation("分类信息分页获取")
    @ApiOperationLog(description = "分类信息分页获取")
    public PageResponse findCategroyPageList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO){
        return adminCategoryService.findCategoryPageList(findCategoryPageListReqVO);
    }
    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryVO deleteCategoryVO){
        return adminCategoryService.deleteCategory(deleteCategoryVO);
    }

    @PostMapping("/category/select/list")
    @ApiOperation("分类select 下拉列表数据获取")
    @ApiOperationLog(description = "分类select 下拉列表数据获取")
    public Response findCategorySelectList(){
        return adminCategoryService.findCategorySelectList();
    }
}
