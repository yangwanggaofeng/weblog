package com.study.weblog.admin.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName FindTagPageListRspVO
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagPageListRspVO {
    /**
     * 标签id
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
