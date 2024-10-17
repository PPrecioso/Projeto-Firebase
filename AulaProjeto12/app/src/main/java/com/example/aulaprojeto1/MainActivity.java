package com.example.aulaprojeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText textUser, textPass;
    private Button BtLogin, BtCriar, btnForgotPassword; // Alterado aqui
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUser = findViewById(R.id.textUser);
        textPass = findViewById(R.id.textPass);
        BtLogin = findViewById(R.id.BtLogin);
        BtCriar = findViewById(R.id.BtCriar);
        btnForgotPassword = findViewById(R.id.btSenha); // Alterado aqui

        mAuth = FirebaseAuth.getInstance();

        BtLogin.setOnClickListener(v -> {
            String email = textUser.getText().toString();
            String password = textPass.getText().toString();
            loginUser(email, password);
        });

        BtCriar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateAccount.class));
        });

        // Implementação do botão de recuperação de senha
        btnForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, forgotPassword.class));
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Tela3.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Falha no login: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
