package com.study.weblog.admin.service.impl;

import com.study.weblog.admin.model.vo.user.FindUserInfoRspVo;
import com.study.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.study.weblog.admin.service.AdminUserService;
import com.study.weblog.common.domain.mapper.UserMapper;
import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminUserServiceImpl
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/2
 * @Version 1.0
 **/
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();
        //加密密码
        String encodePassword = passwordEncoder.encode(password);
        int count = userMapper.updatePasswordByUsername(username, encodePassword);
        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    @Override
    public Response findUserInfo() {
        //获取存储在ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //拿到用户名
        String username = authentication.getName();

        return Response.success(FindUserInfoRspVo.builder().username(username).build());
    }
}
