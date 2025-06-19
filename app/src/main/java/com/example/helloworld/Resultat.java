package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Resultat extends AppCompatActivity {

    int score;
    int total;
    TextView score_joueur;
    TextView nom_joueur;
    TextView score_global;
    TextView nom_global;
    EditText et;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    int meilleur_score;
    String meilleur_joueur;
    final String CLE_SCORE = "MeilleurScore";
    final String CLE_JOUEUR = "MeilleurJoueur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat);

        score_joueur = findViewById(R.id.score_perso);
        nom_joueur = findViewById(R.id.joueur_perso);
        score_global = findViewById(R.id.score_global);
        nom_global = findViewById(R.id.joueur_global);
        et = findViewById(R.id.et_record);

        Intent i = getIntent();
        score = i.getIntExtra("score", 0);
        total = i.getIntExtra("total", 0);
        sharedPref = getSharedPreferences("meilleurs_scores", Context.MODE_PRIVATE);
        meilleur_score = sharedPref.getInt(CLE_SCORE, 0);
        meilleur_joueur = sharedPref.getString(CLE_JOUEUR, "Personne");

        score_joueur.setText(String.valueOf(score) + " / " + String.valueOf(total));
        nom_joueur.setText("Anonyme");
        score_global.setText(String.valueOf(meilleur_score) + " / " + String.valueOf(total));
        nom_global.setText(meilleur_joueur);

        if (score <= meilleur_score) {
            ConstraintLayout layout = findViewById(R.id.resultat);
            layout.removeView(findViewById(R.id.bouton_record));
            layout.removeView(findViewById(R.id.et_record));
            layout.removeView(findViewById(R.id.tv_record));
        }
    }

    public void sauvegarder_score(View view) {
        editor = sharedPref.edit();
        editor.putInt(CLE_SCORE, score);
        editor.putString(CLE_JOUEUR, et.getText().toString());
        editor.apply();
        score_global.setText(String.valueOf(score) + " / " + String.valueOf(total));
        nom_global.setText(et.getText().toString());
    }
}
