package com.example.recudiegoorellano;

import android.app.Application;
import android.content.Context;
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
    private MutableLiveData<TextView> mensaje = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public void valores(String nombre, String peso, String altura, String edad) {

        double parsepeso = Double.parseDouble(peso);
        double parsealtura = Double.parseDouble(altura);
        int parseedad = Integer.parseInt(edad);
        Persona persona = new Persona(nombre, parsepeso, parsealtura,parseedad );
        personaData.setValue(persona);

    }

    public void validarDatos(String nombre, String peso, String altura, String edad, TextView mensaje) {

        boolean datosValidos = true;


        if (nombre.isEmpty() || peso.isEmpty() || altura.isEmpty()  || edad.isEmpty() ) {

            mensaje.setText("Debes Comletar todos los campos");

        } else {
            mensaje.setText("");
            datosValidos = true;
        }
        validado.setValue(datosValidos);
    }


    public LiveData<Persona> getPersonaData() {
        return personaData;
    }

    public LiveData<TextView> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getValidado() {
        return validado;
    }




}
