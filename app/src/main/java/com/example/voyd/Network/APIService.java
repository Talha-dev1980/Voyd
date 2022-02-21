package com.example.voyd.Network;

import com.example.voyd.Models.RegistrationReq;
import com.example.voyd.Models.UserLoginReq;
import com.example.voyd.Models.UserLoginResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {


    @POST("member/api/login")
    Call<UserLoginResp>  loginVyod(@Body UserLoginReq loginReq);
    @POST("member/api/register")
    Call<UserLoginResp>  registerVyod(@Body RegistrationReq req);
    @POST("member/api/forgot")
    Call<UserLoginResp>  isVerified(@Body String email);
    @POST("member/api/validate-reset-code")
    Call<UserLoginResp>  validateResetCode(@Body String email,String code);

}
