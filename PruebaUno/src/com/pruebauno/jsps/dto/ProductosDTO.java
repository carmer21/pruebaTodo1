package com.pruebauno.jsps.dto;

import java.util.ArrayList;

public class ProductosDTO {
	
	private int idProducto;
	private String codigo;
	private String tipo;
	private String nombre;
	private ArrayList<ProductosDTO> listaProductos = new ArrayList<ProductosDTO>();
	private ArrayList<TiposProductosDTO> listaTiposProductos = new ArrayList<TiposProductosDTO>();
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<ProductosDTO> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<ProductosDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ArrayList<TiposProductosDTO> getListaTiposProductos() {
		return listaTiposProductos;
	}
	public void setListaTiposProductos(ArrayList<TiposProductosDTO> listaTiposProductos) {
		this.listaTiposProductos = listaTiposProductos;
	}
	
}