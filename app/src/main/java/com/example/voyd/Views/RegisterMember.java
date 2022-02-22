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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
    private RegisterViewModel regViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_member );
        edtEmail = findViewById( R.id.edtEmailRegister );
        edtFname = findViewById( R.id.edtFirstNameRegister );
        edtLname = findViewById( R.id.edtLastNameRegister );
        edtPass = findViewById( R.id.edtPassRegister );
        btnRegister = findViewById( R.id.btnRegister );
        btnLogin = findViewById( R.id.btnLoginRegister );

        initViewModel();
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

    private void initViewModel() {
        regViewModel = new ViewModelProvider( this ).get( RegisterViewModel.class );
        regViewModel.getMutableRegCreds().observe( this, new Observer<UserLoginResp>() {
            @Override
            public void onChanged(UserLoginResp userResponse) {
                if (!userResponse.isSuccess()) {
                    Toast.makeText( RegisterMember.this, "Registeration Failed ", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( RegisterMember.this, "Successfully Registered", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( RegisterMember.this, MainActivity.class ) );
                }
            }
        } );
    }

    private void registerMember(RegistrationReq req) {

        regViewModel.callToRegister( req );

    }
}