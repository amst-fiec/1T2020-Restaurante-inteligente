package com.example.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    HashMap<String, String> info_user;
    public String claveuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("mesas").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });

        Intent intent = getIntent();
        info_user = (HashMap<String, String>) intent.getSerializableExtra("info_user");
        FirebaseDatabase.getInstance().getReference().child("Usuarios").child(info_user.get("user_id")).child("clave").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    claveuser = dataSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

        referencia.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mostrarNotificacion();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("msg", "cerrarSesion");

        startActivity(intent);
    }

    public void usuarioData(View view) {
        HashMap<String, String> info = this.info_user;
        Intent intent = new Intent(this, Userdata.class);
        intent.putExtra("info_user", info);
        startActivity(intent);

    }

    public void buzon(View view) {
        Intent intent = new Intent(this, buzon.class);
        startActivity(intent);
    }

    public void verestadisticas(View view) {
        Intent intent = new Intent(this, EstadoMesa.class);
        startActivity(intent);
    }

    public void verMesas(View view) {
        startActivity(new Intent(this, MesasDisponibles.class));
    }

    public void verbaterias(View view) {
        Intent intent = new Intent(this, EstadoBateria.class);
        startActivity(intent);
    }

    public void mostrarNotificacion() {
       if (claveuser != "000") {
            RequestQueue myrequest = Volley.newRequestQueue(getApplicationContext());
            JSONObject json=new JSONObject();
            try{
                json.put("to","/topics/"+"mesas");
                JSONObject notification = new JSONObject();
                notification.put("title","Ocurrió un cambio en una mesa");
                notification.put("body","Pulse para más información sobre las mesas");

                json.put("notification",notification);
                String URL="https://fcm.googleapis.com/fcm/send";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> header=new HashMap<>();
                        header.put("content-type","application/json");
                        header.put("authorization","key=AAAAq8ySYgQ:APA91bGv02UQWP6Uunzqbj4oZD_128aIDSp6c2xZpiAB1PobvFOPSRpmnMNCrAJauUdoK10Kcs7En3wiv6D4ppLT8R1-_SkEhkKrQynVLzqSJ41KeG_jgwgfAomvp3e-m_zeHn-daxKC");
                        return header;
                    }
                };
                myrequest.add(request);

            }catch (Exception e){

            }
        }
    }
}
