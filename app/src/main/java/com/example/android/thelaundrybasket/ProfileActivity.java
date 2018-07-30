package com.example.android.thelaundrybasket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    EditText phone;
    static EditText email;
    TextView phone_edit;
    TextView email_edit;
    TextView password;
    ImageView referral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phone = (EditText) findViewById(R.id.phone_text);
        email = (EditText) findViewById(R.id.email_text);

        phone_edit = (TextView) findViewById(R.id.phone_edit);
        email_edit = (TextView) findViewById(R.id.email_edit);
        password = (TextView) findViewById(R.id.password_edit);

        referral = (ImageView) findViewById(R.id.refer_image);

        phone.setEnabled(false);
        email.setEnabled(false);
        phone.setInputType(InputType.TYPE_NULL);
        email.setInputType(InputType.TYPE_NULL);

        phone_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!phone.isEnabled()) {
                    email.setEnabled(false);
                    phone.setEnabled(true);
                    phone.setCursorVisible(true);
                    phone.setInputType(InputType.TYPE_CLASS_NUMBER);
                    phone_edit.setText("Done");
                }
                else {
                    phone_edit.setText("Edit");
                    phone.setEnabled(false);
                    phone.setInputType(InputType.TYPE_NULL);
                }
            }
        });

        email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.isEnabled()) {
                    phone.setEnabled(false);
                    email.setEnabled(true);
                    email.setCursorVisible(true);
                    email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    email_edit.setText("Done");
                }
                else {
                    email_edit.setText("Edit");
                    email.setEnabled(false);
                    email.setInputType(InputType.TYPE_NULL);
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to change password activity
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ReferActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Add intent to login activity
        return super.onOptionsItemSelected(item);
    }
}

