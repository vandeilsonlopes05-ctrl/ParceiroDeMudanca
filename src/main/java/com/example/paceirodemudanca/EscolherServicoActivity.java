package com.example.paceirodemudanca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EscolherServicoActivity extends AppCompatActivity {

    Button btnCaminhao;
    Button btnAjudante;
    Button btnCompleta;

    String origem;
    String destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_escolher_servico);

        origem = getIntent().getStringExtra("origem");
        destino = getIntent().getStringExtra("destino");

        btnCaminhao = findViewById(R.id.btnCaminhao);
        btnAjudante = findViewById(R.id.btnAjudante);
        btnCompleta = findViewById(R.id.btnCompleta);

        btnCaminhao.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolherServicoActivity.this,
                    PedidoActivity.class
            );

            i.putExtra("servico", "Caminhão");
            i.putExtra("origem", origem);
            i.putExtra("destino", destino);

            startActivity(i);

        });

        btnAjudante.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolherServicoActivity.this,
                    PedidoActivity.class
            );

            i.putExtra("servico", "Ajudante");
            i.putExtra("origem", origem);
            i.putExtra("destino", destino);

            startActivity(i);

        });

        btnCompleta.setOnClickListener(v -> {

            Intent i = new Intent(
                    EscolherServicoActivity.this,
                    PedidoActivity.class
            );

            i.putExtra("servico", "Mudança Completa");
            i.putExtra("origem", origem);
            i.putExtra("destino", destino);

            startActivity(i);

        });

    }
}