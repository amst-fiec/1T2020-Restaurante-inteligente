package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class buzon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);
    }
    public void enviar(View view){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Calificación enviada", Toast.LENGTH_SHORT);
        toast1.show();
       
    }
}