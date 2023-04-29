package com.example.recudiegoorellano;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recudiegoorellano.Modelo.Persona;

public class MainActivityViewModel extends AndroidViewModel {


    private Context contexto;
    private MutableLiveData<Persona> personaData = new MutableLiveData<>();
    private MutableLiveData<Boolean> validado = new MutableLiveData<>(false);
    private AlertDialogListener alertDialogListener;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public void valores(String nombre, String peso, String altura, String edad) {


        Log.d("salida","dentro de valores peso es:   " + peso);

        double parsepeso = Double.parseDouble(peso);
        double parsealtura = Double.parseDouble(altura);
        int parseedad = Integer.parseInt(edad);
        Persona persona = new Persona(nombre, parsepeso, parsealtura,parseedad );
        personaData.setValue(persona);
        Log.d("salida","dentro de personaData peso es:   " + personaData);
    }

    public void validarDatos(String nombre, String peso, String altura, String edad) {

        if (nombre.isEmpty() || peso.isEmpty() || altura.isEmpty() || edad.isEmpty()) {

            validado.setValue(false);
        }else{
            validado.setValue(true);
        }

        if(validado.getValue() == false){
            alertDialogListener.mostrarAlerta("Debes llenar todos los datos");
        }else{
            valores(nombre, peso,  altura, edad);
            Log.d("salida","dentro de validardatos peso es:   " + peso);
        }
    }



    public interface AlertDialogListener {
        void mostrarAlerta(String message);
    }


    public LiveData<Persona> getPersonaData() {
        return personaData;
    }

    public LiveData<Boolean> getValidado() {
        return validado;
    }


    public void setAlertDialogListener(AlertDialogListener listener) {
        alertDialogListener = listener;
    }

}
