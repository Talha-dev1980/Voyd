package com.example.voyd.Models;

public class UserLoginResp {
    private boolean success = false;
    private Data data;
    private String message, code;

    private error errorResp;


    public UserLoginResp() {
    }

    public UserLoginResp(boolean success, Data data, String message, String code, error errorResp) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
        this.errorResp = errorResp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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

    public error getErrorResp() {
        return errorResp;
    }

    public void setErrorResp(error errorResp) {
        this.errorResp = errorResp;
    }
}
