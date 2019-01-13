package com.efrei.coursorama.projetpa8;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    private Button btnAccueil;
    private Button btnRayons;
    private Button btnApropos;
    private EditText txtBudget;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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

        ArrayList<Item> exampleList = new ArrayList<>();
        exampleList.add(new Item(R.drawable.ic_android, "Line 1", "Line 2"));
        exampleList.add(new Item(R.drawable.ic_add_alarm,"Line 3", "Line 4"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        init();
    }

    public void init()
    {
        Log.i("DEBUG", "Page d'accueil");

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_USER);
        Log.i("DEBUG", text);

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



}