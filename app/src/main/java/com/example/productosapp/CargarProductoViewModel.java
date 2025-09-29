package com.example.productosapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CargarProductoViewModel extends ViewModel {
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<String> exito = new MutableLiveData<>();
    private final ProductosRepository repo = ProductosRepository.getInstance(); // ✅ usa el repo compartido

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

    // 🆕 Nuevo método para actualizar un producto existente
    public void actualizarProducto(Producto productoActualizado) {
        if (productoActualizado.getCodigo().isEmpty()
                || productoActualizado.getDescripcion().isEmpty()) {
            error.setValue("Campos vacíos");
            return;
        }

        double precio = productoActualizado.getPrecio();
        if (precio <= 0) {
            error.setValue("Precio inválido");
            return;
        }

        List<Producto> lista = repo.getProductos().getValue();
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                Producto p = lista.get(i);
                if (p.getCodigo().equals(productoActualizado.getCodigo())) {
                    lista.set(i, productoActualizado);
                    repo.actualizarLista(lista); // ✅ notificamos al repo para refrescar LiveData
                    exito.setValue("Producto actualizado con éxito");
                    return;
                }
            }
        }
        error.setValue("Producto no encontrado");
    }

    // 🆕 Sobrecarga que recibe Strings y valida (para usar directo desde el Fragment)
    public void actualizarProducto(Producto productoActual, String codigo, String descripcion, String precioStr) {
        if (codigo == null || codigo.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()
                || precioStr == null || precioStr.trim().isEmpty()) {
            error.setValue("Campos vacíos");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr.trim());
        } catch (NumberFormatException e) {
            error.setValue("Precio inválido");
            return;
        }

        productoActual.setCodigo(codigo.trim());
        productoActual.setDescripcion(descripcion.trim());
        productoActual.setPrecio(precio);

        // Reutiliza el método existente
        actualizarProducto(productoActual);
    }

}
