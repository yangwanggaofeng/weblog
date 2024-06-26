package com.study.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.study.weblog.common.domain.dos.CategoryDO;
import com.study.weblog.common.domain.mapper.CategoryMapper;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.study.weblog.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CategoryServiceImpl
 * @Description 分类service
 * @Author zhang
 * @Date 2024/4/18
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
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
}
