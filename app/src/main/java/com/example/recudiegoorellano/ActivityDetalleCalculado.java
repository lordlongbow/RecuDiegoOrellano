package com.example.recudiegoorellano;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.recudiegoorellano.Modelo.Persona;

public class ActivityDetalleCalculado extends AppCompatActivity {

    private TextView tvDetalleNombre, tvDetalleAltura, tvDetalleEdad, tvDetallepeso, tvDetalles;
    private ActivityDetalleCalculadoViewModel dcvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallecalculado);

        dcvm = new ViewModelProvider(this).get(ActivityDetalleCalculadoViewModel.class);

        this.tvDetalleNombre = findViewById(R.id.tvDetalleNombre);
        this.tvDetalleAltura = findViewById(R.id.tvDetalleAltura);
        this.tvDetalleEdad = findViewById(R.id.tvDetalleEdad);
        this.tvDetallepeso = findViewById(R.id.tvDetallepeso);
        this.tvDetalles = findViewById(R.id.tvDetalleCalculo);

        Bundle bundle = getIntent().getExtras();
        Persona persona = (Persona) bundle.getSerializable("persona");
        double peso = persona.getPeso();
        double altura = persona.getAltura();
        tvDetalleNombre.setText(persona.getNombre());
        tvDetallepeso.setText(String.valueOf(peso));
        tvDetalleAltura.setText(String.valueOf(altura));
        tvDetalleEdad.setText(String.valueOf(persona.getEdad()));

        dcvm.calcularIMC(peso, altura);

        dcvm.medirIMC();

        dcvm.detalles.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String detalles) {
                tvDetalles.setText(detalles);
            }
        });
    }
}
