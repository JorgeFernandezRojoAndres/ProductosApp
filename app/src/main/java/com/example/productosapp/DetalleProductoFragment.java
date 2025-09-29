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
import androidx.navigation.Navigation;

public class DetalleProductoFragment extends Fragment {

    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnGuardar;
    private Producto producto;
    private CargarProductoViewModel cargarViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        etCodigo = v.findViewById(R.id.etCodigo);
        etDescripcion = v.findViewById(R.id.etDescripcion);
        etPrecio = v.findViewById(R.id.etPrecio);
        btnGuardar = v.findViewById(R.id.btnGuardar);

        cargarViewModel = new ViewModelProvider(requireActivity()).get(CargarProductoViewModel.class);

        // ✅ Recuperar producto de argumentos
        if (getArguments() != null && getArguments().containsKey("producto")) {
            producto = (Producto) getArguments().getSerializable("producto");
        }

        // ✅ Mostrar datos si existe el producto
        if (producto != null) {
            etCodigo.setText(producto.getCodigo());
            etDescripcion.setText(producto.getDescripcion());
            etPrecio.setText(String.valueOf(producto.getPrecio()));
        }

        // ✅ Acción Guardar → delega todo al ViewModel
        btnGuardar.setOnClickListener(view -> {
            if (producto != null) {
                cargarViewModel.actualizarProducto(
                        producto,
                        etCodigo.getText().toString(),
                        etDescripcion.getText().toString(),
                        etPrecio.getText().toString()
                );
            }
        });

        // ✅ Observar errores
        cargarViewModel.getError().observe(getViewLifecycleOwner(), errorMsg ->
                Toast.makeText(getContext(), "❌ " + errorMsg, Toast.LENGTH_SHORT).show()
        );

        // ✅ Observar éxito
        cargarViewModel.getExito().observe(getViewLifecycleOwner(), successMsg -> {
            Toast.makeText(getContext(), "✅ " + successMsg, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireView()).navigate(R.id.nav_listar);
        });

        return v;
    }
}
