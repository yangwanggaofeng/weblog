package com.study.weblog.web.model.vo.archive;

import com.study.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName FindArchiveArticlePageListReqVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/6/29
 * @Version 1.0
 **/
@Data
@Builder
@ApiModel(value = "文章归档分类IO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
