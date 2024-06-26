package com.study.weblog.web.service.impl;

import com.study.weblog.common.domain.dos.BlogSettingsDO;
import com.study.weblog.common.domain.mapper.BlogSettingsMapper;
import com.study.weblog.common.utils.Response;
import com.study.weblog.web.convert.BlogSettingsConvert;
import com.study.weblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import com.study.weblog.web.service.BlogSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BlogSettingsServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/24
 * @Version 1.0
 **/
@Service
public class BlogSettingsServiceImpl implements BlogSettingsService {
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    /**
     * 获取博客设置信息
     * @return
     */
    @Override
    public Response findBlogSettingsDeatail() {
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindBlogSettingsDetailRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        return Response.success(vo);
    }
}
