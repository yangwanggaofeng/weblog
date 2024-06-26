package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.weblog.common.domain.dos.ArticleContentDO;

public interface ArticleContentMapper extends BaseMapper<ArticleContentDO> {
    /**
     * 根据文章ID 删除文章内容
     * @param articleId
     * @return
     */
    default int deleteByAritcleId(Long articleId){
//        LambdaQueryWrapper<ArticleContentDO> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ArticleContentDO::getArticleId, articleId);
        return delete(Wrappers.<ArticleContentDO>lambdaQuery().eq(ArticleContentDO::getArticleId, articleId));
    }
    /**
     * 根据文章 ID 查询
     * @param articleId
     * @return
     */
    default ArticleContentDO selectByArticleId(Long articleId){
        return selectOne(Wrappers.<ArticleContentDO>lambdaQuery().eq(ArticleContentDO::getArticleId, articleId));
    }

    /**
     * 根据文章id更新文章内容
     * @param articleContentDO
     * @return
     */
    default int updateByArticleId(ArticleContentDO  articleContentDO){
        return update(articleContentDO, Wrappers.<ArticleContentDO>lambdaQuery()
        .eq(ArticleContentDO::getArticleId, articleContentDO.getArticleId()));
    }
}
