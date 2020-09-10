package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class EstadoBateria extends AppCompatActivity {
    LinearLayout layoutt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_bateria);
        getinfo();
        layoutt= findViewById(R.id.lay);

    }
    public CardView makeCard(String name, String bateria){
        CardView cardView = new CardView(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams.bottomMargin = 80;
        cardView.setLayoutParams(layoutParams);
        cardView.setRadius(15);
        cardView.setCardBackgroundColor(Color.parseColor("#E4E6E8"));
        LinearLayout linearLayout = new LinearLayout(this);
        //linearLayout.setGravity(LinearLayout.VERTICAL);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsH = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setWeightSum(2);
        linearLayout.setPadding(15,15,15,15);
        linearLayout.setLayoutParams(layoutParamsH);
        TextView nombre = new TextView(this);
        TextView bateriap=new TextView(this);
        nombre.setText(name.toUpperCase());
        bateriap.setText("Estado de bateria: "+bateria+"%");
        linearLayout.addView(nombre);
        linearLayout.addView(bateriap);
        cardView.addView(linearLayout);
        return cardView;
    }
    public void getinfo(){
        FirebaseDatabase.getInstance().getReference().child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layoutt.removeAllViews();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                       String nombre= dataSnapshot1.getKey();
                       Dispositivo device= dataSnapshot1.getValue(Dispositivo.class);
                       System.out.println(device.toString());
                       CardView cv= makeCard(nombre,device.getBateria());
                       layoutt.addView(cv);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}