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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

    private UserLoginResp response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        edtEmail = findViewById( R.id.edtEmailLogin );
        edtPass = findViewById( R.id.edtPassLogin );
        tvForgot = findViewById( R.id.tvForgot );
        btnLogin = findViewById( R.id.btnLogin );
        btnReg = findViewById( R.id.btnRegisterLogin );
        initViewModel();
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

    private void initViewModel() {
        loginViewModel = new ViewModelProvider( this ).get( LoginViewModel.class );
        loginViewModel.getMutableLoginCreds().observe( this, new Observer<UserLoginResp>() {
            @Override
            public void onChanged(UserLoginResp userResponse) {
                if (!userResponse.isSuccess()) {
                    Toast.makeText( LoginActivity.this, "failed to log in", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
                }
            }
        } );
    }

    private void loginRequest(String email, String pass) {


        loginViewModel.callToLogin( new UserLoginReq( email, pass ), this );


    }
}