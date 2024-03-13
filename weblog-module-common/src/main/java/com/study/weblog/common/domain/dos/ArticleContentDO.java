package com.study.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName ArticleContentDO
 * @Description 文章内容bean
 * @Author zhang
 * @Date 2024/3/10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_article_content" ,schema = "weblog")
public class ArticleContentDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long articleId;
    private String content;
}
