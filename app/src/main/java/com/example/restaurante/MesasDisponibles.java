package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MesasDisponibles extends AppCompatActivity {
    public ArrayList<ImageView> imagenes = new ArrayList<>();
    int disponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas_disponibles);
        ImageView mesares = findViewById(R.id.ejemploRes);
        mesares.setColorFilter(Color.RED);
        ImageView mesa1 = findViewById(R.id.mesa1);
        ImageView mesa2 = findViewById(R.id.mesa2);
        ImageView mesa3 = findViewById(R.id.mesa3);
        ImageView mesa4 = findViewById(R.id.mesa4);
        ImageView mesa5 = findViewById(R.id.mesa5);
        ImageView mesa6 = findViewById(R.id.mesa6);

        imagenes.add(mesa1);
        imagenes.add(mesa2);
        imagenes.add(mesa3);
        imagenes.add(mesa4);
        imagenes.add(mesa5);
        imagenes.add(mesa6);
        lecturaFirebase();
        if (disponibles==0){
            Toast.makeText(this,"No hay mesas Disponibles",Toast.LENGTH_LONG);
        }
    }

    public void lecturaFirebase() {
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cont = 0;
                disponibles=0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Dispositivo dispositivo = data.getValue(Dispositivo.class);
                    if (dispositivo.getEstado().equals("DE")) {
                        imagenes.get(cont).setColorFilter(null);
                        disponibles++;
                    }
                    if (dispositivo.getEstado().equals("OC")) {
                        imagenes.get(cont).setColorFilter(Color.RED);
                    }
                    cont++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
