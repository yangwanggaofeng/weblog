package com.study.weblog.admin.model.vo.aiticle;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PublishArticleReqVO
 * @Description 文章发布请求VO
 * @Author zhang
 * @Date 2024/3/11
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_article", schema = "weblog")
public class PublishArticleReqVO {

    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 40, message = "文章标题需要大于1小于40")
    private String title;
    @NotBlank(message = "文章封面不能为空")
    private String cover;
    @NotBlank(message = "文章内容不能为空")
    private String content;

    private String summary;

    @NotNull(message = "文章分类不能为空")
    private Long categoryId;
    @NotEmpty(message = "文章标签不能为空")
    private List<String> tags;

}
