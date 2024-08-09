package com.natalyrodriguez.ApiTest.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int phonenumber;
}
