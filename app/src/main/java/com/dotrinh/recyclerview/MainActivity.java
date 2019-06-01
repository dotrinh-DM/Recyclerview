/*
 * Copyright (c) 2019.
 * Do Trinh - https://dotrinh.com
 * All Rights Reserved
 * Written by dotrinh contact to contact@dotrinh.com please.
 */

package com.dotrinh.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import static com.dotrinh.recyclerview.misc.LogUtil.LogI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView myRecycleView = findViewById(R.id.myRecycleView);
        myRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        myRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycleView.setAdapter(new ChildAdapter());
    }

    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

        @Override
        public int getItemCount() {
            return 50;
        }

        public ChildAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new ChildViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ChildAdapter.ChildViewHolder holder, int position) { // this method will call every time when we refresh recyclerview
            holder.rowItem.setText(position + " Dotrinh.com");
            LogI("dong thu: " + position);
        }

        // VIEW HOLDER
        class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            CardView card_view;
            TextView rowItem;

            ChildViewHolder(@NonNull View itemView) {
                super(itemView);
                card_view = itemView.findViewById(R.id.cardView);
                rowItem = itemView.findViewById(R.id.rowItem);
                card_view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "dong thu: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
