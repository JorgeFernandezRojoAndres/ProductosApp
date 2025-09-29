package com.example.productosapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarProductosViewModel extends ViewModel {
    private final ProductosRepository repo = ProductosRepository.getInstance();

    // ✅ Nunca devuelve null, siempre lista vacía o productos ordenados
    public LiveData<List<Producto>> getProductosOrdenados() {
        return Transformations.map(repo.getProductos(), lista -> {
            if (lista == null) return new ArrayList<>(); // protegemos contra null
            List<Producto> copia = new ArrayList<>(lista);
            Collections.sort(copia);
            return copia;
        });
    }

    // ✅ Buscar por código (sin cambios)
    public Producto buscarPorCodigo(String codigo) {
        List<Producto> lista = repo.getProductos().getValue();
        if (lista != null) {
            for (Producto p : lista) {
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    return p;
                }
            }
        }
        return null; // Si no lo encuentra
    }
}
