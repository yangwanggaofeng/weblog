package com.study.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteTagVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除标签VO")
public class DeleteTagVO {
    @NotNull(message = "标签 ID 不能为空")
    private Long id;
}
