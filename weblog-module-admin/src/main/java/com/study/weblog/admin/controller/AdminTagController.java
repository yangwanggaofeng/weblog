package com.study.weblog.admin.controller;

import com.study.weblog.admin.model.vo.tag.AddTagReqVO;
import com.study.weblog.admin.model.vo.tag.DeleteTagVO;
import com.study.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.study.weblog.admin.model.vo.tag.SearchTagVO;
import com.study.weblog.admin.service.AdminTagService;
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
 * @ClassName AdminTagController
 * @Description 标签
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 标签模块")
public class AdminTagController {
    @Autowired
    private AdminTagService adminTagService;

    @PostMapping("/tag/add")
    @ApiOperation("添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addTag(@RequestBody @Validated AddTagReqVO addTagReqVo){
        return adminTagService.addTag(addTagReqVo);
    }

    @PostMapping("/tag/list")
    @ApiOperation("分类信息分页获取")
    @ApiOperationLog(description = "分类信息分页获取")
    public PageResponse findtagList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO){
        return adminTagService.findTagPageList(findTagPageListReqVO);
    }
    @PostMapping("/tag/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagVO deleteTagVO){
        return adminTagService.deleteTag(deleteTagVO);
    }

    @PostMapping("/tag/select/list")
    @ApiOperation("标签select 下拉列表数据获取")
    @ApiOperationLog(description = "标签select 下拉列表数据获取")
    public Response findTagSelectList(){
        return adminTagService.findTagSelectList();
    }

    @PostMapping("/tag/search")
    @ApiOperation("标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTag(@RequestBody @Validated SearchTagVO searchTagVO){
        return adminTagService.searchTag(searchTagVO);
    }
}
