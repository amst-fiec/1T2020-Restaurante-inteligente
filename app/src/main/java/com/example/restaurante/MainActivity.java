package com.example.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    HashMap<String, String> info_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");


    }
    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }
    public void usuarioData(View view){
        HashMap<String, String> info= this.info_user;
        Intent intent = new Intent(this, Userdata.class);
        intent.putExtra("info_user", info);
        startActivity(intent);

    }
    public void buzon(View view){
        Intent intent = new Intent(this, buzon.class);
        startActivity(intent);
    }
}