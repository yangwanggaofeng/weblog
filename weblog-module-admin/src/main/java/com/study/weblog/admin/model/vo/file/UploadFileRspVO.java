package com.study.weblog.admin.model.vo.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UploadFileRspVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileRspVO {
    /**
     * 文件的访问链接
     */
    private String url;
}
