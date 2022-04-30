package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Ajout extends AppCompatActivity {

    private EditText ednom,edprenom,edphone;
    private Button btnajout;
    private Button btnqte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        ednom=findViewById(R.id.ednom_ajout);
        edprenom=findViewById(R.id.edprenom_ajout);
        edphone=findViewById(R.id.edphone_ajout);
        btnajout=findViewById(R.id.btnvalide_ajout);
        btnqte=findViewById(R.id.btnqte_ajout);
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ajout.this.finish();
            }
        });
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=ednom.getText().toString();
                String p=edprenom.getText().toString();
                String num=edphone.getText().toString();
                Contact c=new Contact(n,p,num);
                Accueil.data.add(c);
            }
        });
    }
}