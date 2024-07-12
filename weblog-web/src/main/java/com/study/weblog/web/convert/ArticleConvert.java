package com.study.weblog.web.convert;

import com.study.weblog.common.domain.dos.ArticleDO;
import com.study.weblog.web.model.vo.archive.FindArchiveArticlePageListRspVO;
import com.study.weblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.study.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.study.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName ArticleConvert
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/16
 * @Version 1.0
 **/
@Mapper
public interface ArticleConvert {
    /**
     * 初始化convert实例
     */
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    /**
     * 将DO转为VO
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindIndexArticlePageListRspVO convertDO2VO(ArticleDO bean);

    /**
     * 将DO转为ArchiveArticleRspVO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    @Mapping(target = "createMonth",expression = "java(java.time.YearMonth.from(bean.getCreateTime()))")
    FindArchiveArticleRspVO convertDO2ArchiveArticleVO(ArticleDO bean);

    /**
     * 将DO转为FindCategoryArticlePageListRspVO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindCategoryArticlePageListRspVO convertDO2CategoryArticleVO(ArticleDO bean);

    /**
     * 将Do转为FindTagArticlePageListRspVO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindTagArticlePageListRspVO convertDO2TagArticleVO(ArticleDO bean);
}
