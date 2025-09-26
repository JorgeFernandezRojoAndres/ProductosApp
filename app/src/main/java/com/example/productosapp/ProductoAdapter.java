package com.example.productosapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productosapp.databinding.ItemProductoBinding;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> productos;
    private OnItemClickListener listener;

    // ✅ Interfaz para manejar clicks en los ítems
    public interface OnItemClickListener {
        void onItemClick(Producto producto);
    }

    public ProductoAdapter() {
        // vacío por defecto
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setProductos(List<Producto> nuevos) {
        this.productos = nuevos;
        ordenarPorDescripcion();
        notifyDataSetChanged();
    }

    private void ordenarPorDescripcion() {
        if (productos != null) {
            Collections.sort(productos, Comparator.comparing(
                    Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.bind(producto, listener);
    }

    @Override
    public int getItemCount() {
        return (productos == null) ? 0 : productos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductoBinding binding;

        ViewHolder(ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Producto producto, OnItemClickListener listener) {
            binding.txtDescripcion.setText(producto.getDescripcion());
            binding.txtCodigo.setText("Código: " + producto.getCodigo());
            binding.txtPrecio.setText("$ " + producto.getPrecio());

            // ✅ Click opcional en el item
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onItemClick(producto);
            });
        }
    }
}
