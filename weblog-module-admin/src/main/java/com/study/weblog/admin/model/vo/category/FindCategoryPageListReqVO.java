package com.study.weblog.admin.model.vo.category;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName FindCategoryPageListReqVo
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询分类分页数据入参VO")
public class FindCategoryPageListReqVO  extends BasePageQuery {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 创建的起始日期
     */
    private LocalDateTime strarTime;
    /**
     * 创建的结束日期
     */
    private LocalDateTime endTime;
}
