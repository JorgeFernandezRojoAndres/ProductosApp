package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListaProductosActivity extends AppCompatActivity {

    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 👉 Usar la lista compartida de MainActivity
        adapter = new ProductoAdapter(MainActivity.listaProductos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 👉 Refresca la lista cuando vuelvas desde el formulario
        adapter.notifyDataSetChanged();
    }
}
