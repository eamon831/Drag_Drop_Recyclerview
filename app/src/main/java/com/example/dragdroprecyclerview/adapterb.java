package com.example.dragdroprecyclerview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterb extends RecyclerView.Adapter<adapterb.viewholder> {
    Context context;
    List<String> list;

    public adapterb(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.t.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView t;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.itemTextView);
            t.setGravity(Gravity.CENTER_HORIZONTAL);
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setAlpha(0.8F);
            //itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        }

    }
}
