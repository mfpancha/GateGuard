package com.example.gateguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;


public class Ciudadelas extends AppCompatActivity {

    private ImageButton botonCerrarSesion;
    private ImageButton botonInicio;
    private ImageButton botonSoporte;

    private ImageButton botonPerfil;
    private ImageButton botonHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudadelas);

        botonCerrarSesion = findViewById(R.id.boton_salir);
        botonInicio = findViewById(R.id.boton_inicio);
        botonSoporte = findViewById(R.id.boton_soporte);
        botonPerfil = findViewById(R.id.boton_perfil);
        botonHistorial = findViewById(R.id.boton_historial);

        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarInicio();
            }
        });

        botonSoporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soporte();
            }
        });

        botonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historial();
            }
        });

        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil();
            }
        });

    }

    public void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        finish();
        // Redirigir a la actividad InicioSesion
        Intent intent = new Intent(Ciudadelas.this, InicioSesion.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }

    public void regresarInicio() {
        // Redirigir a la actividad Ciudadelas
        Intent intent = new Intent(Ciudadelas.this, Ciudadelas.class);
        startActivity(intent);
    }

    public void soporte() {
        // Redirigir a la actividad Soporte
        Intent intent = new Intent(Ciudadelas.this, Soporte.class);
        startActivity(intent);
    }

    public void perfil() {
        // Redirigir a la actividad Perfil Usuario
        Intent intent = new Intent(Ciudadelas.this, PerfilUsuario.class);
        startActivity(intent);
    }

    public void historial() {
        // Redirigir a la actividad Ciudadelas
        Intent intent = new Intent(Ciudadelas.this, Historial.class);
        startActivity(intent);
    }

}
