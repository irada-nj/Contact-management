package com.example.gestioncontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Affichage extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private EditText edrech;
    private ListView lv_affiche;
    //ArrayAdapter adapter;
    MonAdapter adapter,adapter_rech;
    ArrayList<Contact> rech_data=new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        edrech=findViewById(R.id.edrech_affichage);
        lv_affiche=findViewById(R.id.lv_affichage);
        //ArrayAdapter adapter =new ArrayAdapter(Affichage.this, android.R.layout.simple_list_item_1,Accueil.data);
        adapter=new MonAdapter(Affichage.this,Accueil.data);
        lv_affiche.setAdapter(adapter);
        lv_affiche.setOnItemClickListener(this);//eli fi west parenthese dima de type listener eli nedineh

        edrech.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Affichage.this.rech_data.clear();
                s=edrech.getText();
                String rech=s.toString();
                if(!(rech.equals(""))){
                    for(int i=0;i<Accueil.data.size();i++){
                        Contact c=Accueil.data.get(i);
                        if((c.getNom().startsWith(rech)) || (c.getPrenom().startsWith(rech))){
                            Affichage.this.rech_data.add(c);
                        }
                    }
                    adapter_rech=new MonAdapter(Affichage.this,Affichage.this.rech_data);
                    lv_affiche.setAdapter(adapter_rech);
                    lv_affiche.invalidateViews();
                }
                else {

                    lv_affiche.setAdapter(adapter);
                    Affichage.this.rech_data.clear();
                    lv_affiche.invalidateViews();
                }
            }
        });
    }
    int indice;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       indice=position;
        if (!rech_data.isEmpty()) {
            indice=Accueil.data.indexOf(rech_data.get(position));
        }
        AlertDialog.Builder alert=new AlertDialog.Builder(Affichage.this);
        alert.setTitle("Attention...");
        alert.setMessage("Veuillez choisir une action:");
        alert.setPositiveButton("Supprimer",this);
        alert.setNegativeButton("Modifier",this);
        alert.setNeutralButton("Supprimer tous",this);
        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
       if(which==dialog.BUTTON_POSITIVE){
            Accueil.data.remove(indice);
           lv_affiche.invalidateViews();
       }
       if(which==dialog.BUTTON_NEGATIVE){
         //tetelansa activity jdida trois edit text m3ebyin b le elts te3 contact  eli nzelt 3lih w 2 boutons valid w quitter
           Intent i=new Intent(Affichage.this,Modifie.class);
           i.putExtra("indice",indice);
           startActivity(i);
           Affichage.this.finish();
       }
       if(which==dialog.BUTTON_NEUTRAL){
          Accueil.data.clear();
           lv_affiche.invalidateViews(); //refraichissement de la liste view apres suppression
       }
    }


}