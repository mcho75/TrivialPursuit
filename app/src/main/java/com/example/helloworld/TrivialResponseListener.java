package com.example.helloworld;

import android.content.Context;
import android.text.Html;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrivialResponseListener implements Response.Listener<String> {

    private JSONObject reponseJSON;
    private JSONArray cartes;
    private MainActivity activite;
    private Quiz q;
    private Carte c;
    private JSONObject cJSON;
    private JSONArray mauvaises_reponses;
    private int nb_questions;

    public TrivialResponseListener(MainActivity context, int n) {
        nb_questions = n;
        activite = context;
    }

    @Override
    public void onResponse(String response) {
        try {
            System.out.println("Contenu : " + response);
            reponseJSON = new JSONObject(response);
            cartes = reponseJSON.getJSONArray("results");
            q = new Quiz();
            for (int i = 0; i < nb_questions; i++) {
                c = new Carte();
                cJSON = cartes.getJSONObject(i);
                c.setQuestion(Html.fromHtml(cJSON.getString("question"), Html.FROM_HTML_MODE_LEGACY).toString());
                c.setBonneReponse(Html.fromHtml(cJSON.getString("correct_answer"), Html.FROM_HTML_MODE_LEGACY).toString());
                mauvaises_reponses = cJSON.getJSONArray("incorrect_answers");
                for (int j = 0; j < mauvaises_reponses.length(); j++) {
                    c.addMauvaiseReponse(Html.fromHtml(mauvaises_reponses.getString(j), Html.FROM_HTML_MODE_LEGACY).toString());
                }
                q.ajouterCarte(c);
            }
        }
        catch (JSONException e) {
            System.out.println("Erreur lors de l'extraction");
            throw new RuntimeException(e);
        }
        activite.apres_importation(q);
    }
}
