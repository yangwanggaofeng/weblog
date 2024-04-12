package com.study.weblog.admin.convert;

import com.study.weblog.admin.model.vo.aiticle.FindArticleDetailRsp;
import com.study.weblog.admin.model.vo.aiticle.FindArticlePageListRspVO;
import com.study.weblog.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ArticleDetailConvert {
    /**
     * 初始化convert实例
     */
    ArticleDetailConvert INSTANCE = Mappers.getMapper(ArticleDetailConvert.class);
    /**
     * 将DO转化为VO
     */
    FindArticleDetailRsp convertDO2VO(ArticleDO bean);
}
