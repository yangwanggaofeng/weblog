package com.study.weblog.admin.service.impl;

import com.study.weblog.admin.model.vo.category.AddCategoryVo;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.study.weblog.admin.service.AdminCategoryService;
import com.study.weblog.common.domain.dos.CategoryDo;
import com.study.weblog.common.domain.mapper.CategoryMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName AdminCategoryServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/14
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param addCategoryVo
     * @return
     */
    @Override
    public Response addCategory(AddCategoryVo addCategoryVo) {
        String categoryName = addCategoryVo.getName();
        //判断分类是否存在
        CategoryDo categoryDo = categoryMapper.selectByName(categoryName);

        if(Objects.nonNull(categoryDo)){
            log.warn("分类名称： {}，此分类已存在", categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
        //构建DO类
        CategoryDo insertCategoryDo = CategoryDo.builder()
                .name(categoryName.trim())
                .build();

        //执行 insert
        categoryMapper.insert(insertCategoryDo);
        return Response.success();
    }

    @Override
    public PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return null;
    }
}
