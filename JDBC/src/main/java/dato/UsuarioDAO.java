package dato;
import static dato.Conexion.*; 
import domain.*;
import java.sql.*;
import java.util.*;

public class UsuarioDAO { 
 
   private Connection conexionTrasancional ; 
    // Selecionar 
    private static final String SQL_SELECT ="SELECT id_usuario,usuario,pasword FROM test.usuario"; 
    private static final String SQL_INSERT ="INSERT INTO usuario(usuario,pasword) VALUES(? ,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?,pasword=? WHERE id_usuario=? ";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario =?"; 
    
    // Constructor vacio 
  
    public UsuarioDAO (){
        
    }
    // Aqui recibo una conexion 
    public UsuarioDAO (Connection conexion){
        this.conexionTrasancional= conexion;
    }  
    
    public List<Usuario>selecionar() throws SQLException{
        
        // vamos a nuestra instruccion  
            Connection conn=null;  
            PreparedStatement stmt =null; 
            ResultSet rs =null;  
            Usuario usuario_=null;  
            
            List<Usuario> usuarios = new ArrayList<>(); 
        try {
            // Import statico si no quieres llamar a la clase  "con = Conexion.getconection"
            //conn=getConnection(); 
            
            conn = this.conexionTrasancional !=null ?this.conexionTrasancional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);  
            rs = stmt.executeQuery();  
            
            while (rs.next()){// va iterando las columnas mientras haya registro
                int idPersona  = rs.getInt("id_usuario"); 
                String usuario = rs.getString("usuario"); 
                String pasword = rs.getString("pasword");  
                usuario_= new Usuario (idPersona, usuario, pasword); 
                usuarios.add(usuario_);
            } 
            
        }
        finally {
            // Cerramos los Objetos de conexion 
                try { 
                    close(rs); // result.close
                    close(stmt);
                    
                    if (this.conexionTrasancional ==null){
                       close(conn);
                    }
                    
                } catch (SQLException ex) { 
                    ex.printStackTrace(System.out);
                }
        }
           
        return usuarios;
    }

//    
    public int insertar (Usuario usuario) throws SQLException{
        Connection conn=null; 
        PreparedStatement stmnt=null;  
        int resultado = 0; 
        try { 
            //conn = getConnection(); 
            conn= this.conexionTrasancional !=null ? this.conexionTrasancional : Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_INSERT); 
            stmnt.setString(1,usuario.getUsuario()); 
            stmnt.setString(2, usuario.getPasword());
            resultado = stmnt.executeUpdate();// guardar Cambios  
            
        }
        finally {
            try {
                close(stmnt); 
                
                if (this.conexionTrasancional==null){
                    close(conn);
                }
                
            } catch (SQLException ex) { 
                ex.printStackTrace(System.out);
            }
        }
        return resultado;
    } 
    
     public int actualizar (Usuario usuario) throws SQLException{
        Connection conn=null; 
        PreparedStatement stmnt=null;  
        int resultado = 0; 
        try { 
            //conn = getConnection(); 
            conn = this.conexionTrasancional !=null ? this.conexionTrasancional : Conexion.getConnection(); 
            stmnt = conn.prepareStatement(SQL_UPDATE); 
            stmnt.setString(1,usuario.getUsuario()); 
            stmnt.setString(2, usuario.getPasword()); 
            stmnt.setInt(3, usuario.getIdUsuario());
            resultado = stmnt.executeUpdate();// guardar Cambios  
            
        }
        finally {
            try {
                close(stmnt); 
                if (this.conexionTrasancional==null){
                    close(conn);
                }
                
            } catch (SQLException ex) { 
                ex.printStackTrace(System.out);
            }
        }
        return resultado;
    } 
     
    public int eliminar (Usuario usuario) throws SQLException{
        Connection conn=null; 
        PreparedStatement stmnt=null;  
        int resultado = 0; 
        try { 
            //conn = getConnection();
            conn= this.conexionTrasancional != null ? this.conexionTrasancional : Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_DELETE); 
            stmnt.setInt(1, usuario.getIdUsuario());
            resultado = stmnt.executeUpdate();// guardar Cambios  
            
        }
        finally {
            try {
                close(stmnt);
                close(conn);
            } catch (SQLException ex) { 
                ex.printStackTrace(System.out);
            }
        }
        return resultado;
    } 
}
