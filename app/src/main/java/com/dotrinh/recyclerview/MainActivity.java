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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Person> people = new ArrayList<>();
    RecyclerView myRecycleView;
    private LinearLayoutManager layoutManager;
    ChildAdapter adapter;
    int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        myRecycleView = findViewById(R.id.myRecycleView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecycleView.setLayoutManager(layoutManager);
        adapter = new ChildAdapter();
        myRecycleView.setAdapter(adapter);
        findViewById(R.id.delActionButton).setOnClickListener(this);
        findViewById(R.id.allActionButton).setOnClickListener(this);
        findViewById(R.id.addActionButton).setOnClickListener(this);
        myRecycleView.addOnScrollListener(onScrollListener);
    }

    void initData() {
        for (int i = 0; i < 10; i++) {
            people.add(new Person(i, "Dotrinh", false));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delActionButton:
                int c = people.size() - 1;
                for (int i = c; i >= 0; i--) {
                    if (people.get(i).isCheck()) {
                        people.remove(i);
                        adapter.notifyItemRemoved(i);
                        c--;
                    }
                }
                break;
            case R.id.allActionButton:
                boolean isUncheck = false;
                for (Person it : people) {
                    if (it.isCheck()) {
                        isUncheck = true;
                    } else {
                        isUncheck = false;
                        break;
                    }
                }
                if (isUncheck) {
                    for (Person it2 : people) {
                        it2.setCheck(false);
                    }
                } else {
                    for (Person it2 : people) {
                        it2.setCheck(true);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.addActionButton:
                int nextId;
                if (people.size() == 0) {
                    nextId = 0;
                } else {
                    nextId = (people.get(people.size() - 1).getId()) + 1;
                }
                people.add(new Person(nextId, "Dotrinh", false));
                if (lastVisibleItem == nextId) {
                    myRecycleView.scrollToPosition(nextId);
                } else {
                    myRecycleView.smoothScrollToPosition(nextId);
                }
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int sumVisibleItem = layoutManager.getChildCount(); // tuy theo do cao cua row va do cao cua man hinh
            int sum = layoutManager.getItemCount(); // bang voi size cua array list
            int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition(); // dong dau tien dc hien thi tren man hinh
            lastVisibleItem = firstVisibleItemPos + sumVisibleItem;
        }
    };

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
            holder.rowItem.setText(people.get(position).getName() + " " + people.get(position).getId());
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
