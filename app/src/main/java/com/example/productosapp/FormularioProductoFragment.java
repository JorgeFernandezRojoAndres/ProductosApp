package com.example.productosapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.productosapp.databinding.FragmentFormularioProductoBinding;

public class FormularioProductoFragment extends Fragment {

    private FragmentFormularioProductoBinding binding;
    private ProductoViewModel productoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentFormularioProductoBinding.inflate(inflater, container, false);

        productoViewModel = new ViewModelProvider(
                requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(ProductoViewModel.class);

        binding.btnGuardar.setOnClickListener(v -> {
            String codigo = binding.txtCodigo.getText().toString().trim();
            String descripcion = binding.txtDescripcion.getText().toString().trim();
            String precioStr = binding.txtPrecio.getText().toString().trim();

            // ✅ Validación y agregado delegados al ViewModel
            boolean agregado = productoViewModel.validarYAgregar(codigo, descripcion, precioStr);

            if (agregado) {
                Toast.makeText(getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(); // volver atrás
            } else {
                Toast.makeText(getContext(), "Error al agregar producto", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}
