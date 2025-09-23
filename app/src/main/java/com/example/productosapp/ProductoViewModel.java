package com.example.productosapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoViewModel extends ViewModel {
    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Producto>> getProductos() {
        return productosLiveData;
    }

    public boolean agregarProducto(Producto producto) {
        List<Producto> lista = new ArrayList<>(productosLiveData.getValue());

        for (Producto p : lista) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                return false; // Código repetido
            }
        }

        lista.add(producto);
        Collections.sort(lista);
        productosLiveData.setValue(lista);
        return true;
    }
}
