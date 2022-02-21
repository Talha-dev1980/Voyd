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
import com.example.voyd.Models.RegistrationReq;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.RegisterViewModel;

public class RegisterMember extends AppCompatActivity {
    private EditText edtEmail, edtPass, edtFname, edtLname;
    private Button btnRegister;
    private TextView btnLogin;
    private MutableLiveData<UserLoginResp> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_member );
        edtEmail = (EditText) findViewById( R.id.edtEmailRegister );
        edtFname = (EditText) findViewById( R.id.edtFirstNameRegister );
        edtLname = (EditText) findViewById( R.id.edtLastNameRegister );
        edtPass = (EditText) findViewById( R.id.edtPassRegister );
        btnRegister = (Button) findViewById( R.id.btnRegister );
        btnLogin = (TextView) findViewById( R.id.btnLoginRegister );

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                String firstName = edtFname.getText().toString();
                String lastName = edtLname.getText().toString();
                if (TextUtils.isEmpty( email ) || TextUtils.isEmpty( pass ) || TextUtils.isEmpty( firstName ) || TextUtils.isEmpty( lastName )) {
                    Toast.makeText( RegisterMember.this, "All fields are mandatory", Toast.LENGTH_LONG ).show();
                } else {

                    registerMember( new RegistrationReq( firstName, lastName, email, pass, "u2cXXDKF" ) );

                }
            }
        } );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( RegisterMember.this, LoginActivity.class );
                startActivity( it );
            }
        } );
    }

    private void registerMember(RegistrationReq req) {

        RegisterViewModel regViewModel = new RegisterViewModel();
        regViewModel.callToRegister( req );

        response = regViewModel.getMutableRegCreds();

        if (response != null) {
            if (response.getValue().isSuccess()) {
                Intent it = new Intent( RegisterMember.this, MainActivity.class );
                startActivity( it );
            } else {

                Toast.makeText( RegisterMember.this, "" + response.getValue().getErrorResponce().getMessage(), Toast.LENGTH_LONG ).show();

            }

        } else {
            Toast.makeText( RegisterMember.this, "Failed Registration", Toast.LENGTH_LONG ).show();

        }

    }
}