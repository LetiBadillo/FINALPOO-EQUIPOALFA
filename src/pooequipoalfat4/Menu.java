/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooequipoalfat4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author osilru
 */
public class Menu extends JFrame implements ActionListener {
    JMenuItem cmdAltaCliente, cmdRegistroCompra, cmdPagos, cmdConsultaSaldo, cmdConfiguracion;
    JMenu menuClientes, menuMovimientos,menuConfiguracion;
    JMenuBar braMenu;
    
    //Constructor
    public Menu(){
        //Establecemos el título de la ventana.
        super("Bienvenido");
        //Establecemos la ubicación en la pantalla y las dimenciones de la ventana
//        MonederoElectronico monederoElectronico = new MonederoElectronico(1);
//        Cliente cliente = new Cliente();
//        Bitacora bitacora = new Bitacora();
//        Transferencia transferencia = new Transferencia();
        
        setBounds(200,200,524,203); //x,y,ancho,alto        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programamos la X para cerrar la ventana               
        setLayout(new FlowLayout());
        setResizable(false);
        
        
        
        //Paso 1. Crear los JMenuItems
        cmdAltaCliente = new JMenuItem("Alta de Cliente");
        cmdRegistroCompra = new JMenuItem("Registro de Compras");
        cmdPagos = new JMenuItem("Pagos");
        cmdConsultaSaldo = new JMenuItem("Consulta de Saldo");
        cmdConfiguracion = new JMenuItem("Porcentaje de Acomulado");
       
        //Paso 2. Creamos los JMenus
        menuClientes = new JMenu("Clientes");
        menuMovimientos = new JMenu("Movimientos");
        menuConfiguracion = new JMenu("Configuración");
        
        //Paso 3. Creamos la Barra JMenuBar
        braMenu = new JMenuBar();
        
        // Además, agregamos la imagen
        JLabel lblImagen = new JLabel("");
        Image image = new ImageIcon(this.getClass().getResource("Drawing-layerExport.jpeg")).getImage();
        lblImagen.setIcon(new ImageIcon(image));
        lblImagen.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(lblImagen);
        
        //Paso 4. Agregar los items a los menus
        menuClientes.add(cmdAltaCliente);
        menuMovimientos.add(cmdRegistroCompra);
        menuMovimientos.add(cmdPagos);
        menuMovimientos.add(cmdConsultaSaldo);
        menuConfiguracion.add(cmdConfiguracion);
        
        //Paso 5. Agregar los menus a la barra
        braMenu.add(menuClientes);
        braMenu.add(menuMovimientos);
        braMenu.add(menuConfiguracion);
        setJMenuBar(braMenu); 
        
        //Se agrega la imagen al menu
        JPanel pnlContenido = new JPanel(null);         
        pnlContenido.add(lblImagen);        
        setContentPane(pnlContenido);
        
        setVisible(true);
        
        //Paso 6. Que los comandos ESCUCHEN
        cmdAltaCliente.addActionListener(this);
        cmdRegistroCompra.addActionListener(this);
        cmdPagos.addActionListener(this);
        cmdConsultaSaldo.addActionListener(this);
        cmdConfiguracion.addActionListener(this);
    }
    
//    private void salir(){
//        System.exit(0);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdAltaCliente){
            Ventana1 ventana1 = new Ventana1();   
        } else if (e.getSource() == cmdRegistroCompra){
            Ventana2 ventana2 = new Ventana2();
        } else if (e.getSource() == cmdPagos){
            Ventana3 ventana3 = new Ventana3();
        } else if (e.getSource() == cmdConsultaSaldo){
            Ventana4 ventana4 = new Ventana4();
        } else if (e.getSource() == cmdConfiguracion){
            Ventana5 ventana5 = new Ventana5();
        }
    }
}
