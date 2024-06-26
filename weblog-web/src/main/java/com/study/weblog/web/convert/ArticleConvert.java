package com.study.weblog.web.convert;

import com.study.weblog.common.domain.dos.ArticleDO;
import com.study.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
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
}
