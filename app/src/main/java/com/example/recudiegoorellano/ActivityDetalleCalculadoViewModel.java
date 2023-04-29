package com.example.recudiegoorellano;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;

public class ActivityDetalleCalculadoViewModel extends AndroidViewModel {

    public MutableLiveData<String> detalles = new MutableLiveData<>();

    Double resultado;

    public ActivityDetalleCalculadoViewModel(@NonNull Application application) {
        super(application);
    }

    public void calcularIMC(Double peso, Double altura) {
        resultado = peso / (altura * altura);
       // resultado = Double.parseDouble(new DecimalFormat("#.##").format(resultado));
    }

    public void medirIMC() {
        if (resultado < 20) {
            detalles.postValue( " Usted se encuentra en bajo IMC, debería consultar con su médico");
        } else if (resultado >= 20 && resultado <= 25) {
            detalles.postValue( " Usted se encuentra con un muy buen IMC, Felicitaciones");
        } else if (resultado > 25) {
            detalles.postValue( " Usted se encuentra en alto IMC, debería consultar con su médico");
        }
    }
}
