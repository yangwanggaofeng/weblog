package com.study.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserRoleDo
 * @Description 用户角色实体类
 * @Author zhang
 * @Date 2023/12/4
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@Builder
@TableName("t_user_role")
public class UserRoleDo {
    @TableId(type = IdType.AUTO)
    private long id;
    private String username;
    private String role;
    private Date createTime;
}
