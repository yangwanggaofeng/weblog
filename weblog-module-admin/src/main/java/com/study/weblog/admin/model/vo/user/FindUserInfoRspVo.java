package com.study.weblog.admin.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FindUserInfoRspVo
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/4
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserInfoRspVo {
    /**
     * 用户名
     */
    private String username;
}
