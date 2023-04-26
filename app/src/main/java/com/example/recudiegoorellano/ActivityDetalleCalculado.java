package com.example.recudiegoorellano;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ActivityDetalleCalculado extends AppCompatActivity {


    private TextView tvDetalleNombre, tvDetalleAltura, tvDetalleEdad, tvDetallepeso, tvDetalles;
    private ActivityDetalleCalculadoViewModel dcvm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallecalculado);
        dcvm = new ViewModelProvider(this).get(ActivityDetalleCalculadoViewModel.class);
        setContentView(R.layout.detallecalculado);

        this.tvDetalleNombre = findViewById(R.id.tvDetalleNombre);
        this.tvDetalleAltura = findViewById(R.id.tvDetalleAltura);
        this.tvDetalleEdad = findViewById(R.id.tvDetalleEdad);
        this.tvDetallepeso = findViewById(R.id.tvDetallepeso);
        this.tvDetalles = findViewById(R.id.tvDetalleCalculo);


        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("nombre");
        double peso = bundle.getDouble("peso");
        double altura = bundle.getDouble("altura");
        int edad = bundle.getInt("edad");

        tvDetalleNombre.setText(nombre);
        tvDetallepeso.setText(String.valueOf(peso));
        tvDetalleAltura.setText(String.valueOf(altura));
        tvDetalleEdad.setText(String.valueOf(edad));

        dcvm.calcularIMC(peso,altura);

        dcvm.getDetalles().observe(this, new Observer<TextView>() {
              @Override
              public void onChanged(TextView textView) {
                  dcvm.medirIMC(tvDetalles);
              }
          });







    }
}
