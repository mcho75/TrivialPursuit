package com.example.helloworld;

import java.io.Serializable;
import java.util.Vector;

public class Quiz implements Serializable {

    private Vector<Carte> cartes;

    public Quiz() {
        cartes = new Vector<Carte>();
    }

    public void ajouterCarte(Carte c) {
        cartes.add(c);
    }

    public Vector<Carte> getCartes() {
        return cartes;
    }

    public int getNbCartes() {
        return cartes.size();
    }

    public Carte getCarte(int index) {
        return cartes.get(index);
    }
}