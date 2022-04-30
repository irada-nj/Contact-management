package com.example.gestioncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MonAdapter extends BaseAdapter {
    Context con;
    ArrayList<Contact> d;
    public MonAdapter(Context con, ArrayList<Contact> d) {
        this.con=con;
        this.d=d;
    }

    @Override
    public int getCount() {
        //nbre des views à créer
        return d.size();
    }

    @Override
    public Contact getItem(int position) {
        //renvoie les données eli bch y7othom fi el view
        return d.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //kol donnee bch ye3ml view feha contact mel data
       // TextView tv=new TextView(con);
        //tv.setText(Accueil.data.get(position).toString());

        // (creer un view ou un layout à partir d'un fichier xml)==>
        //parsing code xml
        LayoutInflater inf=LayoutInflater.from(con);
        //recuperer l
        LinearLayout l= (LinearLayout) inf.inflate(R.layout.view_contact,null);
        TextView tvnom=l.findViewById(R.id.tvnom_view);// context mte3 view_contact hiya llinearlauot l
        TextView tvprenom=l.findViewById(R.id.tvprenom_view);
        TextView tvphone=l.findViewById(R.id.tvphone_view);
        //modification du contenu
        Contact c=getItem(position);
        tvnom.setText(c.nom);
        tvprenom.setText(c.prenom);
        tvphone.setText(c.numero);
        return l;
    }
}
