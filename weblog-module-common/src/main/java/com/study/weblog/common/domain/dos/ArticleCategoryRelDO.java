package com.study.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ArticleCategoryRelDO
 * @Description 文章分类关联bean
 * @Author zhang
 * @Date 2024/3/10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "article_category" ,schema = "weblog")
public class ArticleCategoryRelDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long articleId;
    private Long categoryId;
}
