package com.lyk.store.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @Size(max = 32)
    private String username;

    @NotNull
    @Size(max = 32)
    private String password;


}
