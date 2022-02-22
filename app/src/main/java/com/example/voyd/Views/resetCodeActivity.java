package com.example.voyd.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.voyd.MainActivity;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.RegisterViewModel;

public class resetCodeActivity extends AppCompatActivity {
    private EditText edtCode;
    private Button btnSubmit;
    private String email;
    private MutableLiveData<UserLoginResp> response;
    private RegisterViewModel regViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reset_code );
        edtCode = findViewById( R.id.edtCodeVerify );
        btnSubmit = findViewById( R.id.btnSubmit );

        Intent it = getIntent();
        email = it.getStringExtra( "email" );
        initViewModel();
        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtCode.getText().length() >= 8) {
                    resetVerificationCode( email, edtCode.getText().toString().trim() );

                } else {

                }
            }
        } );

    }

    private void initViewModel() {
        regViewModel = new ViewModelProvider( this ).get( RegisterViewModel.class );
        regViewModel.getMutableRegCreds().observe( this, new Observer<UserLoginResp>() {
            @Override
            public void onChanged(UserLoginResp userResponse) {
                if (!userResponse.isSuccess()) {
                    Toast.makeText( resetCodeActivity.this, "Registeration Failed ", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( resetCodeActivity.this, "Successfully Registered", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( resetCodeActivity.this, MainActivity.class ) );
                }
            }
        } );
    }

    private void resetVerificationCode(String email, String code) {
        regViewModel.resetVerificationCode( email, code );
    }
}