package com.efrei.coursorama.projetpa8;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    public static final String EXTRA_POSITION = "com.efrei.coursorama.projetpa8.EXTRA_POSITION";

    private Button btnAccueil;
    private Button btnRayons;
    private Button btnApropos;
    private EditText txtBudget;

    private ArrayList<Item> mList;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btnAccueil = findViewById(R.id.btnAccueil);
        btnRayons = findViewById(R.id.btnRayons);
        btnApropos = findViewById(R.id.btnApropos);

        txtBudget = findViewById(R.id.txtBudget);

        btnAccueil.setOnClickListener(btnAccueilListen);
        btnRayons.setOnClickListener(btnRayonsListen);
        btnApropos.setOnClickListener(btnAproposListen);

        createList();
        buildRecyclerView();

        init();
    }

    public void init()
    {
        Log.i("DEBUG", "Page d'accueil");

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_USER);
        Log.i("DEBUG", text);

        System.out.println(LoadImageFromWebOperations("https://static.openfoodfacts.org/images/products/000/000/000/0017/front_fr.4.400.jpg"));

    }


    private View.OnClickListener btnAccueilListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Redirection vers l'accueil");
            openAccueil();
        }
    };

    private View.OnClickListener btnRayonsListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Redirection vers les rayons");
            openRayons();
        }
    };

    private View.OnClickListener btnAproposListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Redirection vers A Propos");
            openApropos();
        }
    };

    public void openAccueil()
    {
        Intent intent = new Intent(this, Accueil.class);
        startActivity(intent);
    }

    public void openRayons()
    {
        Intent intent = new Intent(this, Accueil.class);
        startActivity(intent);
    }

    public void  openApropos()
    {
        Intent intent = new Intent(this, Apropos.class);
        startActivity(intent);
    }

    public void getInformationItem(int position)
    {
        Log.i("DEBUG","position: " + mList.get(position).toString());
        String text = mList.get(position).toString();
        Intent intent = new Intent(this, Apropos.class);
        intent.putExtra(EXTRA_POSITION, text);
        startActivity(intent);
    }

    public void createList()
    {
        mList = new ArrayList<>();
        mList.add(new Item(R.drawable.cocacola, "Coca Cola", "1☆"));
        mList.add(new Item(R.drawable.monster,"Monster", "1☆"));
        mList.add(new Item(R.drawable.cocacola, "Coca Cola", "2☆"));
        mList.add(new Item(R.drawable.monster,"Monster", "2☆"));
        mList.add(new Item(R.drawable.cocacola, "Coca Cola", "3☆"));
        mList.add(new Item(R.drawable.monster,"Monster", "3☆"));
        mList.add(new Item(R.drawable.cocacola, "Coca Cola", "4☆"));
        mList.add(new Item(R.drawable.monster,"Monster", "4☆"));

    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
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