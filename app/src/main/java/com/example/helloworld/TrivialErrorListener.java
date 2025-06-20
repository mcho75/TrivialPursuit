package com.example.helloworld;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class TrivialErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Erreur lors du téléchargement");
    }
}
