package com.example.paceirodemudanca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PedidoActivity extends AppCompatActivity {

    TextView txtServico;
    TextView txtOrigem;
    TextView txtDestino;
    TextView txtAjudante;
    TextView txtTotal;

    Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        txtServico = findViewById(R.id.txtServico);
        txtOrigem = findViewById(R.id.txtOrigem);
        txtDestino = findViewById(R.id.txtDestino);
        txtAjudante = findViewById(R.id.txtAjudante);
        txtTotal = findViewById(R.id.txtTotal);

        btnSolicitar = findViewById(R.id.btnSolicitar);

        String servico = getIntent().getStringExtra("servico");
        String origem = getIntent().getStringExtra("origem");
        String destino = getIntent().getStringExtra("destino");

        if (origem != null) {
            txtOrigem.setText("Origem: " + origem);
        }

        if (destino != null) {
            txtDestino.setText("Destino: " + destino);
        }

        if (servico != null) {
            txtServico.setText(servico);
        }

        btnSolicitar.setOnClickListener(v -> {

            new Thread(() -> {

                try {

                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/pedido?origem="
                                    + origem
                                    + "&destino="
                                    + destino
                                    + "&servico="
                                    + servico)
                            .post(RequestBody.create(new byte[0]))
                            .build();

                    Response response = client.newCall(request).execute();

                    runOnUiThread(() ->
                            Toast.makeText(
                                    PedidoActivity.this,
                                    "Pedido enviado para o backend!",
                                    Toast.LENGTH_LONG
                            ).show()
                    );

                } catch (Exception e) {

                    runOnUiThread(() ->
                            Toast.makeText(
                                    PedidoActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show()
                    );

                }

            }).start();

        });
    }
}