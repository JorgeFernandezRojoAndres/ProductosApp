package com.example.productosapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FormularioProductoFragment extends Fragment {

    private CargarProductoViewModel viewModel;
    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_producto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etCodigo = view.findViewById(R.id.etCodigo);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        etPrecio = view.findViewById(R.id.etPrecio);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        // ✅ Cada fragment tiene su propio ViewModel (no se comparte con Lista)
        viewModel = new ViewModelProvider(this).get(CargarProductoViewModel.class);

        // Observers de error y éxito (nunca serán null)
        viewModel.getError().observe(getViewLifecycleOwner(), msg ->
                Toast.makeText(getContext(), "❌ " + msg, Toast.LENGTH_SHORT).show()
        );

        viewModel.getExito().observe(getViewLifecycleOwner(), msg ->
                Toast.makeText(getContext(), "✅ " + msg, Toast.LENGTH_SHORT).show()
        );

        btnGuardar.setOnClickListener(v -> {
            String codigo = etCodigo.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String precio = etPrecio.getText().toString();
            viewModel.guardarProducto(codigo, descripcion, precio);

            // 🔄 Limpio siempre los campos después de guardar
            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");
        });
    }
}
