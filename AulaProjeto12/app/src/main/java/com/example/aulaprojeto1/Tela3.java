package com.example.aulaprojeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Tela3 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView title4;
    private Button BtVoltarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        mAuth = FirebaseAuth.getInstance();
        title4 = findViewById(R.id.title4);
        BtVoltarLogin = findViewById(R.id.BtVoltarLogin);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            title4.setText("Você Entrou! " + email);
        } else {
            startActivity(new Intent(Tela3.this, MainActivity.class));
            finish();
        }

        BtVoltarLogin.setOnClickListener(v -> {
            startActivity(new Intent(Tela3.this, MainActivity.class));
            finish();
        });
    }
}

