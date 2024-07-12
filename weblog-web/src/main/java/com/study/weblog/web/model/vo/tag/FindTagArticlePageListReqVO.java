package com.study.weblog.web.model.vo.tag;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName FindTagArticlePageListReqVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/7/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "前台查询标签下文章分页 VO")
public class FindTagArticlePageListReqVO extends BasePageQuery {
    /**
     * 标签ID
     */
    @NotNull(message = "分类tagId不能为空")
    private Long tagId;
}
