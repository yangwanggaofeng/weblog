package com.study.weblog.admin.model.vo.aiticle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName FindArticlePageListRspVO
 * @Description 分页查询文章出参 VO
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticlePageListRspVO {
    /**
     * 分类id
     */
    private Long id;
    /**
     * 文章命令
     */
    private String title;
    /**
     * 文章封面
     */
    private String cover;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
