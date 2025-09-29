package com.example.productosapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Producto> productos = new ArrayList<>();

    // Tipos de vista: normal y vacío
    private static final int TYPE_PRODUCTO = 1;
    private static final int TYPE_EMPTY = 0;

    // Método público para actualizar la lista
    public void setProductos(List<Producto> nuevaLista) {
        productos.clear();
        if (nuevaLista != null) {
            productos.addAll(nuevaLista);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (productos.isEmpty()) {
            return TYPE_EMPTY;
        }
        return TYPE_PRODUCTO;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new EmptyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_producto, parent, false);
            return new ProductoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductoViewHolder) {
            Producto producto = productos.get(position);
            ((ProductoViewHolder) holder).tvCodigo.setText("Código: " + producto.getCodigo());
            ((ProductoViewHolder) holder).tvDescripcion.setText("Descripción: " + producto.getDescripcion());
            ((ProductoViewHolder) holder).tvPrecio.setText(String.format("Precio: $%.2f", producto.getPrecio()));
        } else if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).textView.setText("No hay productos cargados");
        }
    }

    @Override
    public int getItemCount() {
        if (productos.isEmpty()) {
            return 1; // mostramos el mensaje vacío
        }
        return productos.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
