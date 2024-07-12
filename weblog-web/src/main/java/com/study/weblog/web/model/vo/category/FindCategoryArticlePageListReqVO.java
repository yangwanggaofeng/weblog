package com.study.weblog.web.model.vo.category;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName FindCategoryArticlePageListRspVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/7/6
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "前台查询分类文章分页 VO")
public class FindCategoryArticlePageListReqVO extends BasePageQuery {
    /**
     * 分类Id
     */
    @NotNull(message = "分类Id不能为空")
    private Long categoryId;
}
