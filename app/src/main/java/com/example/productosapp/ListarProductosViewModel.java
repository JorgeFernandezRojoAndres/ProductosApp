package com.example.productosapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarProductosViewModel extends ViewModel {
    private final ProductosRepository repo = ProductosRepository.getInstance();

    // LiveData para comunicar búsqueda
    private final MutableLiveData<Producto> productoEncontrado = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<Producto> getProductoEncontrado() {
        return productoEncontrado;
    }

    public LiveData<String> getError() {
        return error;
    }

    // ✅ Nunca devuelve null, siempre lista vacía o productos ordenados
    public LiveData<List<Producto>> getProductosOrdenados() {
        return Transformations.map(repo.getProductos(), lista -> {
            if (lista == null) return new ArrayList<>();
            List<Producto> copia = new ArrayList<>(lista);
            Collections.sort(copia);
            return copia;
        });
    }

    // ✅ Buscar por código y notificar resultado por LiveData
    public void buscarPorCodigo(String codigo) {
        List<Producto> lista = repo.getProductos().getValue();
        if (lista == null || lista.isEmpty()) {
            error.setValue("No hay productos cargados");
            return;
        }

        for (Producto p : lista) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                productoEncontrado.setValue(p);
                return;
            }
        }

        error.setValue("Producto no encontrado");
    }
}
