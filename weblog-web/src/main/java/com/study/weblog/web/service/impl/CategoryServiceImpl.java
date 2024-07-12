package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.study.weblog.common.domain.dos.ArticleDO;
import com.study.weblog.common.domain.dos.CategoryDO;
import com.study.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.study.weblog.common.domain.mapper.ArticleMapper;
import com.study.weblog.common.domain.mapper.CategoryMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.convert.ArticleConvert;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.study.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.study.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.study.weblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName CategoryServiceImpl
 * @Description 分类service
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 获取所有分类
     * @return
     */
    @Override
    public Response FindCategoryList() {
        // 查询所有分类
        List<CategoryDO> categoryDOList =  categoryMapper.selectList(Wrappers.emptyWrapper());
        List<FindCategoryListRspVO> vos = null;
        // DO 转 VO
        if(!CollectionUtils.isEmpty(categoryDOList)){
            vos = categoryDOList.stream()
                    .map(categoryDO -> FindCategoryListRspVO.builder()
                    .id(categoryDO.getId())
                    .name(categoryDO.getName())
                    .build()).collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    @Override
    public PageResponse fingCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        Long categoryId = findCategoryArticlePageListReqVO.getCategoryId();
        Long current = findCategoryArticlePageListReqVO.getCurrent();
        Long size = findCategoryArticlePageListReqVO.getSize();
        //查询分类信息
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        //判断分类是否存在
        if(Objects.isNull(categoryDO)){
            log.warn("==>改分类不存在，categoryId :{}",categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        //先查询改分类下的所有关联的文章Id
        List<ArticleCategoryRelDO> articleCategoryRelDOList = articleCategoryRelMapper.selectListByCategoryId(categoryId);
        if(CollectionUtils.isEmpty(articleCategoryRelDOList)){
            log.warn("==> 该分类下还未发布任何文章，categoryId is :{}",categoryId);
            return PageResponse.success(null, null);
        }
        List<Long> articleIds = articleCategoryRelDOList.stream().map(ArticleCategoryRelDO::getArticleId).collect(Collectors.toList());
        //根据文章Id集合查询文章分页数据
        Page<ArticleDO>  articleDOPage = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOList = articleDOPage.getRecords();
        List<FindCategoryArticlePageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(articleDOList)){
           vos = articleDOList.stream()
                   .map(articleDO -> ArticleConvert.INSTANCE.convertDO2CategoryArticleVO(articleDO))
                   .collect(Collectors.toList());
        }
        return PageResponse.success(articleDOPage, vos);
    }
}
