package com.study.weblog.admin.convert;

import com.study.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.study.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.study.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化convert实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将VO转为DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);
    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
