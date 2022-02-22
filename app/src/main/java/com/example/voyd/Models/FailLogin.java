package com.example.voyd.Models;

public class FailLogin {
    private String message, code;

    public FailLogin() {
    }

    public FailLogin(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorResponce{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
