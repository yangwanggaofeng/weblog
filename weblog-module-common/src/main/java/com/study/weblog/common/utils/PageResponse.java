package com.study.weblog.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName PageResponse
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/15
 * @Version 1.0
 **/
@Data
public class PageResponse<T> extends Response<List<T>> {
    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页显示的记录数，默认10条
     */
    private long size = 10;
    /**
     * 当前页码
     */
    private long current;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 成功响应
     * @param page Mybatis Plus 提供的分页接口
     * @param data
     * @return
     * @param <T>
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data){
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(Objects.isNull(page) ? 1l : page.getCurrent());
        response.setTotal(Objects.isNull(page) ? 0l : page.getTotal());
        response.setSize(Objects.isNull(page) ? 10l : page.getSize());
        response.setPages(Objects.isNull(page) ? 0l : page.getPages());
        response.setData(data);
        return response;
    }

}
