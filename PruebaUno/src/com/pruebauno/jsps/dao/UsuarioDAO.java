package com.pruebauno.jsps.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.dto.UsuarioDTO;

public class UsuarioDAO {
	
	private static UsuarioDAO singleton = null;
	private static Logger log = Logger.getLogger("todo1");

	public static UsuarioDAO getInstance() {
		if (singleton == null) {
			synchronized (UsuarioDAO.class) {
				if (singleton == null) {
					singleton = new UsuarioDAO();
				}
			}
		}
		return singleton;
	}

	private UsuarioDAO() {
	}
	
	private static String myDriver = "org.gjt.mm.mysql.Driver";
    private static String myUrl = "jdbc:mysql://localhost:3306/pruebatodo1";
	
	public UsuarioDTO consultarUsuario(String user, String pass) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Statement st = null;
        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
		try {
			String sql = " SELECT ID_USUARIO,TIPO_DOCUMENTO,DOCUMENTO,NOMBRES,APELLIDOS,CARGO FROM USUARIOS_TODO1 WHERE DOCUMENTO = '"+user+"' AND PASSWORD = '"+pass+"'; ";
			String sqlTodos = " SELECT ID_USUARIO,TIPO_DOCUMENTO,DOCUMENTO,NOMBRES,APELLIDOS,CARGO FROM USUARIOS_TODO1;";
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    st = conn.createStatement();
		    // execute the query, and get a java resultset
		    rs = st.executeQuery(sql);
            while(rs.next()){
            	objUsuarioDTO.setIdUsuario(rs.getInt("ID_USUARIO"));
            	objUsuarioDTO.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
            	objUsuarioDTO.setDocumento(rs.getString("DOCUMENTO"));
            	objUsuarioDTO.setNombres(rs.getString("NOMBRES"));
            	objUsuarioDTO.setApellidos(rs.getString("APELLIDOS"));
            	objUsuarioDTO.setCargo(rs.getString("CARGO"));
            }
            
            rs2 = st.executeQuery(sqlTodos);
            UsuarioDTO objUsuarioDTO2 = new UsuarioDTO();
            ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
            while(rs2.next()){
            	objUsuarioDTO2 = new UsuarioDTO();
            	objUsuarioDTO2.setIdUsuario(rs2.getInt("ID_USUARIO"));
            	objUsuarioDTO2.setTipoDocumento(rs2.getString("TIPO_DOCUMENTO"));
            	objUsuarioDTO2.setDocumento(rs2.getString("DOCUMENTO"));
            	objUsuarioDTO2.setNombres(rs2.getString("NOMBRES"));
            	objUsuarioDTO2.setApellidos(rs2.getString("APELLIDOS"));
            	objUsuarioDTO2.setCargo(rs2.getString("CARGO"));
            	lista.add(objUsuarioDTO2);
            }
            objUsuarioDTO.setListaUsuarios(lista);
            return objUsuarioDTO;
        } catch(SQLException sqle){
        	log.error("UsuarioDAO >>> ERROR-consultarUsuario: "+sqle);
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
        }
	}
	
	public int grabarUsuario(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int grabar = 0;
        Statement st = null;
		try {
			String sql = " INSERT INTO USUARIOS_TODO1(TIPO_DOCUMENTO,DOCUMENTO,NOMBRES,APELLIDOS,CARGO,PASSWORD,FECHA_CREACION)VALUES( '"
				+usuarioDTO.getTipoDocumento()+"','"+usuarioDTO.getDocumento()+"','"+usuarioDTO.getNombres()+"','"+usuarioDTO.getApellidos()+"','"+usuarioDTO.getCargo()+"',123456,SYSDATE()); ";
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
        } catch(SQLException sqle){
        	grabar = 1;
        	log.error("UsuarioDAO >>> ERROR-grabarUsuario: "+sqle);
            throw sqle;
        }finally {
        	if(conn!=null)
        		conn.close();
        	
        	if(st!=null)
        		st.close();
        }
		return grabar;
	}
	
	public int eliminarUsuario(int idUsuario) throws SQLException, ClassNotFoundException{
		Connection conn = null;
        int eliminar = 0;
        Statement st = null;
        String sql = " DELETE FROM USUARIOS_TODO1 WHERE ID_USUARIO = "+idUsuario+"; ";
		try {
		    Class.forName(myDriver);
		    conn = DriverManager.getConnection(myUrl, "root", "");
		    // create the java statement
		    st = conn.createStatement();
		    // execute the query
		    st.executeQuery(sql);
        } catch(SQLException sqle){
        	eliminar = 1;
        	log.error("UsuarioDAO >>> ERROR-eliminarUsuario: "+sqle);
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
