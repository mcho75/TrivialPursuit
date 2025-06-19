package com.example.helloworld;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class Bouton extends AppCompatButton {

    private boolean est_correct;

    public Bouton(android.content.Context context, View.OnClickListener fonction) {
        super(context);
        setTextColor(getResources().getColor(R.color.palette5));
        setAllCaps(false);
        setBackgroundResource(R.drawable.bouton_carre);
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