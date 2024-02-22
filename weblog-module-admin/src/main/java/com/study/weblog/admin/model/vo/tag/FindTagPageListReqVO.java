package com.study.weblog.admin.model.vo.tag;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @ClassName FindTagPageListReqVO
 * @Description 标签分页
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询标签分页数据入参VO")
public class FindTagPageListReqVO extends BasePageQuery {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 创建的起始日期
     */
    private LocalDate strarTime;
    /**
     * 创建的结束日期
     */
    private LocalDate endTime;
}
