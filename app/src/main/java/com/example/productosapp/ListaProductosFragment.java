package com.example.productosapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaProductosFragment extends Fragment {

    private ProductoAdapter adapter;
    private RecyclerView recyclerView;
    private ListarProductosViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_productos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductoAdapter();
        recyclerView.setAdapter(adapter);

        // ✅ ViewModel propio de listar
        viewModel = new ViewModelProvider(this).get(ListarProductosViewModel.class);

        // ✅ Observamos la lista ordenada del repo (nunca null, puede estar vacía)
        viewModel.getProductosOrdenados().observe(getViewLifecycleOwner(), lista -> {
            adapter.setProductos(lista); // lista ya es List<Producto>
        });
    }
}
