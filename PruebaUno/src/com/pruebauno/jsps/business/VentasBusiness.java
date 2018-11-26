package com.pruebauno.jsps.business;

import com.pruebauno.jsps.dao.VentasDAO;
import com.pruebauno.jsps.dto.VentasDTO;

public class VentasBusiness {
	
	private static VentasBusiness singleton = null;

	public static VentasBusiness getInstance() {
		if (singleton == null) {
			synchronized (VentasBusiness.class) {
				if (singleton == null) {
					singleton = new VentasBusiness();
				}
			}
		}
		return singleton;
	}

	private VentasBusiness() {
	}
	
	public VentasDTO consultarInventarioProducto() throws Exception{
		return VentasDAO.getInstance().consultarInventarioProducto();
	}
	
	public int grabarVenta(VentasDTO ventasDTO) throws Exception{
		return VentasDAO.getInstance().grabarVenta(ventasDTO);
	}
	
	public int eliminarVenta(int idVentas, int idProducto, int cantidad) throws Exception{
		return VentasDAO.getInstance().eliminarVenta(idVentas, idProducto, cantidad);
	}

}
