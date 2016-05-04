/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author osilru
 */
public class Ventana2 extends JFrame implements ActionListener {
    
    
    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;    
    private JTextField txtIDTarjeta, txtTicket,txtCantidad;

    //Constructor
    public Ventana2() {
        super("Registro de Compras");
        setSize(400, 200);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");
        JLabel lblTicket = new JLabel("No. Ticket: ");
        JLabel lblCantidad = new JLabel("Cantidad:");
        
        //Paso 3. Vamos a crear un campo de texto
        //JTextField que limita el que solo se puedan escribir 20 números
        txtIDTarjeta = new JTextField();
        txtIDTarjeta.setDocument(new LimiteDeTexto(20));
        txtIDTarjeta.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));
        //JTextField que limita el que solo se puedan escribir 13 números
        txtTicket = new JTextField();
        txtTicket.setDocument(new LimiteDeTexto(13));
        txtTicket.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));
        
        //JTextField que solo permite numeros, punto y coma
        txtCantidad = new JTextField();
        txtCantidad.addKeyListener(SoporteValidaciones.KA_NumerosDouble(this, txtCantidad));
        
        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblIDTarjeta.setBounds(10, 30, 80, 15); //x,y, ancho y alto
        txtIDTarjeta.setBounds(80, 25, 290, 25);
        lblTicket.setBounds(10, 60, 80, 10);
        txtTicket.setBounds(80, 55, 290, 25);
        lblCantidad.setBounds(10, 90, 60, 10);
        txtCantidad.setBounds(80, 85, 290, 25);
        btnAceptar.setBounds(100, 120, 90, 25);
        btnCancelar.setBounds(200, 120, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(lblTicket);
        pnlContenido.add(txtTicket);
        pnlContenido.add(lblCantidad);
        pnlContenido.add(txtCantidad);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        txtIDTarjeta.addActionListener(SoporteValidaciones.AL_CambiarFocusACampoTextoA(txtTicket));
        txtTicket.addActionListener(SoporteValidaciones.AL_CambiarFocusACampoTextoA(txtCantidad));
        txtCantidad.addActionListener(this);

        //Paso 10. Hacemos visible la ventana
        setVisible(true);
    }

    private void salir() {
        dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == btnAceptar) || (e.getSource() == txtCantidad)) {
            try {
                String IDTarjeta = txtIDTarjeta.getText();
                String ticket = txtTicket.getText();
                String cantidad = txtCantidad.getText();
                
                if (MonederoElectronico.checarTarjeta(IDTarjeta)==false){
                      JOptionPane.showMessageDialog(null, "Verifique el número de tarjeta.", "", -1);
                }else{
                // Similar a Ventana 1, en el if debería ir el argumento que checa lo que
                        if (Bitacora.registrarCompra(IDTarjeta, ticket, cantidad)){
                        JOptionPane.showMessageDialog(null, "Se ha registrado con éxito", "", -1);
                        salir();
                         } else{                
                        JOptionPane.showMessageDialog(null, "No se ha podido registrar", "Advertencia", 0);
                        }
            }}catch (SQLException ex) {
                Logger.getLogger(Ventana2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            salir();
        }
    }
}
    