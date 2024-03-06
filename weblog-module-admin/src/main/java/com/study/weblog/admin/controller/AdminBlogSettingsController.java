package com.study.weblog.admin.controller;

import com.study.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.study.weblog.admin.service.AdminBlogSettingsService;
import com.study.weblog.common.aspect.ApiOperationLog;
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
 * @ClassName AdminBlogSettingsController
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 博客设置模块")
public class AdminBlogSettingsController {

    @Autowired
    private AdminBlogSettingsService adminBlogSettingsService;

    @PostMapping("/blog/settings/update")
    @ApiOperation("博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    public Response updateBlogSettings(@RequestBody @Validated UpdateBlogSettingsReqVO updateBlogSettingsReqVO){
        return adminBlogSettingsService.updateBlogSettings(updateBlogSettingsReqVO);
    }

    @PostMapping("/blog/detail")
    @ApiOperation("获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Response findBlogDetail(){
        return adminBlogSettingsService.findBlogDetail();
    }
}
