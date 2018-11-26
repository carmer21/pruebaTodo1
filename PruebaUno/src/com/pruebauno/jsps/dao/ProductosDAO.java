package com.pruebauno.jsps.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.dto.ProductosDTO;
import com.pruebauno.jsps.dto.TiposProductosDTO;

public class ProductosDAO {
	
	private static ProductosDAO singleton = null;
	private static Logger log = Logger.getLogger("todo1");

	public static ProductosDAO getInstance() {
		if (singleton == null) {
			synchronized (ProductosDAO.class) {
				if (singleton == null) {
					singleton = new ProductosDAO();
				}
			}
		}
		return singleton;
	}

	private ProductosDAO() {
	}
	
	private static String myDriver = "org.gjt.mm.mysql.Driver";
    private static String myUrl = "jdbc:mysql://localhost:3306/pruebatodo1";
	
	public ProductosDTO consultarProductos() throws SQLException, ClassNotFoundException{
		Connection conn = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ProductosDTO productosDTO = new ProductosDTO();
        String sql = " SELECT ID_PRODUCTO,CODIGO,TIPO,NOMBRE FROM PRODUCTOS_TODO1; ";
		String sqlTipos = " SELECT ID_TIPOS_PRODUCTOS,TIPO FROM TIPOS_PRODUCTOS_TODO1; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs = st.executeQuery(sql);
		    ArrayList<ProductosDTO> lista = new ArrayList<ProductosDTO>();
            while(rs.next()){
            	productosDTO = new ProductosDTO();
            	productosDTO.setIdProducto(rs.getInt("ID_PRODUCTO"));
            	productosDTO.setCodigo(rs.getString("CODIGO"));
            	productosDTO.setTipo(rs.getString("TIPO"));
            	productosDTO.setNombre(rs.getString("NOMBRE"));
            	lista.add(productosDTO);
            }
            
            // create the java statement
		    Statement st2 = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs2 = st2.executeQuery(sqlTipos);
		    ArrayList<TiposProductosDTO> listaTiposProductos = new ArrayList<TiposProductosDTO>();
		    TiposProductosDTO tiposProductosDTO = new TiposProductosDTO();
            while(rs2.next()){
            	tiposProductosDTO = new TiposProductosDTO();
            	tiposProductosDTO.setIdTiposProductos(rs2.getInt("ID_TIPOS_PRODUCTOS"));
            	tiposProductosDTO.setTipo(rs2.getString("TIPO"));
            	listaTiposProductos.add(tiposProductosDTO);
            }
            productosDTO.setListaTiposProductos(listaTiposProductos);
            productosDTO.setListaProductos(lista);
            return productosDTO;
        } catch(SQLException sqle){
        	log.error("ProductosDAO >>> ERROR-consultarProductos: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(rs!=null)
        		rs.close();
        }
	}
	
	public int grabarProducto(ProductosDTO productosDTO) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int grabar = 0;
        String sql = " INSERT INTO PRODUCTOS_TODO1(CODIGO,TIPO,NOMBRE,FECHA_CREACION)VALUES( '"
				+productosDTO.getCodigo()+"','"+productosDTO.getTipo()+"','"+productosDTO.getNombre()+"',SYSDATE()); ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
        } catch(SQLException sqle){
        	grabar = 1;
        	log.error("ProductosDAO >>> ERROR-grabarProducto: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        }
		return grabar;
	}
	
	public int eliminarProductos(int idProducto) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int eliminar = 0;
        String sql = " DELETE FROM PRODUCTOS_TODO1 WHERE ID_PRODUCTO = "+idProducto+"; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
        } catch(SQLException sqle){
        	eliminar = 1;
        	log.error("ProductosDAO >>> ERROR-eliminarProductos: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        }
		return eliminar;
	}

}
