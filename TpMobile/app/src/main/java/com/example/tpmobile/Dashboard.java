package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Dashboard extends AppCompatActivity {

    JSONObject jsonCitoyen;
    TextView messageReussite;
    TextView messageEchec;
    TextView messageBienvenue;
    Button buttonRenouvellement;
    int id;
    String typePermis;
    String reponseWS;
    String nom;
    String prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        messageEchec = findViewById(R.id.messageErreur);
        messageReussite = findViewById(R.id.messageReussite);
        buttonRenouvellement = findViewById(R.id.buttonRenouvellerPermis);
        messageBienvenue = findViewById(R.id.messageBienvenue);

        ImageView codeQr = findViewById(R.id.codeQr);

        String url = "http://10.0.2.2:2021/permisSante/qrCode/" + DemandePermis.nassm;

        Picasso.get().load(url).resize(900,900).into(codeQr);

        recolterInformations();

        messageBienvenue.setText("Bonjour " + nom + " " + prenom);
    }

    public void renouvellerPermis(View view) throws ExecutionException, InterruptedException {
        buttonRenouvellement.setVisibility(View.INVISIBLE);
        CallRestApi callRestApi = new CallRestApi(getApplicationContext());
        callRestApi.execute("http://10.0.2.2:2021/permisSante/renouveller/" + id + "/" + typePermis);
        reponseWS = callRestApi.get();
        if(reponseWS != null){
            if(reponseWS.equals("true")){
                messageReussite.setVisibility(View.VISIBLE);
            } else {
                messageEchec.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void recolterInformations() {
        try {
            jsonCitoyen = Login.jsonObjectCitoyen;
            id = Integer.parseInt(jsonCitoyen.getString("id"));
            typePermis = jsonCitoyen.getString("typePermis");
            nom = jsonCitoyen.getString("nom");
            prenom = jsonCitoyen.getString("prenom");
            if(typePermis.toUpperCase().equals("VACCIN")){
                buttonRenouvellement.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}