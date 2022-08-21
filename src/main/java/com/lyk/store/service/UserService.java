package com.lyk.store.service;

import com.lyk.store.model.dto.request.UserRequest;


public interface UserService {

    void insert(UserRequest userRequest);

    void login(UserRequest userRequest);
}
