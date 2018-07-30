package com.example.android.thelaundrybasket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderSummaryActivity extends AppCompatActivity {

    private Button done;
    private TextView deliveryDate;
    private TextView service;
    private TextView quantity;

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mMessagesDatabaseReference;

    String deliveryTimeSlot;
    private Spinner deliverySlotSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_summary );

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child( "order" );

        order this_order = new order();
        this_order = getIntent().getParcelableExtra( "Order Summary" );

        /*Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("DeliveryDate");
        Log.e("delivery date",message);*/

        deliverySlotSpinner = (Spinner) findViewById( R.id.time_slots );

        deliveryDate = (TextView) findViewById( R.id.delivery_date );
        service = (TextView) findViewById( R.id.delivery_type );
        quantity = (TextView) findViewById( R.id.delivery_quantity );
        deliveryDate.setText( this_order.getDeliveryDate() );
        service.setText( this_order.getService() );
        quantity.setText( this_order.getQuantity() );

        final String key = this_order.getKey();

        done = (Button) findViewById( R.id.done_order );
        done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessagesDatabaseReference.orderByChild( "key" ).equalTo( key ).addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                            orderSnapshot.getRef().child( "deliverySlot" ).setValue( deliveryTimeSlot );
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
                Intent i = new Intent( OrderSummaryActivity.this, ThankYouActivity.class );
                i.putExtra( "orderr id", key );
                startActivity( i );
            }
        } );

        setupTimeSlotSpinner();
    }

    private void setupTimeSlotSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter deliveryTimeSlotSpinnerAdapter = ArrayAdapter.createFromResource( this, R.array.time_slots_options,
                android.R.layout.simple_spinner_item );

        // Specify dropdown layout style - simple list view with 1 item per line
        deliveryTimeSlotSpinnerAdapter.setDropDownViewResource( android.R.layout.simple_dropdown_item_1line );

        // Apply the adapter to the spinner
        deliverySlotSpinner.setAdapter( deliveryTimeSlotSpinnerAdapter );

        // Set the integer mSelected to the constant values
        deliverySlotSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition( position );

                if (selection.equals( "10-12" )) {
                    deliveryTimeSlot = "10-12";
                } else if (selection.equals( "1-3" )) {
                    deliveryTimeSlot = "1-3";
                } else if (selection.equals( "5-7" )) {
                    deliveryTimeSlot = "5-7";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }
}
