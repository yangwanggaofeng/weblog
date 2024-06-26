package com.study.weblog.web.model.vo.article;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName FindIndexArticlePageListReqVO
 * @Description 首页查询文章分页 VO
 * @Author zhang
 * @Date 2024/4/12
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(value="首页查询文章分页 VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}
