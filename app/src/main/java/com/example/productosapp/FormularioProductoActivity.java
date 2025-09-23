package com.example.productosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto);

        EditText txtCodigo = findViewById(R.id.txtCodigo);
        EditText txtDescripcion = findViewById(R.id.txtDescripcion);
        EditText txtPrecio = findViewById(R.id.txtPrecio);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String codigo = txtCodigo.getText().toString().trim();
            String descripcion = txtDescripcion.getText().toString().trim();
            String precioStr = txtPrecio.getText().toString().trim();

            if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar duplicados
            for (Producto p : MainActivity.listaProductos) {
                if (p.getCodigo().equals(codigo)) {
                    Toast.makeText(this, "Código duplicado", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Agregar producto
            MainActivity.listaProductos.add(new Producto(codigo, descripcion, precio));
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show();
            finish(); // vuelve a la lista
        });
    }
}
