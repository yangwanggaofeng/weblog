package com.study.weblog.admin.controller;

import com.study.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("后台测试接口")
    @PostMapping("/test/admin")
    public Response testAdmin(){
        return Response.success("测试后台模块接口");
    }
}
