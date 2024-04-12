package com.study.weblog.admin.model.vo.aiticle;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName FindArticlePageListReqVO
 * @Description 分页查询文章
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章分页数据入参VO")
public class FindArticlePageListReqVO extends BasePageQuery {
    /**
     * 分类名称
     */
    private String title;
    /**
     * 创建的起始日期
     */
    private LocalDateTime strartTime;
    /**
     * 创建的结束日期
     */
    private LocalDateTime endTime;
}
