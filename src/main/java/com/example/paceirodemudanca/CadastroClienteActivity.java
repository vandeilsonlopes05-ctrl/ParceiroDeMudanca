package com.example.paceirodemudanca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroClienteActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText telefone;
    EditText senha;

    Button btnCadastrar;

    FirebaseAuth auth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cliente);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        telefone = findViewById(R.id.telefone);
        senha = findViewById(R.id.senha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {

            String txtNome = nome.getText().toString();
            String txtEmail = email.getText().toString();
            String txtTelefone = telefone.getText().toString();
            String txtSenha = senha.getText().toString();

            if (txtNome.isEmpty()) {
                nome.setError("Digite seu nome");
                return;
            }

            if (txtEmail.isEmpty()) {
                email.setError("Digite seu email");
                return;
            }

            if (txtTelefone.isEmpty()) {
                telefone.setError("Digite seu telefone");
                return;
            }

            if (txtSenha.isEmpty()) {
                senha.setError("Digite sua senha");
                return;
            }

            auth.createUserWithEmailAndPassword(txtEmail, txtSenha)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            String uid = auth.getCurrentUser().getUid();

                            database.child("usuarios")
                                    .child(uid)
                                    .child("tipo")
                                    .setValue("cliente");

                            Toast.makeText(
                                    CadastroClienteActivity.this,
                                    "Cadastro realizado",
                                    Toast.LENGTH_SHORT
                            ).show();

                            Intent i = new Intent(
                                    CadastroClienteActivity.this,
                                    HomeClienteActivity.class
                            );

                            startActivity(i);
                            finish();

                        } else {

                            Toast.makeText(
                                    CadastroClienteActivity.this,
                                    "Erro: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                    });

        });

    }
}