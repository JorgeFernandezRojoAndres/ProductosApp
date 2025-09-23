package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 📌 Lista compartida de productos (accesible desde cualquier Activity)
    public static List<Producto> listaProductos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCargar = findViewById(R.id.btnCargar);
        Button btnListar = findViewById(R.id.btnListar);
        Button btnSalir = findViewById(R.id.btnSalir);

        // Ir a formulario para cargar producto
        btnCargar.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormularioProductoActivity.class);
            startActivity(intent);
        });

        // Ir a lista de productos
        btnListar.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        });

        // Salir de la app
        btnSalir.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Desea cerrar la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
