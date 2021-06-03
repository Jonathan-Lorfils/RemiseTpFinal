package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CreerCitoyen extends AppCompatActivity {

    EditText mdpEditText;
    EditText ageEditText;
    EditText prenomEditText;
    EditText nomEditText;
    EditText typePermisEditText;
    Button buttonConfirmation;
    TextView messageErreur;

    String nassm;
    String nom;
    String prenom;
    int age;
    String courriels;
    String mdp;
    String typePermis;
    String reponseWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typePermisEditText = findViewById(R.id.editTextTypePermis);
        prenomEditText =  findViewById(R.id.editTextNom);
        nomEditText =  findViewById(R.id.editTextPrenom);
        mdpEditText = findViewById(R.id.editTextMdp);
        ageEditText = findViewById(R.id.editTexAge);
        messageErreur = findViewById(R.id.messageErreur);
        buttonConfirmation = findViewById(R.id.buttonValiderInfo);
    }

    public void confirmation(View view) throws ExecutionException, InterruptedException, JSONException {
        recolterInfo();
        CallRestApi callRestApi = new CallRestApi(getApplicationContext());
        if((typePermis.toUpperCase().equals("TEST") || typePermis.toUpperCase().equals("VACCIN"))){
            callRestApi.execute("http://10.0.2.2:2021/permisSante/" + nassm + "/"+ nom +"/"+ prenom +"/"+ age +"/" + courriels + "/" + mdp + "/" + typePermis);
            reponseWS = callRestApi.get();
            if(reponseWS != null){
                if(reponseWS.length() > 0){
                    Login.jsonObjectCitoyen = new JSONObject(reponseWS);
                    startActivity(new Intent(CreerCitoyen.this, Dashboard.class));
                } else {
                    messageErreur.setVisibility(View.VISIBLE);
                }
            }
        } else {
            messageErreur.setVisibility(View.VISIBLE);
        }
    }

    public void recolterInfo() {
        nassm = DemandePermis.nassm;
        nom = nomEditText.getText().toString();
        prenom = prenomEditText.getText().toString();
        age = Integer.parseInt(ageEditText.getText().toString());
        courriels = DemandePermis.courriel;
        mdp = mdpEditText.getText().toString();
        typePermis = typePermisEditText.getText().toString();
    }
}