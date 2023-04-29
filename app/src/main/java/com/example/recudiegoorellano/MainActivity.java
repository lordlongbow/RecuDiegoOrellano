package com.example.recudiegoorellano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.recudiegoorellano.Modelo.Persona;

public class MainActivity extends AppCompatActivity implements MainActivityViewModel.AlertDialogListener {

    private EditText etNombre, etAltura, etEdad, etPeso;
    private TextView tvMensaje;
    private Button btnCalcular;


    private MainActivityViewModel vm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);
        vm.setAlertDialogListener(this);

        this.etNombre = findViewById(R.id.etNombre);
        this.etAltura = findViewById(R.id.etAltura);
        this.etEdad = findViewById(R.id.etEdad);
        this.etPeso = findViewById(R.id.etPeso);
        this.tvMensaje = findViewById(R.id.tvMensaje);
        this.btnCalcular = findViewById(R.id.btnCalcular);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String edad = etEdad.getText().toString();
                String peso = etPeso.getText().toString();
                String altura = etAltura.getText().toString();
                vm.validarDatos(nombre, peso, altura, edad);

                vm.getValidado().observe(MainActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {

                        if (aBoolean) {

                        /*    String nombre = etNombre.getText().toString();
                            String edad = etEdad.getText().toString();
                            String peso = etPeso.getText().toString();
                            String altura = etAltura.getText().toString();



                            Intent intent = new Intent(MainActivity.this, ActivityDetalleCalculado.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("nombre", nombre);
                            bundle.putString("peso", peso);
                            bundle.putString("altura", altura);
                            bundle.putString("edad", edad);
                            intent.putExtras(bundle);
                            startActivity(intent);*/


                            vm.getPersonaData().observe(MainActivity.this, new Observer<Persona>() {
                                @Override
                                public void onChanged(Persona persona) {
                                    Intent intent = new Intent(MainActivity.this, ActivityDetalleCalculado.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("persona", persona);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }
        });
    }


    @Override
    public void mostrarAlerta(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
