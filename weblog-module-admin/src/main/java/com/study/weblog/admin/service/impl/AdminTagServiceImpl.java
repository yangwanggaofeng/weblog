package com.study.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.study.weblog.admin.model.vo.tag.AddTagReqVO;
import com.study.weblog.admin.model.vo.tag.DeleteTagVO;
import com.study.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.study.weblog.admin.model.vo.tag.SearchTagVO;
import com.study.weblog.admin.service.AdminTagService;
import com.study.weblog.common.domain.dos.ArticleTagRelDO;
import com.study.weblog.common.domain.dos.TagDO;
import com.study.weblog.common.domain.dos.TagDO;
import com.study.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.study.weblog.common.domain.mapper.TagMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.model.vo.SelectRspVO;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.BatchUpdateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName AdminCategoryServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 添加分类
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTag(AddTagReqVO addTagReqVO) {
        // vo转do
        List<TagDO> tagDOList = addTagReqVO.getTags()
                .stream().map(tagName -> TagDO.builder()
                        .name(tagName.trim())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build()
                ).collect(Collectors.toList());
        //批量插入
        //业务： 插入重复的标签后，忽略重复插入的错误
        try{
            saveBatch(tagDOList);
        }catch (Exception e){
            log.warn("标签已存在", e);
        }

        return Response.success();
    }

    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        //构建查询条件
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStrarTime();
        LocalDate endDate = findTagPageListReqVO.getEndTime();
        //执行分页查询
        Page<TagDO> tagDOPage = tagMapper.selectPageList(current, size, name, startDate, endDate);
        List<TagDO>  tagDOList = tagDOPage.getRecords();
        //Do 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(tagDOList)){
            vos = tagDOList.stream()
                    .map(tagDO -> FindCategoryPageListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .createTime(tagDO.getCreateTime())
                            .build()).collect(Collectors.toList());
        }
        return PageResponse.success(tagDOPage,vos);
    }

    /**
     * 删除标签
     * @param deleteTagVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagVO deleteTagVO) {
        Long tagId = deleteTagVO.getId();
        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectByTagId(tagId);
        if(Objects.nonNull(articleTagRelDO)){
            log.warn("==>此分类下包含文章，无法删除，标签ID:{}",tagId);
            throw new BizException(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }
        int count = tagMapper.deleteById(deleteTagVO.getId());
        return count >0 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NAME_IS_NOT_EXISTED);
    }

    @Override
    public Response findTagSelectList() {
        //查询所有标签
        List<TagDO> tagDOList = tagMapper.selectList(null);
        //DO转VO
        List<SelectRspVO> selectRspVOList = null;
        //如果分类数据不为空
        if(!CollectionUtils.isEmpty(tagDOList)){
            //将标签id作为value值，将标签名称作为label展示
            selectRspVOList = tagDOList.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build()).collect(Collectors.toList());
        }
        return Response.success(selectRspVOList);
    }

    @Override
    public Response searchTag(SearchTagVO searchTagVO) {
        String key = searchTagVO.getKey();
        //执行模糊查询
        List<TagDO> tagDOList = tagMapper.selectByKey(key);
        //DO 转 VO
        List<SelectRspVO> selectRspVOList = null;
        if(!CollectionUtils.isEmpty(tagDOList)){
            //将标签id作为value值，将标签名称作为label展示
            selectRspVOList = tagDOList.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspVOList);
    }
}
