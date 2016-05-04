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
 * @author A00509871, jesus_ignacio_159
 */
public class SoporteValidaciones {
    
    protected static KeyAdapter KA_SoloNumeros(JFrame ventana){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)) {
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                }
            }
        };
    }
    
    protected static KeyAdapter KA_NumerosDouble(JFrame ventana, JTextField campoTexto){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((((c < '0') || (c > '9')) && ((c != '.') && (c != ','))) 
                        && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)) {
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                } else if (campoTexto.getText()!= null && (((c == '.')  || (c == ',')) &&(campoTexto.getText().contains(".") || campoTexto.getText().contains(",")))){
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                }
            }
        };
    }
    
    protected static KeyAdapter KA_CorreoElectronico(JFrame ventana, JTextField campoTexto){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && ((c < '@') || (c > 'Z')) 
                        && ((c < 'a') || (c > 'z')) && (c != '.') && (c != '-') 
                        && (c != '_') && (c != KeyEvent.VK_BACK_SPACE)  && (c != KeyEvent.VK_DELETE)) {
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                } else if (campoTexto.getText()!= null && (c == '@') && (campoTexto.getText().contains("@"))){
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                }
            }
        };
    }
    
//    protected static KeyAdapter KA_SoloLetras(JFrame ventana){
//        return new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z')) && (c != KeyEvent.VK_BACK_SPACE)) {
//                    e.consume();  // ignore event
//                    ventana.getToolkit().beep(); //sonido
//                }
//            }
//        };
//    }

        protected static KeyAdapter KA_SoloLetras(JFrame ventana){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z')) 
                        && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)) {
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                }
            }
        };
    }
    
//        protected static KeyAdapter KA_SoloLetras(JFrame ventana){
//        return new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z')) && (c != KeyEvent.VK_BACK_SPACE)) {
//                    e.consume();  // ignore event
//                    ventana.getToolkit().beep(); //sonido
//                }
//            }
//        };
//    }
    
    protected static KeyAdapter KA_SoloLetrasAcentoEspacio(JFrame ventana){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isLetter(c)) && (c != KeyEvent.VK_BACK_SPACE) 
                         && (c != KeyEvent.VK_DELETE) && (c != KeyEvent.VK_SPACE)) {
                    e.consume();  // ignore event
                    ventana.getToolkit().beep(); //sonido
                }
            }
        };
    }
    
    protected static ActionListener AL_CambiarFocusACampoTextoA(JTextField campoTextoA) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTextoA.requestFocusInWindow();
            }
        };
    }
    
}
