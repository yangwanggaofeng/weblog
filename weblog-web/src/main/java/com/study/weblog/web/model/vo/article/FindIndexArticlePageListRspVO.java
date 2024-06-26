package com.study.weblog.web.model.vo.article;

import com.study.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.study.weblog.web.model.vo.tag.FindTagListRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName FindIndexArticlePageListRspVO
 * @Description 文章 VO
 * @Author zhang
 * @Date 2024/4/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindIndexArticlePageListRspVO {
    private Long id;
    private String title;
    private String summary;
    private String cover;
    private String content;
    private LocalDate createDate;
    private LocalDateTime updateTime;
    /**
     * 文章分类
     */
    private FindCategoryListRspVO category;
    /**
     * 文章标签
     */
    private List<FindTagListRspVO> tags;

}
