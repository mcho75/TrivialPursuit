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

    public void changer_bg() {
        if (est_correct) {
            setBackgroundResource(R.drawable.bouton_carre);
        }
        else {
            setBackgroundResource(R.drawable.bouton_carre_3);
        }
    }

    public void zoom_apparition() {
        setScaleX(0.5f);
        setScaleY(0.5f);
        animate().scaleX(1f);
        animate().scaleY(1f);
        animate().setDuration(200);
        animate().setInterpolator(new android.view.animation.OvershootInterpolator());
        animate().start();
    }

    public void zoom_disparition() {
        setScaleX(1f);
        setScaleY(1f);
        animate().scaleX(0.5f);
        animate().scaleY(0.5f);
        animate().setDuration(200);
        animate().setInterpolator(new android.view.animation.AnticipateInterpolator());
        animate().start();
    }
}