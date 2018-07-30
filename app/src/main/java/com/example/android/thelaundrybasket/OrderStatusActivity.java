package com.example.android.thelaundrybasket;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderStatusActivity extends AppCompatActivity {

    private TextView quantity;
    private TextView timeSlot;
    private TextView Service;
    private TextView date;
    private TextView adrress;
    private TextView key;
    private TextView deliveryDate;
    private TextView deliveryTimeSlot;

    private Button cancelOrder;

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mMessagesDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("order");

        quantity = (TextView)findViewById(R.id.quantity_detail);
        timeSlot = (TextView)findViewById(R.id.time_slot_detail);
        Service = (TextView)findViewById(R.id.service_detail);
        date = (TextView)findViewById(R.id.date_detail);
        adrress = (TextView)findViewById(R.id.address_detail);
        key = (TextView)findViewById(R.id.key_detail);
        deliveryDate = (TextView)findViewById(R.id.delivery_date_detail);
        deliveryTimeSlot = (TextView)findViewById(R.id.delivery_time_slot_detail);

        cancelOrder = (Button)findViewById(R.id.cancel_order);



        order this_order = new order();
        this_order = getIntent().getParcelableExtra("Detail_Order");
        quantity.setText(this_order.getQuantity());
        timeSlot.setText(this_order.getSlot());
        Service.setText(this_order.getService());
        date.setText(this_order.getDate());
        adrress.setText(this_order.getAddress());
        key.setText(this_order.getKey());
        deliveryDate.setText(this_order.getDeliveryDate());
        deliveryTimeSlot.setText(this_order.getDeliverySlot());

        final String key2 = this_order.getKey();

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessagesDatabaseReference.orderByChild("key").equalTo(key2).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot orderSnapshot: dataSnapshot.getChildren()) {
                                    orderSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                Toast.makeText(OrderStatusActivity.this,"Your order is cancelled",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

