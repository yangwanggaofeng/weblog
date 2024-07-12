package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.ArticleDO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface ArticleMapper extends BaseMapper<ArticleDO> {
    default Page<ArticleDO> selectPageList(Long current, Long size,String titleName, LocalDateTime startTime, LocalDateTime endTime){
        Page<ArticleDO> page = new Page<>(current, size);
        LambdaQueryWrapper<ArticleDO> wrapper = new LambdaQueryWrapper();
        wrapper.like(StringUtils.isNotBlank(titleName),ArticleDO::getTitle, titleName)
                .ge(Objects.nonNull(startTime), ArticleDO::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), ArticleDO::getCreateTime, endTime)
                .orderByDesc(ArticleDO::getCreateTime);
        //执行分页查询
        Page<ArticleDO> articleDOPage = selectPage(page, wrapper);
        return articleDOPage;

    }

    /**
     * 根据文章id 批量分页查询文章列表
     * @param current
     * @param size
     * @param articleIds
     * @return
     */
    default Page<ArticleDO> selectPageListByArticleIds(Long current, long size, List<Long> articleIds){
        Page<ArticleDO> page = new Page<>(current, size);
        LambdaQueryWrapper<ArticleDO> wrapper = new LambdaQueryWrapper<ArticleDO>()
                .in(ArticleDO::getId, articleIds)
                .orderByDesc(ArticleDO::getCreateTime);
        return selectPage(page, wrapper);
    }

}
