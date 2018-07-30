package com.example.android.thelaundrybasket;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankYouActivity extends AppCompatActivity {

    private Button myOrders;

    private TextView morder_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        myOrders = (Button) findViewById(R.id.all_orders);
        morder_id = (TextView)findViewById(R.id.order_id);

        Intent intent = getIntent();
        String order_id = intent.getExtras().getString("orderr id");
        morder_id.setText(order_id);

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThankYouActivity.this,MyOrdersActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ThankYouActivity.this,MainActivity2.class);
        startActivity(i);
    }
}
