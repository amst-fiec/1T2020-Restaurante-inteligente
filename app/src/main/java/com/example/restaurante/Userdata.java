package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Userdata extends AppCompatActivity {
    TextView numerotxt, txt_name, txt_email;
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
        numerotxt.setText("Numero celular:"+info_user.get("user_phone"));
        txt_name.setText("Nombre:"+info_user.get("user_name").toUpperCase());
        txt_email.setText("Correo:"+info_user.get("user_email"));
        String photo = info_user.get("user_photo");
        Picasso.with(getApplicationContext()).load(photo).into(imv_photo);
    }
}