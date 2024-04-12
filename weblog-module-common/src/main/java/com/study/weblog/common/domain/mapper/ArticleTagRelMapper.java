package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.weblog.common.config.InsertBatchMapper;
import com.study.weblog.common.domain.dos.ArticleContentDO;
import com.study.weblog.common.domain.dos.ArticleTagRelDO;

import java.util.List;

public interface ArticleTagRelMapper extends InsertBatchMapper<ArticleTagRelDO> {
    /**
     * 根据文章 ID 删除关联标签记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId){
        return delete(Wrappers.<ArticleTagRelDO>lambdaQuery().eq(ArticleTagRelDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 查询
     * @param articleId
     * @return
     */
    default List<ArticleTagRelDO> selectByArticleId(Long articleId){
        return selectList(Wrappers.<ArticleTagRelDO>lambdaQuery().eq(ArticleTagRelDO::getArticleId, articleId));
    }
    /**
     * 根据标签 ID 查询
     * @param tagId
     * @return
     */
    default ArticleTagRelDO selectByTagId(Long tagId){
        return selectOne(Wrappers.<ArticleTagRelDO>lambdaQuery()
        .eq(ArticleTagRelDO::getTagId, tagId)
        .last("limit 1"));
    }
}
