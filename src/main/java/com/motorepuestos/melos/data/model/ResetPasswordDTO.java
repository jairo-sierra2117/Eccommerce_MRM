package com.motorepuestos.melos.data.model;


public class ResetPasswordDTO {

    private String email;
    private String actualPassword;
    private String newPassword;

    // Constructor, getters y setters
    public ResetPasswordDTO() {
    }

    public ResetPasswordDTO(String email, String actualPassword, String newPassword) {
        this.email = email;
        this.actualPassword = actualPassword;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
