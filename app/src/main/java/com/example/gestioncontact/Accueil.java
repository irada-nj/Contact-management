package com.example.gestioncontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    static ArrayList<Contact> data=new ArrayList<Contact>();

    private TextView tvusername;
    private Button btnajout,btnaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        tvusername=findViewById(R.id.tvuser_acc);
        btnajout=findViewById(R.id.btnajout_acc);
        btnaff=findViewById(R.id.btnaff_acc);
        //import fichier
        String dir= Environment.getExternalStorageDirectory().getPath();
        File f=new File(dir,"fichier.txt");
        if(f.exists()){
            try {
                data.clear();
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String ligne=null;
                while((ligne=br.readLine())!=null){
                    String[] t=ligne.split("#");
                    Contact c=new Contact(t[0],t[1],t[2]);
                    data.add(c);
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent x=this.getIntent();// x est l'intente de l'accueil
        Bundle b=x.getExtras(); //stock des données kima hmap
        //String s=x.getStringExtra("USER");
        String u=b.getString("USER");
        tvusername.setText("Accueil de M."+u);
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Accueil.this,Ajout.class);
                startActivity(i);
            }
        });
        btnaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Accueil.this,Affichage.class);
                startActivity(i);
            }
        });
    }
    boolean permission_write=false;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "STARTED", Toast.LENGTH_SHORT).show();

        if(ContextCompat.checkSelfPermission(Accueil.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            permission_write=true;

        }
        else{
            permission_write=false;
            ActivityCompat.requestPermissions(Accueil.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }
    @Override
    protected void onStop() {
        Toast.makeText(this, "STOPED", Toast.LENGTH_SHORT).show();
        //sauvegarde
      if(permission_write==true){
           save_data();
       }
        super.onStop();
    }

    protected void save_data() {
        String dir= Environment.getExternalStorageDirectory().getPath();
        File f=new File(dir,"fichier.txt");
        try {
            FileWriter fw=new FileWriter(f,false);
            BufferedWriter bw=new BufferedWriter(fw);
            for(int i=0;i<data.size();i++){
                bw.write(data.get(i).nom+"#"+data.get(i).prenom+"#"+data.get(i).numero+"\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(this, "DESTROYED", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, //liste des permissions eli tlebethom
                                           @NonNull int[] grantResults) {//permission garantie
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                permission_write=true;
            }
            else{
                permission_write=false;
            }
        }
    }
}