package com.study.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName SearchTagVO
 * @Description 标签模糊查询 VO
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="标签模糊查询 VO")
public class SearchTagVO {
    @NotBlank(message = "标签查询关键字不能为空")
    private String key;
}
