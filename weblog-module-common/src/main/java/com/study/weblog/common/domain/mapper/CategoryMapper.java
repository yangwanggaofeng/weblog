package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.CategoryDO;

import java.time.LocalDate;
import java.util.Objects;

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    /**
     * 分页查询分类信息
     * @param current 当前页
     * @param size  每页数据条数
     * @param name 分类名称
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return
     */
    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate){
        Page<CategoryDO> page = new Page<>(current, size);
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);
        //执行分页查询
        Page<CategoryDO> categoryDoPage = selectPage(page, wrapper);
        return categoryDoPage;
    }
    /**
     * 根据名称查询
     */
    default CategoryDO selectByName(String categoryName){
        //构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper();
        wrapper.eq(CategoryDO::getName, categoryName);
        //执行查询
        return selectOne(wrapper);
    }
}
