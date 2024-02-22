package com.study.weblog.admin.controller;

import com.study.weblog.admin.service.AdminFileService;
import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AdminFileController
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/22
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api( tags = "Admin 文件模块")
public class AdminFileController {
    @Autowired
    private AdminFileService adminFileService;

    @PostMapping("/file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Response uploadFile(@RequestParam MultipartFile file){
        return adminFileService.uploadFile(file);
    }
}
