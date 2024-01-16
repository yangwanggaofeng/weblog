package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.weblog.common.domain.dos.CategoryDo;

public interface CategoryMapper extends BaseMapper<CategoryDo> {
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
