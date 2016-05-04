/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class mysqlConnection {
      
public Connection con = null;
  
    public Connection conexion() {
     
try { 
               
    Class.forName("com.mysql.jdbc.Driver"); //Carga la clase donde se va a agregar el driver jdbc
    con = DriverManager.getConnection("jdbc:mysql://localhost/monedero", "root", ""); //hace conexión con el servidor
    
     
            } 
        
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        catch(Exception r){
            JOptionPane.showMessageDialog(null, "Error"+r);
        }
    return con;
    } 

    

    
}

    

