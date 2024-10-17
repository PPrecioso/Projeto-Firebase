package com.example.aulaprojeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private EditText textNewUser, textNewPass, textNewPass3;
    private Button BtCreateAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        textNewUser = findViewById(R.id.textNewUser);
        textNewPass = findViewById(R.id.textNewPass);
        textNewPass3 = findViewById(R.id.textNewPass3);
        BtCreateAccount = findViewById(R.id.BtCreateAccount);

        mAuth = FirebaseAuth.getInstance();

        BtCreateAccount.setOnClickListener(v -> {
            String email = textNewUser.getText().toString();
            String password = textNewPass.getText().toString();
            String confirmPassword = textNewPass3.getText().toString();

            if (password.equals(confirmPassword)) {
                registerUser(email, password);
            } else {
                Toast.makeText(CreateAccount.this, "As senhas não coincidem!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateAccount.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateAccount.this, "Falha no registro: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
