package com.example.voyd.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voyd.Models.RegistrationReq;
import com.example.voyd.Models.UserLoginReq;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.Network.APIService;
import com.example.voyd.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    final private MutableLiveData<UserLoginResp> mutableRegCreds;

    public RegisterViewModel() {
        mutableRegCreds = new MutableLiveData<UserLoginResp>();

    }

    public MutableLiveData<UserLoginResp> getMutableRegCreds() {
        return mutableRegCreds;
    }

    public MutableLiveData<UserLoginResp>  callToRegister(RegistrationReq req) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<UserLoginResp> call = apiService.registerVyod(req);
        call.enqueue(new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {

                mutableRegCreds.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                mutableRegCreds.postValue(null);
            }
        });
        return getMutableRegCreds();
    }
    public MutableLiveData<UserLoginResp>  sendVerificationEmail(String email) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<UserLoginResp> call = apiService.isVerified(email);
        call.enqueue(new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {

                mutableRegCreds.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                mutableRegCreds.postValue(null);
            }
        });
        return getMutableRegCreds();
    }
    public MutableLiveData<UserLoginResp>  resetVerificationCode(String email,String code) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<UserLoginResp> call = apiService.validateResetCode(email,code);
        call.enqueue(new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {

                mutableRegCreds.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                mutableRegCreds.postValue(null);
            }
        });
        return getMutableRegCreds();
    }
    public MutableLiveData<UserLoginResp>  isVerified(String email) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<UserLoginResp> call = apiService.isVerified(email);
        call.enqueue(new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {

                mutableRegCreds.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                mutableRegCreds.postValue(null);
            }
        });
        return getMutableRegCreds();
    }
}
