/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pooequipoalfat4;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author jesus_ignacio_159
 */
public class Ventana5 extends JFrame implements ActionListener {

    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;
    private JTextField txtConfiguracion;

    //Constructor
    public Ventana5() {
        super("Configuración");
        setSize(400, 150);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblConfiguracion = new JLabel("Porcentaje de Acomulado: ");
        
//        JLabel lblImagen = new JLabel("");
//        Image image = new ImageIcon(this.getClass().getResource("Drawing-layerExport.jpeg")).getImage();
//        lblImagen.setIcon(new ImageIcon(image));
//        lblImagen.setBounds(0, 0, this.getWidth(), this.getHeight());
//        this.getContentPane().add(lblImagen);

        //Paso 3. Vamos a crear un campo de texto
        //JTextField que limita el que solo se puedan escribir números
        txtConfiguracion = new JTextField();
        txtConfiguracion.setDocument(new LimiteDeTexto(2));
        txtConfiguracion.addKeyListener(SoporteValidaciones.KA_SoloNumeros(this));
        
        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblConfiguracion.setBounds(10, 30, 160, 15); //x,y, ancho y alto
        txtConfiguracion.setBounds(190, 25, 180, 25);
      
        btnAceptar.setBounds(100, 80, 90, 25);
        btnCancelar.setBounds(200, 80, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblConfiguracion);
        pnlContenido.add(txtConfiguracion);
        
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);
        
//        pnlContenido.add(lblImagen);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        txtConfiguracion.addActionListener(this);
        
        //Paso 10. Hacemos visible la ventana
        setVisible(true);
    }

    private void salir() {
        dispose();
    }
    
//    public void close() {
//        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
//    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == btnAceptar) || (e.getSource() == txtConfiguracion)) {
         String configuracion = txtConfiguracion.getText();

            if (MonederoElectronico.agregarConfiguracion(configuracion)){
                JOptionPane.showMessageDialog(null, "Se ha agregado con éxito", "", -1);
                salir();
            } else{
                JOptionPane.showMessageDialog(null, "No se ha podido agregar", "Advertencia", 0);                
            }    
          salir();
        } else {
            salir();
        }
    }
}