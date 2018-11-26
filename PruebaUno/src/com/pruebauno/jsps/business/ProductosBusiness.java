package com.pruebauno.jsps.business;

import com.pruebauno.jsps.dao.ProductosDAO;
import com.pruebauno.jsps.dto.ProductosDTO;

public class ProductosBusiness {
	
	private static ProductosBusiness singleton = null;

	public static ProductosBusiness getInstance() {
		if (singleton == null) {
			synchronized (ProductosBusiness.class) {
				if (singleton == null) {
					singleton = new ProductosBusiness();
				}
			}
		}
		return singleton;
	}

	private ProductosBusiness() {
	}
	
	public ProductosDTO consultarProductos() throws Exception{
		return ProductosDAO.getInstance().consultarProductos();
	}
	
	public int grabarProducto(ProductosDTO productosDTO) throws Exception{
		return ProductosDAO.getInstance().grabarProducto(productosDTO);
	}
	
	public int eliminarProductos(int idProducto) throws Exception{
		return ProductosDAO.getInstance().eliminarProductos(idProducto);
	}

}
