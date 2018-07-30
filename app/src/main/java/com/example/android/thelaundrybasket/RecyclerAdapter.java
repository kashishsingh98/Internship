package com.example.android.thelaundrybasket;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    Context context;
    String[] playes;
    Dialog dialog;
    TextView textView ;

    public RecyclerAdapter(String[]playes,Context context,TextView textView,Dialog dialog)
    {
        this.playes=playes;
        this.textView=textView;
        this.context=context;
        this.dialog = dialog;

    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.costum_sumit,parent,false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {

        holder.txt1.setText(playes[position]);
    }

    @Override
    public int getItemCount() {
        return playes.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txt1;

        public RecyclerViewHolder(View itemView)
        {
            super(itemView);

            txt1=itemView.findViewById(R.id.txtSumitMallick
            );
            txt1.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position==0)
            {
                return;
            }
            else
            {
                textView.setText(playes[position]);
                dialog.dismiss();
            }
        }
    }
}

