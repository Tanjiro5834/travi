package com.nathaniel.travel_guide_app.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;    // Or username, depending on your strategy
    private String password;
}
