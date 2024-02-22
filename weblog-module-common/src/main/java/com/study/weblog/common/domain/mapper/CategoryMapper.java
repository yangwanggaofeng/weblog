package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.CategoryDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface CategoryMapper extends BaseMapper<CategoryDo> {
    /**
     * 分页查询分类信息
     * @param current 当前页
     * @param size  每页数据条数
     * @param name 分类名称
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return
     */
    default Page<CategoryDo> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate){
        Page<CategoryDo> page = new Page<>(current, size);
        LambdaQueryWrapper<CategoryDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), CategoryDo::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDo::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDo::getCreateTime, endDate)
                .orderByDesc(CategoryDo::getCreateTime);
        //执行分页查询
        Page<CategoryDo> categoryDoPage = selectPage(page, wrapper);
        return categoryDoPage;
    }
    /**
     * 根据名称查询
     */
    default CategoryDo selectByName(String categoryName){
        //构建查询条件
        LambdaQueryWrapper<CategoryDo> wrapper = new LambdaQueryWrapper();
        wrapper.eq(CategoryDo::getName, categoryName);
        //执行查询
        return selectOne(wrapper);
    }
}
