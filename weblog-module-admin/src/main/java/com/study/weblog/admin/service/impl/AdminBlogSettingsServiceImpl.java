package com.study.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.weblog.admin.convert.BlogSettingsConvert;
import com.study.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.study.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.study.weblog.admin.service.AdminBlogSettingsService;
import com.study.weblog.common.domain.dos.BlogSettingsDO;
import com.study.weblog.common.domain.mapper.BlogSettingsMapper;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * @ClassName AdminBlogSettingsServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/23
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        //VO转DO
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1l);
//        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
//                .id(1l)
//                .author(updateBlogSettingsReqVO.getAuthor())
//                .avatar(updateBlogSettingsReqVO.getAvatar())
//                .logo(updateBlogSettingsReqVO.getLogo())
//                .name(updateBlogSettingsReqVO.getName())
//                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
//                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
//                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
//                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
//                .build();
        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    /**
     * 获取博客设置详情
     *
     * @return
     */
    @Override
    public Response findBlogDetail() {
        // 查询 ID 为 1 的记录
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1);
        // DO 转 VO
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        return Response.success(findBlogSettingsRspVO);
    }
}
