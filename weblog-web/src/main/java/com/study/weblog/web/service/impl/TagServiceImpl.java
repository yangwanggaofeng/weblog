package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.common.domain.dos.ArticleDO;
import com.study.weblog.common.domain.dos.ArticleTagRelDO;
import com.study.weblog.common.domain.dos.TagDO;
import com.study.weblog.common.domain.mapper.ArticleMapper;
import com.study.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.study.weblog.common.domain.mapper.TagMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.convert.ArticleConvert;
import com.study.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.study.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import com.study.weblog.web.model.vo.tag.FindTagListRspVO;
import com.study.weblog.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public Response findTagList() {
        //获取所有tag
        List<TagDO> tagDOList = tagMapper.selectList(Wrappers.emptyWrapper());
        List<FindTagListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(tagDOList)){
           vos = tagDOList.stream()
           .map(tagDO -> FindTagListRspVO.builder()
           .id(tagDO.getId())
           .name(tagDO.getName())
           .build()).collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    @Override
    public PageResponse findTagArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();
        Long tagId = findTagArticlePageListReqVO.getTagId();
        TagDO tagDO = tagMapper.selectById(tagId);
        if(Objects.isNull(tagDO)){
            log.error("==> 该标签不存在，tagId :", tagId);
            throw new BizException(ResponseCodeEnum.TAG_NAME_IS_NOT_EXISTED);
        }
        List<ArticleTagRelDO> articleTagRelDOList = articleTagRelMapper.selectListByTagId(tagId);
        if(CollectionUtils.isEmpty(articleTagRelDOList)){
            log.info("==> 该标签下还未发布任何文章，tagId :", tagId);
            return PageResponse.success(null, null);
        }
        List<FindTagArticlePageListRspVO> vos = null;
        //提取所有文章Id
        List<Long> articleIds = articleTagRelDOList.stream().map(ArticleTagRelDO::getArticleId).collect(Collectors.toList());

        Page<ArticleDO> articleDOPage = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOList = articleDOPage.getRecords();
        if(!CollectionUtils.isEmpty(articleDOPage.getRecords())) {
            vos = articleDOList.stream()
                    .map(articleDO -> ArticleConvert.INSTANCE.convertDO2TagArticleVO(articleDO))
                    .collect(Collectors.toList());
        }

        return PageResponse.success(articleDOPage, vos);
    }
}
