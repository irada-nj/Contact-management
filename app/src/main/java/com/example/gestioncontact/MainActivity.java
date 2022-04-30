package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    //declaration des composants
    EditText ednom,edmp;
    private Button btnval;
    private Button btnqte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recuperation des composants
        ednom=findViewById(R.id.ednom_auth);
        edmp=findViewById(R.id.edmp_auth);
        btnval=findViewById(R.id.btnval_auth);
        btnqte=findViewById(R.id.btnqte_auth);
        //gestion des evenements
       // btnqte.setOnClickListener(this);
       // btnval.setOnClickListener(this);
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=ednom.getText().toString();
                String mp=edmp.getText().toString();
                if(nom.equalsIgnoreCase("test") && mp.equals("000")){
                    Intent i=new Intent(MainActivity.this,Accueil.class);
                    i.putExtra("USER",nom);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Valeur non valide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}