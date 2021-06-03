package com.example.tpmobile;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestApi extends AsyncTask<String, Void, String> {

    Context context;


    public CallRestApi(Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try{
            url = new URL(urls[0]); // se pointer sur un url
            urlConnection = (HttpURLConnection) url.openConnection(); // ouvrir la connection

            InputStream in = urlConnection.getInputStream(); // ouvre le buffer ex: bus
            InputStreamReader reader = new InputStreamReader(in); //permet de lire les infos  ex : les gens qui monte dans le bus, chacun ont une info
//            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream()); version clean code

            int data = reader.read();
            while (data != -1){ // tant qu'il y a tel que chose a lire data retourne autre chose que -1
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (Exception e){
            e.printStackTrace();
            Log.i("Error", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String str){
        super.onPostExecute(str);

        try{


        }catch (Exception e){
            Log.i("Error", e.getMessage());
        }
    }
}
