package com.pruebauno.jsps.dto;

import java.util.ArrayList;

public class InventarioProductoDTO {
	
	private int idInventarioProducto;
	private ProductosDTO productoDTO;
	private int cantidad;
	private int valorUnidad;
	private ArrayList<ProductosDTO> listaProductos = new ArrayList<ProductosDTO>();
	private ArrayList<InventarioProductoDTO> listaInventarioProducto = new ArrayList<InventarioProductoDTO>();
	
	public int getIdInventarioProducto() {
		return idInventarioProducto;
	}
	public void setIdInventarioProducto(int idInventarioProducto) {
		this.idInventarioProducto = idInventarioProducto;
	}
	public ProductosDTO getProductoDTO() {
		return productoDTO;
	}
	public void setProductoDTO(ProductosDTO productoDTO) {
		this.productoDTO = productoDTO;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(int valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	public ArrayList<ProductosDTO> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<ProductosDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ArrayList<InventarioProductoDTO> getListaInventarioProducto() {
		return listaInventarioProducto;
	}
	public void setListaInventarioProducto(ArrayList<InventarioProductoDTO> listaInventarioProducto) {
		this.listaInventarioProducto = listaInventarioProducto;
	}
	
}