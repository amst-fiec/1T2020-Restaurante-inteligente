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

import java.util.ArrayList;

public class MesasDisponibles extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas_disponibles);
        lecturaFirebase();
        ImageView mesares=findViewById(R.id.ejemploRes);
        mesares.setColorFilter(Color.RED);
        ImageView mesa2=findViewById(R.id.mesa2);
        mesa2.setColorFilter(Color.RED);
        ImageView mesa3=findViewById(R.id.mesa3);
        mesa3.setColorFilter(Color.RED);
        ImageView mesa4=findViewById(R.id.mesa4);

        ImageView mesa5=findViewById(R.id.mesa5);
        mesa5.setColorFilter(Color.RED);
        ImageView mesa6=findViewById(R.id.mesa6);


    }
    public void lecturaFirebase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference referencia=database.getReference("devide");
        Query q=referencia.orderByChild("nombre");
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ImageView mesa1=findViewById(R.id.mesa1);
                mesa1.setColorFilter(Color.RED);
                ArrayList<String> lista=new ArrayList();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    lista.add(ds.getValue().toString());
                    System.out.println(ds.getValue().toString());
                }
                String estado=lista.get(0);
                System.out.println(estado);
                if(estado.equals("DE")){
                    mesa1.setColorFilter(null);
                }
                else{
                    mesa1.setColorFilter(Color.RED);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}