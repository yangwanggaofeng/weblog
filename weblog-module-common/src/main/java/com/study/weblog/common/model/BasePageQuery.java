package com.study.weblog.common.model;

import lombok.Data;

/**
 * @ClassName BasePageQuery
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/16
 * @Version 1.0
 **/
@Data
public class BasePageQuery {
    /**
     * 当前页码，默认第一页
     */
    private Long current = 1l;
    /**
     * 每页展示的数据数量，默认每页展示10条数据
     */
    private Long size = 10l;
}
