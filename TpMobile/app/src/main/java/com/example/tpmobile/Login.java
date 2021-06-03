package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    EditText courriel;
    EditText mdp;
    TextView messageErreur;
    String reponse;
    static JSONObject jsonObjectCitoyen;
    Button buttonLogin;
    Button buttonSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        courriel = findViewById(R.id.editTextPersonCourriel);
        mdp = findViewById(R.id.editTextMdp);
        messageErreur = findViewById(R.id.messageErreur);
        buttonLogin = findViewById(R.id.buttonConnection);
        buttonSubscribe = findViewById(R.id.buttonSignIn);
    }

    public void onLogin(View view) throws InterruptedException, ExecutionException, JSONException {
        CallRestApi callRestApi = new CallRestApi(getApplicationContext());
        callRestApi.execute("http://10.0.2.2:2021/permisSante/login/" + courriel.getText().toString() + "/" + mdp.getText().toString());
        reponse = callRestApi.get();
        if(reponse != null){
            if(reponse.length() > 0){
                jsonObjectCitoyen = new JSONObject(reponse);
                DemandePermis.nassm = jsonObjectCitoyen.getString("numeroAssuranceSocial");
                startActivity(new Intent(Login.this, Dashboard.class));
            } else {
                messageErreur.setVisibility(View.VISIBLE);
            }
        }
    }

    public void signOnClicked(View view){
        startActivity(new Intent(Login.this, DemandePermis.class));
    }
}