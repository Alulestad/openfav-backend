package com.senadi.pasantes.intranet.service.dto;

public class LoginResponse {
    private boolean valid;
    private String role;
    private Long userId;

    public LoginResponse() {
    }

    public LoginResponse(boolean valid, String role, Long userId) {
        this.valid = valid;
        this.role = role;
        this.userId = userId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
