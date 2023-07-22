package com.example.gateguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button usuarioButton;
    private Button administradorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioButton = findViewById(R.id.usuario);
        administradorButton = findViewById(R.id.administrador);

        usuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción cuando se hace clic en el botón "Soy usuario"
                iniciarUsuario();
            }
        });

        administradorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción cuando se hace clic en el botón "Soy administrador"
                iniciarAdministrador();
            }
        });
    }

    public void iniciarUsuario() {
        Intent intent = new Intent(MainActivity.this, InicioSesion.class);
        startActivity(intent);
    }

    public void iniciarAdministrador() {
        // Lógica para iniciar sesión como administrador
    }
}