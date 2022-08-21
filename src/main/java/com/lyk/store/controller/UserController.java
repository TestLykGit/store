package com.lyk.store.controller;

import com.lyk.store.config.DataResult;
import com.lyk.store.model.dto.request.UserRequest;
import com.lyk.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/add")
    public DataResult<?> addUser(@Valid @RequestBody UserRequest userRequest){
        userService.insert(userRequest);
        return DataResult.success();
    }

    @PutMapping("/login")
    public DataResult<?> login(@Valid @RequestBody UserRequest userRequest){
        userService.login(userRequest);
        return DataResult.success();
    }
}
