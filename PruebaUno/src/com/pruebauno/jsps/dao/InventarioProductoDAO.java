package com.pruebauno.jsps.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.dto.InventarioProductoDTO;
import com.pruebauno.jsps.dto.ProductosDTO;

public class InventarioProductoDAO {
	
	private static InventarioProductoDAO singleton = null;

	public static InventarioProductoDAO getInstance() {
		if (singleton == null) {
			synchronized (InventarioProductoDAO.class) {
				if (singleton == null) {
					singleton = new InventarioProductoDAO();
				}
			}
		}
		return singleton;
	}

	private InventarioProductoDAO() {
	}
	
	private static String myDriver = "org.gjt.mm.mysql.Driver";
    private static String myUrl = "jdbc:mysql://localhost:3306/pruebatodo1";
    private static String campoIdProducto = "ID_PRODUCTO";
    private static Logger log = Logger.getLogger("todo1");
	
	public InventarioProductoDTO consultarInventarioProducto() throws SQLException, ClassNotFoundException{
		Connection conn = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Statement st = null;
        Statement st2 = null;
        InventarioProductoDTO inventarioProductoDTO = new InventarioProductoDTO();
        String sql = " SELECT A.ID_INVENTARIO_PRODUCTO,A.ID_PRODUCTO,A.CANTIDAD,A.VALOR_UNIDAD,B.NOMBRE FROM INVENTARIO_PRODUCTO_TODO1 A INNER JOIN PRODUCTOS_TODO1 B ON A.ID_PRODUCTO = B.ID_PRODUCTO; ";
		String sqlProductos = " SELECT ID_PRODUCTO,CODIGO,TIPO,NOMBRE FROM PRODUCTOS_TODO1; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    st = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs = st.executeQuery(sql);
		    ArrayList<InventarioProductoDTO> lista = new ArrayList<InventarioProductoDTO>();
		    ProductosDTO productosDTO = new ProductosDTO();
            while(rs.next()){
            	inventarioProductoDTO = new InventarioProductoDTO();
            	productosDTO = new ProductosDTO();
            	inventarioProductoDTO.setIdInventarioProducto(rs.getInt("ID_INVENTARIO_PRODUCTO"));
            	productosDTO.setIdProducto(rs.getInt(campoIdProducto));
            	productosDTO.setNombre(rs.getString("NOMBRE"));
            	inventarioProductoDTO.setProductoDTO(productosDTO);
            	inventarioProductoDTO.setCantidad(rs.getInt("CANTIDAD"));
            	inventarioProductoDTO.setValorUnidad(rs.getInt("VALOR_UNIDAD"));
            	lista.add(inventarioProductoDTO);
            }
            inventarioProductoDTO.setListaInventarioProducto(lista);
            st2 = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs2 = st2.executeQuery(sqlProductos);
		    ArrayList<ProductosDTO> listaProductos = new ArrayList<ProductosDTO>();
            while(rs2.next()){
            	productosDTO = new ProductosDTO();
            	productosDTO.setIdProducto(rs2.getInt(campoIdProducto));
            	productosDTO.setCodigo(rs2.getString("CODIGO"));
            	productosDTO.setTipo(rs2.getString("TIPO"));
            	productosDTO.setNombre(rs2.getString("NOMBRE"));
            	listaProductos.add(productosDTO);
            }
            inventarioProductoDTO.setListaProductos(listaProductos);
            return inventarioProductoDTO;
        } catch(SQLException sqle){
        	log.error("InventarioProductoDAO >>> ERROR-Consulta: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(rs!=null)
        		rs.close();
        	
        	if(rs2!=null)
        		rs2.close();
        	
        	if(st!=null)
        		st.close();
        	
        	if(st2!=null)
        		st2.close();
        }
	}
	
	public int grabarInventarioProducto(InventarioProductoDTO inventarioProductoDTO) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int grabar = 0;
        ResultSet rs = null;
        Statement st = null;
        Statement st2 = null;
        String sqlCountInvent = " SELECT COUNT(*) AS CANTIDAD FROM INVENTARIO_PRODUCTO_TODO1 WHERE ID_PRODUCTO = "+inventarioProductoDTO.getProductoDTO().getIdProducto()+"; ";
		String sqlInsert = " INSERT INTO INVENTARIO_PRODUCTO_TODO1(ID_PRODUCTO,CANTIDAD,VALOR_UNIDAD,FECHA_CREACION)VALUES( '"
			+inventarioProductoDTO.getProductoDTO().getIdProducto()+"','"+inventarioProductoDTO.getCantidad()+"','"+inventarioProductoDTO.getValorUnidad()+"',SYSDATE()); ";
		String sqlUpdateInvent = " UPDATE INVENTARIO_PRODUCTO_TODO1 SET CANTIDAD = CANTIDAD+"+inventarioProductoDTO.getCantidad()+" WHERE ID_PRODUCTO = "+inventarioProductoDTO.getProductoDTO().getIdProducto()+";";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    
		    // create the java statement
		    st = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs = st.executeQuery(sqlCountInvent);
		    int cantidadRegistros = 0;
		    while(rs.next()){
		    	cantidadRegistros = rs.getInt("CANTIDAD");
		    }
		    // create the java statement
		    st2 = conn.createStatement();
		    // execute the query
		    if(cantidadRegistros==0)
		    	st2.executeQuery(sqlInsert);
		    else
		    	st2.executeQuery(sqlUpdateInvent);
        } catch(SQLException sqle){
        	grabar = 1;
        	log.error("InventarioProductoDAO >>> ERROR-insertar: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(st!=null)
        		st.close();
        	
        	if(st2!=null)
        		st2.close();
        }
		return grabar;
	}
	
	public int eliminarInventario(int idInventariProducto) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		Statement st = null;
        int eliminar = 0;
        String sql = " DELETE FROM INVENTARIO_PRODUCTO_TODO1 WHERE ID_INVENTARIO_PRODUCTO = "+idInventariProducto+"; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    st = conn.createStatement();
		    st.executeQuery(sql);
        } catch(SQLException sqle){
        	eliminar = 1;
        	log.error("InventarioProductoDAO >>> ERROR-eliminar: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(st!=null)
        		st.close();
        }
		return eliminar;
	}

}
