package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/2
 * @Version 1.0
 **/
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User testUser(@RequestBody User user){
        return user;
    }
}
