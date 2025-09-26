package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.productosapp.databinding.ActivityFormularioProductoBinding;

public class FormularioProductoActivity extends AppCompatActivity {

    private ActivityFormularioProductoBinding binding;
    private ProductoViewModel productoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormularioProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ✅ ViewModel compartido a nivel de aplicación
        productoViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(ProductoViewModel.class);

        binding.btnGuardar.setOnClickListener(v -> {
            String codigo = binding.txtCodigo.getText().toString().trim();
            String descripcion = binding.txtDescripcion.getText().toString().trim();
            String precioStr = binding.txtPrecio.getText().toString().trim();

            if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", "❌ Campos vacíos");
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", "❌ Precio inválido: " + precioStr);
                return;
            }

            // ✅ Agregar producto con validación desde ViewModel
            boolean agregado = productoViewModel.agregarProducto(
                    new Producto(codigo, descripcion, precio)
            );

            if (agregado) {
                Log.d("DEBUG", "✅ Producto agregado: " + codigo + " - " + descripcion + " ($" + precio + ")");
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show();
                finish(); // vuelve a la lista
            } else {
                Log.d("DEBUG", "⚠️ Código duplicado: " + codigo);
                Toast.makeText(this, "Código duplicado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
