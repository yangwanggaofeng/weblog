package com.study.weblog.admin.model.vo.aiticle;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName FindArticleDetailReq
 * @Description 文章详情出参VO
 * @Author zhang
 * @Date 2024/3/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRsp {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 文章标题
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
     * 文章内容
     */
    private String content;
    /**
     * 文章分类Id
     */
    private Long categoryId;

    /**
     * 文章分类Id
     */
    private List<Long> tagIds;

}
