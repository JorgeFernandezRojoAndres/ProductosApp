package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListaProductosActivity extends AppCompatActivity {

    private ProductoAdapter adapter;
    private ProductoViewModel productoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductoAdapter();
        recyclerView.setAdapter(adapter);

        // ✅ ViewModel compartido a nivel de aplicación
        productoViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(ProductoViewModel.class);

        // 👀 Observar cambios en la lista de productos
        productoViewModel.getProductos().observe(this, productos -> {
            adapter.setProductos(productos);
        });
    }
}
