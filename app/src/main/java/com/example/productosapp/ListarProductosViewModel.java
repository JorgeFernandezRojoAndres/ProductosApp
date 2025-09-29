package com.example.productosapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarProductosViewModel extends AndroidViewModel {
    private final ProductosRepository repo;

    public ListarProductosViewModel(@NonNull Application application) {
        super(application);
        repo = ProductosRepository.getInstance();
    }

    public LiveData<List<Producto>> getProductosOrdenados() {
        return Transformations.map(repo.getProductos(), lista -> {
            List<Producto> copia = new ArrayList<>(lista);
            Collections.sort(copia);
            return copia;
        });
    }
}
