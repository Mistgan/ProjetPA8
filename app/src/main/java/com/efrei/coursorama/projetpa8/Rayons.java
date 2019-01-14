package com.efrei.coursorama.projetpa8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Rayons extends AppCompatActivity {

    private ArrayList<Item> mList;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rayons);

        createList();
        buildRecyclerView();
    }

    public void getInformationItem(int position)
    {
        Log.i("DEBUG","position: " + mList.get(position).toString());
        String text = mList.get(position).toString();
        Intent intent = new Intent(this, Apropos.class);
        //intent.putExtra(EXTRA_POSITION, text);
        startActivity(intent);
    }

    public void createList()
    {
        mList = new ArrayList<>();
        mList.add(new Item(R.drawable.laitier, "Laitier", "1☆"));
        mList.add(new Item(R.drawable.charcuterie,"Charcuterie", "1☆"));
        mList.add(new Item(R.drawable.fruit_legumes, "Fruit & Légume", "2☆"));
        mList.add(new Item(R.drawable.viande,"Viande", "2☆"));
        mList.add(new Item(R.drawable.boisson, "Boisson", "3☆"));
        mList.add(new Item(R.drawable.epicerie_sucree,"Epicerie Sucrée", "3☆"));

    }

    public void buildRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(mList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getInformationItem(position);
            }
        });
    }
}
