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

public class ModificarProductoFragment extends Fragment {

    private EditText etCodigo;
    private Button btnBuscar;
    private ListarProductosViewModel listarViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_producto, container, false);

        etCodigo = v.findViewById(R.id.etCodigo);
        btnBuscar = v.findViewById(R.id.btnBuscar);

        listarViewModel = new ViewModelProvider(requireActivity()).get(ListarProductosViewModel.class);

        // 🚩 Observa producto encontrado
        listarViewModel.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("producto", producto);
            Navigation.findNavController(requireView()).navigate(R.id.nav_detalle, bundle);
        });

        // 🚩 Observa errores
        listarViewModel.getError().observe(getViewLifecycleOwner(), errorMsg ->
                Toast.makeText(getContext(), "❌ " + errorMsg, Toast.LENGTH_SHORT).show()
        );

        // 🚩 Acción buscar delegada al ViewModel
        btnBuscar.setOnClickListener(view ->
                listarViewModel.buscarPorCodigo(etCodigo.getText().toString().trim())
        );

        return v;
    }
}
