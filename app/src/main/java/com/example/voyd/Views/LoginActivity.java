package com.example.voyd.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.voyd.MainActivity;
import com.example.voyd.Models.UserLoginReq;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText edtEmail, edtPass;
    private Button btnLogin, btnReg;
    private TextView tvForgot;

    private MutableLiveData<UserLoginResp> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        edtEmail = (EditText) findViewById( R.id.edtEmailLogin );
        edtPass = (EditText) findViewById( R.id.edtPassLogin );
        tvForgot = (TextView) findViewById( R.id.tvForgot );
        btnLogin = (Button) findViewById( R.id.btnLogin );
        btnReg = (Button) findViewById( R.id.btnRegisterLogin );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                if (TextUtils.isEmpty( email ) || TextUtils.isEmpty( pass )) {
                    Toast.makeText( LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_LONG ).show();
                } else {
                    loginRequest( email, pass );

                }
            }
        } );
        tvForgot.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( LoginActivity.this, ForgotActivity.class );
                startActivity( it );
            }
        } );
        btnReg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( LoginActivity.this, RegisterMember.class );
                startActivity( it );
            }
        } );

    }

    private void loginRequest(String email, String pass) {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.callToLogin( new UserLoginReq( email, pass ) );

        response = loginViewModel.getMutableLoginCreds();
        if (response != null) {
            if (response.getValue().isSuccess()) {
                Intent it = new Intent( LoginActivity.this, MainActivity.class );
                startActivity( it );
            } else {

                Toast.makeText( LoginActivity.this, "" + response.getValue().getErrorResponce().getMessage(), Toast.LENGTH_LONG ).show();

            }

        } else {
            Toast.makeText( LoginActivity.this, "Failed Login", Toast.LENGTH_LONG ).show();

        }

    }
}