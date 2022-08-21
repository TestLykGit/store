package com.lyk.store.dao;

import com.lyk.store.model.po.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    User selectOne(String username);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User user);
}