package com.openfav.dto;

public class LoginResponse {
    private boolean valid;
    private Integer userId;
    private String role;
    private Integer ongId;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(boolean valid, Integer userId, String role, Integer ongId, String message) {
        this.valid = valid;
        this.userId = userId;
        this.role = role;
        this.ongId = ongId;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getOngId() {
        return ongId;
    }

    public void setOngId(Integer ongId) {
        this.ongId = ongId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
