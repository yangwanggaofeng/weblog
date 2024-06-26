package com.study.weblog.web.model.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FindCategoryListRspVO
 * @Description 分类 VO
 * @Author zhang
 * @Date 2024/4/12
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindCategoryListRspVO {
    private Long id;
    private String name;
}
