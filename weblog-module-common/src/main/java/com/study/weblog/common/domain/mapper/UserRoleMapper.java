package com.study.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.weblog.common.domain.dos.UserRoleDo;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRoleDo> {

    default List<UserRoleDo> selectByUsername(String username){
        LambdaQueryWrapper<UserRoleDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleDo::getUsername, username);
        return selectList(wrapper);
    }
}
