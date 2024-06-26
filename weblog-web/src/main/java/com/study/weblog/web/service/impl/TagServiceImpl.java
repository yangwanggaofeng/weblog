package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.weblog.common.domain.dos.TagDO;
import com.study.weblog.common.domain.mapper.TagMapper;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.tag.FindTagListRspVO;
import com.study.weblog.web.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

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
}
