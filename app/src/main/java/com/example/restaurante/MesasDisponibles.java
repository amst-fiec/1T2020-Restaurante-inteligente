package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas_disponibles);
        lecturaFirebase1();
        lecturaFirebase2();
        lecturaFirebase3();
        lecturaFirebase4();
        lecturaFirebase5();
        lecturaFirebase6();
        ImageView mesares=findViewById(R.id.ejemploRes);
        mesares.setColorFilter(Color.RED);
    }//Necesita aun Notificaciones
    public void lecturaFirebase1(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa1 = findViewById(R.id.mesa1);
                    String estado = dataSnapshot.child("device1").child("estado").getValue().toString();
                    System.out.println("La mesa 1 tiene un estado de: "+estado);
                    if (estado.equals("DE")) {
                        mesa1.setColorFilter(null);
                    }
                    if (estado.equals("OC")){
                        mesa1.setColorFilter(Color.RED);
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void lecturaFirebase2(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa2 = findViewById(R.id.mesa2);
                String estado = dataSnapshot.child("device2").child("estado").getValue().toString();
                System.out.println("La mesa 2 tiene un estado de: "+estado);
                if (estado.equals("DE")) {
                    mesa2.setColorFilter(null);
                }
                if (estado.equals("OC")){
                    mesa2.setColorFilter(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void lecturaFirebase3(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa3 = findViewById(R.id.mesa3);
                String estado = dataSnapshot.child("device3").child("estado").getValue().toString();
                System.out.println("La mesa 3 tiene un estado de: "+estado);
                if (estado.equals("DE")) {
                    mesa3.setColorFilter(null);
                }
                if (estado.equals("OC")){
                    mesa3.setColorFilter(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void lecturaFirebase4(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa4 = findViewById(R.id.mesa4);
                String estado = dataSnapshot.child("device4").child("estado").getValue().toString();
                System.out.println("La mesa 4 tiene un estado de: "+estado);
                if (estado.equals("DE")) {
                    mesa4.setColorFilter(null);
                }
                if (estado.equals("OC")){
                    mesa4.setColorFilter(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void lecturaFirebase5(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa5 = findViewById(R.id.mesa5);
                String estado = dataSnapshot.child("device1").child("estado").getValue().toString();
                System.out.println("La mesa 5 tiene un estado de: "+estado);
                if (estado.equals("DE")) {
                    mesa5.setColorFilter(null);
                }
                if (estado.equals("OC")){
                    mesa5.setColorFilter(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void lecturaFirebase6(){
        DatabaseReference referencia=FirebaseDatabase.getInstance().getReference();
        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ImageView mesa6 = findViewById(R.id.mesa6);
                String estado = dataSnapshot.child("device6").child("estado").getValue().toString();
                System.out.println("La mesa 6 tiene un estado de: "+estado);
                if (estado.equals("DE")) {
                    mesa6.setColorFilter(null);
                }
                if (estado.equals("OC")){
                    mesa6.setColorFilter(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}