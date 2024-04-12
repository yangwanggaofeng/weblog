package com.study.weblog.admin.model.vo.aiticle;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName FindArticleDetailReq
 * @Description TODO
 * @Author zhang
 * @Date 2024/3/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章详情入参VO")
public class FindArticleDetailReq {
    @NotNull(message = "文章ID不能为空！")
    private Long id;
}
