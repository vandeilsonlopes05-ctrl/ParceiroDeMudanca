package com.example.paceirodemudanca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroAjudanteActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    EditText documento;
    EditText idade;
    EditText senha;

    Button btnCadastrar;

    FirebaseAuth auth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajudante);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        nome = findViewById(R.id.nome);
        telefone = findViewById(R.id.telefone);
        documento = findViewById(R.id.documento);
        idade = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {

            String txtNome = nome.getText().toString();
            String txtTelefone = telefone.getText().toString();
            String txtDocumento = documento.getText().toString();
            String txtIdade = idade.getText().toString();
            String txtSenha = senha.getText().toString();

            if (txtNome.isEmpty()) {
                nome.setError("Digite seu nome");
                return;
            }

            if (txtTelefone.isEmpty()) {
                telefone.setError("Digite seu telefone");
                return;
            }

            if (txtDocumento.isEmpty()) {
                documento.setError("Digite seu CPF ou RG");
                return;
            }

            if (txtIdade.isEmpty()) {
                idade.setError("Digite seu email");
                return;
            }

            if (txtSenha.isEmpty()) {
                senha.setError("Digite sua senha");
                return;
            }

            auth.createUserWithEmailAndPassword(
                    txtDocumento + "@ajudante.com",
                    txtSenha
            ).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    String uid = auth.getCurrentUser().getUid();

                    database.child("usuarios")
                            .child(uid)
                            .child("tipo")
                            .setValue("ajudante");

                    Toast.makeText(
                            CadastroAjudanteActivity.this,
                            "Cadastro realizado",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Toast.makeText(
                            CadastroAjudanteActivity.this,
                            "Erro: " + task.getException().getMessage(),
                            Toast.LENGTH_LONG
                    ).show();

                }

            });

        });

    }
}