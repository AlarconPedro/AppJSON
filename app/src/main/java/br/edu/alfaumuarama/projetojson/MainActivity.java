package br.edu.alfaumuarama.projetojson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ListActivity {

    ArrayList<Emissora> listaEmissora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String link = "http://www.marcosdiasvendramini.com.br/Get/Estereogramas.aspx";
            listaEmissora = new BuscaDados().execute(link).get();

            ListAdapter adapter = new SimpleAdapter(this, getLista(),
                    R.layout.activity_list_view_emissora,
                    new String[] {"nome"},
                    new int[] {R.id.lblNome}
            );

            setListAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<HashMap <String, String>> getLista() {
        ArrayList<HashMap<String, String>> listaRetorno = new ArrayList<>();

        for (int i = 0; i < listaEmissora.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("nome", String.valueOf(listaEmissora.get(i).nome));
            item.put("cod", String.valueOf(listaEmissora.get(i).codigo));

            listaRetorno.add(item);
        }

        return listaRetorno;
    }
}