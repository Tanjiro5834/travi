package com.nathaniel.travel_guide_app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
}