package com.example.productosapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CargarProductoViewModel extends AndroidViewModel {
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<String> exito = new MutableLiveData<>();
    private final ProductosRepository repo;

    public CargarProductoViewModel(@NonNull Application application) {
        super(application);
        repo = ProductosRepository.getInstance(); // ✅ usa el repo compartido
    }

    public LiveData<String> getError() { return error; }
    public LiveData<String> getExito() { return exito; }

    public void guardarProducto(String codigo, String descripcion, String precioStr) {
        if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            error.setValue("Campos vacíos");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            error.setValue("Precio inválido");
            return;
        }

        List<Producto> lista = repo.getProductos().getValue();
        if (lista != null) {
            for (Producto p : lista) {
                if (p.getCodigo().equals(codigo)) {
                    error.setValue("Producto duplicado");
                    return;
                }
            }
        }

        repo.agregarProducto(new Producto(codigo, descripcion, precio)); // ✅ guarda en el repo
        exito.setValue("Producto agregado con éxito");
    }
}
