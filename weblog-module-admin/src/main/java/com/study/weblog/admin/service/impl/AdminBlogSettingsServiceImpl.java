package com.study.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.study.weblog.admin.service.AdminBlogSettingsService;
import com.study.weblog.common.domain.dos.BlogSettingsDO;
import com.study.weblog.common.domain.mapper.BlogSettingsMapper;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        //VO转DO
        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
                .id(1l)
                .author(updateBlogSettingsReqVO.getAuthor())
                .avatar(updateBlogSettingsReqVO.getAvatar())
                .logo(updateBlogSettingsReqVO.getLogo())
                .name(updateBlogSettingsReqVO.getName())
                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
                .build();
        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
}
