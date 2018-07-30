package com.example.android.thelaundrybasket;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<order> {

    public OrderAdapter(Context context, ArrayList<order> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.items_orders, parent, false);
        }

        // TextView usernameView = (TextView) convertView.findViewById(R.id.username_item);

        TextView dateTextView = (TextView) convertView.findViewById(R.id.date_item);

        TextView slotTextView = (TextView) convertView.findViewById(R.id.time_slot_item);

        TextView registrationTextView = (TextView) convertView.findViewById(R.id.registration_item);

        //TextView serviceTextView = (TextView) convertView.findViewById(R.id.service_item);



        order new_order = getItem(position);

        //usernameView.setText(new_order.getQuantity());
        dateTextView.setText(new_order.getDate());
        slotTextView.setText(new_order.getSlot());
        //serviceTextView.setText(new_order.getService());
        //addressTextView.setText(new_order.getAddress());
        registrationTextView.setText(new_order.getKey());




        return convertView;

    }
}

