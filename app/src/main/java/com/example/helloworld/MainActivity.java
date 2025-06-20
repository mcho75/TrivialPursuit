package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Bouton bouton;
    private ConstraintLayout layout;
    private ConstraintLayout.LayoutParams params;
    private RequestQueue requestQueue;
    private TrivialResponseListener responseListener;
    private TrivialErrorListener errorListener;
    private StringRequest request;
    private JSONArray cartes;

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
        progressBar.setProgress(33);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        responseListener = new TrivialResponseListener();
        errorListener = new TrivialErrorListener();
        request = new StringRequest(Request.Method.GET, "https://opentdb.com/api.php?amount=10&type=multiple", responseListener, errorListener);
        requestQueue.add(request);
        progressBar.setProgress(66);

        cartes = responseListener.getCartes();

        bouton = new Bouton(this, false, this::lancer);
        bouton.setText("Nouvelle partie");
        progressBar.setProgress(100);

        new CountDownTimer(200, 200) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
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
                System.out.println("Bouton affiché");
            }

        }.start();
    }

    public void lancer(View view) {
        System.out.println("Hello world");
        startActivity(new Intent(this, Questionnaire.class));
    }
}