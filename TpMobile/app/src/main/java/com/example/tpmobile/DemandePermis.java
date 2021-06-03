package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;

public class DemandePermis extends AppCompatActivity {

    EditText editTextNassmDP;
    EditText editTextCourrielDP;
    TextView messageErreur;

    static String nassm;
    static String courriel;

    String reponseWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_permis);

        messageErreur = findViewById(R.id.messageErreur);
        editTextNassmDP = findViewById(R.id.editTextNassmDP);
        editTextCourrielDP = findViewById(R.id.editTextCourrielDP);
    }

    public void ValiderInfo(View view) throws InterruptedException, ExecutionException {
        CallRestApi callRestApi = new CallRestApi(getApplicationContext());
        callRestApi.execute("http://10.0.2.2:9393/ministere/" +editTextNassmDP.getText().toString());
        reponseWS = callRestApi.get();
        if(reponseWS != null){
            if(reponseWS.equals("true")){
                courriel = editTextCourrielDP.getText().toString();
                nassm = editTextNassmDP.getText().toString();
                startActivity(new Intent(DemandePermis.this, CreerCitoyen.class));
            } else {
                messageErreur.setVisibility(View.VISIBLE);
            }
        } else {
            messageErreur.setVisibility(View.VISIBLE);
        }
    }
}