package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.study.weblog.common.domain.dos.*;
import com.study.weblog.common.domain.mapper.*;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.convert.ArticleConvert;
import com.study.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;
import com.study.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.study.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.study.weblog.web.model.vo.tag.FindTagListRspVO;
import com.study.weblog.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ArticleServiceImpl
 * @Description 前端文章service
 * @Author zhang
 * @Date 2024/4/15
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    /**
     * 获取首页文章分页数据
     * @param findIndexArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long current = findIndexArticlePageListReqVO.getCurrent();
        Long size = findIndexArticlePageListReqVO.getSize();
        Page<ArticleDO>  articleDOPage = articleMapper.selectPageList(current, size, null, null,null);
        List<ArticleDO> articleDOList = articleDOPage.getRecords();
        List<FindIndexArticlePageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(articleDOList)){
            // 文章 DO 转 VO
            vos = articleDOList.stream()
                    .map(articleDO -> ArticleConvert.INSTANCE.convertDO2VO(articleDO))
                    .collect(Collectors.toList());
            //拿到所有文章的 ID 集合
            List<Long> articleIds = articleDOList.stream()
                    .map(ArticleDO::getId)
                    .collect(Collectors.toList());
            // 第二步：设置文章所属分类
            // 查询所有分类
            List<CategoryDO> categoryDOList = categoryMapper.selectList(Wrappers.emptyWrapper());
            Map<Long,String> categoryIdNameMap =  categoryDOList.stream()
                    .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
            // 根据文章 ID 批量查询所有关联记录
            List<ArticleCategoryRelDO> articleCategoryRelDOList = articleCategoryRelMapper.selectByArticleIds(articleIds);
            // 拿到所有文章的标签关联记录
            vos.forEach(v ->{
                Long currentArticleId = v.getId();
                // 过滤出当前文章对应的关联数据
                Optional<ArticleCategoryRelDO> optional = articleCategoryRelDOList.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), currentArticleId))
                        .findAny();
                // 若不为空
                if (optional.isPresent()) {
                    ArticleCategoryRelDO articleCategoryRelDO = optional.get();
                    long categoryId = articleCategoryRelDO.getCategoryId();
                    // 通过分类 ID 从 map 中拿到对应的分类名称
                    String categoryName = categoryIdNameMap.get(categoryId);
                    FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build();
                    // 设置到当前 vo 类中
                    v.setCategory(findCategoryListRspVO);
                }
            });
            // 第三步：设置文章标签
            // 查询所有标签
            List<TagDO> tagDOList = tagMapper.selectList(Wrappers.emptyWrapper());
            Map<Long, String> tagIdNameMap = tagDOList.stream()
                    .collect(Collectors.toMap(TagDO::getId, TagDO::getName));
            // 拿到所有文章的标签关联记录
            List<ArticleTagRelDO> articleTagRelDOList = articleTagRelMapper.selectByArticleIds(articleIds);
            vos.forEach(v ->{
                Long currArticleId = v.getId();
                // 过滤出当前文章的标签关联记录
                List<ArticleTagRelDO> currArticleTagRelDOS = articleTagRelDOList.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), currArticleId))
                        .collect(Collectors.toList());
                List<FindTagListRspVO> findTagListRspVOList = Lists.newArrayList();
                currArticleTagRelDOS.forEach( c ->{
                    Long tagId = c.getTagId();
                    String tagName = tagIdNameMap.get(tagId);
                    FindTagListRspVO findTagListRspVO = FindTagListRspVO.builder()
                            .id(tagId)
                            .name(tagName)
                            .build();
                    findTagListRspVOList.add(findTagListRspVO);
                });
                v.setTags(findTagListRspVOList);

            });
        }
        return PageResponse.success(articleDOPage, vos);
    }

    @Override
    public Response testsingel() {
        return null;
    }
}
