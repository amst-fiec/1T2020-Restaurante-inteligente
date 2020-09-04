package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Userdata extends AppCompatActivity {
    TextView numerotxt, txt_name, txt_email, txt_ut;
    String claveuser;
    ImageView imv_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);

        Intent intent = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");
        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        numerotxt = findViewById(R.id.numero);
        imv_photo = findViewById(R.id.imv_foto);
        txt_ut = findViewById(R.id.usuario);
        numerotxt.setText(info_user.get("user_phone"));
        txt_name.setText(info_user.get("user_name"));
        txt_email.setText(info_user.get("user_email"));
        String photo = info_user.get("user_photo");
        FirebaseDatabase.getInstance().getReference().child("Usuarios").child(info_user.get("user_id")).child("clave").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    claveuser=(String) dataSnapshot.getValue();
                    if (claveuser=="000"){
                        txt_ut.setText("Cliente");
                    }
                    else {
                        txt_ut.setText("Empleado");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Picasso.with(getApplicationContext()).load(photo).into(imv_photo);
    }
}