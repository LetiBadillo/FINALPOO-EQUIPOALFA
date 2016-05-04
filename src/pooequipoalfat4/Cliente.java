/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

 
public class Cliente {
    
    //Como usaremos una base de datos 
    //Atributos
//    private Long id_cliente;
//    private String Nombre;
//    private String email;    
//    private String MonederoElectronico;
//    private Long idTarjeta;

    //Método que hace la conexión con el GUI. Debe devolver si se pudo agregar o no el cliente. 
    private static boolean e= false;
    private static String a;
    public static boolean agregarCliente(String nombreDeCliente, String email, String telefono, String IDTarjeta) throws SQLException {
      
        try{
        mysqlConnection con = new mysqlConnection();
        con.conexion();
        Connection cc= con.conexion();
        String query = "insert into clientes (nombre, email, telefono, numtarjetaC, idTarjetaC) values (?, ?, ?, ?, ?)";
      PreparedStatement preparedStmt = cc.prepareStatement(query);
      preparedStmt.setString (1, nombreDeCliente);
      preparedStmt.setString (2, email);
      preparedStmt.setString   (3, telefono);
      preparedStmt.setString(4, IDTarjeta);
      preparedStmt.setString(5, IDTarjeta);
      int a= preparedStmt.executeUpdate();
      
      String query1 = "insert into tarjetas (idTarjeta, NumTarjeta, saldo) values (?, ?, ?)";
      PreparedStatement stTarjetas = cc.prepareStatement(query1);
      stTarjetas.setString (1, IDTarjeta);
      stTarjetas.setString (2, IDTarjeta);
      stTarjetas.setDouble   (3, 0);
      int b= stTarjetas.executeUpdate();
       if (a != 0 && b!=0){
           e= true;
       }
      preparedStmt.close();
      
        }
      catch (SQLException e){
          JOptionPane.showMessageDialog(null, "Error"+e);
      }
      
        catch(Exception r){
            JOptionPane.showMessageDialog(null, "Error"+r);
        }
      
      
      
       return e;
    }

    static String obtenerNombreClienteConMonedero(String IDTarjeta) {
        try {
            mysqlConnection s = new mysqlConnection();
        s.conexion();
        Connection cc= s.conexion();
        String select = "select nombre from clientes where idTarjetaC="+IDTarjeta;
        ResultSet rs;
        Statement stmt= cc.createStatement();
        rs = stmt.executeQuery(select);
        while ( rs.next() ) {
                a = rs.getString("nombre");
                
               
            }
                 rs.close();
                stmt.close();
            }
                
        catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch (Exception e){
                    
                    } 
        return a;
    
    }
    
    
}
