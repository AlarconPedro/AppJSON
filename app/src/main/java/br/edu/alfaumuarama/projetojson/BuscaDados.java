package br.edu.alfaumuarama.projetojson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscaDados extends AsyncTask <String, Void, ArrayList<Emissora>> {

    @Override
    protected ArrayList<Emissora> doInBackground(String... parametros) {
        ArrayList<Emissora> listaRetorno = new ArrayList<>();

        String link = parametros[0];

        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader buferedReader = new BufferedReader(inputStream);

            String linha;

            while ((linha = buferedReader.readLine()) != null) {
                JSONArray ja = new JSONArray(linha);

                for (int i = 0; i < ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);

                    Emissora emissora = new Emissora();
                    emissora.nome   = jo.getString("nome");
                    emissora.logo   = jo.getString("img");
                    emissora.codigo = jo.getInt("cod");
                    emissora.logo = emissora.logo.replace("~/", "http://controle.mdvsistemas.com.br/");

                    listaRetorno.add(emissora);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaRetorno;
    }
}
