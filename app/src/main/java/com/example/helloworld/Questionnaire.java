package com.example.helloworld;

import java.util.Random;
import java.util.Vector;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Questionnaire extends AppCompatActivity {

    int numero_question;
    int score;
    boolean premier_essai;
    Quiz quiz;
    GridLayout grille;
    TextView texte;
    Vector<Bouton> boutons;
    Vector<String> mauvaises_reponses;
    int nb_mauvaises_reponses;
    GridLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        score = 0;
        numero_question = -1;
        grille = findViewById(R.id.grille);
        texte = findViewById(R.id.intitule);
        quiz = new Quiz();
        this.reponse_suivante();
    }

    public void verifier_reponse(View b) {
        Bouton b2 = (Bouton)b;
        if (b2.est_correct()) {
            Toast.makeText(this, "Réponse correcte", Toast.LENGTH_SHORT).show();
            score++;
            if (premier_essai) {
                score++;
            }
            for (Bouton bou: boutons) {
                grille.removeView(bou);
            }
            this.reponse_suivante();
        } else {
            if (premier_essai) {
                Toast.makeText(this, "Réponse incorrecte", Toast.LENGTH_SHORT).show();
                premier_essai = false;
            }
            else {
                for (Bouton bou: boutons) {
                    grille.removeView(bou);
                }
                this.reponse_suivante();
            }
        }
    }

    public void melanger(int[] tab) {
        Random r2 = new Random();
        for (int i = tab.length - 1; i > 0; i--) {
            int j = r2.nextInt(i + 1);
            int temp = tab[i];
            tab[i] = tab[j];
            tab[j] = temp;
        }
    }

    public int[] range(int n) {
        int[] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = i;
        }
        return tab;
    }

    public void reponse_suivante() {
        if (numero_question == quiz.getNbCartes() - 1) {
            System.out.println(score);
            Intent i = new Intent(this, Resultat.class);
            i.putExtra("score", score);
            i.putExtra("total", quiz.getNbCartes()*2);
            startActivity(i);
            finish();
            return;
        }
        premier_essai = true;
        numero_question++;
        texte.setText(quiz.getCarte(numero_question).getQuestion());
        mauvaises_reponses = quiz.getCarte(numero_question).getMauvaisesReponses();
        boutons = new Vector<Bouton>();
        nb_mauvaises_reponses = mauvaises_reponses.size();
        int[] positions = range(nb_mauvaises_reponses+1);
        this.melanger(positions);
        for (int i = 0; i < nb_mauvaises_reponses+1; i++) {
            boutons.add(new Bouton(this, true, this::verifier_reponse));
            boutons.get(i).setHeight(100);
            if (i == nb_mauvaises_reponses) {
                boutons.get(i).setText(quiz.getCarte(numero_question).getBonneReponse());
                boutons.get(i).definir_correction(true);
            } else {
                boutons.get(i).setText(mauvaises_reponses.get(i));
                boutons.get(i).definir_correction(false);
            }
            grille.addView(boutons.get(i));
            params = (GridLayout.LayoutParams) boutons.get(i).getLayoutParams();
            params.rowSpec = GridLayout.spec(i);
            params.columnSpec = GridLayout.spec(0);
            params.width = GridLayout.LayoutParams.MATCH_PARENT;
            params.setMargins(100, 20, 100, 20);
            boutons.get(i).setLayoutParams(params);
        }
    }

    public void chercher_en_ligne(View view) {
        Intent recherche = new Intent(Intent.ACTION_WEB_SEARCH);
        recherche.putExtra(SearchManager.QUERY, quiz.getCarte(numero_question).getQuestion());
        if (recherche.resolveActivity(getPackageManager()) != null) {
            startActivity(recherche);
        }
        else {
            System.out.println("Pas d'application trouvée");
        }
    }
}