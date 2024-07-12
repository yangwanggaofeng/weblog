package com.study.weblog.web.controller;

import com.study.weblog.common.aspect.ApiOperationLog;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.study.weblog.web.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ArchiveArticleController
 * @Description TODO
 * @Author zhang
 * @Date 2024/6/29
 * @Version 1.0
 **/
@RestController
@RequestMapping("archive")
@Api(tags = "文章归档")
public class ArchiveArticleController {
    @Autowired
    private ArchiveService archiveService;

    @PostMapping("list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public Response findArchiveArticlePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        return archiveService.findArchiveArticlePageList(findArchiveArticlePageListReqVO);
    }

}
