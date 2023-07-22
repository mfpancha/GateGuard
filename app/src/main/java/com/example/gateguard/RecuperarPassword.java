package com.example.gateguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RecuperarPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button aceptarButton;
    private Button cancelarButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        emailEditText = findViewById(R.id.email);
        aceptarButton = findViewById(R.id.boton_aceptar);
        cancelarButton = findViewById(R.id.boton_cancelar);

        mAuth = FirebaseAuth.getInstance();

        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarCuenta();
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    private void recuperarCuenta() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    if (result != null && result.getSignInMethods() != null && !result.getSignInMethods().isEmpty()) {
                        // El correo electrónico está vinculado a una cuenta
                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Correo electrónico enviado", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RecuperarPassword.this, Mensaje.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error al enviar el correo electrónico", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        // El correo electrónico no está vinculado a ninguna cuenta
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Error al verificar el correo electrónico
                    Exception exception = task.getException();
                    if (exception instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cancelar() {
        Intent intent = new Intent(RecuperarPassword.this, InicioSesion.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual para que no se pueda volver atrás
    }
}
