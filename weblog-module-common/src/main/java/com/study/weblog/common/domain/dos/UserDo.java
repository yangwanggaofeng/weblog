package com.study.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserDo
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@Builder
@TableName("t_user")
public class UserDo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;
}
