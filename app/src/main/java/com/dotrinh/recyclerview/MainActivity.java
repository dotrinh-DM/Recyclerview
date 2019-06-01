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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dotrinh.recyclerview.model.Person;

import java.util.ArrayList;

import static com.dotrinh.recyclerview.misc.LogUtil.LogI;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView myRecycleView = findViewById(R.id.myRecycleView);
        myRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        myRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        initData();
        myRecycleView.setAdapter(new ChildAdapter());
    }

    void initData() {
        for (int i = 0; i < 20; i++) {
            people.add(new Person(i, "Dotrinh", false));
        }
    }

    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

        @Override
        public int getItemCount() {
            return people.size();
        }

        public ChildAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new ChildViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ChildAdapter.ChildViewHolder holder, int position) { // this method will call every time when we refresh recyclerview
            holder.rowItem.setText(people.get(position).getName() + " " + position);
            holder.checkBox.setChecked(people.get(position).isCheck());
            LogI("dong thu: " + position);
        }

        // VIEW HOLDER
        class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {

            CardView card_view;
            TextView rowItem;
            CheckBox checkBox;

            ChildViewHolder(@NonNull View itemView) {
                super(itemView);
                card_view = itemView.findViewById(R.id.cardView);
                rowItem = itemView.findViewById(R.id.rowItem);
                checkBox = itemView.findViewById(R.id.checkBox);
                card_view.setOnClickListener(this);
                checkBox.setOnCheckedChangeListener(this);
            }

            @Override
            public void onClick(View v) {
                people.get(getAdapterPosition()).setCheck(!people.get(getAdapterPosition()).isCheck());
                notifyDataSetChanged();
            }

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                people.get(getAdapterPosition()).setCheck(isChecked);
            }
        }

    }
}
