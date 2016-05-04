/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author  osilru
 */
public class Ventana4 extends JFrame implements ActionListener {
    
    
    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;    
    private JTextField txtIDTarjeta,txtNombreCliente,txtSaldo;

    //Constructor
    public Ventana4() {
        super("Consulta de Saldos");
        setSize(400, 200);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");
        JLabel lblNombreCliente = new JLabel("Cliente: ");
        JLabel lblSaldo = new JLabel("Saldo:");
        
        //Paso 3. Vamos a crear un campo de texto
        // JTextfield que solo permite escribir numeros
        txtIDTarjeta = new JTextField();
        txtIDTarjeta.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));
        // Como los siguientes campos de texto sólo son de salida, no se permiten editar
        txtNombreCliente = new JTextField();
        txtSaldo = new JTextField();
        txtNombreCliente.setEditable(false);
        txtSaldo.setEditable(false);
        
        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Salir");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblIDTarjeta.setBounds(10, 30, 80, 15); //x,y, ancho y alto
        txtIDTarjeta.setBounds(80, 25, 290, 25);
        lblNombreCliente.setBounds(10, 60, 80, 10);
        txtNombreCliente.setBounds(80, 55, 290, 25);
        lblSaldo.setBounds(10, 90, 60, 10);
        txtSaldo.setBounds(80, 85, 290, 25);
        btnAceptar.setBounds(100, 120, 90, 25);
        btnCancelar.setBounds(200, 120, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(lblNombreCliente);
        pnlContenido.add(txtNombreCliente);
        pnlContenido.add(lblSaldo);
        pnlContenido.add(txtSaldo);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
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
            String IDTarjeta = txtIDTarjeta.getText();
            String[] datosTarjeta;
            datosTarjeta = MonederoElectronico.informacionTarjeta(IDTarjeta);
            // Método que devuelve un arreglo de Strings con los datos que encontró en la base de datos
            if(MonederoElectronico.checarTarjeta(IDTarjeta)==false){
                JOptionPane.showMessageDialog(null, "No se ha encontrado la Tarjeta", "Advertencia", 0);
            }  else {         
            if (datosTarjeta == null) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado la Tarjeta", "Advertencia", 0);
            } else {
                txtNombreCliente.setText(datosTarjeta[0]);
                txtSaldo.setText(datosTarjeta[1]);
            }}
        } else {
            salir();
        }
    }}


