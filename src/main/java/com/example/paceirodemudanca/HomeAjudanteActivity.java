package com.example.paceirodemudanca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeAjudanteActivity extends AppCompatActivity {

    TextView txtPedidos;
    TextView txtPedidosAceitos;

    Button btnAceitar;
    Button btnRecusar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ajudante);

        txtPedidos = findViewById(R.id.txtPedidos);
        txtPedidosAceitos = findViewById(R.id.txtPedidosAceitos);

        btnAceitar = findViewById(R.id.btnAceitar);
        btnRecusar = findViewById(R.id.btnRecusar);

        new Thread(() -> {

            try {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/pedidos/ajudante")
                        .build();

                Response response = client.newCall(request).execute();

                String resultado = response.body().string();

                runOnUiThread(() ->
                        txtPedidos.setText(resultado)
                );

            } catch (Exception e) {

                runOnUiThread(() ->
                        txtPedidos.setText("Erro ao carregar serviços")
                );

            }

        }).start();

        btnAceitar.setOnClickListener(v -> {

            txtPedidosAceitos.setText(txtPedidos.getText());

            txtPedidos.setText("Nenhum serviço disponível");

            Toast.makeText(
                    HomeAjudanteActivity.this,
                    "Serviço aceito!",
                    Toast.LENGTH_SHORT
            ).show();

        });

        btnRecusar.setOnClickListener(v -> {

            txtPedidos.setText("Nenhum serviço disponível");

            Toast.makeText(
                    HomeAjudanteActivity.this,
                    "Serviço recusado!",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }
}