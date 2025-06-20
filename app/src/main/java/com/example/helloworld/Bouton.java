package com.example.helloworld;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class Bouton extends AppCompatButton {

    private boolean est_correct;

    public Bouton(android.content.Context context, boolean couleurPrimaire, View.OnClickListener fonction) {
        super(context);
        setTextColor(getResources().getColor(R.color.palette5));
        setAllCaps(false);
        if (couleurPrimaire) {
            setBackgroundResource(R.drawable.bouton_carre);
        }
        else {
            setBackgroundResource(R.drawable.bouton_carre_2);
        }
        setTypeface(getResources().getFont(R.font.delagothicone));
        setOnClickListener(fonction);
    }

    public void definir_correction(boolean valeur) {
        this.est_correct = valeur;
    }

    public boolean est_correct() {
        return this.est_correct;
    }
}