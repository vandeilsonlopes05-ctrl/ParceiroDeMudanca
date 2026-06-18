package com.example.paceirodemudanca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeClienteActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button btnContinuar;
    EditText inputOrigem;
    EditText inputPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_cliente);

        btnContinuar = findViewById(R.id.btnContinuar);
        inputOrigem = findViewById(R.id.inputOrigem);
        inputPesquisa = findViewById(R.id.inputPesquisa);

        btnContinuar.setOnClickListener(v -> {

            String origem = inputOrigem.getText().toString();
            String destino = inputPesquisa.getText().toString();

            Intent i = new Intent(
                    HomeClienteActivity.this,
                    EscolherServicoActivity.class
            );

            i.putExtra("origem", origem);
            i.putExtra("destino", destino);

            startActivity(i);

        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        LatLng caruaru = new LatLng(-8.2836, -35.9761);

        mMap.addMarker(
                new MarkerOptions()
                        .position(caruaru)
                        .title("Caruaru")
        );

        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(caruaru, 13)
        );

        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}