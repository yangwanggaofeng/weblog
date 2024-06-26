package com.study.weblog.web.convert;

import com.study.weblog.common.domain.dos.BlogSettingsDO;
import com.study.weblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化convert实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);
    /**
     * 将DO转为VO
     */
    FindBlogSettingsDetailRspVO convertDO2VO(BlogSettingsDO bean);
}
