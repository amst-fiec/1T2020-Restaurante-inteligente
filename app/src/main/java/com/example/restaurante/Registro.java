package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Registro extends AppCompatActivity {
    TextView numerotxt, txt_name, txt_email;
    ImageView imv_photo;
    String user_uid,name,email,phone;
    String photo;
    public String usuarioSeleccionado;
    private TextView clavetxt;
    private TextView claveView;
    private Spinner spnLocale;
    private String clavecliente="000";
    HashMap<String, String> info_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Intent intent = getIntent();
        info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");
        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        numerotxt = findViewById(R.id.numero);
        imv_photo = findViewById(R.id.imv_foto);
        name=info_user.get("user_name");
        email=info_user.get("user_email");
        phone=info_user.get("user_phone");
        user_uid=info_user.get("user_id");
        numerotxt.setText(phone);
        txt_name.setText(name);
        txt_email.setText(email);
        photo = info_user.get("user_photo");
        clavetxt = findViewById(R.id.clavetxt);
        claveView = findViewById(R.id.claveView);
        Picasso.with(getApplicationContext()).load(photo).into(imv_photo);
        spnLocale = (Spinner)findViewById(R.id.spinner);
        spnLocale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usuarioSeleccionado = (String) adapterView.getItemAtPosition(i);
                if (usuarioSeleccionado.equals("Cliente")){
                    claveView.setVisibility(View.INVISIBLE);
                    clavetxt.setVisibility(View.INVISIBLE);
                }
                else{
                    claveView.setVisibility(View.VISIBLE);
                    clavetxt.setVisibility(View.VISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
    public void registrar(View v){
        Database db = new Database(FirebaseDatabase.getInstance());
        Usuario user;
        if (spnLocale.getSelectedItem().toString().equals("Empleado")){

            user = new Usuario(user_uid,name,email,phone,photo, claveView.getText().toString());
            System.out.println(user.getClave()+"HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            db.registrarUsuario(user_uid,user,this,info_user);
        }
        else {
            user = new Usuario(user_uid,name,email,phone,photo, clavecliente);
            System.out.println(user.getClave()+"HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            db.registrarUsuario(user_uid,user,this,info_user);
        }
    }

}