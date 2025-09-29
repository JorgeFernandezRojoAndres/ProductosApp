package com.example.productosapp;

import java.io.Serializable;

public class Producto implements Comparable<Producto>, Serializable {
    // 🔑 Buenas prácticas: definir un serialVersionUID
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String descripcion;
    private double precio;

    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // ✅ Getters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    // ✅ Setters (necesarios para editar)
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }

    // ✅ Orden alfabético por descripción
    @Override
    public int compareTo(Producto o) {
        return this.descripcion.compareToIgnoreCase(o.descripcion);
    }
}
