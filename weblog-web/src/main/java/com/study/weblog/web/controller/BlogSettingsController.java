package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.service.BlogSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BlogSettingsController
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/blog/settings")
@Api(tags = "前台博客设置")
public class BlogSettingsController {
    @Autowired
    private BlogSettingsService blogSettingsService;

    @PostMapping("/detail")
    @ApiOperation(value = "获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Response findBlogSettingsDeail(){
        return blogSettingsService.findBlogSettingsDeatail();
    }


}
