package com.example.productosapp;

public class Producto implements Comparable<Producto> {
    private String codigo;
    private String descripcion;
    private double precio;

    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    @Override
    public int compareTo(Producto o) {
        return this.descripcion.compareToIgnoreCase(o.descripcion);
    }
}
