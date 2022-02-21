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

import com.example.voyd.MainActivity;
import com.example.voyd.Models.UserLoginResp;
import com.example.voyd.R;
import com.example.voyd.ViewModels.RegisterViewModel;

public class ForgotActivity extends AppCompatActivity {

    private EditText edtEmail;
    private String email;
    private Button btnReset;

    private MutableLiveData<UserLoginResp> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot );
        edtEmail = (EditText) findViewById( R.id.edtEmailForgot );
        btnReset = (Button) findViewById( R.id.btnReset );
        btnReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= edtEmail.getText().toString().trim();
                if (TextUtils.isEmpty( edtEmail.getText().toString().trim() )) {
                    Toast.makeText( ForgotActivity.this, "Please fill in field", Toast.LENGTH_LONG ).show();

                } else {
                    if (emailVerified( email )) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotActivity.this);
                        builder.setPositiveButton("Resend verfication Email", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               resendEmail(email);
                            }
                        });
                    }
                }
            }
        } );
    }

    private void resendEmail(String email) {
        RegisterViewModel regViewModel = new RegisterViewModel();
        regViewModel.sendVerificationEmail( email );

        response = regViewModel.getMutableRegCreds();
        if (response != null) {
            if (response.getValue().isSuccess()) {



                Toast.makeText( ForgotActivity.this, "" + response.getValue().getErrorResponce().getMessage(), Toast.LENGTH_LONG ).show();

                Intent it=new Intent(ForgotActivity.this,resetCodeActivity.class);
                it.putExtra( "email",email );
                startActivity( it );
            }

        } else {
            Toast.makeText( ForgotActivity.this, "Failed To send email", Toast.LENGTH_LONG ).show();

        }

    }

    private boolean emailVerified(String email) {

        RegisterViewModel regViewModel = new RegisterViewModel();
        regViewModel.isVerified( email );

        response = regViewModel.getMutableRegCreds();

        if (response != null) {
            if (response.getValue().isSuccess()) {
                return true;
            } else {

                Toast.makeText( ForgotActivity.this, "" + response.getValue().getErrorResponce().getMessage(), Toast.LENGTH_LONG ).show();

            }

        } else {
            Toast.makeText( ForgotActivity.this, "Failed Registration", Toast.LENGTH_LONG ).show();

        }
        return false;
    }
}