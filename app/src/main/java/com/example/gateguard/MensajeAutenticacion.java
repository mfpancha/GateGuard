package com.example.gateguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MensajeAutenticacion extends AppCompatActivity {

    private Button aceptarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_autenticacion);

        aceptarButton = findViewById(R.id.boton_aceptar);

        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aceptar();
            }
        });

    }

    public void aceptar() {
        Intent intent = new Intent(MensajeAutenticacion.this, InicioSesion.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual para que no se pueda volver atrás
    }
}