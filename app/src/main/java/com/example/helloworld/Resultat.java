package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultat extends AppCompatActivity {

    int score;
    TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat);
        Intent i = getIntent();
        score = i.getIntExtra("score", 0);
        texte = findViewById(R.id.affichage_resultat);
        texte.setText(String.valueOf(score) + " points");
    }
}
