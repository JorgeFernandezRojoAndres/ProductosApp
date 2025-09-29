package com.example.productosapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ProductosRepository {
    private static ProductosRepository instancia;
    private final MutableLiveData<List<Producto>> productos = new MutableLiveData<>(new ArrayList<>());

    private ProductosRepository() {}

    public static synchronized ProductosRepository getInstance() {
        if (instancia == null) {
            instancia = new ProductosRepository();
        }
        return instancia;
    }

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto nuevo) {
        List<Producto> lista = productos.getValue();
        if (lista == null) lista = new ArrayList<>();
        lista.add(nuevo);
        productos.setValue(lista);
    }
}
