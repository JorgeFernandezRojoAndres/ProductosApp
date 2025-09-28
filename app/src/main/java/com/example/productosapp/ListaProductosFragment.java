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

import com.example.productosapp.databinding.FragmentListaProductosBinding;

public class ListaProductosFragment extends Fragment {

    private FragmentListaProductosBinding binding;
    private ProductoAdapter adapter;
    private ProductoViewModel productoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentListaProductosBinding.inflate(inflater, container, false);

        // Configurar RecyclerView
        adapter = new ProductoAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        // ✅ ViewModel compartido a nivel de aplicación
        productoViewModel = new ViewModelProvider(
                requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(ProductoViewModel.class);

        // 👀 Observar cambios en la lista de productos
        productoViewModel.getProductos().observe(getViewLifecycleOwner(), productos -> {
            adapter.setProductos(productos);
        });

        return binding.getRoot();
    }
}
