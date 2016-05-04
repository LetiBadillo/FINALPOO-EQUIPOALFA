/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 * Clase que almacenará todas las transferencias o transacciones de la tienda
 * @author osilru
 * @author LetiBadillo
 */


public class Bitacora {
    private static int a = 0;
    private static boolean o=false;
    public static boolean registrarCompra(String IDTarjeta, String ticket, String cantidad) throws SQLException {
        
                       //si arroja un resultado
    try {
        mysqlConnection c = new mysqlConnection(); //crea una nueva conexión a la bd
        c.conexion();                              //establece conexión
        Connection co= c.conexion();
        String query = "insert into registrodecompra (numtarjetaR, ticket, cantidad) values (?, ?, ?)"; //inserta los campos puestos por el cliente en la tabla de registros de compra
    
      PreparedStatement preparedStmt = co.prepareStatement(query);      //statement que ejecuta el query
      preparedStmt.setString (1, IDTarjeta);            //guarda ID tarjeta en la columna 1
      preparedStmt.setString (2, ticket);               //guarda el ticket en la columna 2
      preparedStmt.setString (3,cantidad);              //guarda la cantidad en la columna 3
      int r= preparedStmt.executeUpdate();
      if (r== 1){                                       //si el query se ejecuta entonces devuelve true
          o=true;
          a = 1;
          
      }else {
          o=false;
      }
      preparedStmt.close();
    } 
      catch (SQLException e){
          JOptionPane.showMessageDialog(null, "El ticket ya fue registrado.");
      }
      
        catch(Exception r){
            JOptionPane.showMessageDialog(null, "Error"+r);
        }
        if(a == 1){            //si sí se hizo el insert, añade el porcentaje al saldo de la tarjeta
          double incremento = MonederoElectronico.getIncremento()/100*Double.parseDouble(cantidad);
          try{
        mysqlConnection con = new mysqlConnection();
        con.conexion();
        Connection cc= con.conexion();
     
      String query = "UPDATE tarjetas SET saldo= saldo+" +incremento+" WHERE idTarjeta="+IDTarjeta;
      PreparedStatement stTarjetas = cc.prepareStatement(query);
      int b= stTarjetas.executeUpdate();
       if (b!=0){
           o= true;
       }
          stTarjetas.close();
          
          }
      catch (SQLException e){
          JOptionPane.showMessageDialog(null, "Error"+e);
      }
      
        catch(Exception q){
            JOptionPane.showMessageDialog(null, "Error"+q);
        }
      }

 
            return o;
        }
    }
    
 
    

