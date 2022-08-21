package com.lyk.store.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.lyk.store.dao.UserMapper;
import com.lyk.store.enums.UserDeleteStatus;
import com.lyk.store.exception.AppException;
import com.lyk.store.exception.AppExceptionCode;
import com.lyk.store.model.dto.request.UserRequest;
import com.lyk.store.model.po.User;
import com.lyk.store.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMD5Password(user.getPassword(), salt));
        user.setCreatedTime(new Date());
        user.setIsDelete(UserDeleteStatus.DELETE.getCode());
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            if (StrUtil.contains(e.getMessage(), "uk_username")) {
                throw new AppException(AppExceptionCode.BAD_REQUEST_PARAMS_MATCHING_ERROR, "用户名已存在");
            }
        }
    }

    @Override
    public void login(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        User oldUser = userMapper.selectOne(user.getUsername());
        if(oldUser == null){
            throw new AppException(AppExceptionCode.BAD_REQUEST_PARAMS_MATCHING_ERROR, "用户不存在，请注册");
        }
        if(!Objects.equals(getMD5Password(user.getPassword(), oldUser.getSalt()), oldUser.getPassword())){
            throw new AppException(AppExceptionCode.BAD_REQUEST_PARAMS_MATCHING_ERROR, "用户名或密码错误");
        }
    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
