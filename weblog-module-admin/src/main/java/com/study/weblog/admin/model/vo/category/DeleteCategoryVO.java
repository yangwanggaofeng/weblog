package com.study.weblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteCategoryVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/17
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除分类VO")
public class DeleteCategoryVO {
    @NotNull(message = "分类 ID 不能为空")
    private Long id;
}
