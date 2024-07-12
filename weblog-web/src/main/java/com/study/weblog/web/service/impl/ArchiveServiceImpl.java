package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.study.weblog.common.domain.dos.ArticleDO;
import com.study.weblog.common.domain.mapper.ArticleMapper;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.convert.ArticleConvert;
import com.study.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.study.weblog.web.model.vo.archive.FindArchiveArticlePageListRspVO;
import com.study.weblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.study.weblog.web.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Year;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @ClassName ArchiveServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/6/29
 * @Version 1.0
 **/
@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取文章归档分页列表
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArchiveArticlePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        long current = findArchiveArticlePageListReqVO.getCurrent();
        long size = findArchiveArticlePageListReqVO.getSize();
        //分页查询
        IPage<ArticleDO> page = articleMapper.selectPageList(current, size, null,null, null);
        List<ArticleDO> articleDOList = page.getRecords();

        List<FindArchiveArticlePageListRspVO> vos = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(articleDOList)){
            //DO转VO
            List<FindArchiveArticleRspVO> archiveArticleRspVOList = articleDOList.stream()
                    .map(articleDO -> ArticleConvert.INSTANCE.convertDO2ArchiveArticleVO(articleDO))
                    .collect(Collectors.toList());
            //按照创建的月份进行分类
            Map<YearMonth, List<FindArchiveArticleRspVO>> map = archiveArticleRspVOList.stream()
                    .collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));
            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<FindArchiveArticleRspVO> > sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((k,v) ->vos.add(FindArchiveArticlePageListRspVO.builder().month(k).articleList(v).build()));

        }

        return PageResponse.success(page,vos);
    }
}
