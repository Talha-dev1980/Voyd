package com.example.voyd.Models;

public class UserLoginResp {
    private boolean success;
   private Data data;
   private ErrorResponce errorResponce;


    public UserLoginResp() {
    }

    public UserLoginResp(boolean success, Data data, ErrorResponce errorResponce) {
        this.success = success;
        this.data = data;
        this.errorResponce = errorResponce;
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

    public ErrorResponce getErrorResponce() {
        return errorResponce;
    }

    public void setErrorResponce(ErrorResponce errorResponce) {
        this.errorResponce = errorResponce;
    }
}
