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
public class Ventana1 extends JFrame implements ActionListener {

    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;
    private JTextField txtNombre, txtEmail, txtTelefono, txtIDTarjeta;

    //Constructor
    public Ventana1() {
        super("Alta de Cliente");
        setSize(400, 230);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblNombre = new JLabel("Nombre: ");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");

        //Paso 3. Vamos a crear un campo de texto
        // JTextField que limita que sólo se puedan escribir letras y espacio
        txtNombre = new JTextField();
        txtNombre.addKeyListener(SoporteValidaciones.KA_SoloLetrasAcentoEspacio(this));
        // JTextField que limita a los caracteres válidos de un email (limitando a un solo @)
        txtEmail = new JTextField();
        txtEmail.addKeyListener(SoporteValidaciones.KA_CorreoElectronico(this, txtEmail));
        //JTextField que limita el que solo se puedan escribir 10 números
        txtTelefono = new JTextField();
        txtTelefono.setDocument(new LimiteDeTexto(10));
        txtTelefono.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));
        //JTextField que limita el que solo se puedan escribir 20 números
        txtIDTarjeta = new JTextField();
        txtIDTarjeta.setDocument(new LimiteDeTexto(20));
        txtIDTarjeta.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));

        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblNombre.setBounds(10, 30, 60, 10); //x,y, ancho y alto
        txtNombre.setBounds(80, 25, 290, 25);
        lblEmail.setBounds(10, 60, 60, 10);
        txtEmail.setBounds(80, 55, 290, 25);
        lblTelefono.setBounds(10, 90, 60, 10);
        txtTelefono.setBounds(80, 85, 290, 25);
        lblIDTarjeta.setBounds(10, 120, 80, 15);
        txtIDTarjeta.setBounds(80, 115, 290, 25);
        btnAceptar.setBounds(100, 150, 90, 25);
        btnCancelar.setBounds(200, 150, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblNombre);
        pnlContenido.add(txtNombre);
        pnlContenido.add(lblEmail);
        pnlContenido.add(txtEmail);
        pnlContenido.add(lblTelefono);
        pnlContenido.add(txtTelefono);
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        // ActionListener para Cambiar de Focus al dar Enter
        txtNombre.addActionListener(SoporteValidaciones.AL_CambiarFocusACampoTextoA(txtEmail));
        txtEmail.addActionListener(SoporteValidaciones.AL_CambiarFocusACampoTextoA(txtTelefono));
        txtTelefono.addActionListener(SoporteValidaciones.AL_CambiarFocusACampoTextoA(txtIDTarjeta));
        txtIDTarjeta.addActionListener(this);

        //Paso 10. Hacemos visible la ventana
        setVisible(true);
    }

    private void salir() {
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == btnAceptar) || (e.getSource() == txtIDTarjeta)) {

            String nombreDeCliente = txtNombre.getText();
            String email = txtEmail.getText();
            String telefono = txtTelefono.getText();
            String IDTarjeta = txtIDTarjeta.getText();

            if (nombreDeCliente.equals("")) {
                JOptionPane.showMessageDialog(null, "No se han ingresado todos los campos", "Advertencia", 0);
                txtNombre.requestFocusInWindow();
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "No se han ingresado todos los campos", "Advertencia", 0);
                txtEmail.requestFocusInWindow();
            } else if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null, "Ingrese un email válido.", "Advertencia", 0);
                txtEmail.requestFocusInWindow();
            }else if (telefono.equals("")) {
                JOptionPane.showMessageDialog(null, "No se han ingresado todos los campos", "Advertencia", 0);
                txtTelefono.requestFocusInWindow();
            } else if (IDTarjeta.equals("")) {
                JOptionPane.showMessageDialog(null, "No se han ingresado todos los campos", "Advertencia", 0);
                txtIDTarjeta.requestFocusInWindow();
            } else {
                try {

                    if (Cliente.agregarCliente(nombreDeCliente, email, telefono, IDTarjeta)) {
                        JOptionPane.showMessageDialog(null, "Se ha agregado con éxito", "", -1);
                        salir();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido agregar", "Advertencia", 0);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            salir();
        }
    }
}
