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
import com.pruebauno.jsps.dto.UsuarioDTO;
import com.pruebauno.jsps.dto.VentasDTO;

public class VentasDAO {
	
	private static VentasDAO singleton = null;
	private static Logger log = Logger.getLogger("todo1");

	public static VentasDAO getInstance() {
		if (singleton == null) {
			synchronized (VentasDAO.class) {
				if (singleton == null) {
					singleton = new VentasDAO();
				}
			}
		}
		return singleton;
	}

	private VentasDAO() {
	}
	
	private static String myDriver = "org.gjt.mm.mysql.Driver";
    private static String myUrl = "jdbc:mysql://localhost:3306/pruebatodo1";
	
	public VentasDTO consultarInventarioProducto() throws SQLException, ClassNotFoundException{
		Connection conn = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        VentasDTO ventasDTO = new VentasDTO();
        String sql = 	" SELECT A.ID_PRODUCTO,A.CANTIDAD,A.VALOR_UNIDAD,B.NOMBRE,B.TIPO,B.CODIGO FROM INVENTARIO_PRODUCTO_TODO1 A "+
				" INNER JOIN PRODUCTOS_TODO1 B ON A.ID_PRODUCTO = B.ID_PRODUCTO WHERE A.CANTIDAD > 0; ";
        String sqlVentas = 	" SELECT A.ID_VENTAS, A.ID_USUARIO, C.NOMBRES ||' '||C.APELLIDOS AS NOMBRE_USUARIO, A.ID_PRODUCTO, B.NOMBRE AS PRODUCTO, "+
					" A.CANTIDAD,A.VALOR_VENTA FROM VENTAS_TODO1 A INNER JOIN PRODUCTOS_TODO1 B ON A.ID_PRODUCTO = B.ID_PRODUCTO "+
					" INNER JOIN USUARIOS_TODO1 C ON A.ID_USUARIO = C.ID_USUARIO; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    Statement st2 = conn.createStatement();
            rs2 = st2.executeQuery(sqlVentas);
		    ArrayList<VentasDTO> listaVentas = new ArrayList<VentasDTO>();
		    ProductosDTO productosDTO = new ProductosDTO();
		    UsuarioDTO usuarioDTO = new UsuarioDTO();
            while(rs2.next()){
            	ventasDTO = new VentasDTO();
            	productosDTO = new ProductosDTO();
            	usuarioDTO = new UsuarioDTO();
            	ventasDTO.setIdVentas(rs2.getInt("ID_VENTAS"));
            	productosDTO.setIdProducto(rs2.getInt("ID_PRODUCTO"));
            	productosDTO.setNombre(rs2.getString("PRODUCTO"));
            	ventasDTO.setProductosDTO(productosDTO);
            	usuarioDTO.setIdUsuario(rs2.getInt("ID_USUARIO"));
            	usuarioDTO.setNombres(rs2.getString("NOMBRE_USUARIO"));
            	ventasDTO.setUsuarioDTO(usuarioDTO);
            	ventasDTO.setCantidad(rs2.getInt("CANTIDAD"));
            	ventasDTO.setValorVenta(rs2.getInt("VALOR_VENTA"));
            	listaVentas.add(ventasDTO);
            }
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs = st.executeQuery(sql);
		    InventarioProductoDTO InventarioProductoDTO = new InventarioProductoDTO();
		    ArrayList<InventarioProductoDTO> listaInventario = new ArrayList<InventarioProductoDTO>();
            while(rs.next()){
            	InventarioProductoDTO = new InventarioProductoDTO();
            	productosDTO = new ProductosDTO();
            	productosDTO.setIdProducto(rs.getInt("ID_PRODUCTO"));
            	productosDTO.setTipo(rs.getString("TIPO"));
            	productosDTO.setCodigo(rs.getString("CODIGO"));
            	productosDTO.setNombre(rs.getString("NOMBRE"));
            	InventarioProductoDTO.setProductoDTO(productosDTO);
            	InventarioProductoDTO.setCantidad(rs.getInt("CANTIDAD"));
            	InventarioProductoDTO.setValorUnidad(rs.getInt("VALOR_UNIDAD"));
            	listaInventario.add(InventarioProductoDTO);
            }
            ventasDTO.setListaVentas(listaVentas);
            ventasDTO.setListaInventario(listaInventario);
            return ventasDTO;
        } catch(SQLException sqle){
        	log.error("VentasDAO >>> ERROR-consultarInventarioProducto: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(rs!=null)
        		rs.close();
        	
        	if(rs2!=null)
        		rs2.close();
        }
	}
	
	public int grabarVenta(VentasDTO ventasDTO) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int grabar = 0;
        String sql = " INSERT INTO VENTAS_TODO1(ID_PRODUCTO,ID_USUARIO,CANTIDAD,VALOR_VENTA,FECHA_CREACION)VALUES( '"
				+ventasDTO.getProductosDTO().getIdProducto()+"','"+ventasDTO.getUsuarioDTO().getIdUsuario()+"','"+ventasDTO.getCantidad()+"','"+ventasDTO.getValorVenta()+"',SYSDATE()); ";
		String sqlInventario = " UPDATE INVENTARIO_PRODUCTO_TODO1 SET CANTIDAD = CANTIDAD-"+ventasDTO.getCantidad()+" WHERE ID_PRODUCTO = "+ventasDTO.getProductosDTO().getIdProducto()+"; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
		    // create the java statement
		    Statement st2 = conn.createStatement();
		    // execute the query
		    st2.executeQuery(sqlInventario);
        } catch(SQLException sqle){
        	grabar = 1;
        	log.error("VentasDAO >>> ERROR-grabarVenta: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        }
		return grabar;
	}
	
	public int eliminarVenta(int idVentas, int idProducto, int cantidad) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int eliminar = 0;
        String sql = " DELETE FROM VENTAS_TODO1 WHERE ID_VENTAS = "+idVentas+"; ";
		String sqlActulizaInventario = " UPDATE INVENTARIO_PRODUCTO_TODO1 SET CANTIDAD = CANTIDAD+"+cantidad+" WHERE ID_PRODUCTO = "+idProducto+"; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    Statement st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
		    // create the java statement
		    Statement st2 = conn.createStatement();
		    // execute the query
		    st2.executeQuery(sqlActulizaInventario);
        } catch(SQLException sqle){
        	eliminar = 1;
        	log.error("VentasDAO >>> ERROR-eliminarVenta: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        }
		return eliminar;
	}

}
