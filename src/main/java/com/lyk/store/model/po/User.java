package com.lyk.store.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private Integer gender;

    private String avatar;

    private Long isDelete;

    private String createdUser;

    private Date createdTime;

    private String updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}