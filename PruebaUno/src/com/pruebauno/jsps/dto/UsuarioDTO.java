package com.pruebauno.jsps.dto;

import java.util.ArrayList;

public class UsuarioDTO {
	
	private int idUsuario;
	private String tipoDocumento;
	private String documento;
	private String nombres;
	private String apellidos;
	private String cargo;
	private ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public ArrayList<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(ArrayList<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	

}
