package com.example.android.thelaundrybasket;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText newPassword;
    EditText reenterPassword;
    Button resetPassword;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        auth = FirebaseAuth.getInstance();
        newPassword = (EditText) findViewById(R.id.new_password);
        dialog=new ProgressDialog(this);
        reenterPassword = (EditText) findViewById(R.id.re_enter_password);

        resetPassword = (Button) findViewById(R.id.reset_button);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reenterPassword.getText().toString().equals(newPassword.getText().toString()))

                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        dialog.setMessage("Changing Password Please Wait");
                        dialog.show();
                        user.updatePassword(reenterPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Your Password has been Changed", Toast.LENGTH_LONG).show();
                                    auth.signOut();
                                    finish();
                                    Intent i=new Intent(ChangePasswordActivity.this,ProfileActivity.class);//Put the activity of the login screen
                                    startActivity(i);
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Re Entered password and new password should match",Toast.LENGTH_LONG).show();
                }


            }


        });


    }
}


