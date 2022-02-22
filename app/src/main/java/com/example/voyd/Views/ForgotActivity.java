package com.example.voyd.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.RegisterViewModel;

public class ForgotActivity extends AppCompatActivity {

    private EditText edtEmail;
    private String email;
    private Button btnReset;
    private RegisterViewModel regViewModel;
    private MutableLiveData<UserLoginResp> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot );
        edtEmail = findViewById( R.id.edtEmailForgot );
        btnReset = findViewById( R.id.btnReset );
        initViewModel();
        btnReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString().trim();
                if (TextUtils.isEmpty( edtEmail.getText().toString().trim() )) {
                    Toast.makeText( ForgotActivity.this, "Please fill in field", Toast.LENGTH_LONG ).show();
                } else {
                    emailVerified( email );
                }
            }

        } );
    }

    private void initViewModel() {
        regViewModel = new ViewModelProvider( this ).get( RegisterViewModel.class );
        regViewModel.getMutEmailSent().observe( this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean userResponse) {
                if (!userResponse) {
                    Toast.makeText( ForgotActivity.this, "Email not verified ", Toast.LENGTH_SHORT ).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder( ForgotActivity.this );
                    builder.setMessage( "Email not verified" );
                    builder.setPositiveButton( "Resend verfication Email", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            resendEmail( email );
                        }
                    } );
                } else {
                    startActivity( new Intent( ForgotActivity.this, resetCodeActivity.class ) );
                }
            }
        } );
        regViewModel.getMutableRegCreds().observe( this, new Observer<UserLoginResp>() {
            @Override
            public void onChanged(UserLoginResp userResponse) {
                if (!userResponse.isSuccess()) {
                    Toast.makeText( ForgotActivity.this, "Failed To send email ", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( ForgotActivity.this, "" + userResponse.getMessage(), Toast.LENGTH_SHORT ).show();

                }
            }
        } );
    }

    private void resendEmail(String email) {
        regViewModel.sendVerificationEmail( email );


    }

    private void emailVerified(String email) {


        regViewModel.isVerified( email );

    }
}