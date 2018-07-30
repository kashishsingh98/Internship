package com.example.android.thelaundrybasket;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ReferActivity extends AppCompatActivity {

    TextView referral_code;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);

        referral_code = (TextView) findViewById(R.id.code);
        send = (Button) findViewById(R.id.send_button);

        Random r = new Random();
        for (int j = 0; j < 6; j++) {
            int i = r.nextInt(26) + 65;
            String letter = Character.toString((char) (int) i);
            referral_code.append(letter);
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, ProfileActivity.email.getText());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Referral code for Laundry App");
                intent.putExtra(Intent.EXTRA_TEXT, referral_code.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

    }
}
