package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.weblog.common.domain.dos.UserDo;

import java.time.LocalDateTime;

public interface UserMapper extends BaseMapper<UserDo> {
    default UserDo findByUserName(String username){
        LambdaQueryWrapper<UserDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDo::getUsername, username);
        return selectOne(wrapper);

    }
    default int updatePasswordByUsername(String username, String password){
        LambdaUpdateWrapper<UserDo> wrapper = new LambdaUpdateWrapper<>();
        //设置需要更新的字段
        wrapper.set(UserDo::getPassword, password);
        wrapper.set(UserDo::getUpdateTime, LocalDateTime.now());
        //更新条件
        wrapper.eq(UserDo::getUsername, username);
        return update(null, wrapper);
    }
}
