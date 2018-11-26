package com.pruebauno.jsps.business;

import com.pruebauno.jsps.dao.UsuarioDAO;
import com.pruebauno.jsps.dto.UsuarioDTO;

public class UsuarioBusiness {
	
	private static UsuarioBusiness singleton = null;

	public static UsuarioBusiness getInstance() {
		if (singleton == null) {
			synchronized (UsuarioBusiness.class) {
				if (singleton == null) {
					singleton = new UsuarioBusiness();
				}
			}
		}
		return singleton;
	}

	private UsuarioBusiness() {
	}
	
	public UsuarioDTO consultarUsuario(String user, String pass ) throws Exception{
		return UsuarioDAO.getInstance().consultarUsuario(user, pass);
	}
	
	public int grabarUsuario(UsuarioDTO usuarioDTO) throws Exception{
		return UsuarioDAO.getInstance().grabarUsuario(usuarioDTO);
	}
	
	public int eliminarUsuario(int idUsuario) throws Exception{
		return UsuarioDAO.getInstance().eliminarUsuario(idUsuario);
	}

}
