package com.nathaniel.travel_guide_app.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
