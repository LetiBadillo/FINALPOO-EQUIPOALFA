/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Esta clase representará la tarjeta, el monedero electronico. Puede crearse un
 * monedero electrónico con un cliente asociado o sin cliente.
 *
 * @author osilru
 */
public class MonederoElectronico {

    //Atributos
//    private Long IDTarjeta;
//    private Long IDMonedero;
//    private double saldo;
    private static double sa, in;
    private static int x=0;
    private static boolean b = false;
    private static boolean e = false;
    private static boolean w = false;
    

    public static boolean pago(String IDTarjeta, String cantidad) {
        if (cantidad.equalsIgnoreCase(".") || cantidad.equalsIgnoreCase(",") || cantidad == null) {            
            return false;
        } else {            
            
            double monto = Double.parseDouble(cantidad); //convierte el String a double para poder realizar la operación.
            if (monto > 0) {
                return modificarSaldoDeTablaMonedero(IDTarjeta, obtenerSaldoDeTablaMonedero(IDTarjeta) + monto); //regresa true si se modificó el saldo en el monedero.
            } else {
                return false;
            }
        }
    }

    
    protected static boolean cargoATarjeta(String IDTarjeta, double cargo) {
        // Debe sacar el saldo que tenga tal ID        
        double monto = obtenerSaldoDeTablaMonedero(IDTarjeta);        
        // Debe comparar el saldo que se tiene contra el que se pide.
        // Si se tiene suficiente saldo, se debe modificar la base de datos. En caso de no
        // contar con fondos suficientes, el método debe regresar False        
        if (cargo <= monto) {
            return modificarSaldoDeTablaMonedero(IDTarjeta, obtenerSaldoDeTablaMonedero(IDTarjeta)-cargo);
        } else {
            return false;
        }
    }
    
    // Devolverá un Arreglo de Strings doble. Regresará Null en caso de no haber encontrado dicha tarjeta.
    public static String[] informacionTarjeta(String IDTarjeta) {        
        double saldo = obtenerSaldoDeTablaMonedero(IDTarjeta);
        if (saldo<0){
            return null;
        } else{
            String[] informacion = new String[2];
            String nombreCliente = Cliente.obtenerNombreClienteConMonedero(IDTarjeta);
            if(nombreCliente == null){
                nombreCliente = "=TARJETA SIN CLIENTE ASIGNADO=";
            }            
            informacion[0] = nombreCliente;
            informacion[1] = Double.toString(saldo);
            
            return informacion;
        }
    }
    
    
    // Obtiene el Saldo que hay en el monedero. En caso de no existir en la base de datos, regresa un número negativo
    private static double obtenerSaldoDeTablaMonedero(String IDTarjeta){        
        if(checarTarjeta(IDTarjeta))
        {try {
            mysqlConnection s = new mysqlConnection(); //Se abre la conexión a la base de datos
        s.conexion();
        Connection cc= s.conexion();
        String select = "select saldo from tarjetas, clientes where idTarjeta ="+IDTarjeta; //query para seleccionar el saldo de la tarjeta
        ResultSet rs; //guarda el resultado del select
        Statement stmt= cc.createStatement();  //crea el statement parra ejecutar el query
        rs = stmt.executeQuery(select); //ejecuta el query
        while ( rs.next() ) {           //mientras haya resultados en el resultset va a imprimir los valores
                sa = rs.getDouble("tarjetas.saldo"); //almacena en la variable el saldo
                
            }
                 rs.close(); //cierra la conexión del resultset.
                stmt.close(); //cierra la conexión del statement.
                //la base de datos queda cerrada.
            }
                //Excepciones del sql
        catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch (Exception e){
                    
                    }}else{
            
        }
    
    
        return sa; //regresa el saldo de la tarjeta
    }
    
    // Modifica el saldo el saldo del Monedero en las tablas
    private static boolean modificarSaldoDeTablaMonedero(String IDTarjeta, double monto) {
    if(checarTarjeta(IDTarjeta)) {    //Revisa que el número de tarjeta exista   
        try {
            //Se establece la conexión
                 mysqlConnection s = new mysqlConnection();
                    s.conexion();
                 Connection cc = s.conexion();
                        
                         String query = "update tarjetas set saldo ="+monto+" where NumTarjeta ="+IDTarjeta; //query que actualiza el saldo de la tarjeta IDTarjeta con el double monto
                    PreparedStatement stmt = cc.prepareStatement(query); //Mete el query en el statement
                    int q = stmt.executeUpdate(); //Ejecuta el query y lo guarda en q el número de filas afectadas en la base de datos
                    if (q !=0) //Si el número de filas afectadas es diferente de 0, b = true
                    {
                   
                    b = true;
                    }
               stmt.close(); //Cierra la conexión a la base de datos
               
            
        } 
        //Excepciones del sql
        catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
             
            
        } } else {
    b=false; //b es falso si la tarjeta no existe
    }return b;
    }
     

    
    static boolean agregarConfiguracion(String configuracion) {  //Agrega el incremento a la base de datoa
       try{
        mysqlConnection con = new mysqlConnection(); //Abre la conexión a la base de datos
        con.conexion();
        Connection cc= con.conexion();
     
      String query = "UPDATE incremento SET incremento= (?) WHERE idi = '1'"; //query que actualiza la columna de incremento en la tabla de la bd
      PreparedStatement stTarjetas = cc.prepareStatement(query); //manda a la base de datos el query
      stTarjetas.setString (1, configuracion); //actualiza la columna incremento en la bd
      int b= stTarjetas.executeUpdate(); //ejecuta el query y guarda el resultado del número de filas afectadas en la variable b
       if (b!=0){ //Si las filas afectadas no son cero, e = true
           e= true;
       }
       stTarjetas.close(); //Cierra la conexión a la base de datos
      
        }
    //Excepciones del sql
       catch (SQLException e){
          JOptionPane.showMessageDialog(null, "Error"+e);
      }
      
        catch(Exception r){
            JOptionPane.showMessageDialog(null, "Error"+r);
        }
      
      
       return e; //regresa true si se ejecutó bien la actualización o false si hubo algún problema
    }
    
    public static double getIncremento(){    //se obtiene el valor de incremento    
        try {
            mysqlConnection s = new mysqlConnection(); //Establece conexión a la base de datos
        s.conexion();           
        Connection cc= s.conexion();
        String select = "select incremento from incremento";    //selecciona el valor actualizado del incremento
         
        Statement stmt= cc.createStatement(); //statement que manda el select a la bd
        ResultSet rs = stmt.executeQuery(select); //ejecuta el query y lo guarda en el result set
        while ( rs.next() ) { //mientras siga habiendo valores en el result set, se va a agregar a la variable in
                in = rs.getDouble("incremento"); //guarda el incremento en la variable in
                
               
            }
                 rs.close();       //Cierra la conexión a la base de datos
                stmt.close();
            }
             //Excepciones de la bd   
        catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch (Exception e){
                    
                    }
    
    
        return in;
    }
    
     static boolean checarTarjeta(String IDTarjeta) {
        try {
            mysqlConnection s = new mysqlConnection();
        s.conexion();
        Connection cc= s.conexion();
        String select = "select numTarjetaC from clientes where numTarjetaC="+IDTarjeta;
        ResultSet rs;
        Statement stmt= cc.createStatement();
        rs = stmt.executeQuery(select);
        x = 0;
        while ( rs.next() ) {
                ++x;
                }
        if(x==1){
               w = true;  
            } 
        stmt.close();
        
        }
                
        catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error"+x);
        }
            catch (Exception e){
                    
                    } 
        return w;
    
    }
    
    }
    



