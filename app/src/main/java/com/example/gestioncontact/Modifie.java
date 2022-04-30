package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modifie extends AppCompatActivity {

    private EditText ednom_modifie;
    private EditText edprenom_modifie;
    private EditText edphone_modifie;
    private Button btnval_modifie,btnqte_modifie;
    int index_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifie);
        ednom_modifie=findViewById(R.id.ednom_modifie);
        edprenom_modifie=findViewById(R.id.edprenom_modifie);
        edphone_modifie=findViewById(R.id.edphone_modifie);
        btnval_modifie=findViewById(R.id.btnvalide_modifie);
        btnqte_modifie=findViewById(R.id.btnqte_modifie);

        Intent x=this.getIntent();
        Bundle b=x.getExtras();
        index_data=b.getInt("indice");
        Contact c;
        c=Accueil.data.get(index_data);
        ednom_modifie.setText(c.getNom());
        edprenom_modifie.setText(c.getPrenom());
        edphone_modifie.setText(c.getNumero());

        btnqte_modifie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modifie.this.finish();
            }
        });
        btnval_modifie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=ednom_modifie.getText().toString();
                String p=edprenom_modifie.getText().toString();
                String num=edphone_modifie.getText().toString();
                if(!(n.equals("")) && !(p.equals("")) && !(num.equals(""))){
                    Contact con=new Contact(n,p,num);
                    Accueil.data.set(index_data,con);
                    Intent i=new Intent(Modifie.this,Affichage.class);
                    startActivity(i);
                    Modifie.this.finish();
                }
                else if(n.equals("")){
                        Toast.makeText(Modifie.this, "Il faut remplir le nom ! ", Toast.LENGTH_LONG).show();
                    }
                else if(p.equals("")){
                        Toast.makeText(Modifie.this, "Il faut remplir le prenom ! ", Toast.LENGTH_LONG).show();
                    }
                else if(num.equals("")){
                        Toast.makeText(Modifie.this, "Il faut remplir le numero! ", Toast.LENGTH_SHORT).show();
                    }
                else{
                        Toast.makeText(Modifie.this, "Erreur !!! ", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
}