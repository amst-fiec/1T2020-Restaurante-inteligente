package com.example.restaurante;
/*
Muestra en un diagrama de pastel las mesas disponibles, ocupadas e inhabilitadas
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.model.SliceValue;
import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class EstadoMesa extends AppCompatActivity {
    PieChartView pieChartView;
    int disponibles;
    int ocupadas;
    int inhabilitadas=1;
    List pieData = new ArrayList<>();//cargar valores de la base de la DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_mesa);
        pieChartView = findViewById(R.id.chart);
        getinfo();
        String strdis=String.valueOf(disponibles);
        String strocu=String.valueOf(ocupadas);
        String strin=String.valueOf(inhabilitadas);




    }
    public void getinfo(){
        FirebaseDatabase.getInstance().getReference().child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                disponibles=0;
                ocupadas=0;
                pieData.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Dispositivo device= dataSnapshot1.getValue(Dispositivo.class);
                    System.out.println(device.getEstado());
                    if(device.getEstado().equals("OC")){
                        ocupadas=ocupadas+1;

                    }else{
                        disponibles=disponibles+1;

                    }

                }
                pieData.add(new SliceValue(disponibles, Color.GREEN).setLabel("Disponibles: "+disponibles));
                pieData.add(new SliceValue(ocupadas, Color.RED).setLabel("Ocupadas: "+ocupadas));
                pieData.add(new SliceValue(inhabilitadas, Color.GRAY).setLabel("Inhabilitadas: "+inhabilitadas));

                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasLabels(true).setValueLabelTextSize(14);
                pieChartData.setHasCenterCircle(true).setCenterText1("Procentaje Mesas").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
                pieChartView.setPieChartData(pieChartData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}