package com.example.paceirodemudanca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText email, senha;
    Button entrar;
    TextView criarConta;

    FirebaseAuth auth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        entrar = findViewById(R.id.entrar);
        criarConta = findViewById(R.id.criarConta);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        entrar.setOnClickListener(v -> {

            String txtEmail = email.getText().toString().trim();
            String txtSenha = senha.getText().toString().trim();

            if (txtEmail.isEmpty()) {
                email.setError("Digite o email");
                return;
            }

            if (txtSenha.isEmpty()) {
                senha.setError("Digite a senha");
                return;
            }

            auth.signInWithEmailAndPassword(txtEmail, txtSenha)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            String uid = auth.getCurrentUser().getUid();

                            database.child("usuarios")
                                    .child(uid)
                                    .child("tipo")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {

                                            String tipo = snapshot.getValue(String.class);

                                            Intent i;

                                            if ("transportador".equals(tipo)) {

                                                i = new Intent(
                                                        MainActivity.this,
                                                        HomeTransportadorActivity.class
                                                );

                                            } else if ("ajudante".equals(tipo)) {

                                                i = new Intent(
                                                        MainActivity.this,
                                                        HomeAjudanteActivity.class
                                                );

                                            } else {

                                                i = new Intent(
                                                        MainActivity.this,
                                                        HomeClienteActivity.class
                                                );

                                            }

                                            startActivity(i);
                                            finish();
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {

                                            Toast.makeText(
                                                    MainActivity.this,
                                                    "Erro ao carregar usuário",
                                                    Toast.LENGTH_LONG
                                            ).show();

                                        }
                                    });

                        } else {

                            Toast.makeText(
                                    MainActivity.this,
                                    "Erro: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });

        });

        criarConta.setOnClickListener(v -> {

            Intent i = new Intent(
                    MainActivity.this,
                    EscolhaActivity.class
            );

            startActivity(i);

        });

    }
}