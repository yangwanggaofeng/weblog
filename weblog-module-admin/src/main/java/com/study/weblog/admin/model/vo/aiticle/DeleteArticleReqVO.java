package com.study.weblog.admin.model.vo.aiticle;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteArticleReqVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/3/14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除文章 VO")
public class DeleteArticleReqVO {

    @NotNull(message = "文章ID 不能为空")
    private Long id;
}
