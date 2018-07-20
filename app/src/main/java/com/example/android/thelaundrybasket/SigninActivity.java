package com.example.android.thelaundrybasket;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity{
    TextView t1;
    EditText e1,e2;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sign_in );
         t1= findViewById( R.id.textView );
         e1= findViewById( R.id.editText );
         e2= findViewById( R.id.editText2 );
         firebaseAuth= FirebaseAuth.getInstance();
         dialog= new ProgressDialog( this );
    }

    public void signInUser(View v)
    {
        dialog.setMessage( "Signing in.Please wait." );
        dialog.show();
        if(e1.getText().toString().equals( "" )|| e2.getText().toString().equals("")){
            Toast.makeText( getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_SHORT ).show();
        }
        else {
            firebaseAuth.signInWithEmailAndPassword( e1.getText().toString(), e2.getText().toString() ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        dialog.hide();
                        Toast.makeText( getApplicationContext(),"User successfully signed in.",Toast.LENGTH_SHORT ).show();
                        Intent intent= new Intent( SigninActivity.this ,MainPageActivity.class );
                        startActivity( intent );
                        finish();
                    }
                    else
                    {
                        dialog.hide();
                        Toast.makeText( getApplicationContext(),"User not found.",Toast.LENGTH_SHORT ).show();
                    }
                }
            } );
        }
    }
}
