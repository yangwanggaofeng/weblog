package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.User;
import com.study.weblog.web.service.TestService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName TestController
 * @Description 测试接口
 * @Author zhang
 * @Date 2023/11/2
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口", notes = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户",required = true,type = "body")
    })
    public Response<User> testUser(@RequestBody @Validated User user/*, BindingResult bindingResult*/){
        //校验参数
//        if(bindingResult.hasErrors()){
//            //获取校验不通过字段的提示信息
//            String errorMsg = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
//                    .collect(Collectors.joining(","));
//            return Response.fail(errorMsg);
//        }

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());
        //返回参数
        return Response.success(user);
    }
    @PostMapping("/testException")
    @ApiOperationLog(description = "测试全局异常类")
    public Response testException(){
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        throw new BizException(ResponseCodeEnum.LOGIN_FAIL);
    }
    @GetMapping("/test/weblog/get")
    @ApiOperationLog(description = "测试weblog服务get接口")
    public Response testWeblogGet(){
        return Response.success("测试weblog服务get接口");
    }
    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate(){
        log.info("更新成功");
        return Response.success();
    }

}
