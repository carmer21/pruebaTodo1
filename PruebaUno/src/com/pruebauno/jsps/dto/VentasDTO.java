package com.pruebauno.jsps.dto;

import java.util.ArrayList;

public class VentasDTO {
	
	private int idVentas;
	private ProductosDTO productosDTO;
	private UsuarioDTO usuarioDTO;
	private int cantidad;
	private int valorVenta;
	private ArrayList<InventarioProductoDTO> listaInventario = new ArrayList<InventarioProductoDTO>();
	private ArrayList<VentasDTO> listaVentas = new ArrayList<VentasDTO>();
	
	public int getIdVentas() {
		return idVentas;
	}
	public void setIdVentas(int idVentas) {
		this.idVentas = idVentas;
	}
	public ProductosDTO getProductosDTO() {
		return productosDTO;
	}
	public void setProductosDTO(ProductosDTO productosDTO) {
		this.productosDTO = productosDTO;
	}
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValorVenta() {
		return valorVenta;
	}
	public void setValorVenta(int valorVenta) {
		this.valorVenta = valorVenta;
	}
	public ArrayList<InventarioProductoDTO> getListaInventario() {
		return listaInventario;
	}
	public void setListaInventario(ArrayList<InventarioProductoDTO> listaInventario) {
		this.listaInventario = listaInventario;
	}
	public ArrayList<VentasDTO> getListaVentas() {
		return listaVentas;
	}
	public void setListaVentas(ArrayList<VentasDTO> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	
}
