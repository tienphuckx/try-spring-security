package com.tienphuckx.AuthenticateJWT.model;

import java.util.Objects;

public final class LoginRequestForm {
    private final String username;
    private final String password;

    public LoginRequestForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LoginRequestForm) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "LoginRequestForm[" +
                "username=" + username + ", " +
                "password=" + password + ']';
    }

}
