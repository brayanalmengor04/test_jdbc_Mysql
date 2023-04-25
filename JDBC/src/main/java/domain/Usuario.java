
package domain;


public class Usuario {
    private int idUsuario ; 
    private String usuario ;  
    private String pasword ;  

    public Usuario() {
    }
    // Eliminar
    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    // Insertar Datos 
    public Usuario(String usuario, String pasword) {
        this.usuario = usuario;
        this.pasword = pasword;
    }
    // Modificar y Actualizar 
    public Usuario(int idUsuario, String usuario, String pasword) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.pasword = pasword;
    } 

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idPersona) {
        this.idUsuario = idPersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idPersona=" + idUsuario + ", usuario=" + usuario + ", pasword=" + pasword + '}';
    }
    
    
    
    
    
    
}
