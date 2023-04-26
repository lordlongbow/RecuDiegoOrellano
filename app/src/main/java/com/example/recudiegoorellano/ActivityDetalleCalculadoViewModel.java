package com.example.recudiegoorellano;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ActivityDetalleCalculadoViewModel extends AndroidViewModel {

    private Context contexto;

    public MutableLiveData<TextView> getDetalles() {
        return detalles;
    }

    MutableLiveData <TextView> detalles  =new MutableLiveData<>();

    Double resultado;

    public ActivityDetalleCalculadoViewModel(@NonNull Application application) {
        super(application);
    }

    public void calcularIMC(Double peso, Double altura){
        resultado  = peso/(altura * altura);
    }

    public void medirIMC(TextView detalles){
        if(resultado < 20){
            detalles.setText("Usted se encuentra en bajo IMC, deberia consultar con su medico");
        }else if(resultado >= 20 && resultado <= 25){
            detalles.setText("Usted se encuentra con un muy buen IMC, Felicitaciones");
        }else if(resultado >25){
            detalles.setText("Usted se en alto IMC, deberia consultar con su medico");
        }
    }





}
