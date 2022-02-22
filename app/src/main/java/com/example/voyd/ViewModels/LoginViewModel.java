package com.example.voyd.ViewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voyd.Models.UserLoginReq;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.Network.APIService;
import com.example.voyd.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    final private MutableLiveData<UserLoginResp> mutableLoginCreds;
    private static UserLoginResp loginCreds;

    public LoginViewModel() {
        mutableLoginCreds = new MutableLiveData<UserLoginResp>();

        loginCreds = null;
    }

    public UserLoginResp getLoginCreds() {
        return loginCreds;
    }

    public MutableLiveData<UserLoginResp> getMutableLoginCreds() {
        return mutableLoginCreds;
    }


    public void callToLogin(UserLoginReq loginReq, Context ctx) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create( APIService.class );
        Call<UserLoginResp> call = apiService.loginVyod( loginReq );
        call.enqueue( new Callback<UserLoginResp>() {
            @Override
            public void onResponse(Call<UserLoginResp> call, Response<UserLoginResp> response) {
                //mutableLoginCreds.postValue(response.body());
                if (response.isSuccessful()) {
                    mutableLoginCreds.postValue( response.body() );
                } else {
                    mutableLoginCreds.postValue( null );
                }
                if (response.body().getErrorResp() != null) {
                    Toast.makeText( ctx, response.body().getErrorResp().getEmail() + "", Toast.LENGTH_LONG ).show();
                } else if (response.body().getMessage() != null) {
                    Toast.makeText( ctx, response.body().getMessage() + "", Toast.LENGTH_LONG ).show();
                } else {
                    Toast.makeText( ctx, response.body().isSuccess() + "", Toast.LENGTH_LONG ).show();
                }

            }

            @Override
            public void onFailure(Call<UserLoginResp> call, Throwable t) {
                loginCreds = null;
            }
        });

    }
}
