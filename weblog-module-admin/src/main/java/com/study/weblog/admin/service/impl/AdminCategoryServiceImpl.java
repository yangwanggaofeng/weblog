package com.study.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.weblog.admin.model.vo.category.AddCategoryVO;
import com.study.weblog.admin.model.vo.category.DeleteCategoryVO;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.study.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.study.weblog.admin.service.AdminCategoryService;
import com.study.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.study.weblog.common.domain.dos.CategoryDO;
import com.study.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.study.weblog.common.domain.mapper.CategoryMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.exception.BizException;
import com.study.weblog.common.model.vo.SelectRspVO;
import com.study.weblog.common.utils.PageResponse;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    /**
     * 添加分类
     * @param addCategoryVo
     * @return
     */
    @Override
    public Response addCategory(AddCategoryVO addCategoryVo) {
        String categoryName = addCategoryVo.getName();
        //判断分类是否存在
        CategoryDO categoryDo = categoryMapper.selectByName(categoryName);

        if(Objects.nonNull(categoryDo)){
            log.warn("分类名称： {}，此分类已存在", categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
        //构建DO类
        CategoryDO insertCategoryDO = CategoryDO.builder()
                .name(categoryName.trim())
                .build();

        //执行 insert
        categoryMapper.insert(insertCategoryDO);
        return Response.success();
    }

    @Override
    public PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();
        //构建查询条件
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStrarTime();
        LocalDate endDate = findCategoryPageListReqVO.getEndTime();
        //执行分页查询
        Page<CategoryDO> categoryDoPage = categoryMapper.selectPageList(current, size, name, startDate, endDate);
        List<CategoryDO> categoryDOList = categoryDoPage.getRecords();
        //Do 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(categoryDOList)){
            vos = categoryDOList.stream()
                    .map(categoryDo -> FindCategoryPageListRspVO.builder()
                            .id(categoryDo.getId())
                            .name(categoryDo.getName())
                            .createTime(categoryDo.getCreateTime())
                            .build()).collect(Collectors.toList());
        }
        return PageResponse.success(categoryDoPage,vos);
    }

    /**
     * 删除分类
     * @param deleteCategoryVO
     * @return
     */
    @Override
    public Response deleteCategory(DeleteCategoryVO deleteCategoryVO) {
        // 校验该分类下是否已经有文章，若有，则提示需要先删除分类下所有文章，才能删除
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByCategoryId(deleteCategoryVO.getId());
        if(Objects.nonNull(articleCategoryRelDO)){
            log.warn("==> 此分类下包含文章，无法删除，categoryId: {}", deleteCategoryVO.getId());
            throw new BizException(ResponseCodeEnum.CATEGORY_CAN_NOT_DELETE);
        }
        categoryMapper.deleteById(deleteCategoryVO.getId());
        return Response.success();

    }

    @Override
    public Response findCategorySelectList() {
        //查询所有分类
        List<CategoryDO> categoryDOList = categoryMapper.selectList(null);
        //DO转VO
        List<SelectRspVO> selectRspVOList = null;
        //如果分类数据不为空
        if(!CollectionUtils.isEmpty(categoryDOList)){
            //将分类id作为value值，将分类名称作为label展示
            selectRspVOList = categoryDOList.stream()
                    .map(categoryDo -> SelectRspVO.builder()
                            .label(categoryDo.getName())
                            .value(categoryDo.getId())
                            .build()).collect(Collectors.toList());
        }
        return Response.success(selectRspVOList);
    }
}
