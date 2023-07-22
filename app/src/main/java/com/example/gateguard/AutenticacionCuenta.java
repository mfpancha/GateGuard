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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class AutenticacionCuenta extends AppCompatActivity {

    private EditText emailEditText;
    private Button enviarButton;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacion_cuenta);

        emailEditText = findViewById(R.id.email);
        enviarButton = findViewById(R.id.boton_enviar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarCuenta();
            }
        });
    }

    private void recuperarCuenta() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentUser != null && currentUser.getEmail().equals(email)) {
            // El correo ingresado es el mismo que el usuario de Google actualmente conectado
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
                                        Intent intent = new Intent(AutenticacionCuenta.this, MensajeAutenticacion.class);
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
        } else {
            // El correo ingresado no coincide con el usuario de Google actualmente conectado
            Toast.makeText(getApplicationContext(), "El correo electrónico no coincide con el usuario actual", Toast.LENGTH_SHORT).show();
        }
    }
}