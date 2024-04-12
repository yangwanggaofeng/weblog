package com.study.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.study.weblog.admin.convert.ArticleDetailConvert;
import com.study.weblog.admin.model.vo.aiticle.*;
import com.study.weblog.admin.service.AdminArticleService;
import com.study.weblog.common.domain.dos.*;
import com.study.weblog.common.domain.mapper.*;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName AdminArticleServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/3/11
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        //1. VO转DO
        ArticleDO articleDO = ArticleDO.builder().title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        articleMapper.insert(articleDO);

        //拿到插入文章记录的id
        Long articleId = articleDO.getId();
        //VO转DO
        ArticleContentDO articleContentDO = ArticleContentDO.builder().articleId(articleId)
                .content(publishArticleReqVO.getContent()).build();
        articleContentMapper.insert(articleContentDO);
        //处理文章分类
        Long categoryId = publishArticleReqVO.getCategoryId();
        CategoryDo categoryDo = categoryMapper.selectById(categoryId);
        if(Objects.isNull(categoryDo)){
            log.error("===> 文章分类不存在，categoryId:{}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        //插入文章分类表
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId).categoryId(publishArticleReqVO.getCategoryId())
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);
        //插入文章标签表
        List<String> tagList = publishArticleReqVO.getTags();
        insertTags(articleId, tagList);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {
        Long articleId = deleteArticleReqVO.getId();
        // 1. 删除文章
        articleMapper.deleteById(articleId);
        // 1. 删除文章内容
        articleContentMapper.deleteByAritcleId(articleId);
        // 3. 删除文章-分类关联记录
        articleCategoryRelMapper.deleteByArticleId(articleId);
        // 4. 删除文章-标签关联记录
        articleTagRelMapper.deleteByArticleId(articleId);
        return Response.success();
    }

    @Override
    public PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO) {
        Long size = findArticlePageListReqVO.getSize();
        Long current = findArticlePageListReqVO.getCurrent();
        LocalDateTime strarTime = findArticlePageListReqVO.getStrartTime();
        LocalDateTime endTime = findArticlePageListReqVO.getEndTime();
        String titleName = findArticlePageListReqVO.getTitle();
        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, titleName, strarTime, endTime);
        List<ArticleDO> articleDOList = articleDOPage.getRecords();
        List<FindArticlePageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(articleDOList)){
            vos = articleDOList.stream().map(articleDO ->FindArticlePageListRspVO.builder()
                    .id(articleDO.getId())
                    .title(articleDO.getTitle())
                    .cover(articleDO.getCover())
                    .summary(articleDO.getSummary())
                    .createTime(articleDO.getCreateTime())
                    .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(articleDOPage, vos);
    }

    @Override
    public Response findArticleDetail(FindArticleDetailReq findArticleDetailReq) {
        Long articleId = findArticleDetailReq.getId();
        ArticleDO articleDO = articleMapper.selectOne(Wrappers.<ArticleDO>lambdaQuery().eq(ArticleDO::getId, articleId));
        if(Objects.isNull(articleDO)){
            log.warn("==> 查询的文章不存在，articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_EXISTED);
        }
        //所属分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByArticleId(articleId);
        //文章内容
        ArticleContentDO articleContentDO = articleContentMapper.selectByArticleId(articleId);
        //所属标签
        List<ArticleTagRelDO> articleTagRelDOList = articleTagRelMapper.selectByArticleId(articleId);
        List<Long> tagIds = articleTagRelDOList.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());
        //DO转VO
        FindArticleDetailRsp vo = ArticleDetailConvert.INSTANCE.convertDO2VO(articleDO);
        vo.setContent(articleContentDO.getContent());
        vo.setCategoryId(articleCategoryRelDO.getCategoryId());
        vo.setTagIds(tagIds);
        return Response.success(vo);
    }

    /**
     * 更新文章信息
     * @param updateArticleReqVO
     * @return
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public Response updateArticle(UpdateArticleReqVO updateArticleReqVO) {
        Long articleId = updateArticleReqVO.getId();
        // 1. VO 转 ArticleDO, 并更新
        ArticleDO articleDO = ArticleDO.builder()
                .id(articleId)
                .title(updateArticleReqVO.getTitle())
                .cover(updateArticleReqVO.getCover())
                .summary(updateArticleReqVO.getSummary())
                .updateTime(LocalDateTime.now())
                .build();
        int count = articleMapper.updateById(articleDO);
        // 根据更新是否成功，来判断该文章是否存在
        if(count == 0){
            log.error("==> 该文章不存在, articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_EXISTED);
        }
        // 2. VO 转 ArticleContentDO，并更新
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(updateArticleReqVO.getContent())
                .build();
        articleContentMapper.updateByArticleId(articleContentDO);
        //3. 查询文章分类是否存在
        CategoryDo categoryDo = categoryMapper.selectById(updateArticleReqVO.getCategoryId());
        if(Objects.isNull(categoryDo)){
            log.error("==> 该文章分类不存在, articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        //先删除该文章的分类信息
        articleCategoryRelMapper.deleteByArticleId(articleId);
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(updateArticleReqVO.getCategoryId())
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        //删除文章标签关联信息
        articleTagRelMapper.deleteByArticleId(articleId);
        List<String> publishTags = updateArticleReqVO.getTags();
        insertTags(articleId, publishTags);

        return Response.success();
    }

    /**
     * 保存文章-标签关联信息
     * @param articleId
     * @param publishTagList
     */
    private void insertTags(Long articleId, List<String> publishTagList) {
        //删选提交标签中表中存在的标签
        List<String> existTags = null;
        //删选提交标签中表中不存在的标签
        List<String> notExistTags = null;
        //查询出所有的标签
        List<TagDO> tagDOList = tagMapper.selectList(null);

        if(CollectionUtils.isEmpty(tagDOList)){
            notExistTags = publishTagList;
        }else{
            List<String> tagIds = tagDOList.stream().map( t ->String.valueOf(t.getId())).collect(Collectors.toList());
            // 表中已添加相关标签，则需要筛选
            // 通过标签 ID 来筛选，包含对应 ID 则表示提交的标签是表中存在的
            existTags = publishTagList.stream().filter(t -> tagIds.contains(t)).collect(Collectors.toList());
            // 否则则是不存在的
            notExistTags = publishTagList.stream().filter( t -> !tagIds.contains(t)).collect(Collectors.toList());
            // 补充逻辑：
            // 还有一种可能：按字符串名称提交上来的标签，也有可能是表中已存在的，比如表中已经有了 Java 标签，用户提交了个 java 小写的标签，需要内部装换为 Java 标签
            Map<String, Long> tagNameIdMap = tagDOList.stream().collect(Collectors.toMap(tagDO -> tagDO.getName().toLowerCase(), TagDO::getId ));

            // 使用迭代器进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while(iterator.hasNext()){
                String notExistTag = iterator.next();
                // 转小写, 若 Map 中相同的 key，则表示该新标签是重复标签
                if(tagNameIdMap.containsKey(notExistTag.toLowerCase())){
                    // 从不存在的标签集合中清除
                    iterator.remove();
                    // 并将对应的 ID 添加到已存在的标签集合
                    existTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
            //将提交上来的，已存在于表中的标签，文章-标签关联信息入库
            if(!CollectionUtils.isEmpty(existTags)){
                List<ArticleTagRelDO> articleTagRelDOList = Lists.newArrayList();
                existTags.forEach(tagId ->{
                    ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                            .tagId(Long.valueOf(tagId))
                            .articleId(articleId)
                            .build();
                    articleTagRelDOList.add(articleTagRelDO);
                });
                //批量插入
                articleTagRelMapper.insertBatchSomeColumn(articleTagRelDOList);
            }

            if(!CollectionUtils.isEmpty(notExistTags)){
                // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
                List<ArticleTagRelDO> articleTagRelDOList = Lists.newArrayList();
                notExistTags.forEach( tagName ->{
                    TagDO tagDO = TagDO.builder()
                            .name(tagName)
                            .createTime(LocalDateTime.now())
                            .updateTime(LocalDateTime.now())
                            .build();
                    //标签入库
                    tagMapper.insert(tagDO);
                    //拿到对应标签 ID
                    Long tagId = tagDO.getId();
                    ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                            .tagId(Long.valueOf(tagId))
                            .articleId(articleId)
                            .build();
                    articleTagRelDOList.add(articleTagRelDO);

                });
                //批量插入
                articleTagRelMapper.insertBatchSomeColumn(articleTagRelDOList);
            }

        }

    }

}
