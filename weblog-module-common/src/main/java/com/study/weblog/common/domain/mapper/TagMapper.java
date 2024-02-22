package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.TagDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface TagMapper extends BaseMapper<TagDO> {
    /**
     * 分页查询分类信息
     * @param current 当前页
     * @param size  每页数据条数
     * @param name 分类名称
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return
     */
    default Page<TagDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate){
        Page<TagDO> page = new Page<>(current, size);


        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), TagDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime, TagDO::getName);
        //执行分页查询
        return selectPage(page, wrapper);
    }
    /**
     * 根据名称查询标签
     */
    default TagDO selectByName(String tagName){
        //构建查询条件
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper();
        wrapper.eq(TagDO::getName, tagName);
        //执行查询
        return selectOne(wrapper);
    }

    /**
     * 根据标签模糊查询
     * @param key
     * @return
     */
    default List<TagDO> selectByKey(String key){
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(key), TagDO::getName, key.trim())
                .orderByDesc(TagDO::getCreateTime);
        return selectList(wrapper);
    }
}
