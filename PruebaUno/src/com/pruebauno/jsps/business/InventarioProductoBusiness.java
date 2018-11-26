package com.pruebauno.jsps.business;

import com.pruebauno.jsps.dao.InventarioProductoDAO;
import com.pruebauno.jsps.dto.InventarioProductoDTO;

public class InventarioProductoBusiness {
	
	private static InventarioProductoBusiness singleton = null;

	public static InventarioProductoBusiness getInstance() {
		if (singleton == null) {
			synchronized (InventarioProductoBusiness.class) {
				if (singleton == null) {
					singleton = new InventarioProductoBusiness();
				}
			}
		}
		return singleton;
	}

	private InventarioProductoBusiness() {
	}
	
	public InventarioProductoDTO consultarInventarioProducto() throws Exception{
		return InventarioProductoDAO.getInstance().consultarInventarioProducto();
	}
	
	public int grabarInventarioProducto(InventarioProductoDTO inventarioProductoDTO) throws Exception{
		return InventarioProductoDAO.getInstance().grabarInventarioProducto(inventarioProductoDTO);
	}
	
	public int  eliminarInventario(int idInventariProducto) throws Exception{
		return InventarioProductoDAO.getInstance().eliminarInventario(idInventariProducto);
	}

}
