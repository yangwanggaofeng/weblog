package com.study.weblog.admin.controller;

import com.study.weblog.admin.service.TestAdminService;
import com.study.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestAdminController
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/5
 * @Version 1.0
 **/
@RestController
@Api(tags = "后台管理模块")
public class TestAdminController {
    @Autowired
    private TestAdminService testAdminService;

    @ApiOperation("后台测试接口")
    @PostMapping("/admin/test")
    public Response testAdmin(){
        return Response.success("测试后台模块接口");
    }
    @ApiOperation("后台测试接口")
    @GetMapping("/admin/test/get")
    public Response testAdminGet(){
        return Response.success("测试后台模块get接口");
    }

}
