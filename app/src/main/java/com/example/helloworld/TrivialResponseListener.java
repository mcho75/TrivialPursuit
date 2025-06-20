package com.example.helloworld;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrivialResponseListener implements Response.Listener<String> {

    private JSONObject reponseJSON;
    private JSONArray cartes;

    @Override
    public void onResponse(String response) {
        try {
            reponseJSON = new JSONObject(response);
            cartes = reponseJSON.getJSONArray("results");
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getCartes() {
        return cartes;
    }
}
