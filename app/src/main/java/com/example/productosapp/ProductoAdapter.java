package com.example.productosapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
        ordenarPorDescripcion();
    }

    public void setProductos(List<Producto> nuevos) {
        this.productos = nuevos;
        ordenarPorDescripcion();
        notifyDataSetChanged();
    }

    private void ordenarPorDescripcion() {
        if (productos != null) {
            Collections.sort(productos, Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 🔹 Ahora usamos tu layout personalizado item_producto.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.txtDescripcion.setText(producto.getDescripcion());
        holder.txtCodigo.setText("Código: " + producto.getCodigo());
        holder.txtPrecio.setText("$ " + producto.getPrecio());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCodigo, txtDescripcion, txtPrecio;

        ViewHolder(View itemView) {
            super(itemView);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
        }
    }
}
