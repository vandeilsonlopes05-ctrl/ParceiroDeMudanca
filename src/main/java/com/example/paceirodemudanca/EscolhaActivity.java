package com.example.paceirodemudanca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EscolhaActivity extends AppCompatActivity {

    LinearLayout btnCliente;

    LinearLayout btnTransportador;

    LinearLayout btnAjudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_escolha);

        btnCliente = findViewById(R.id.btnCliente);

        btnTransportador = findViewById(R.id.btnTransportador);

        btnAjudante = findViewById(R.id.btnAjudante);

        btnCliente.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolhaActivity.this,
                    CadastroClienteActivity.class
            );

            startActivity(i);

        });

        btnTransportador.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolhaActivity.this,
                    CadastroTransportadorActivity.class
            );

            startActivity(i);

        });

        btnAjudante.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolhaActivity.this,
                    CadastroAjudanteActivity.class
            );

            startActivity(i);

        });

    }
}