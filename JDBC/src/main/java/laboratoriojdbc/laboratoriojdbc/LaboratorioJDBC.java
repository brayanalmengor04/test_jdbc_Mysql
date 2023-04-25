package laboratoriojdbc.laboratoriojdbc;
import dato.*;
import domain.*;
import java.sql.*;
import java.util.*;
import java.util.*;

public class LaboratorioJDBC {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            UsuarioDAO JDBCUSUARIO = new UsuarioDAO(conexion); // Obtenemos conexion con el contructor 
            menu();

            conexion.commit();
            System.out.println("Se ha realizado el commit");
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }
    private static void menu() throws SQLException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        int opcion = 0;
        while (opcion != 5) { 
            System.out.println("---------------------------------------------------------------");
            System.out.println("\tMENU TRANSACIONES\n"
                    + "\n   1. Agregar Usuario SQL "
                    + "\n   2. Actualizar Usuario SQL "
                    + "\n   3. Eliminar Usuario SQL "
                    + "\n   4. Listar Usuarios SQL "
                    + "\n   5. Salir ");
            System.out.println("Proporcione la opcion : ");
            opcion = Integer.parseInt(scan.nextLine());
            System.out.println("-----------------------------------------------------------------");

            switch (opcion) {

                case 1:
                    System.out.println("TRANSACION AGREGAR USUARIO");
                    System.out.println("");

                    System.out.println("Proporcione el Username : ");
                    String Username = scan.nextLine();

                    System.out.println("Proporcione el PassWord : ");
                    String PassWord = scan.nextLine();

                    if (Username != null & PassWord != null) {
                        Usuario personaNueva = new Usuario();
                        personaNueva.setUsuario(Username);
                        personaNueva.setPasword(PassWord);
                        usuariodao.insertar(personaNueva);
                    } else {
                        System.out.println("No se ha Agregado el Usuario");
                    }
                    System.out.println("Transacion Terminada");
                    break;
                case 2:

                    System.out.println("ACTUALIZAR: ");
                    System.out.println("Proporcione el ID Usuario : ");
                    var id = Integer.parseInt(scan.nextLine());

                    System.out.println("Proporcione el Username :");
                    var Usernames = scan.nextLine();

                    System.out.println("Proporcione el Password :");
                    var Passw = scan.nextLine();

                    Usuario personaModificar = new Usuario(id, Usernames, Passw);
                    usuariodao.actualizar(personaModificar); 
                    System.out.println("Se ha añadido usuario SQL");
                    break;
          
                 case 3: 
                     System.out.println("Proporcion el usuario que desea eliminar : ");
                     var usuarioE = Integer.parseInt(scan.nextLine()); 
                     Usuario eliminar = new Usuario(usuarioE);
                     usuariodao.eliminar(eliminar); 
                     System.out.println("Se ha eliminado usuario SQL");
                     break; 
                 case 4:   
                     List<Usuario> usuarios = usuariodao.selecionar(); 
                     System.out.println("\tLISTA DE USUARIOS ");
                     System.out.println("\n");
                     for (Usuario element : usuarios){
                         System.out.println(element);
                     }
                     System.out.println("------------------------------------------------------------");
                     break; 
                 case 5:  
                     break;
                 default :  
                     System.out.println("Error opcion Incorrecta");
                     
             }
        }
        
    }
    
    

  

}
