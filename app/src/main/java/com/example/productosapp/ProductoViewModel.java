package com.example.productosapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoViewModel extends AndroidViewModel {

    // ✅ Static → compartido en toda la app
    private static final MutableLiveData<List<Producto>> productosLiveData =
            new MutableLiveData<>(new ArrayList<>());

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Producto>> getProductos() {
        return productosLiveData;
    }

    public boolean agregarProducto(Producto producto) {
        List<Producto> lista = new ArrayList<>(productosLiveData.getValue());

        for (Producto p : lista) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                return false; // ⚠️ Código repetido
            }
        }

        lista.add(producto);
        Collections.sort(lista);
        productosLiveData.setValue(lista);
        return true;
    }

    // 🔍 Buscar un producto por código
    public Producto buscarPorCodigo(String codigo) {
        List<Producto> lista = productosLiveData.getValue();
        if (lista != null) {
            for (Producto p : lista) {
                if (p.getCodigo().equals(codigo)) {
                    return p;
                }
            }
        }
        return null;
    }

    // 🗑️ Eliminar producto
    public boolean eliminarProducto(String codigo) {
        List<Producto> lista = new ArrayList<>(productosLiveData.getValue());
        boolean eliminado = lista.removeIf(p -> p.getCodigo().equals(codigo));

        if (eliminado) {
            productosLiveData.setValue(lista);
        }
        return eliminado;
    }

    // ✅ Nueva función: validación + alta de producto
    public boolean validarYAgregar(String codigo, String descripcion, String precioStr) {
        if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            Log.d("DEBUG", "❌ Campos vacíos");
            return false;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            Log.d("DEBUG", "❌ Precio inválido: " + precioStr);
            return false;
        }

        boolean agregado = agregarProducto(new Producto(codigo, descripcion, precio));

        if (agregado) {
            Log.d("DEBUG", "✅ Producto agregado: " + codigo + " - " + descripcion + " ($" + precio + ")");
        } else {
            Log.d("DEBUG", "⚠️ Código duplicado: " + codigo);
        }

        return agregado;
    }
}
