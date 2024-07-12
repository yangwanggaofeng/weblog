package com.study.weblog.web.model.vo.archive;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

/**
 * @ClassName FindArchiveArticlePageListRspVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/6/29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticlePageListRspVO {
    /**
     * 归档月份
     */
    private YearMonth month;
    private List<FindArchiveArticleRspVO> articleList;
}
