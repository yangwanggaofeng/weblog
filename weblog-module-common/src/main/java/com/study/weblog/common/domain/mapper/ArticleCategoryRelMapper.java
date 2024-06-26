package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.weblog.common.domain.dos.ArticleCategoryRelDO;

import java.sql.Wrapper;
import java.util.List;

public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRelDO> {
    /**
     * 根据文章 ID 删除关联分类记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId){
        return delete(Wrappers.<ArticleCategoryRelDO>lambdaQuery().eq(ArticleCategoryRelDO::getArticleId, articleId));
    }
    /**
     * 根据文章 ID 查询
     * @param articleId
     * @return
     */
    default ArticleCategoryRelDO selectByArticleId(Long articleId){
        LambdaQueryWrapper<ArticleCategoryRelDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategoryRelDO::getArticleId, articleId);
        return selectOne(wrapper);
    }
    /**
     * 根据分类 ID 查询
     * @param categoryId
     * @return
     */
    default ArticleCategoryRelDO selectByCategoryId(Long categoryId){
        return selectOne(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
        .eq(ArticleCategoryRelDO::getCategoryId, categoryId)
        .last("limit 1"));
    }

    /**
     * 根据文章id集合查询批量查询
     * @param articleIds
     * @return
     */
    default List<ArticleCategoryRelDO> selectByArticleIds(List<Long> articleIds){
        return selectList(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
        .in(ArticleCategoryRelDO::getArticleId, articleIds));
    }
}
