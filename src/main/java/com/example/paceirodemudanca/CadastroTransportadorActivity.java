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

public class CadastroTransportadorActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText telefone;
    EditText cnh;
    EditText categoria;
    EditText veiculo;
    EditText senha;

    Button btnCadastrar;

    FirebaseAuth auth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transportador);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        telefone = findViewById(R.id.telefone);
        cnh = findViewById(R.id.cnh);
        categoria = findViewById(R.id.categoria);
        veiculo = findViewById(R.id.veiculo);
        senha = findViewById(R.id.senha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {

            String txtNome = nome.getText().toString();
            String txtEmail = email.getText().toString();
            String txtTelefone = telefone.getText().toString();
            String txtCnh = cnh.getText().toString();
            String txtCategoria = categoria.getText().toString();
            String txtVeiculo = veiculo.getText().toString();
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

            if (txtCnh.isEmpty()) {
                cnh.setError("Digite a CNH");
                return;
            }

            if (txtCategoria.isEmpty()) {
                categoria.setError("Digite a categoria");
                return;
            }

            if (txtVeiculo.isEmpty()) {
                veiculo.setError("Digite o veículo");
                return;
            }

            if (txtSenha.isEmpty()) {
                senha.setError("Digite a senha");
                return;
            }

            auth.createUserWithEmailAndPassword(
                    txtEmail,
                    txtSenha
            ).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    String uid = auth.getCurrentUser().getUid();

                    database.child("usuarios")
                            .child(uid)
                            .child("tipo")
                            .setValue("transportador");

                    Toast.makeText(
                            CadastroTransportadorActivity.this,
                            "Cadastro realizado",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent i = new Intent(
                            CadastroTransportadorActivity.this,
                            HomeTransportadorActivity.class
                    );

                    startActivity(i);
                    finish();

                } else {

                    Toast.makeText(
                            CadastroTransportadorActivity.this,
                            "Erro: " + task.getException().getMessage(),
                            Toast.LENGTH_LONG
                    ).show();

                }

            });

        });

    }
}