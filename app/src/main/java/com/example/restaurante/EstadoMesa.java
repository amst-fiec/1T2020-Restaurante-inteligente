package com.example.restaurante;
/*
Muestra en un diagrama de pastel las mesas disponibles, ocupadas e inhabilitadas
 */
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;
public class EstadoMesa extends AppCompatActivity {
    PieChartView pieChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartView = findViewById(R.id.chart);

        List pieData = new ArrayList<>();//cargar valores de la base de la DB
        pieData.add(new SliceValue(5, Color.GREEN).setLabel("Disponibles: 5"));
        pieData.add(new SliceValue(3, Color.RED).setLabel("Ocupadas: 3"));
        pieData.add(new SliceValue(1, Color.GRAY).setLabel("Inhabilitadas: 1"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Procentaje Mesas").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

    }
}