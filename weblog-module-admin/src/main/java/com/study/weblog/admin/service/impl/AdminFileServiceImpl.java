package com.study.weblog.admin.service.impl;

import com.study.weblog.admin.model.vo.file.UploadFileRspVO;
import com.study.weblog.admin.service.AdminFileService;
import com.study.weblog.admin.utils.MinioUtil;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.Response;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AdminFileServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/22
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {
    @Autowired
    private MinioUtil minioUtil;
    @Override
    public Response uploadFile(MultipartFile file) {
        try{
            //上传文件
            String url = minioUtil.uploadFile(file);
            return Response.success(UploadFileRspVO.builder().url(url).build());
        }catch (Exception e){
            log.error("==> 文件上传至Minio错误：{}",e);
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
