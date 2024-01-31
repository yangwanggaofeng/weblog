package com.study.weblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SelectRspVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/21
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {
    /**
     * 下拉列表的文字展示
     */
    private String label;
    /**
     * 下拉列表对应的value值 ，这里指ID
     */
    private Object value;
}
