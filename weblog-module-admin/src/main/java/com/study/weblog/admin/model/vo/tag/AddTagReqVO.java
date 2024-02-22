package com.study.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName AddCategoryVo
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="添加标签 VO")
public class AddTagReqVO {
    @NotEmpty(message = "标签集合不能为空")
    private List<String> tags;
}
