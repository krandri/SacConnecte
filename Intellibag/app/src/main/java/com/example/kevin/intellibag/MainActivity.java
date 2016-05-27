package com.example.kevin.intellibag;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lstFunc);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        TextView txtDate = (TextView)findViewById(R.id.txtDate);
        txtDate.setText(formattedDate);


        //Vérifier que le bluetooth est déclenché
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
        {
            Toast.makeText(MainActivity.this, "Votre appareil ne possède pas le bluetooth.", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (!mBluetoothAdapter.isEnabled()) {
                Toast.makeText(MainActivity.this, "Votre appareil n'est pas connecté en bluetooth. Activation en cours..", Toast.LENGTH_LONG).show();
                mBluetoothAdapter.enable();
                Toast.makeText(MainActivity.this, "Votre appareil est maintenant connecté en bluetooth.", Toast.LENGTH_LONG).show();



            }
            else
            {
                Toast.makeText(MainActivity.this, "Votre appareil est  connecté en bluetooth.", Toast.LENGTH_LONG).show();
                //Traitement avec arduino

            }
        }
        afficherListeFonctions();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
              Toast.makeText(MainActivity.this, "Click sur l'item numero", Toast.LENGTH_LONG).show();
            }
        });

    }

    private List<Fonction> genererFonctions(){
        List<Fonction> fonctions = new ArrayList<Fonction>();
        fonctions.add(new Fonction("kilogram", "Poids:", 20));
        fonctions.add(new Fonction("footsteps_silhouette_variant", "Nombre de pas effectués:", 10));
        fonctions.add(new Fonction("drops", "Humidité ambiante:", 20));
        fonctions.add(new Fonction("thermometer", "Température:", 20));
        return fonctions;
    }

    private void afficherListeFonctions(){
        List<Fonction> fonctions = genererFonctions();

        FunctionsAdapter adapter = new FunctionsAdapter(MainActivity.this, fonctions);
        mListView.setAdapter(adapter);
    }
}
