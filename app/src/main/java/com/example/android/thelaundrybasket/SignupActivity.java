package com.example.android.thelaundrybasket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText e3,e4,e5,e6,e7;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        e3= findViewById( R.id.editText3 );
        e4= findViewById( R.id.editText4 );
        e5= findViewById( R.id.editText5 );
        e6= findViewById( R.id.editText6 );
        e7= findViewById( R.id.editText7 );
        firebaseAuth= FirebaseAuth.getInstance();
        dialog= new ProgressDialog( this );
    }
    public void signUpUser(View v)
    {
        dialog.setMessage( "Signing up.Please wait." );
        dialog.show();
        String name= e3.getText().toString();
        String phone= e4.getText().toString();
        String email=e5.getText().toString();
        String password= e6.getText().toString();
        String referral = e7.getText().toString();

        if(name.equals( "" )|| phone.equals("")||password.equals( "" )|| email.equals("")){
            Toast.makeText( getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_SHORT ).show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email,password  ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        dialog.hide();
                        Toast.makeText( getApplicationContext(),"User successfully signed up.",Toast.LENGTH_SHORT ).show();
                        Intent intent= new Intent( SignupActivity.this ,MainPageActivity.class );
                        startActivity( intent );
                        finish();
                    }
                    else
                    {
                        dialog.hide();
                        Toast.makeText( getApplicationContext(),"User could not be registered.",Toast.LENGTH_SHORT ).show();
                    }
                }
            } );
        }
    }
}
