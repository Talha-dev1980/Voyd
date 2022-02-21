package com.example.voyd.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.voyd.MainActivity;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.RegisterViewModel;

public class resetCodeActivity extends AppCompatActivity {
    private EditText edtCode;
    private Button btnSubmit;
    private String email;
    private MutableLiveData<UserLoginResp> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reset_code );
        edtCode = (EditText) findViewById( R.id.edtCodeVerify );
        btnSubmit = (Button) findViewById( R.id.btnSubmit );

        Intent it=getIntent();
        email=it.getStringExtra( "email" );
        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtCode.getText().length() >= 8) {
                    resetVerificationCode( email,edtCode.getText().toString().trim() );

                } else {

                }
            }
        } );

    }

    private void resetVerificationCode(String email,String code) {
        RegisterViewModel regViewModel = new RegisterViewModel();
        regViewModel.resetVerificationCode( email,code );

        response = regViewModel.getMutableRegCreds();

        if (response != null) {
            if (response.getValue().isSuccess()) {
                Intent it = new Intent( resetCodeActivity.this, LoginActivity.class );
                startActivity( it );
            } else {

                Toast.makeText( resetCodeActivity.this, "" + response.getValue().getErrorResponce().getMessage(), Toast.LENGTH_LONG ).show();

            }

        } else {
            Toast.makeText( resetCodeActivity.this, "Failed Password change", Toast.LENGTH_LONG ).show();

        }
    }
}