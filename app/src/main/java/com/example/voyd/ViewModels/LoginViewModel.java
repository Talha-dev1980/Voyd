package com.example.voyd.ViewModels;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voyd.Models.UserLoginReq;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.Network.APIService;
import com.example.voyd.Network.RetrofitInstance;
import com.example.voyd.Views.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    final private MutableLiveData<UserLoginResp> mutableLoginCreds;

    public LoginViewModel() {
        mutableLoginCreds = new MutableLiveData<>();

    }

    public MutableLiveData<UserLoginResp> getMutableLoginCreds() {
        return mutableLoginCreds;
    }

    public MutableLiveData<UserLoginResp>  callToLogin(UserLoginReq loginReq) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<UserLoginResp> call = apiService.loginVyod(loginReq);
        call.enqueue(new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {

                mutableLoginCreds.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                mutableLoginCreds.postValue(null);
            }
        });
        return getMutableLoginCreds();
    }
}
