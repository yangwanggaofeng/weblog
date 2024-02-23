package com.study.weblog.admin.service;

import com.study.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.study.weblog.common.utils.Response;

public interface AdminBlogSettingsService {
    /**
     * 博客基本信息设置修改
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
}
