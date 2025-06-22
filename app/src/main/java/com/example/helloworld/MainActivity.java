package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Bouton bouton;
    private ConstraintLayout layout;
    private int nb_questions = 10;
    private ConstraintLayout.LayoutParams params;
    private Quiz q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setNavigationBarColor(getResources().getColor(R.color.palette5));
        layout = findViewById(R.id.main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        TrivialResponseListener responseListener = new TrivialResponseListener(this, nb_questions);
        TrivialErrorListener errorListener = new TrivialErrorListener();
        StringRequest request = new StringRequest(Request.Method.GET,
                                    "https://opentdb.com/api.php?amount="+String.valueOf(nb_questions),
                                    responseListener, errorListener);
        progressBar.setProgress(50);
        requestQueue.add(request);
    }

    public void apres_importation(Quiz q2) {
        progressBar.setProgress(100);
        q = q2;
        bouton = new Bouton(this, false, this::lancer);
        bouton.setText("Nouvelle partie");
        layout.removeView(progressBar);
        layout.addView(bouton);
        params = (ConstraintLayout.LayoutParams)bouton.getLayoutParams();
        params.topToTop = R.id.main;
        params.bottomToBottom = R.id.main;
        params.startToStart = R.id.main;
        params.endToEnd = R.id.main;
        params.verticalBias = 0.6f;
        params.width = GridLayout.LayoutParams.MATCH_PARENT;
        params.setMargins(100, 20, 100, 20);
        bouton.setLayoutParams(params);
        bouton.zoom_apparition();
    }

    public void lancer(View view) {
        System.out.println("Hello world");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ois = new ObjectOutputStream(baos);
            ois.writeObject(q);
            String quiz_string = Base64.getEncoder().encodeToString(baos.toByteArray());
            Intent i = new Intent(this, Questionnaire.class);
            i.putExtra("quiz", quiz_string);
            startActivity(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}