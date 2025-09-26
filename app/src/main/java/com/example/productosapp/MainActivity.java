package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

import com.example.productosapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // ⚠️ Antes estaba aquí la lista estática de productos.
    // public static List<Producto> listaProductos = new ArrayList<>();
    // 👉 Esto lo vamos a manejar desde ProductoViewModel (paso siguiente).

    private ActivityMainBinding binding; // 📌 ViewBinding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ir a formulario para cargar producto
        binding.btnCargar.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormularioProductoActivity.class);
            startActivity(intent);
        });

        // Ir a lista de productos
        binding.btnListar.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        });

        // Salir de la app
        binding.btnSalir.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Desea cerrar la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
