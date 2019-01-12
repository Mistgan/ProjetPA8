package com.efrei.coursorama.projetpa8;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private EditText txtusr;
    private EditText txtpw;
    private Button btnConnexion;
    private Button btnNew;
    private HashMap Login = new HashMap<>();
    private Boolean test_id = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusr = findViewById(R.id.txtusr);
        txtpw = findViewById(R.id.txtpw);
        btnConnexion = findViewById(R.id.btnConnexion);
        btnNew = findViewById(R.id.btnNew);

        btnConnexion.setOnClickListener(btnConnexionListen);
        btnNew.setOnClickListener(btnNewListener);



        init();

    }

    public void init()
    {
        Login.put("admin", "admin");
        Login.put("tamere","lapute");
        Login.put("tamere","enslip");
        return;
    }

    private View.OnClickListener btnConnexionListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Connexion en cours");
            switch (txtusr.getText().toString())
            {
                case "vip":
                    Log.i("DEBUG", "L'administrateur a voulu se connecter");
                    if (txtpw.getText().toString().equals("admin"))
                        //setContentView(R.layout.activity_accueil);
                        Log.i("DEBUG", "L'administrateur est connecter");
                break;
                default:
                    if (Login.containsKey(txtusr.getText().toString()))
                    {
                        Log.i("DEBUG", txtusr.getText().toString()+" a voulu se connecter");
                        Log.i("DEBUG", "Login user/password: "+Login.get(txtusr.getText().toString()));
                        if (Login.get(txtusr.getText().toString()).equals(txtpw.getText().toString()))
                        {
                            Log.i("DEBUG", txtusr.getText().toString()+" s'est bien connecter");
                            setContentView(R.layout.activity_accueil);
                        }
                        else
                        {
                            Log.i("DEBUG", "Une erreur s'est produite");
                        }
                    }
                    else
                        Log.i("DEBUG", "Dsl je ne t'ai pas trouvé dans la bdd");
            }
        }
    };

    private View.OnClickListener btnNewListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if ( !txtusr.getText().toString().isEmpty() && !txtpw.getText().toString().isEmpty() ) {
                if (Login.containsKey(txtusr.getText().toString())) {
                        test_id = true;
                }
                if (test_id == true)
                {
                    Log.i("DEBUG", "L'identifiant est déjà utilisé");
                }
                if (test_id == false)
                {
                    Login.put(txtusr.getText().toString(),txtpw.getText().toString());
                }
            }
        }
    };



}
