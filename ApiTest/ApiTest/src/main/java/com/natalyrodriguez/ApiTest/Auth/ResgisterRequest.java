package com.natalyrodriguez.ApiTest.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResgisterRequest {
    String username;
    String password;
    String email;
    Integer phonenumber;
}
