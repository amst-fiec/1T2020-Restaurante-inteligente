package com.example.restaurante;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class Database {
    public FirebaseDatabase db;
    public Database(FirebaseDatabase db) {
        this.db = db;
    }

    public void registrarUsuario(String uid, final Usuario usuario, final Context contexto, final HashMap<String, String> info_user){
        final DatabaseReference userRef = this.db.getReference("Usuarios/"+uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(contexto,"Este correo ya ha sido usado", Toast.LENGTH_LONG).show();
}               else{
                    userRef.setValue(usuario)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(contexto,"Usuario ha sido registrado correctamente",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(contexto,MainActivity.class);
                                    i.putExtra("info_user",info_user);
                                    contexto.startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Write failed
                                    Toast.makeText(contexto,"Ocurrio un fallo en el registro de Usuario",Toast.LENGTH_LONG).show();

                                }
                            });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void iniciarSesion(final Context contexto, String uid, final HashMap<String,String> info_user) {
        DatabaseReference UserRef = this.db.getReference("Usuarios/" + uid);
        UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Intent intent = new Intent(contexto, MainActivity.class);
                    intent.putExtra("info_user", info_user);
                    contexto.startActivity(intent);
                }
                else {
                    Toast.makeText(contexto,"Usuario no registrado",Toast.LENGTH_LONG);
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

