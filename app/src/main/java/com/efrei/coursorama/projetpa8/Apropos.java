package com.efrei.coursorama.projetpa8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Apropos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropos);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Accueil.EXTRA_POSITION);

        TextView textView1 = (TextView) findViewById(R.id.textview1);

        textView1.setText(text);
    }
}
